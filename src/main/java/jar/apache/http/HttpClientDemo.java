package jar.apache.http;

import l.demo.Demo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * HttpClient
 *
 * @author Arsenal
 * created on 2020/11/13 1:22
 */
public class HttpClientDemo extends Demo {

    /**
     * InputStream is = httpEntity.getContent();
     */
    @Test
    public void getContent() throws IOException, URISyntaxException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(BAIDU_URL);
            HttpGet httpGet = new HttpGet(builder.build());

            try (CloseableHttpResponse response = client.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity httpEntity = response.getEntity();

                    try (BufferedReader br = new BufferedReader(new InputStreamReader(
                            httpEntity.getContent(), StandardCharsets.UTF_8))) {
                        String line;
                        while (null != (line = br.readLine())) {
                            p(line);
                        }
                        EntityUtils.consume(httpEntity);
                    }
                }
            }
        }
    }
}
