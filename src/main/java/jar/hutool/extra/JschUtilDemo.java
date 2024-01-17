package jar.hutool.extra;

import cn.hutool.extra.ssh.JschUtil;
import com.jcraft.jsch.Session;
import l.demo.Demo;

/**
 * <a href="https://hutool.cn/docs/#/extra/Jsch封装/Jsch工具-JschUtil">JschUtil</a>
 * <p>需要引入 com.github.mwiede:jsch
 * <p>参考：
 * <pre>
 * <a href="https://segmentfault.com/a/1190000019967309">JSch-用java实现服务器远程操作</a>
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/extra/ssh/JschUtil.html">JschUtil api</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/20 17:28
 */
public class JschUtilDemo extends Demo {

    public static void main(String[] args) {
        Session session = JschUtil.getSession(MY_SERVER_IP, 22, "root", "Cesc123!");
        System.out.println(session.getClientVersion()); // SSH-2.0-JSCH_0.2.16
        System.out.println(session.getServerVersion()); // SSH-2.0-OpenSSH_8.0
    }
}
