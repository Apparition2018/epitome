package knowledge.网络编程.socket.nio;

import l.demo.Demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 服务端
 * <p>
 * Java NIO教程：https://www.yiibai.com/java_nio
 * java分布式开发TCP/IP NIO无阻塞 Socket：https://www.iteye.com/blog/mars914-1238353
 *
 * @author NioServer
 * created on 2020/9/22 15:36
 */
public class NioServer extends Demo {

    /**
     * NIO实现TCP文件传输：https://www.jianshu.com/p/84e57630b25b
     */
    public static void main(String[] args) throws IOException {
        new NioServer().init(9981).listen();
    }

    // 通道选择器
    private Selector selector;

    /**
     * 获取一个 ServerSocket 通道，并初始化通道
     */
    public NioServer init(int port) throws IOException {
        // 获取一个 ServerSocket 通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置为非阻塞
        serverChannel.configureBlocking(false);
        // 绑定监听，配置 TCP 参数
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获取通道选择器
        selector = Selector.open();
        // 将通道选择器与通道绑定，并为该通道注册 SelectionKey.OP_ACCEPT 事件，
        // 只有当该事件到达时，Selector.select() 会返回，否则一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        return this;
    }

    public void listen() throws IOException {
        p("***服务器启动***");
        while (true) {
            // 当有注册的事件到达时，方法返回，否则阻塞。
            selector.select();
            // 获取 selector 中的迭代器，选中项为注册的事件
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                // 删除已选的key，防止重复处理
                ite.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 获得客户端连接通道
                    SocketChannel channel = server.accept();
                    // 可以在任意位置调用这个方法，新的阻塞模式只会影响下面的 i/o 操作
                    channel.configureBlocking(false);
                    // 在与客户端连接成功后，为客户端通道注册 SelectionKey.OP_WRITE 事件。
                    channel.register(selector, SelectionKey.OP_WRITE);
                    p("客户端请求连接事件");
                } else if (key.isReadable()) {

                } else if (key.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    FileInputStream file = new FileInputStream(DEMO_FILE_PATH);
                    FileChannel fileChannel = file.getChannel();
                    // 500M 堆外内存
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(524288000);
                    while (fileChannel.position() < fileChannel.size()) {
                        // 从文件通道读取到 byteBuffer
                        fileChannel.read(byteBuffer);
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                            // 写入通道
                            socketChannel.write(byteBuffer);
                        }
                        byteBuffer.clear();
                        p(fileChannel.position() + " " + fileChannel.size());
                    }
                    p("结束写操作");
                    socketChannel.close();
                }
            }
        }
    }
}
