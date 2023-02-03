package jar.apache.httpcomponents.client4;

import l.demo.Demo;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * URIBuilder
 *
 * @author ljh
 * @since 2020/11/12 21:35
 */
public class URIBuilderDemo extends Demo {

    @Test
    public void get() throws URISyntaxException {
        URIBuilder builder = new URIBuilder(MOZILLA_DEMO_URL);

        p(builder);                     // https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container
        p(builder.getScheme());         // https
        p(builder.getHost());           // developer.mozilla.org
        p(builder.getPort());           // -1
        p(builder.getPath());           // /search
        p(builder.getQueryParams());    // [q=URL]
        p(builder.getFragment());       // search-results-close-container
    }

    @Test
    public void set() throws URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost("developer.mozilla.org")
                .setPath("/search")
                .setParameter("q", "URL")
                .build();

        HttpGet httpget = new HttpGet(uri);

        p(httpget.getURI()); // https://developer.mozilla.org/search?q=URL
    }

    @Test
    public void testURIBuilder() throws URISyntaxException {
        Map<String, String> params = Map.of("a", "1", "b", "2", "c", "3");
        URIBuilder builder = new URIBuilder(BAIDU_URL);
        params.forEach((k, v) -> builder.addParameter(k, params.get(v)));
        p(builder.build()); // https://www.baidu.com/?a=1&b=2&c=3
    }
}
