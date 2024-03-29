package spring.api.http;

import l.demo.Demo;
import l.demo.Person;
import l.demo.Person.Student;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.io.SocketConfig;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RestTemplate
 * <p><a href="https://www.baeldung.com/rest-template">A Guide to the RestTemplate</a>
 *
 * @author ljh
 * @since 2020/11/13 1:35
 */
public class RestTemplateDemo extends Demo {

    private static final RestTemplate restTemplate;
    private static final HttpHeaders requestHeaders = new HttpHeaders();
    private static final Map<String, Object> map;
    private static final MultiValueMap<String, Object> multiValueMap;
    private static final Person person = new Person(1, "John");

    private String url;
    private ResponseEntity<Student> responseEntity;

    static {
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(5, TimeUnit.SECONDS).build();
        try (PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager()) {
            poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
            try (CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager).build()) {
                HttpComponentsClientHttpRequestFactory requestFactory =
                        new HttpComponentsClientHttpRequestFactory(httpClient);
                requestFactory.setConnectionRequestTimeout((int) TimeUnit.SECONDS.toMillis(5));
                requestFactory.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(5));

                restTemplate = new RestTemplate(requestFactory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        map = Map.of("id", 1, "name", "John");

        multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("id", 1);
        multiValueMap.add("name", "John");
    }

    @Test
    public void cookie() {
        requestHeaders.add("Cookie", "cny=1");
        HttpEntity<?> httpEntity = new HttpEntity<>(map, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(COOKIE_URL, HttpMethod.POST, httpEntity, String.class);
        p(responseEntity.getBody());
    }

    @Test
    public void exchange() {
        // http://localhost:3333/demo/post3
        url = DEMO_URL + "post3";
        HttpEntity<?> httpEntity;
        if (randomBoolean()) {
            httpEntity = new HttpEntity<>(person, requestHeaders);
        } else {
            httpEntity = new HttpEntity<>(map, requestHeaders);
        }
        responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Student.class);
        printResponseEntity(responseEntity);
    }

    @Test
    public void get() {
        // http://localhost:3333/demo/get
        //********** 1.getForEntity() **********
        switch (randomInt(1, 3)) {
            case 1 -> {
                url = DEMO_URL + "get?id=" + 1 + "&name=" + "John";
                responseEntity = restTemplate.getForEntity(url, Student.class);
            }
            case 2 -> {
                url = DEMO_URL + "get?id={id}&name={name}";
                responseEntity = restTemplate.getForEntity(url, Student.class, 1, "John");
            }
            default -> {
                url = DEMO_URL + "get?id={id}&name={name}";
                responseEntity = restTemplate.getForEntity(url, Student.class, map);
            }
        }
        printResponseEntity(responseEntity);

        //********** 2.getForObject() **********
        // 同 getForEntity()，只是返回值变为 T
    }

    @Test
    public void post() {
        //********** 1.postForEntity() **********
        switch (randomInt(1, 4)) {
            case 1 -> {
                // http://localhost:3333/demo/post
                url = DEMO_URL + "post";
                responseEntity = restTemplate.postForEntity(url, multiValueMap, Student.class);
            }
            case 2 -> {
                // http://localhost:3333/demo/post2
                url = DEMO_URL + "post2";
                responseEntity = restTemplate.postForEntity(url, multiValueMap, Student.class);
            }
            case 3 -> {
                // http://localhost:3333/demo/post3
                url = DEMO_URL + "post3";
                responseEntity = restTemplate.postForEntity(url, map, Student.class);
            }
            default -> {
                // http://localhost:3333/demo/post3
                url = DEMO_URL + "post3";
                responseEntity = restTemplate.postForEntity(url, person, Student.class);
            }
        }
        printResponseEntity(responseEntity);

        //********** 2.postForObject() **********
        // 同 postForEntity()，只是返回值变为 T
    }

    /** &#064;PathVariable */
    @Test
    public void path() {
        // http://localhost:3333/demo/path/{id}/{name}
        //********** 1.getForEntity() **********
        switch (randomInt(1, 3)) {
            case 1 -> {
                url = DEMO_URL + "path/1/John";
                responseEntity = restTemplate.getForEntity(url, Student.class);
            }
            case 2 -> {
                url = DEMO_URL + "path/{id}/{name}";
                responseEntity = restTemplate.getForEntity(url, Student.class, 1, "John");
            }
            default -> {
                url = DEMO_URL + "path/{id}/{name}";
                responseEntity = restTemplate.getForEntity(url, Student.class, map);
            }
        }
        printResponseEntity(responseEntity);

        //********** 2.getForObject() **********
        // 同 getForEntity()，只是返回值变为 T

        //********** 3.postForEntity() **********
        switch (randomInt(1, 3)) {
            case 1 -> {
                url = DEMO_URL + "path/1/John";
                responseEntity = restTemplate.postForEntity(url, null, Student.class);
            }
            case 2 -> {
                url = DEMO_URL + "path/{id}/{name}";
                responseEntity = restTemplate.postForEntity(url, null, Student.class, 1, "John");
            }
            default -> {
                url = DEMO_URL + "path/{id}/{name}";
                responseEntity = restTemplate.postForEntity(url, null, Student.class, map);
            }
        }
        printResponseEntity(responseEntity);

        //********** 4.postForObject() **********
        // 同 postForEntity()，只是返回值变为 T
    }

    private void printResponseEntity(ResponseEntity<?> responseEntity) {
        p(responseEntity.getStatusCode());
        p(responseEntity.getHeaders());
        p(responseEntity.getBody() + StringUtils.CR);
    }
}
