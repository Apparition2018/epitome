package jar.apache.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * https://my.oschina.net/xinxingegeya/blog/282683
 */
public class HttpClientDemo {

    @Test
    public void testNameValuePair() {
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("account", ""));
        formParams.add(new BasicNameValuePair("password", ""));
        HttpEntity httpEntity = new UrlEncodedFormEntity(formParams, StandardCharsets.UTF_8);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)    // 连接超时：指连接一个 url 的连接等待时间
                .setSocketTimeout(5000)     // 读取数据超时：指连接上一个 url ，获取 response 的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost("http://cnivi.com.cn/login");
            post.setEntity(httpEntity);
            post.setConfig(requestConfig);
            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                String message = EntityUtils.toString(resEntity, StandardCharsets.UTF_8);
                System.out.println(message);
            } else {
                System.out.println("请求失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
