package jar.hutool.net;

import cn.hutool.core.net.url.UrlBuilder;
import org.junit.jupiter.api.Test;

/**
 * UrlBuilder
 * <p>
 * 按照 Uniform Resource Identifier 的标准定义，URL的结构如下：
 * [scheme:]scheme-specific-part[#fragment]
 * [scheme:][//authority][path][?query][#fragment]
 * [scheme:][//host:port][path][?query][#fragment]
 * <p>
 * https://hutool.cn/docs/#/core/%E7%BD%91%E7%BB%9C/URL%E7%94%9F%E6%88%90%E5%99%A8-UrlBuilder
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/net/url/UrlBuilder.html
 *
 * @author ljh
 * @since 2020/11/2 9:46
 */
public class UrlBuilderDemo {

    @Test
    public void testUrlBuilder() {
        String url = UrlBuilder.create()
                .setScheme("https").setHost("developer.mozilla.org")
                .addPath("en-US").addPath("search")
                .addQuery("q", "URL").setFragment("search-results-close-container")
                .build();
        System.out.println(url);
        // https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container
    }

}
