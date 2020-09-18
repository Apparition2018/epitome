package knowledge.网络编程.socket.tcp;

import l.demo.Demo;
import org.apache.commons.lang3.time.DateUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * 服务端
 * 1)创建ServerSocket对象，绑定监听端口。
 * 2)通过accept()监听客户端请求。
 * 3)连接建立后，通过输入流读取客户端发送的请求信息。
 * 4)通过输出流向客户端发送响应信息。
 * 5)关闭响应的资源。
 * <p>
 * https://www.cnblogs.com/yiwangzhibujian/p/7107785.html
 * https://blog.csdn.net/a78270528/article/details/80318571
 * http://www.runoob.com/java/net-serversocket-socket.html
 * http://www.runoob.com/java/net-multisoc.html
 */
public class Server extends Demo implements Runnable {

    private ServerSocket serverSocket;

    int clientCount = 0; // 记录客户端的数量

    public static void main(String[] args) {
        try {
            Thread t = new Thread(new Server(4444));
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server(int port) throws IOException {
        // ServerSocket([int port[, int backlog[, InetAddress bindAddr]]])
        // 使用指定的端口、侦听 backlog 和要绑定到的本地 IP 地址创建服务器
        serverSocket = new ServerSocket(port);
        // int	                    getLocalPort()              返回此套接字在其上侦听的端口
        p("***端口号为" + serverSocket.getLocalPort() + "服务器即将启动，等待客户端的连接***");

        // void	    	            setSoTimeout(int timeout)   通过指定超时值启用/禁用 SO_TIMEOUT，以毫秒为单位
        serverSocket.setSoTimeout(Math.toIntExact(DateUtils.MILLIS_PER_HOUR));
    }

    /**
     * Socket
     * https://jdk6.net/net/Socket.html
     * ServerSocket
     * https://jdk6.net/net/ServerSocket.html
     */
    public void run() {
        while (true) {
            try {
                // Socket	        accept()                    侦听并接受到此套接字的连接
                Socket socket = serverSocket.accept();

                p("客户端数量：" + ++clientCount);

                // SocketAddress	getRemoteSocketAddress()    返回此套接字连接的端点的地址，如果未连接则返回 null
                p("远程主机地址：" + socket.getRemoteSocketAddress());

                // InputStream	    getInputStream()            返回此套接字的输入流
                DataInputStream in = new DataInputStream(socket.getInputStream());
                p(in.readUTF());
                // void	            shutdownInput()             此套接字的输入流置于“流的末尾”
                socket.shutdownInput();

                // OutputStream	    getOutputStream()           返回此套接字的输出流
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                // SocketAddress	getLocalSocketAddress()     返回此套接字绑定的端点的地址，如果尚未绑定则返回 null
                out.writeUTF("谢谢连接我：" + socket.getLocalSocketAddress() + "\nGoodbye!");
            } catch (SocketTimeoutException s) {
                p("Socket timed out!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}