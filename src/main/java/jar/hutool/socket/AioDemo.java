package jar.hutool.socket;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.BufferUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import cn.hutool.socket.aio.AioClient;
import cn.hutool.socket.aio.AioServer;
import cn.hutool.socket.aio.AioSession;
import cn.hutool.socket.aio.SimpleIoAction;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * AIO
 * https://hutool.cn/docs/#/socket/AIO%E5%B0%81%E8%A3%85-AioServer%E5%92%8CAioClient
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/socket/aio/AioClient.html
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/socket/aio/AioServer.html
 *
 * @author ljh
 * @since 2020/11/2 13:39
 */
public class AioDemo {

    @Test
    public void testAioServer() {
        try (AioServer aioServer = new AioServer(8899)) {
            aioServer.setIoAction(new SimpleIoAction() {

                @Override
                public void accept(AioSession session) {
                    StaticLog.debug("【客户端】：{} 连接。", session.getRemoteAddress());
                    session.write(BufferUtil.createUtf8("=== Welcome to Hutool socket server. ==="));
                }

                @Override
                public void doAction(AioSession session, ByteBuffer data) {
                    Console.log(data);

                    if (!data.hasRemaining()) {
                        StringBuilder response = StrUtil.builder()
                                .append("HTTP/1.1 200 OK\r\n")
                                .append("Date: ").append(DateUtil.formatHttpDate(DateUtil.date())).append("\r\n")
                                .append("Content-Type: text/html; charset=UTF-8\r\n")
                                .append("\r\n")
                                .append("Hello Hutool socket");
                        session.writeAndClose(BufferUtil.createUtf8(response));
                    } else {
                        session.read();
                    }
                }
            }).start(true);
        }
    }

    @Test
    public void testAioClient() {
        AioClient client = new AioClient(new InetSocketAddress("localhost", 8899), new SimpleIoAction() {

            @Override
            public void doAction(AioSession session, ByteBuffer data) {
                if (data.hasRemaining()) {
                    Console.log(StrUtil.utf8Str(data));
                    session.read();
                }
                Console.log("OK");
            }
        });

        client.write(ByteBuffer.wrap("Hello".getBytes()));
        client.read();

        client.close();
    }
}
