package jar.apache.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.auth.StandardAuthScheme;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.util.TimeValue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * HttpClientUtils
 * HttpClient Overview：https://hc.apache.org/httpcomponents-client-5.1.x/index.html
 * HttpClient 工具详解：http://www.mydlq.club/article/68/
 * HttpClient 设置 cookies：https://www.cnblogs.com/lixianshengfitting/p/13840123.html
 *
 * @author ljh
 * created on 2020/11/12 21:35
 */
@Slf4j
public abstract class HttpClientUtils {

    private static CloseableHttpClient httpClient;

    private static CookieStore cookieStore;

    private static BasicCredentialsProvider basicCredentialsProvider;

    private static final HttpClientResponseHandler<String> responseHandler;

    // HttpClient 配置
    // https://github.com/apache/httpcomponents-client/blob/5.1.x/httpclient5/src/test/java/org/apache/hc/client5/http/examples/ClientConfiguration.java
    static {
        // 连接 Socket 工厂的注册表
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                .build();

        // 连接管理器
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry, PoolConcurrencyPolicy.STRICT, PoolReusePolicy.LIFO, TimeValue.ofMinutes(5));
        connManager.setDefaultSocketConfig(
                SocketConfig.custom().setSoTimeout(30, TimeUnit.SECONDS).setTcpNoDelay(true).build()
        );
        connManager.setValidateAfterInactivity(TimeValue.ofSeconds(10));
        connManager.setMaxTotal(100);
        connManager.setDefaultMaxPerRoute(10);
        connManager.setValidateAfterInactivity(TimeValue.ofMinutes(10));

        // Cookie
        cookieStore = new BasicCookieStore();

        // Credentials Provider
        basicCredentialsProvider = new BasicCredentialsProvider();

        // 全局请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec(StandardCookieSpec.STRICT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(StandardAuthScheme.NTLM, StandardAuthScheme.DIGEST))
                .setProxyPreferredAuthSchemes(Collections.singletonList(StandardAuthScheme.BASIC))
                .setConnectTimeout(5, TimeUnit.SECONDS)
                .setResponseTimeout(5, TimeUnit.SECONDS)
                .setConnectionRequestTimeout(5, TimeUnit.SECONDS)
                .build();

        // HttpClient
        httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultCookieStore(cookieStore)
                .setDefaultCredentialsProvider(basicCredentialsProvider)
                .setDefaultRequestConfig(requestConfig)
                // 设置定时清理连接池中过期的连接
                .evictExpiredConnections()
                .evictIdleConnections(TimeValue.ofMinutes(3))
                .build();

        // JVM 停止或重启时，关闭连接池释放掉连接
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        // 响应处理
        // https://github.com/apache/httpcomponents-client/blob/5.1.x/httpclient5/src/test/java/org/apache/hc/client5/http/examples/ClientWithResponseHandler.java
        responseHandler = response -> {
            int statusCode = response.getCode();
            if (statusCode >= HttpStatus.SC_SUCCESS && statusCode < HttpStatus.SC_REDIRECTION) {
                HttpEntity httpEntity = response.getEntity();
                try {
                    return httpEntity != null ? EntityUtils.toString(httpEntity, StandardCharsets.UTF_8) : null;
                } catch (ParseException e) {
                    throw new ClientProtocolException(e);
                }
            } else {
                throw new ClientProtocolException("Unexpected response status: " + statusCode);
            }
        };
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println(Request.get("http://localhost:3333/fetch/cookie").execute().returnResponse());

    }

    public static String doPost(String url, Map<String, String> params) throws IOException {
        String responseBody;
        // 2.创建 HttpPost 请求
        HttpPost httpPost = new HttpPost(url);
        if (params != null) {
            List<BasicNameValuePair> nameValuePairList = params.entrySet().stream().map(
                    entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList());
            HttpEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, StandardCharsets.UTF_8);
            httpPost.setEntity(formEntity);
        }
        responseBody = httpClient.execute(httpPost, responseHandler);
        return responseBody;
    }

    public static String doPost(String url, String json) throws IOException {
        String responseBody;
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        responseBody = httpClient.execute(httpPost, responseHandler);
        return responseBody;
    }

    public static String doPost(String url) throws IOException {
        return doPost(url, (Map<String, String>) null);
    }

    public static String doGet(String url, Map<String, String> params) throws IOException, URISyntaxException {
        String responseBody;
        URIBuilder builder = new URIBuilder(url);
        if (params != null) {
            params.forEach(builder::addParameter);
        }
        HttpGet httpGet = new HttpGet(builder.build());
        responseBody = httpClient.execute(httpGet, responseHandler);
        return responseBody;
    }

    public static String doGet(String url) throws IOException, URISyntaxException {
        return doGet(url, null);
    }
}
