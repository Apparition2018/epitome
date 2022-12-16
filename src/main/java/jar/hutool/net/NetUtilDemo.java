package jar.hutool.net;

import cn.hutool.core.net.NetUtil;
import l.demo.Demo;
import l.demo.Person.Student;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * NetUtil  网络工具
 * https://hutool.cn/docs/#/core/%E7%BD%91%E7%BB%9C/%E7%BD%91%E7%BB%9C%E5%B7%A5%E5%85%B7-NetUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/net/NetUtil.html
 *
 * @author ljh
 * @since 2020/10/30 17:16
 */
public class NetUtilDemo extends Demo {

    private static final String LOCAL_HOST = NetUtil.getLocalhostStr();

    @Test
    public void testNetUtil() {
        // 本机网卡IP地址
        p(NetUtil.getLocalhost());                      // JS3-LJH/192.168.8.223
        // 主机名称
        p(NetUtil.getLocalHostName());                  // JS3-LJH
        // 本机网卡IP地址
        p(NetUtil.getLocalhostStr());                   // 192.168.8.223
        // 本机IP地址列表
        p(NetUtil.localIps());                          // [127.0.0.1, 0:0:0:0:0:0:0:1, 192.168.8.223, fe80:0:0:0:c582:686f:2b98:a92b%eth4]
        // 本机IP4地址列表
        p(NetUtil.localIpv4s());                        // [127.0.0.1, 192.168.8.223]
        // 本机IP6地址列表
        p(NetUtil.localIpv6s());                        // [0:0:0:0:0:0:0:1, fe80:0:0:0:c582:686f:2b98:a92b%eth4]
        // 本机MAC地址
        p(NetUtil.getLocalMacAddress());                // 4c-cc-6a-13-0f-31
        // 本机所有网卡
        p(NetUtil.getNetworkInterfaces());

        // 通过域名得到IP
        p(NetUtil.getIpByHost(BAIDU_HOST));             // 14.215.177.38

        // IP → Long
        p(NetUtil.ipv4ToLong(LOCAL_HOST));              // 3232237791
        // Long → IP
        p(NetUtil.longToIpv4(3232237791L));             // 192.168.8.223
        // 隐藏IP地址最后一部分
        p(NetUtil.hideIpPart(LOCAL_HOST));              // 192.168.8.*
        // 是否为内网IP
        p(NetUtil.isInnerIP(LOCAL_HOST));               // 192.168.8.*
        // 是否能ping通过
        p(NetUtil.ping(LOCAL_HOST, 500));               // true

        // 可用端口，org.springframework.util.SocketUtils
        p(NetUtil.getUsableLocalPort());                // 13357，1024~65535
        // 多个可用端口
        p(NetUtil.getUsableLocalPorts(10, 100, 65535)); // [1856, 5001, 5627, 10547, 10759, 13742, 18851, 29337, 32589, 47482]
        // 是否有效端口
        p(NetUtil.isValidPort(65536));                  // false，0~65535
        // 是否可用端口
        p(NetUtil.isUsableLocalPort(1856));             // true

    }

    /**
     * 使用 Socket 发送数据 (Client)
     */
    @Test
    public void netCat() throws IOException {
        Student student = new Student(1, "007", "Mary", 20, 100.0f);

        // static void	    netCat(String host, int port, byte[] data)
        NetUtil.netCat("localhost", 4444, getBytes(student));

        // static void	    netCat(String host, int port, boolean isBlock, ByteBuffer data)
        NetUtil.netCat("localhost", 4444, true, getByteBuffer(student));
    }

    private static byte[] getBytes(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        byte[] bytes = baos.toByteArray();
        oos.close();
        return bytes;
    }

    private static ByteBuffer getByteBuffer(Object obj) throws IOException {
        byte[] bytes = getBytes(obj);
        return ByteBuffer.wrap(bytes);
    }
}
