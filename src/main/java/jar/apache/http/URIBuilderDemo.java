package jar.apache.http;

import l.demo.Demo;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * URIBuilder
 * https://blog.csdn.net/wxy1234556/article/details/79022402
 *
 * @author ljh
 * created on 2020/11/12 21:35
 */
public class URIBuilderDemo extends Demo {

    @Test
    public void get() throws URISyntaxException {

        URIBuilder builder = new URIBuilder(DEMO_URL);

        p(builder);                     // https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container
        p(builder.getScheme());         // https
        p(builder.getHost());           // developer.mozilla.org
        p(builder.getPort());           // -1
        p(builder.getPath());           // /search
        p(builder.getQueryParams());    // [q=URL]
        p(builder.getFragment());       // search-results-close-container
        p(builder.getUserInfo());       // null
        p(builder.getCharset());        // null
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

        Map<String, String> params = new HashMap<>();
        params.put("a", "1");
        params.put("b", "2");
        params.put("c", "3");

        URIBuilder builder = new URIBuilder(BAIDU_URL);

        for (String key : params.keySet()) {
            builder.addParameter(key, params.get(key));
        }

        p(builder.build()); // https://www.baidu.com/?a=1&b=2&c=3
    }

}
