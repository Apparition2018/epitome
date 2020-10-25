package knowledge.网络编程.socket.tcp;

import l.demo.Demo;
import l.demo.Person.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 客户端
 * 1.创建Socket对象，指明需要连接的服务器的地址和端口号。
 * 2.连接建立后，通过输出流向服务器发送请求信息。
 * 3.通过输入流获取服务器响应的信息。
 * 4.关闭相应资源。
 */
public class TCPClient extends Demo {

    public static void main(String[] args) throws IOException {
        // Socket(String host, int port[, InetAddress localAddr, int localPort])
        // 创建一个套接字并将其连接到指定远程主机上的指定远程端口
        Socket socket = new Socket("localhost", 4444);
        // SocketAddress	getRemoteSocketAddress()    返回此套接字连接的端点的地址，如果未连接则返回 null
        p("远程主机地址：" + socket.getRemoteSocketAddress());

        // OutputStream	    getOutputStream()           返回此套接字的输出流
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        Student student = new Student(1, "007", "Mary", 20, 100.0f);
        dos.writeUTF(student + "");

        // InputStream	    getInputStream()            返回此套接字的输入流
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        p("服务器响应：" + dis.readUTF());

        socket.close();
    }

}