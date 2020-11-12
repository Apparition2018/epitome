package jar.spring.http;

import l.demo.Demo;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate
 * <p>
 * 详解 RestTemplate 操作：https://blog.csdn.net/itguangit/article/details/78825505
 * RestTemplate 深度解析：https://my.oschina.net/lifany/blog/688889
 * 基于 springboot 的 RestTemplate, okhttp 和 HttpClient 对比：https://www.cnblogs.com/wzk-0000/p/10955406.html
 *
 * @author Arsenal
 * created on 2020/11/13 1:35
 */
public class RestTemplateDemo extends Demo {

    public static HttpHeaders requestHeaders = new HttpHeaders();

    static {
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void testRestTemplate() {
        HttpEntity<String> requestEntity = new HttpEntity<>(null, requestHeaders);

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> responseEntity = template.exchange(BAIDU_URL, HttpMethod.GET, requestEntity, String.class);
        p(responseEntity.getStatusCode());      // 200 OK
        p(responseEntity.getStatusCodeValue()); // 200
        p(responseEntity.getHeaders());         // [Content-Length:"2443", Content-Type:"text/html", Server:"bfe", Date:"Thu, 12 Nov 2020 17:56:57 GMT"]
        p(responseEntity.getBody());
    }

}
