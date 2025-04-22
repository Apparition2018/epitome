package jar.hutool.http;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;

import static l.demo.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/UserAgentUtil/">UserAgentUtil</a>   User Agent 工具类
 * <p>User Agent 中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、浏览器及版本、浏览器渲染引擎等
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/http/useragent/UserAgentUtil.html">UserAgentUtil api</a>
 *
 * @author ljh
 * @since 2020/11/2 23:38
 */
public class UserAgentUtilDemo {

    public static void main(String[] args) {

        UserAgent userAgent = UserAgentUtil.parse("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome.md/80.0.3987.87 Safari/537.36");

        p(userAgent.getBrowser());      // Chrome.md
        p(userAgent.getVersion());      // 80.0.3987.87
        p(userAgent.getEngine());       // Webkit
        p(userAgent.getEngineVersion());// 537.36
        p(userAgent.getOs());           // Windows 7 or Windows Server 2008R2
        p(userAgent.getPlatform());     // Windows

        p(userAgent.isMobile());        // false
    }
}
