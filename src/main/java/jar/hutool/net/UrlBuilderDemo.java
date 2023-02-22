package jar.hutool.net;

import cn.hutool.core.net.url.UrlBuilder;

/**
 * <a href="https://hutool.cn/docs/#/core/网络/URL生成器-UrlBuilder">UrlBuilder</a>
 * <p>按照 Uniform Resource Identifier 的标准定义，URL的结构如下：
 * <pre>
 * [scheme:]scheme-specific-part[#fragment]
 * [scheme:][//authority][path][?query][#fragment]
 * [scheme:][//host:port][path][?query][#fragment]
 * </pre>
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/net/url/UrlBuilder.html">UrlBuilder api</a>
 *
 * @author ljh
 * @since 2020/11/2 9:46
 */
public class UrlBuilderDemo {

    public static void main(String[] args) {
        String url = UrlBuilder.of()
                .setScheme("https").setHost("developer.mozilla.org")
                .addPath("en-US").addPath("search")
                .addQuery("q", "URL").setFragment("search-results-close-container")
                .build();
        System.out.println(url);
        // https://developer.mozilla.org/en-US/search?q=URL#search-results-close-container
    }
}
