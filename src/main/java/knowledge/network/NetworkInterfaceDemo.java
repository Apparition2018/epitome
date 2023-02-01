package knowledge.network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/net/InetAddress.html">InetAddress</a>
 * <pre>
 * 一个由名称和分配给此接口的 IP 地址列表组成的网络接口。
 * 它用于标识加入多播组的本地接口。
 * 接口通常是按名称（如 "le0"）区分的。
 * </pre>
 *
 * @author ljh
 * @since 2022/12/15 11:21
 */
public class NetworkInterfaceDemo {

    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (!inetAddress.isLinkLocalAddress() && !inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                    System.out.printf("%s %s%n", inetAddress.getHostName(), inetAddress.getHostAddress());
                    // Arsenal 192.168.0.57
                }
            }
        }
    }
}
