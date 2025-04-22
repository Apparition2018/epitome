package jar.hutool.util;

import cn.hutool.core.util.URLUtil;
import l.demo.Demo;

import java.net.URL;

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
public class URLUtilDemo extends Demo {

    public static void main(String[] args) {
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
