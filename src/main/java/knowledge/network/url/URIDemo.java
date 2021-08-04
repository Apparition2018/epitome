package knowledge.network.url;

import l.demo.Demo;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * URI
 * 统一资源标识符
 * https://www.runoob.com/manual/jdk1.6/java.base/java/net/URI.html
 * HTTP 协议中 URI 和 URL 有什么区别：https://www.zhihu.com/question/21950864
 *
 * @author ljh
 * created on 2020/9/16 10:46
 */
public class URIDemo extends Demo {

    public static void main(String[] args) throws URISyntaxException {
        // URI(String str)                  通过解析给定的字符串构造一个 URI
        URI uri = new URI(MOZILLA_DEMO_URL);
        // static URI	create(String str)  通过解析给定的字符串创建 URI
        URI uri2 = URI.create(BAIDU_URL);

        p(uri);                         // https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container
        p(uri.getScheme());             // 方案组成部分
        p(uri.getAuthority());          // 授权部分：developer.mozilla.org
        p(uri.getHost());               // 主机名：developer.mozilla.org
        p(uri.getSchemeSpecificPart()); // //developer.mozilla.org/en-US/search?q=URL
        p(uri.getPort());               // 端口号：-1
        p(uri.getPath());               // 路径部分：/en-US/search
        p(uri.getQuery());              // 查询部分：q=URL
        p(uri.getFragment());           // 锚点：search-results-close-container
    }
}
