package knowledge.网络编程;

import l.demo.Demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress
 * 表示互联网协议 (IP) 地址
 * IP 地址是 IP 使用的 32 位或 128 位无符号数字，它是一种低级协议，UDP 和 TCP 协议都是在它的基础上构建的。
 * https://jdk6.net/net/InetAddress.html
 */
public class InetAddressDemo extends Demo {

    public static void main(String[] args) throws UnknownHostException {
        // 获取本地主机
        InetAddress address = InetAddress.getLocalHost();
        p(address);                 // JS3-LJH/192.168.8.223

        // 在给定主机名的情况下确定主机的 IP 地址
        address = InetAddress.getByName(BAIDU_HOST);
        p(address);                 // www.baidu.com/14.215.177.39

        // 获取主机名
        p(address.getHostName());   // www.baidu.com
        // 获取 IP 地址字符串（以文本表现形式）
        p(address.getHostAddress());// 14.215.177.39
        // 获取原始 IP 地址
        p(address.getAddress());    // [14, -41, -79, 38]

    }

}
