package jar.hutool.extra;

import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.Session;

import static l.demo.Demo.MY_SERVER_IP;

/**
 * <a href="https://doc.hutool.cn/pages/JschUtil/">JschUtil</a>
 * <p>需要引入 com.github.mwiede:jsch
 *
 * @author ljh
 * @see <a href="https://segmentfault.com/a/1190000019967309">JSch-用java实现服务器远程操作</a>
 * @see <a href="https://plus.hutool.cn/apidocs/cn/hutool/extra/ssh/JschUtil.html">JschUtil api</a>
 * @since 2020/11/20 17:28
 */
public class JschUtilDemo {

    public static void main(String[] args) {
        Session session = JschUtil.getSession(MY_SERVER_IP, 22, "root", "Cesc123!");
        System.out.println(session.getClientVersion()); // SSH-2.0-JSCH_0.2.16
        System.out.println(session.getServerVersion()); // SSH-2.0-OpenSSH_8.0
    }
}
