package spring.api.http;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RestTemplateUtils
 * https://www.cnblogs.com/hujunzheng/p/6018505.html
 *
 * @author Arsenal
 * created on 2020/11/13 1:38
 */
public class RestTemplateUtils {

    private static class DefaultResponseErrorHandler implements ResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return response.getStatusCode().value() != HttpServletResponse.SC_OK;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            try {
                throw new Exception(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String get(String url, JSONObject params) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate.getForObject(expandURL(url, params.keySet()), String.class, params);
    }

    /**
     * 将参数都拼接在 url 之后
     */
    public static String post(String url, JSONObject params, MediaType mediaType) {
        RestTemplate restTemplate = new RestTemplate();
        // 拿到 header 信息
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(mediaType);
        HttpEntity<JSONObject> requestEntity = mediaType == MediaType.APPLICATION_JSON
                ? new HttpEntity<>(params, requestHeaders) : new HttpEntity<>(null, requestHeaders);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return mediaType == MediaType.APPLICATION_JSON
                ? restTemplate.postForObject(url, requestEntity, String.class)
                : restTemplate.postForObject(expandURL(url, params.keySet()), requestEntity, String.class, params);
    }


    /**
     * 发送 json 或者 form 格式数据
     */
    public static <T> T post(String url, JSONObject params, MediaType mediaType, Class<T> clz) {
        RestTemplate restTemplate = new RestTemplate();
        // 这是为 MediaType.APPLICATION_FORM_URLENCODED 格式 HttpEntity 数据 添加转换器
        // 还有就是，如果是 APPLICATION_FORM_URLENCODED 方式发送 post请求，
        // 也可以直接 HttpHeaders requestHeaders = new HttpHeaders(createMultiValueMap(params)，true)，就不用增加转换器了
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        // 设置 header 信息
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(mediaType);

        HttpEntity<?> requestEntity = mediaType == MediaType.APPLICATION_JSON
                ? new HttpEntity<>(params, requestHeaders)
                : (mediaType == MediaType.APPLICATION_FORM_URLENCODED
                ? new HttpEntity<>(createMultiValueMap(params), requestHeaders)
                : new HttpEntity<>(null, requestHeaders));

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        return mediaType == MediaType.APPLICATION_JSON
                ? restTemplate.postForObject(url, requestEntity, clz)
                : restTemplate.postForObject(mediaType == MediaType.APPLICATION_FORM_URLENCODED
                ? url
                : expandURL(url, params.keySet()), requestEntity, clz, params);
    }

    @SuppressWarnings("unchecked")
    private static MultiValueMap<String, String> createMultiValueMap(JSONObject params) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (String key : params.keySet()) {
            if (params.get(key) instanceof List) {
                for (String value : (List<String>) params.get(key)) {
                    map.add(key, value);
                }
            } else {
                map.add(key, params.getString(key));
            }
        }
        return map;
    }

    private static String expandURL(String url, Set<?> keys) {
        final Pattern QUERY_PARAM_PATTERN = Pattern.compile("([^&=]+)(=?)([^&]+)?");
        Matcher mc = QUERY_PARAM_PATTERN.matcher(url);
        StringBuilder sb = new StringBuilder(url);
        if (mc.find()) {
            sb.append("&");
        } else {
            sb.append("?");
        }

        for (Object key : keys) {
            sb.append(key).append("=").append("{").append(key).append("}").append("&");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}