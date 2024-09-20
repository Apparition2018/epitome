package jar.apache.httpcomponents.client5;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.HttpRoute;
import org.apache.hc.client5.http.auth.StandardAuthScheme;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
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
import org.apache.hc.core5.http.io.entity.BufferedHttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.util.TimeValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static l.demo.Demo.DESKTOP;

/**
 * HttpClientUtils
 *
 * @author ljh
 * @see <a href="https://hc.apache.org/httpcomponents-client-5.2.x/index.html">HttpClient</a>
 * @see <a href="https://www.baeldung.com/httpclient-guide">Apache HttpClient Tutorial</a>
 * @see <a href="https://blog.csdn.net/citywu123/article/details/109456035">七大 Http 客户端比较</a>
 * @since 2020/11/12 21:35
 */
public final class HttpClientUtils {
    private HttpClientUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    private static volatile HttpClientUtils instance;

    public static HttpClientUtils getInstance() {
        if (instance == null) {
            synchronized (HttpClientUtils.class) {
                if (instance == null) {
                    instance = new HttpClientUtils();
                }
            }
        }
        return instance;
    }

    private static final CloseableHttpClient httpClient;

    private static final CookieStore cookieStore;

    private static final BasicCredentialsProvider basicCredentialsProvider;

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
        connManager.setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(30, TimeUnit.SECONDS).setTcpNoDelay(true).build());
        connManager.setValidateAfterInactivity(TimeValue.ofSeconds(10));
        connManager.setMaxTotal(100);
        connManager.setDefaultMaxPerRoute(10);
        connManager.setMaxPerRoute(new HttpRoute(new HttpHost("somehost", 80)), 20);

        // Cookie
        cookieStore = new BasicCookieStore();

        // Credentials Provider
        basicCredentialsProvider = new BasicCredentialsProvider();

        // 全局请求配置
        RequestConfig requestConfig = RequestConfig.custom()
            .setCookieSpec(StandardCookieSpec.STRICT)
            .setExpectContinueEnabled(true)
            .setTargetPreferredAuthSchemes(List.of(StandardAuthScheme.NTLM, StandardAuthScheme.DIGEST))
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
                throw new RuntimeException(e);
            }
        }));

        // 响应处理
        // https://github.com/apache/httpcomponents-client/blob/5.1.x/httpclient5/src/test/java/org/apache/hc/client5/http/examples/ClientWithResponseHandler.java
        responseHandler = httpResponse -> {
            int statusCode = httpResponse.getCode();
            if (statusCode >= HttpStatus.SC_SUCCESS && statusCode < HttpStatus.SC_REDIRECTION) {
                HttpEntity httpEntity = httpResponse.getEntity();
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

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            // HttpGet
            HttpGet httpGet = new HttpGet("https://credit.gd.gov.cn/creditquery!queryLegalEntityOrgList.do?conditions=914403001922038216");
            httpGet.setHeader("User-Agent", "PostmanRuntime/  7.29.2");
            String paramName;
            String paramValue;
            String responseString = httpClient.execute(httpGet, responseHandler);
            Document document = Jsoup.parse(responseString);
            Elements creditReportInput = document.select("#creditReportForm input");
            paramName = creditReportInput.attr("name");
            paramValue = creditReportInput.attr("value");

            // HttpPost
            HttpPost httpPost = new HttpPost("https://credit.gd.gov.cn/creditreportAction!exportCreditReport.do");
            httpPost.setHeader("User-Agent", "PostmanRuntime/7.29.2");
            List<BasicNameValuePair> nameValuePairList = Collections.singletonList(new BasicNameValuePair(paramName, paramValue));
            HttpEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, StandardCharsets.UTF_8);
            httpPost.setEntity(formEntity);
            HttpEntity responseEntity = httpClient.execute(httpPost, httpResponse -> {
                int statusCode = httpResponse.getCode();
                if (statusCode >= HttpStatus.SC_SUCCESS && statusCode < HttpStatus.SC_REDIRECTION) {
                    return httpResponse.getEntity();
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + statusCode);
                }
            });
            HttpEntity httpEntity = new BufferedHttpEntity(responseEntity);
            String fileMd5 = DigestUtils.md5Hex(httpEntity.getContent());
            Files.copy(httpEntity.getContent(), new File(String.format("%s%s.pdf", DESKTOP, fileMd5)).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String doPost(String url, Map<String, String> params, String json, Map<String, String> cookies) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (params != null) {
            List<BasicNameValuePair> nameValuePairList = params.entrySet().stream().map(
                entry -> new BasicNameValuePair(entry.getKey(), entry.getValue())).collect(Collectors.toList());
            HttpEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, StandardCharsets.UTF_8);
            httpPost.setEntity(formEntity);
        }
        if (StringUtils.isNotBlank(json)) {
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
        }
        if (cookies != null) {
            Header header = new BasicHeader("Cookie", cookies.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&")));
            httpPost.addHeader(header);
        }
        return httpClient.execute(httpPost, responseHandler);
    }

    public String doPost(String url) throws IOException {
        return doPost(url, null, null);
    }

    public String doPost(String url, Map<String, String> params) throws IOException {
        return doPost(url, params, null);
    }

    public String doPost(String url, Map<String, String> params, Map<String, String> cookies) throws IOException {
        return doPost(url, params, null, cookies);
    }

    public String doPost(String url, String json) throws IOException {
        return doPost(url, null, json, null);
    }

    public String doGet(String url, Map<String, String> params) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(url);
        if (params != null) {
            params.forEach(builder::addParameter);
        }
        HttpGet httpGet = new HttpGet(builder.build());
        // 模拟 Postman 访问，防止网页拦截
        httpGet.setHeader("User-Agent", "PostmanRuntime/7.29.2");
        return httpClient.execute(httpGet, responseHandler);
    }

    public String doGet(String url) throws IOException, URISyntaxException {
        return doGet(url, null);
    }
}
