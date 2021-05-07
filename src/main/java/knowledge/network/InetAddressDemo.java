package knowledge.network;

import l.demo.Demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress
 * 表示互联网协议 (IP) 地址
 * IP 地址是 IP 使用的 32 位或 128 位无符号数字，它是一种低级协议，UDP 和 TCP 协议都是在它的基础上构建的。
 * https://www.runoob.com/manual/jdk1.6/java.base/java/net/InetAddress.html
 *
 * @author ljh
 * created on 2020/11/17 19:09
 */
public class InetAddressDemo extends Demo {

    public static void main(String[] args) throws UnknownHostException {
        // static InetAddress	getLocalHost()          获取本地主机
        p(InetAddress.getLocalHost());                  // JS3-LJH/192.168.8.223

        // static InetAddress	getByName(String host)  在给定主机名的情况下确定主机的 IP 地址
        // 在给定主机名的情况下确定主机的 IP 地址
        InetAddress inetAddress = InetAddress.getByName(BAIDU_HOST);
        p(inetAddress);                                 // www.baidu.com/14.215.177.39

        // String	            getHostName()           获取此 IP 地址的主机名
        p(inetAddress.getHostName());                   // www.baidu.com
        // String	            getHostAddress()        获取 IP 地址字符串（以文本表现形式）
        p(inetAddress.getHostAddress());                // 14.215.177.39         、
        // byte[]	            getAddress()            获取此 InetAddress 对象的原始 IP 地址
        p(inetAddress.getAddress());                    // [14, -41, -79, 38]
    }

}
