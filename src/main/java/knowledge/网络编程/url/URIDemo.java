package knowledge.网络编程.url;

import l.demo.Demo;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * URI
 * 统一资源标识符
 * https://jdk6.net/net/URI.html
 * <p>
 * HTTP 协议中 URI 和 URL 有什么区别：https://www.zhihu.com/question/21950864
 *
 * @author ljh
 * created on 2020/9/16 10:46
 */
public class URIDemo extends Demo {

    @Test
    public void testURI() throws URISyntaxException {
        // URI(String str)          通过解析给定的字符串构造一个 URI
        URI uri = new URI(DEMO_URL);

        p(uri);                         // https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container
        p(uri.getScheme());             // 方案组成部分
        p(uri.getPort());               // 端口号：-1
        p(uri.getAuthority());          // 授权部分：developer.mozilla.org
        p(uri.getHost());               // 主机名：developer.mozilla.org
        p(uri.getPath());               // 路径部分：/en-US/search
        p(uri.getQuery());              // 查询部分：q=URL
        p(uri.getFragment());           // 锚点
        p(uri.getSchemeSpecificPart()); // //developer.mozilla.org/en-US/search?q=URL

    }

}
