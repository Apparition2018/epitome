package spring.api.util;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.util.SocketUtils;

/**
 * SocketUtils
 * 查找可用 TCP/UDP 端口
 * <p>
 * TCP          端口          名称
 * FTP          20, 21      文件传输协议
 * SSH          22          安全外壳协议
 * Telnet       23          远程终端协议
 * SMTP         25          简单邮件传输协议
 * DNS          53          域名系统
 * HTTP         80          超文本传输协议
 * POP3         110         邮局协议3
 * NTP          123         网络时间协议
 * IMAP4        143         因特网信息存取协议4
 * HTTPS        443         超文本传输安全协议
 * <p>
 * UDP          端口          名称
 * DNS          53          域名系统
 * DHCP         67          动态主机配置协议
 * TFTP         69          简单文件传输协议
 * SNMP         161         简单网络管理协议
 *
 * @author ljh
 * created on 2021/9/8 16:49
 */
public class SocketUtilsDemo extends Demo {

    @Test
    public void testSocketUtils() {
        // 最大端口
        p(SocketUtils.PORT_RANGE_MAX);                      // 65535
        // 返回1个可用 TCP 端口
        p(SocketUtils.findAvailableTcpPort());              // 51090
        // 返回5个可用 TCP 端口
        p(SocketUtils.findAvailableTcpPorts(5));            // [7819, 14127, 15632, 28173, 59385]
        // 返回范围内1个可用 TCP 端口
        p(SocketUtils.findAvailableTcpPort(1000, 3000));    // 2923
        // 返回范围内5个可用 TCP 端口
        p(SocketUtils.findAvailableTcpPorts(5, 1000, 3000));// [1351, 1514, 1681, 1747, 2833]

        // 返回1个可用 UDP 端口
        p(SocketUtils.findAvailableUdpPort());              // 18562
    }
}
