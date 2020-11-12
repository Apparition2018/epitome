package jar.apache.http;

import l.demo.Demo;
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

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * HttpClient 容易忽视的细节——连接关闭：https://www.iteye.com/blog/seanhe-234759
 * HttpClientUtil 工具类：https://www.cnblogs.com/bignew/p/6715671.html
 * http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/
 */
public class HttpClientUtils extends Demo {

    public static String doPost(String url, Map<String, String> params) {
        String resultStr = "";
        HttpEntity httpEntity = null;
        InputStream is = null;
        BufferedReader br = null;

        // 1.使用默认配置的 httpclient
        CloseableHttpClient client = HttpClients.createDefault();
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
        try (CloseableHttpResponse response = client.execute(httpPost)) {

            // 4.获取响应的实体内容
            HttpEntity entity = response.getEntity();
            resultStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultStr;
    }

    public static String doPost(String url) {
        return doPost(url, (Map<String, String>) null);
    }

    public static String doPost(String url, String json) {
        String resultStr = "";
        CloseableHttpResponse response = null;
        HttpEntity httpEntity = null;
        InputStream is = null;
        BufferedReader br = null;

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
            close(response);
        }
        return resultStr;
    }

    public static void main(String[] args) {
        p(doGet(BAIDU_URL));
    }

    public static String doGet(String url, Map<String, String> params) {
        StringBuilder resultStr = new StringBuilder();
        HttpEntity httpEntity = null;
        BufferedReader br = null;

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

            // 3.执行请求，获取响应
            try (CloseableHttpResponse response = client.execute(httpGet)) {
                // 4.获取响应的实体内容
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    httpEntity = response.getEntity();
                    if (new Random().nextBoolean()) {
                        resultStr.append(EntityUtils.toString(httpEntity, StandardCharsets.UTF_8));
                    } else {
                        InputStream is = httpEntity.getContent();
                        br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                        String line;
                        while (null != (line = br.readLine())) {
                            resultStr.append(line);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            if (null != httpEntity) {
                try {
                    EntityUtils.consume(httpEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            close(br);
        }

        return resultStr.toString();
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }
    
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
