package jar.hutool;

import cn.hutool.http.HttpUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * Http
 * https://hutool.cn/docs/#/http/%E6%A6%82%E8%BF%B0
 * <p>
 * Hutool-http 优点：
 * 1.根据 URL 自动判断是请求 HTTP 还是 HTTPS，不需要单独写多余的代码。
 * 2.表单数据中有 File 对象时自动转为 multipart/form-data 表单，不必单做做操作。
 * 3.默认情况下 Cookie 自动记录，比如可以实现模拟登录，即第一次访问登录 URL 后后续请求就是登录状态。
 * 4.自动识别 304 跳转并二次请求
 * 5.自动识别页面编码，即根据 header 信息或者页面中的相关标签信息自动识别编码，最大可能避免乱码。
 * 6.自动识别并解压 Gzip 格式返回内容
 *
 * @author ljh
 * created on 2020/11/2 15:58
 */
public class HttpDemo extends Demo {

    /**
     * HttpUtil
     */
    private static class HttpUtilDemo {
        @Test
        public void testHttpUtil() {
            HttpUtil.post("http://localhost:3333/chinese/test", "chinese");
        }
    }
}
