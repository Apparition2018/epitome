package jar.apache.httpcomponents.client5;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.util.Timeout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Fluent
 * https://hc.apache.org/httpcomponents-client-4.5.x/current/tutorial/html/fluent.html
 *
 * @author ljh
 * created on 2021/8/2 10:35
 */
public class FluentDemo {

    private static final String URL = "http://localhost:3333/fetch/cookie";

    @Test
    public void testFluent() throws IOException {
        String result = Request.post(URL)
                .connectTimeout(Timeout.of(5, TimeUnit.SECONDS))
                .responseTimeout(Timeout.of(5, TimeUnit.SECONDS))
                .addHeader(new BasicHeader("Cookie", "cny=1"))
                .execute().handleResponse(response -> {
                    if (response.getCode() >= HttpStatus.SC_REDIRECTION) {
                        throw new HttpResponseException(response.getCode(), response.getReasonPhrase());
                    }
                    HttpEntity entity = response.getEntity();
                    if (entity == null) {
                        throw new ClientProtocolException("Response contains no content");
                    }
                    return EntityUtils.toString(entity, StandardCharsets.UTF_8);
                });
        System.out.println(result);
    }
}
