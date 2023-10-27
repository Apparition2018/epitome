package knowledge.network.socket.tcp;

import org.apache.commons.lang3.time.DateUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static l.demo.Demo.PORT;
import static l.demo.Demo.p;

/**
 * 服务端
 * <pre>
 * 1 创建ServerSocket对象，绑定监听端口。
 * 2 通过accept()监听客户端请求。
 * 3 连接建立后，通过输入流读取客户端发送的请求信息。
 * 4 通过输出流向客户端发送响应信息。
 * 5 关闭响应的资源。
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://www.cnblogs.com/yiwangzhibujian/p/7107785.html">Java Socket 编程基础及深入讲解</a>
 * <a href="https://blog.csdn.net/a78270528/article/details/80318571">Java 网络编程 之 socket 的用法与实现</a>
 * <a href="http://www.runoob.com/java/net-multisoc.html">Java 实例 - Socket 实现多线程服务器程序</a>
 * <a href="http://www.ruanyifeng.com/blog/2012/05/internet_protocol_suite_part_i.html">互联网协议入门（一）</a>
 * <a href="http://www.ruanyifeng.com/blog/2012/06/internet_protocol_suite_part_ii.html">互联网协议入门（二）</a>
 * <a href="https://blog.csdn.net/u012426327/article/details/77160503">单工，半双工，全双工</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/11/17 19:09
 */
public class TCPServer implements Runnable {

    private final ServerSocket SERVER_SOCKET;

    private int clientCnt = 0;

    public static void main(String[] args) {
        try {
            Thread t = new Thread(new TCPServer(PORT));
            t.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TCPServer(int port) throws IOException {
        // ServerSocket([int port[, int backlog[, InetAddress bindAddr]]])
        // 使用指定的端口、侦听 backlog 和要绑定到的本地 IP 地址创建服务器
        SERVER_SOCKET = new ServerSocket(port);
        // int	                    getLocalPort()              返回此套接字在其上侦听的端口
        p("***端口号为" + SERVER_SOCKET.getLocalPort() + "服务器即将启动，等待客户端的连接***");

        // void	    	            setSoTimeout(int timeout)   通过指定超时值启用/禁用 SO_TIMEOUT，以毫秒为单位
        SERVER_SOCKET.setSoTimeout(Math.toIntExact(DateUtils.MILLIS_PER_HOUR));
    }

    /**
     * @see <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/net/Socket.html">Socket</a>
     * @see <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/net/ServerSocket.html">ServerSocket</a>
     */
    public void run() {
        while (true) {
            try {
                // Socket           accept()                    侦听并接受到此套接字的连接
                Socket socket = SERVER_SOCKET.accept();

                p("客户端数量：" + ++clientCnt);

                // SocketAddress    getRemoteSocketAddress()    返回此套接字连接的端点的地址，如果未连接则返回 null
                p("远程主机地址：" + socket.getRemoteSocketAddress());

                // InputStream      getInputStream()            返回此套接字的输入流
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                p(dis.readUTF());
                // void             shutdownInput()             此套接字的输入流置于“流的末尾”
                socket.shutdownInput();

                // OutputStream     getOutputStream()           返回此套接字的输出流
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                // SocketAddress    getLocalSocketAddress()     返回此套接字绑定的端点的地址，如果尚未绑定则返回 null
                dos.writeUTF("谢谢连接我：" + socket.getLocalSocketAddress() + "\nGoodbye!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
