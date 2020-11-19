package jar.spring.http;

import l.demo.Demo;
import l.demo.Person;
import l.demo.Person.Student;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * RestTemplate
 * <p>
 * Restful (HttpMethod): GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
 * <p>
 * 详解 RestTemplate 操作：https://blog.csdn.net/itguangit/article/details/78825505
 * RestTemplate 深度解析：https://my.oschina.net/lifany/blog/688889
 * Spring RestTemplate 中几种常见的请求方式：https://segmentfault.com/a/1190000011093597
 * 基于 springboot 的 RestTemplate, okhttp 和 HttpClient 对比：https://www.cnblogs.com/wzk-0000/p/10955406.html
 *
 * @author Arsenal
 * created on 2020/11/13 1:35
 */
public class RestTemplateDemo extends Demo {

    private static RestTemplate restTemplate;
    private static HttpHeaders requestHeaders = new HttpHeaders();

    private String url;
    private ResponseEntity<Student> responseEntity;

    private static Map<String, Object> map;
    private static MultiValueMap<String, Object> multiValueMap;
    private static Person person = new Person(1, "John");

    static {
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
                        .setMaxConnTotal(20).setMaxConnPerRoute(20).build());
        requestFactory.setConnectionRequestTimeout((int) TimeUnit.SECONDS.toMillis(5));
        requestFactory.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(5));
        requestFactory.setReadTimeout((int) TimeUnit.SECONDS.toMillis(5));

        restTemplate = new RestTemplate(requestFactory);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "John");

        multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("id", 1);
        multiValueMap.add("name", "John");
    }

    @Test
    public void exchange() {
        // http://localhost:3333/demo/post3
        url = DEMO_URI + "post3";
        HttpEntity<?> requestEntity;
        if (new Random().nextBoolean()) {
            requestEntity = new HttpEntity<>(person, requestHeaders);
        } else {
            requestEntity = new HttpEntity<>(map, requestHeaders);
        }
        ResponseEntity<Student> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Student.class);
        printResponseEntity(responseEntity);
    }

    /**
     * GET
     */
    @Test
    public void get() {
        // http://localhost:3333/demo/get
        //********** 1.getForEntity() **********
        switch (randomInt(1, 3)) {
            case 1:
                url = DEMO_URI + "get?id=" + 1 + "&name=" + "John";
                responseEntity = restTemplate.getForEntity(url, Student.class);
                break;
            case 2:
                url = DEMO_URI + "get?id={id}&name={name}";
                responseEntity = restTemplate.getForEntity(url, Student.class, 1, "John");
                break;
            default:
                url = DEMO_URI + "get?id={id}&name={name}";
                responseEntity = restTemplate.getForEntity(url, Student.class, map);
                break;
        }
        printResponseEntity(responseEntity);

        //********** 2.getForObject() **********
        // 同 getForEntity()，只是返回值变为 T
    }

    /**
     * POST
     */
    @Test
    public void post() {
        //********** 1.postForEntity() **********
        switch (randomInt(1, 4)) {
            case 1:
                // http://localhost:3333/demo/post
                url = DEMO_URI + "post";
                responseEntity = restTemplate.postForEntity(url, multiValueMap, Student.class);
                break;
            case 2:
                // http://localhost:3333/demo/post2
                url = DEMO_URI + "post2";
                responseEntity = restTemplate.postForEntity(url, multiValueMap, Student.class);
                break;
            case 3:

                // http://localhost:3333/demo/post3
                url = DEMO_URI + "post3";
                responseEntity = restTemplate.postForEntity(url, map, Student.class);
                break;
            default:
                // http://localhost:3333/demo/post3
                url = DEMO_URI + "post3";
                responseEntity = restTemplate.postForEntity(url, person, Student.class);
                break;
        }
        printResponseEntity(responseEntity);

        //********** 2.postForObject() **********
        // 同 postForEntity()，只是返回值变为 T
    }

    /**
     * -@PathVariable
     */
    @Test
    public void path() {
        // http://localhost:3333/demo/path/{id}/{name}
        //********** 1.getForEntity() **********
        switch (randomInt(1, 3)) {
            case 1:
                url = DEMO_URI + "path/1/John";
                responseEntity = restTemplate.getForEntity(url, Student.class);
                break;
            case 2:
                url = DEMO_URI + "path/{id}/{name}";
                responseEntity = restTemplate.getForEntity(url, Student.class, 1, "John");
                break;
            default:
                url = DEMO_URI + "path/{id}/{name}";
                responseEntity = restTemplate.getForEntity(url, Student.class, map);
                break;
        }
        printResponseEntity(responseEntity);

        //********** 2.getForObject() **********
        // 同 getForEntity()，只是返回值变为 T

        //********** 3.postForEntity() **********
        switch (randomInt(1, 3)) {
            case 1:
                url = DEMO_URI + "path/1/John";
                responseEntity = restTemplate.postForEntity(url, null, Student.class);
                break;
            case 2:
                url = DEMO_URI + "path/{id}/{name}";
                responseEntity = restTemplate.postForEntity(url, null, Student.class, 1, "John");
                break;
            default:
                url = DEMO_URI + "path/{id}/{name}";
                responseEntity = restTemplate.postForEntity(url, null, Student.class, map);
                break;
        }
        printResponseEntity(responseEntity);

        //********** 4.postForObject() **********
        // 同 postForEntity()，只是返回值变为 T
    }

    private void printResponseEntity(ResponseEntity<?> responseEntity) {
        p(responseEntity.getStatusCode());      // 200 OK
        // p(responseEntity.getStatusCodeValue()); // 200
        p(responseEntity.getHeaders());
        p(responseEntity.getBody() + "\n");
    }

}
