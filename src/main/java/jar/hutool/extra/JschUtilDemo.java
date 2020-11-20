package jar.hutool.extra;

import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.Session;
import org.junit.Test;

/**
 * JschUtil
 * https://hutool.cn/docs/#/extra/Jsch%E5%B0%81%E8%A3%85/Jsch%E5%B7%A5%E5%85%B7-JschUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/extra/ssh/JschUtil.html
 * JSch-用java实现服务器远程操作：https://segmentfault.com/a/1190000019967309?utm_source=tag-newest
 *
 * @author ljh
 * created on 2020/11/20 17:28
 */
public class JschUtilDemo {
    
    @Test
    public void testJschUtil() {
        Session session = JschUtil.getSession("192.168.15.14", 22, "root", "123456");
    }
}
