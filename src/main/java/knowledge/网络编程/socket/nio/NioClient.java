package knowledge.网络编程.socket.nio;

import l.demo.Demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 客户端
 *
 * @author NioClient
 * created on 2020/9/22 15:36
 */
public class NioClient extends Demo {

    public static void main(String[] args) throws IOException, InterruptedException {
        new NioClient().init("127.0.0.1", 9981).listen();
    }

    // 管道选择器
    private Selector selector;

    public NioClient init(String serverIp, int port) throws IOException, InterruptedException {
        // 获取 ServerSocket 通道
        SocketChannel channel = SocketChannel.open();

        channel.configureBlocking(false);
        // 获得通道选择器
        selector = Selector.open();

        // 客户端连接服务器，需要调用 channel.finishConnect()；才能实际完成连接。
        boolean connectResult = channel.connect(new InetSocketAddress(serverIp, port));

        // 为该通道注册 SelectionKey.OP_CONNECT 事件
        channel.register(selector, SelectionKey.OP_CONNECT);
        return this;
    }

    public void listen() throws IOException {
        p("***客户端启动***");
        // 轮询访问 selector
        while (true) {
            // 选择注册过的 io 操作的事件(第一次为 SelectionKey.OP_CONNECT)
            selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                // 删除已选的 key，防止重复处理
                ite.remove();
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 如果正在连接，则完成连接
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    // 向服务器发送消息
                    channel.write(ByteBuffer.wrap("send message to server.".getBytes()));
                    // 连接成功后，注册接收服务器消息的事件
                    channel.register(selector, SelectionKey.OP_READ);//订阅读取事件
                    p("客户端连接成功");
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(10);
                    int readLength = socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    int count = 0;
                    File file = new File(DEMO_PATH + "demo_copy");
                    if (!file.exists()) {
                        boolean b = file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(file, true);
                    FileChannel fileChannel = fos.getChannel();
                    // 分多次读取
                    while (readLength != -1) {
                        count = count + readLength;
                        p("count=" + count + " readLength=" + readLength);
                        // 将 socketChannel 数据读到 byteBuffer
                        readLength = socketChannel.read(byteBuffer);
                        byteBuffer.flip();
                        // byteBuffer 数据写到 FileChannel
                        fileChannel.write(byteBuffer);
                        fos.flush();
                        byteBuffer.clear();
                    }
                    fileChannel.close();

                    fos.close();
                    p("读取结束");
                    socketChannel.close();
                }
            }
        }
    }
}
