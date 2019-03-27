package knowledge.网络编程.socket.tcp;

import jar.apache.poi.excel.vo.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 客户端
 */
public class Client {

    public static void main(String[] args) {
        try {
            // 1.创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("localhost", 4444);
            System.out.println("远程主机地址：" + socket.getRemoteSocketAddress());

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Student student = new Student();
            student.setId(1);
            student.setName("Mary");
            student.setAge("20");
            out.writeUTF(student + "");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println("服务器响应：" + in.readUTF());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
