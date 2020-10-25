package jar.apache.httpcomponents;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://www.cnblogs.com/bignew/p/6715671.html
 */
public class HttpClientUtils {

    public static String doPost(String url, Map<String, String> params) {
        // 请求返回结果
        String resultStr = "";

        // 1.使用默认配置的 httpclient
        CloseableHttpResponse response = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // 2.创建 HttpPost 请求
            HttpPost httpPost = new HttpPost(url);

            // 请求内容
            if (null != params) {
                List<NameValuePair> list = new ArrayList<>();
                for (String key : params.keySet()) {
                    list.add(new BasicNameValuePair(key, params.get(key)));
                }
                HttpEntity entity = new UrlEncodedFormEntity(list, StandardCharsets.UTF_8); // 模拟表单
                httpPost.setEntity(entity);
            }

            // 3.执行请求，获取响应
            response = client.execute(httpPost);

            // 4.获取响应的实体内容
            HttpEntity entity = response.getEntity();
            resultStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultStr;
    }

    public static String doPost(String url) {
        return doPost(url, (Map<String, String>) null);
    }

    public static String doPost(String url, String json) {
        String resultStr = "";

        CloseableHttpResponse response = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            // 请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            response = client.execute(httpPost);
            resultStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultStr;
    }

    public static String doGet(String url, Map<String, String> params) {
        // 请求返回结果
        String resultStr = "";

        // 1.使用默认配置的 httpclient
        CloseableHttpResponse response = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // URI 传参
            URIBuilder builder = new URIBuilder(url);
            if (null != params) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }

            URI uri = builder.build();

            // 2.创建 HttpGet 请求
            HttpGet httpGet = new HttpGet(uri);

            // 3.执行请求，获取响应
            response = client.execute(httpGet);

            // 判断返回状态是否成功 200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 4.获取响应的实体内容
                HttpEntity entity = response.getEntity();
                resultStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                EntityUtils.consume(entity);
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultStr;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }
}
