package knowledge.network.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * 服务器端
 * <pre>
 * <a href="https://blog.csdn.net/u012426327/article/details/77160517">JAVA 网络编程之 UDP</a>
 * <a href="https://blog.csdn.net/u012426327/article/details/77162296">JAVA 网络编程之 UDP Socket</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class UDPServer {

    public static void main(String[] args) throws IOException {
        /*
         * 接收客户端发送的数据
         */
        // 1.创建服务器DatagramSocket，制定端口
        DatagramSocket socket = new DatagramSocket(8800);
        // 2.创建数据报，用于接收客户端发送的数据
        byte[] data = new byte[1024]; // 创建字节数组，指定接收的数据包的大小
        DatagramPacket packet = new DatagramPacket(data, data.length);
        // 3.接收客户端发送的数据
        System.out.println("***服务器端已经启动，等待客户端发送数据");
        socket.receive(packet); // 此方法在接收到数据之前会一直阻塞
        // 4.读取数据
        String info = new String(data, 0, packet.getLength(), StandardCharsets.UTF_8);
        System.out.println("我是服务器，客户端说：" + info);

        /*
         * 向客户端响应数据
         */
        // 1.定义客户端的地址、端口号、数据
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "欢迎您！".getBytes();
        // 2.创建数据报，包含响应的数据信息
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
        // 3.响应客户端
        socket.send(packet2);
        // 4.关闭资源
        socket.close();
    }
}
