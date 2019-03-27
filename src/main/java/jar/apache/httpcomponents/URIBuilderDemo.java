package jar.apache.httpcomponents;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * URIBuilder
 * <p>
 * https://blog.csdn.net/wxy1234556/article/details/79022402
 */
public class URIBuilderDemo {

    @Test
    public void get() throws URISyntaxException {

        String url = "http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=";

        URIBuilder builder = new URIBuilder(url);

        System.out.println(builder.getScheme());        // http
        System.out.println(builder.getUserInfo());      // null
        System.out.println(builder.getHost());          // www.google.com
        System.out.println(builder.getPort());          // -1
        System.out.println(builder.getPath());          // /search
        System.out.println(builder.getQueryParams());   // [hl=en, q=httpclient, btnG=Google Search, aq=f, oq=]
        System.out.println(builder.getFragment());      // null
        System.out.println(builder.getCharset());       // null
    }

    @Test
    public void set() throws URISyntaxException {

        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.google.com")
                .setPath("/search")
                .setParameter("q", "httpclient")
                .setParameter("btnG", "Google搜索")
                .setParameter("aq", "f")
                .setParameter("oq", "")
                .build();

        HttpGet httpget = new HttpGet(uri);

        System.out.println(httpget.getURI()); // http://www.google.com/search?q=httpclient&btnG=Google%E6%90%9C%E7%B4%A2&aq=f&oq=
    }

    @Test
    public void test3() throws URISyntaxException {

        String url = "http://www.baidu.com";

        Map<String, String> params = new HashMap<>();
        params.put("a", "1");
        params.put("b", "2");
        params.put("c", "3");

        URIBuilder builder = new URIBuilder(url);

        for (String key : params.keySet()) {
            builder.addParameter(key, params.get(key));
        }

        System.out.println(builder.build()); // http://www.baidu.com?a=1&b=2&c=3
    }

}
