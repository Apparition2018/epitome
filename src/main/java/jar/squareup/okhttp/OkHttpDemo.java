package jar.squareup.okhttp;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import l.demo.Demo;
import lombok.NonNull;
import okhttp3.*;
import okio.BufferedSink;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <a href="https://square.github.io/okhttp/recipes/">OkHttp</a>
 *
 * @author ljh
 * @since 2021/8/3 9:10
 */
public class OkHttpDemo extends Demo {
    private static final String MARKDOWN_RAW_URL = "https://api.github.com/markdown/raw";
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
    private OkHttpClient client = new OkHttpClient();

    /**
     * <a href="https://square.github.io/okhttp/recipes/#synchronous-get-kt-java">Synchronous Get</a>
     */
    @Test
    public void testSynchronousGet() throws IOException {
        Request request = new Request.Builder().url(getPublicObjUrl("helloworld.txt")).build();
        executePrintResult(request);
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#asynchronous-get-kt-java">Asynchronous Get</a>
     */
    @Test
    public void testAsynchronousGet() {
        Request request = new Request.Builder().url(getPublicObjUrl("helloworld.txt")).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    p(Objects.requireNonNull(responseBody).string());
                }
            }
        });
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#accessing-headers-kt-java">Accessing Headers</a>
     */
    @Test
    public void testAccessingHeaders() throws IOException {
        Request request = new Request.Builder().url("https://api.github.com/repos/square/okhttp/issues")
                .header(HttpHeaders.USER_AGENT, "OKHttp Headers.java")
                .addHeader(HttpHeaders.ACCEPT, "application/json; q=0.5")
                .addHeader(HttpHeaders.ACCEPT, "application/vnd.github.vs+json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.printf("%s: %s%n", HttpHeaders.SERVER, response.header(HttpHeaders.SERVER));
            System.out.printf("%s: %s%n", HttpHeaders.DATE, response.header(HttpHeaders.DATE));
            System.out.printf("%s: %s%n", HttpHeaders.VARY, response.header(HttpHeaders.VARY));
        }
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#posting-a-string-kt-java">Posting a String</a>
     */
    @Test
    public void testPostingString() throws IOException {
        String postBody = """
                Releases
                --------

                 * _1.0_ May 6, 2013
                 * _1.1_ June 15, 2013
                 * _1.2_ August 11, 2013
                """;
        RequestBody requestBody = RequestBody.create(postBody, MEDIA_TYPE_MARKDOWN);
        Request request = new Request.Builder().url(MARKDOWN_RAW_URL).post(requestBody).build();
        executePrintResult(request);
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#post-streaming-kt-java">Post Streaming</a>
     */
    @Test
    public void testPostingStream() throws IOException {
        RequestBody requestBody = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8("Numbers\n");
                bufferedSink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    bufferedSink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " x " + i;
                }
                return Integer.toString(n);
            }
        };
        Request request = new Request.Builder().url(MARKDOWN_RAW_URL).post(requestBody).build();
        executePrintResult(request);
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#posting-a-file-kt-java">Posting a File</a>
     */
    @Test
    public void testPostFile() throws IOException {
        File file = new File(DEMO_FILE_PATH);
        RequestBody requestBody = RequestBody.create(file, MEDIA_TYPE_MARKDOWN);
        Request request = new Request.Builder().url(MARKDOWN_RAW_URL).post(requestBody).build();
        executePrintResult(request);
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#posting-form-parameters-kt-java">Posting form parameters</a>
     */
    @Test
    public void testPostFormParameters() throws IOException {
        RequestBody requestBody = new FormBody.Builder().add("Search", "Jurassic Park").build();
        Request request = new Request.Builder().url("https://en.wikipedia.org/w/index.php").post(requestBody).build();
        executePrintResult(request);
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#posting-a-multipart-request-kt-java">Posting a multipart request</a>
     */
    @Test
    public void testMultipartRequest() throws IOException {
        RequestBody requestBody = RequestBody.create(new File(BIRD_IMG), MEDIA_TYPE_JPG);
        RequestBody requestBody2 = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Bird")
                .addFormDataPart("image", "bird.jpg", requestBody)
                .build();
        Request request = new Request.Builder()
                .header(HttpHeaders.AUTHORIZATION, "Client-ID ...")
                .url("https://api.imgur.com/3/image")
                .post(requestBody2)
                .build();
        executePrintResult(request);
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#parse-a-json-response-with-moshi-kt-java">Parse a JSON Response With Moshi</a>
     */
    @Test
    public void testParseJSONResponseWithMoshi() throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
        Request request = new Request.Builder().url("https://api.github.com/gists/c2a7c39532239ff261be").build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            Gist gist = gistJsonAdapter.fromJson(Objects.requireNonNull(response.body()).source());
            Objects.requireNonNull(gist).files.forEach((k, v) -> p(k + ": " + v.content));
        }
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#response-caching-kt-java">Response Caching</a>
     */
    @Test
    public void testResponseCaching() throws IOException {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(new File(DEMO_PATH), cacheSize);
        client = new OkHttpClient.Builder().cache(cache).build();
        Request request = new Request.Builder().url(getPublicObjUrl("hellowrold.txt")).build();
        String response1Body;
        try (Response response1 = client.newCall(request).execute()) {
            if (!response1.isSuccessful()) throw new IOException("Unexpected code " + response1);
            response1Body = Objects.requireNonNull(response1.body()).string();
            p("Response 1 response: " + response1);
            p("Response 1 cache response: " + response1.cacheResponse());
            p("Response 1 network response: " + response1.networkResponse());
        }
        String response2Body;
        try (Response response2 = client.newCall(request).execute()) {
            if (!response2.isSuccessful()) throw new IOException("Unexpected code " + response2);
            response2Body = Objects.requireNonNull(response2.body()).string();
            p("Response 2 response: " + response2);
            p("Response 2 cache response: " + response2.cacheResponse());
            p("Response 2 network response: " + response2.networkResponse());
        }
        p("Response 2 equals Response 1 ? " + Objects.equals(response1Body, response2Body));
    }

    private static class Gist {
        Map<String, GistFile> files;
    }

    private static class GistFile {
        String content;
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#canceling-a-call-kt-java">Canceling a Call</a>
     */
    @Test
    public void testCancelingCall() {
        Request request = new Request.Builder().url(getDelayUrl(2)).build();
        final long startNanos = System.nanoTime();
        final Call call = client.newCall(request);
        Executors.newScheduledThreadPool(1).schedule(() -> {
            System.out.printf("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
            call.cancel();
            System.out.printf("%.2f Canceled call.%n", (System.nanoTime() - startNanos) / 1e9f);
        }, 1, TimeUnit.SECONDS);
        System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);
        try (Response response = call.execute()) {
            System.out.printf("%.2f Call was expected to fail, but completed: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, response);
        } catch (IOException e) {
            System.out.printf("%.2f Call failed as expected: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, e);
        }
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#timeouts-kt-java">Timeouts</a>
     */
    @Test
    public void testTimeouts() throws IOException {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(getDelayUrl(2)).build();
        try (Response response = client.newCall(request).execute()) {
            p("Response completed: " + response);
        }
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#per-call-configuration-kt-java">Per-call Configuration</a>
     */
    @Test
    public void testPerCallConfiguration() {
        Request request = new Request.Builder().url(getDelayUrl(1)).build();
        OkHttpClient client1 = client.newBuilder()
                .readTimeout(500, TimeUnit.MILLISECONDS).build();
        try (Response response = client1.newCall(request).execute()) {
            System.out.println("Response 1 succeeded: " + response);
        } catch (IOException e) {
            System.out.println("Response 1 failed: " + e);
        }
        OkHttpClient client2 = client.newBuilder()
                .readTimeout(1, TimeUnit.SECONDS).build();
        try (Response response = client2.newCall(request).execute()) {
            System.out.println("Response 2 succeeded: " + response);
        } catch (IOException e) {
            System.out.println("Response 2 failed: " + e);
        }
    }

    /**
     * <a href="https://square.github.io/okhttp/recipes/#handling-authentication-kt-java">Handling authentication</a>
     */
    @Test
    public void testHandlingAuthentication() throws IOException {
        client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(@Nullable Route route, @NonNull Response response) {
                        if (response.request().header(HttpHeaders.AUTHORIZATION) != null) {
                            return null;
                        }
                        p("Authenticating for response: " + response);
                        p("Challenges: " + response.challenges());
                        String credential = Credentials.basic("jesse", "password1");
                        return response.request().newBuilder().header(HttpHeaders.AUTHORIZATION, credential).build();
                    }
                }).build();
        Request request = new Request.Builder().url(getPublicObjUrl("secrets/hellosecret.txt")).build();
        executePrintResult(request);
    }

    private String getDelayUrl(int seconds) {
        return "https://httpbin.org/delay/" + seconds;
    }

    private String getPublicObjUrl(String path) {
        return "https://publicobject.com/" + path;
    }

    private void executePrintResult(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            p(Objects.requireNonNull(response.body()).string());
        }
    }
}
