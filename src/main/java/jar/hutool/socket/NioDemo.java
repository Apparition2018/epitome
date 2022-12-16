package jar.hutool.socket;

import cn.hutool.core.io.BufferUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.socket.nio.NioClient;
import cn.hutool.socket.nio.NioServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * NIO
 * https://hutool.cn/docs/#/socket/NIO%E5%B0%81%E8%A3%85-NioServer%E5%92%8CNioClient
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/socket/nio/NioClient.html
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/socket/nio/NioServer.html
 *
 * @author ljh
 * @since 2020/11/2 13:55
 */
public class NioDemo {

    @Test
    public void testNioServer() {
        try (NioServer server = new NioServer(8080)) {
            server.setChannelHandler((socketChannel) -> {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                try {
                    // 从channel读数据到缓冲区
                    int readBytes = socketChannel.read(readBuffer);
                    if (readBytes > 0) {
                        // Flips this buffer.  The limit is set to the current position and then
                        // the position is set to zero，就是表示要从起始位置开始读取数据
                        readBuffer.flip();
                        // returns the number of elements between the current position and the  limit.
                        // 要读取的字节长度
                        byte[] bytes = new byte[readBuffer.remaining()];
                        // 将缓冲区的数据读到bytes数组
                        readBuffer.get(bytes);
                        String body = StrUtil.utf8Str(bytes);
                        Console.log("[{}]: {}", socketChannel.getRemoteAddress(), body);
                        doWrite(socketChannel, body);
                    } else if (readBytes < 0) {
                        IoUtil.close(socketChannel);
                    }
                } catch (IOException e) {
                    throw new IORuntimeException(e);
                }
            });
            server.listen();
        }
    }

    private static void doWrite(SocketChannel channel, String response) throws IOException {
        response = "收到消息：" + response;
        // 将缓冲数据写入渠道，返回给客户端
        channel.write(BufferUtil.createUtf8(response));
    }

    @Test
    public void testNioClient() {
        try (NioClient client = new NioClient("127.0.0.1", 8080)) {
            client.setChannelHandler((socketChannel) -> {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                // 从channel读数据到缓冲区
                int readBytes = socketChannel.read(readBuffer);
                if (readBytes > 0) {
                    // Flips this buffer.  The limit is set to the current position and then
                    // the position is set to zero，就是表示要从起始位置开始读取数据
                    readBuffer.flip();
                    // returns the number of elements between the current position and the  limit.
                    // 要读取的字节长度
                    byte[] bytes = new byte[readBuffer.remaining()];
                    // 将缓冲区的数据读到bytes数组
                    readBuffer.get(bytes);
                    String body = StrUtil.utf8Str(bytes);
                    Console.log("[{}]: {}", socketChannel.getRemoteAddress(), body);
                } else if (readBytes < 0) {
                    socketChannel.close();
                }
            });
            client.listen();
            client.write(BufferUtil.createUtf8("你好。\n"));
            client.write(BufferUtil.createUtf8("你好2。"));
            // 在控制台向服务器端发送数据
            Console.log("请输入发送的消息：");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();
                if (request != null && request.trim().length() > 0) {
                    client.write(BufferUtil.createUtf8(request));
                }
            }
        }
    }
}
