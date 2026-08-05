package jar.apache.httpcomponents.client5;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static l.demo.Demo.COOKIE_URL;

/**
 * fluent API
 *
 * @author ljh
 * @since 2021/8/2 10:35
 */
public class HttpClientFluentDemo {

    public static void main(String[] args) throws IOException {
        String result = Request.post(COOKIE_URL)
                .connectTimeout(Timeout.of(5, TimeUnit.SECONDS))
                .responseTimeout(Timeout.of(5, TimeUnit.SECONDS))
                .addHeader(new BasicHeader("Cookie", "cny=1"))
                .execute().handleResponse(response -> {
                    if (response.getCode() >= HttpStatus.SC_REDIRECTION) {
                        throw new HttpResponseException(response.getCode(), response.getReasonPhrase());
                    }
                    HttpEntity httpEntity = response.getEntity();
                    if (httpEntity == null) {
                        throw new ClientProtocolException("Response contains no content");
                    }
                    return EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
                });
        System.out.println(result);
    }
}
