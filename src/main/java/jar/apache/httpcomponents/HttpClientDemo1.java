package jar.apache.httpcomponents;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 * HttpClient编写程序流程总结:
 * 1.创建HttpClient对象
 * 		CloseableHttpClient client = HttpClients.createDefault();
 * 2.创建请求方法的实例，并指定请求URL
 * 		HttpGet, HttpPost
 * 3.发送请求参数
 * 		Get：直接写在url后面或使用setParameter来设置参数  --请查看URIDemo
 * 			URI uri = new URIBuilder()
 * 					.setScheme("http")
 * 					.setHost("www.google.com")
 * 					.setPath("/search")
 * 					.setParameter("q","httpclient")
 * 					.setParameter("btnG","Google搜索")
 * 					.setParameter("aq","f")
 * 					.setParameter("oq","")
 * 					.build();
 * 			HttpGet httpGet = new HttpGet(uri);
 * 			System.out.println(httpget.getURI());
 * 		Post：用setEntity(HttpEntity entity)来设置参数
 * 4.发送请求
 *		CloseableHttpResponse response = client.execute(httpGet);
 *		--CloseableHttpResponse就是用来处理返回数据的实体，通过它，我们可以拿到返回的状态码、首部、实体等
 * 5.获取请求结果
 * 6.关闭连接，释放资源
 *
 * https://www.cnblogs.com/LuckyBao/p/6096145.html
 */

public class HttpClientDemo1 {

    public static void main(String[] args) {
        String url = "http://www.baidu.com";

        // 1.使用默认配置的 httpclient
        CloseableHttpClient client = HttpClients.createDefault();
        // 2.创建 HttpGet 请求
        HttpGet httpGet = new HttpGet(url);
        InputStream inputStream = null;
        CloseableHttpResponse response = null;

        try {
            // 3.执行请求，获取响应
            response = client.execute(httpGet);

            // 打印 http 状态码，看请求是否成功
            System.out.println("StatusCode: " + response.getStatusLine().getStatusCode());

            // 4.获取响应的实体内容，就是我们所要抓取的网页内容
            HttpEntity entity = response.getEntity();

            // 5.将其打印到控制台上面
//			// 方法一：使用 EntityUtils
//			if (entity != null) {
//				System.out.println(EntityUtils.toString(entity, "UTF-8"));
//			}
//			EntityUtils.consume(entity); // 释放资源

            // 方法二：使用 InputStream（官方推荐）
            if (entity != null) {
                inputStream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (UnsupportedOperationException | IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(response);
        }

    }

}
