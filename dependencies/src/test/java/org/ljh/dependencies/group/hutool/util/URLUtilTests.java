package org.ljh.dependencies.group.hutool.util;

import cn.hutool.core.util.URLUtil;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static com.ljh.commons.Demo.*;

/**
 * <a href="https://doc.hutool.cn/pages/URLUtil/">URLUtil</a>   URL工具
 * <pre>
 * 1 网页地址，即互联网上的资源
 * 2 Classpath 中的资源地址
 * </pre>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/URLUtil.html">URLUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 10:32
 */
public class URLUtilTests {

    @Test
    public void test() {
        // static String	normalize(String url[, boolean isEncodePath])
        // 标准化URL字符串
        String urlStr = URLUtil.normalize(MOZILLA_DEMO_URL);

        // static URL	    url(String url)
        //  通过一个字符串形式的URL地址创建URL对象
        URL url = URLUtil.url(DEMO_DIR_PATH);

        // static boolean	isFileURL(URL url)
        // 是否为文件 URL
        p(URLUtil.isFileURL(url));
    }
}
