package jar.apache.httpcomponents.client4;

import l.demo.Demo;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * URLEncodedUtils
 *
 * @author ljh
 * @since 2020/12/9 14:10
 */
public class URLEncodedUtilsDemo extends Demo {

    public static void main(String[] args) throws URISyntaxException {
        URI uri = new URIBuilder(MOZILLA_DEMO_URL).build();

        // 返回 URI 查询参数 NameValuePair 列表
        List<NameValuePair> nameValuePairs = URLEncodedUtils.parse(uri, StandardCharsets.UTF_8);
        p(nameValuePairs); // [q=URL]

        // 返回作适合用作 HTTP PUT 或 HTTP POST 中的 application-x-www-form-urlencoded 参数列表字符串
        p(URLEncodedUtils.format(nameValuePairs, StandardCharsets.UTF_8)); // q=URL
    }
}
