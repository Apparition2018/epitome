package jar.apache.http;

import l.demo.Demo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient 容易忽视的细节——连接关闭：https://www.iteye.com/blog/seanhe-234759
 * HttpClientUtil 工具类：https://www.cnblogs.com/bignew/p/6715671.html
 * HttpComponents之httpclient基本使用方法：https://my.oschina.net/xinxingegeya/blog/282683
 * http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/
 *
 * @author ljh
 * created on 2020/11/12 21:35
 */
public class HttpClientUtils extends Demo {

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(5000)    // 连接超时：指连接一个 url 的连接等待时间
            .setSocketTimeout(5000)     // 读取数据超时：指连接上一个 url ，获取 response 的返回等待时间
            .setConnectionRequestTimeout(5000)
            .build();

    public static void main(String[] args) throws IOException, URISyntaxException {
    }

    public static String doPost(String url, Map<String, String> params) throws IOException {
        String result = "";

        // 1.使用默认配置的 httpclient
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // 2.创建 HttpPost 请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            if (null != params) {
                List<NameValuePair> nameValuePairList = new ArrayList<>();
                for (String key : params.keySet()) {
                    nameValuePairList.add(new BasicNameValuePair(key, params.get(key)));
                }
                HttpEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, StandardCharsets.UTF_8);
                httpPost.setEntity(formEntity);
            }

            // 3.执行请求，获取响应
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                // 4.获取响应的实体内容
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = response.getEntity();
                    result = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
                    EntityUtils.consume(httpEntity);
                }
            }
        }

        return result;
    }

    public static String doPost(String url) throws IOException {
        return doPost(url, (Map<String, String>) null);
    }

    public static String doPost(String url, String json) throws IOException {
        String result = "";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(entity);
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = response.getEntity();
                    result = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
                    EntityUtils.consume(httpEntity);
                }
            }
        }

        return result;
    }

    public static String doGet(String url, Map<String, String> params) throws IOException, URISyntaxException {
        String result = "";

        // 1.使用默认配置的 httpclient
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // 2.创建 HttpGet 请求
            URIBuilder builder = new URIBuilder(url);
            if (null != params) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }
            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.setConfig(requestConfig);

            // 3.执行请求，获取响应
            try (CloseableHttpResponse response = client.execute(httpGet)) {
                // 4.获取响应的实体内容
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = response.getEntity();
                    result = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
                    EntityUtils.consume(httpEntity);
                }
            }
        }

        return result;
    }

    public static String doGet(String url) throws IOException, URISyntaxException {
        return doGet(url, null);
    }
}
