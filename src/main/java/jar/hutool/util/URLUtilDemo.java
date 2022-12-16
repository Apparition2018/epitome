package jar.hutool.util;

import cn.hutool.core.util.URLUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.net.URL;

/**
 * URLUtil  URL工具
 * 1.网页地址，即互联网上的资源
 * 2.Classpath 中的资源地址
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/URL%E5%B7%A5%E5%85%B7-URLUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/URLUtil.html
 *
 * @author ljh
 * @since 2020/11/19 10:32
 */
public class URLUtilDemo extends Demo {
    

    @Test
    public void testURLUtil() {
        // static String	normalize(String url[, boolean isEncodePath])
        // 标准化URL字符串
        String urlStr = URLUtil.normalize(MOZILLA_DEMO_URL);

        // static URL	    url(String url)
        //  通过一个字符串形式的URL地址创建URL对象
        URL url = URLUtil.url(DEMO_PATH);
        
        // static boolean	isFileURL(URL url)
        // 是否为文件 URL        
        p(URLUtil.isFileURL(url));
    }
}
