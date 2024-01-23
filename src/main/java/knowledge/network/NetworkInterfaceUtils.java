package knowledge.network;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/net/NetworkInterface.html">NetworkInterface</a>
 * <pre>
 * 一个由名称和分配给此接口的 IP 地址列表组成的网络接口。
 * 它用于标识加入多播组的本地接口。
 * 接口通常是按名称（如 "le0"）区分的。
 * </pre>
 *
 * @author ljh
 * @since 2022/12/15 11:21
 */
public final class NetworkInterfaceUtils {
    private NetworkInterfaceUtils() {
        throw new AssertionError(String.format("No %s instances for you!", this.getClass().getName()));
    }

    public static void main(String[] args) throws SocketException {
        System.err.println(NetworkInterfaceUtils.getInetAddressList());
    }

    public static List<InetAddress> getInetAddressList() throws SocketException {
        List<InetAddress> inetAddressList = new ArrayList<>();

        for (Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
             networkInterfaces.hasMoreElements(); ) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();

            for (Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                 inetAddresses.hasMoreElements(); ) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (inetAddress.isLinkLocalAddress()) {
                    // pe(String.format("【LinkLocalAddress】: %s, %s", inetAddress.getHostName(), inetAddress.getHostAddress()));
                } else if (inetAddress.isLoopbackAddress()) {
                    // pe(String.format("【LoopbackAddress】: %s, %s", inetAddress.getHostName(), inetAddress.getHostAddress()));
                } else if (inetAddress.isMulticastAddress()) {
                    // pe(String.format("【MulticastAddress】: %s, %s", inetAddress.getHostName(), inetAddress.getHostAddress()));
                } else if (inetAddress instanceof Inet4Address || inetAddress instanceof Inet6Address) {
                    inetAddressList.add(inetAddress);
                }
            }
        }
        return inetAddressList;
    }

    /** 获取 InetAddress 的 硬件地址(MAC) */
    public static String getMacByInetAddress(InetAddress inetAddress) throws SocketException {
        byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : mac) {
            stringBuilder.append(String.format("%02X-", b));
        }
        if (!stringBuilder.isEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString().toUpperCase();
    }
}
