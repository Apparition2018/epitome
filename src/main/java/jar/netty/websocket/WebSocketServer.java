package jar.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * WebSocket
 * <p>客户端：websocket.html
 * <p><a href="https://www.cnblogs.com/carl10086/p/6188808.html">netty(4)高级篇-Websocket协议开发</a>
 *
 * @author ljh
 * @since 2021/3/5 2:34
 */
@Slf4j
public class WebSocketServer {

    public static void main(String[] args) {
        // BossGroup 负责接收 Client 连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // WorkGroup 负责 IO 相关读写
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            // ServerBootstrap 服务端启动配置类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 指定父/子 EventLoopGroup
            serverBootstrap.group(bossGroup, workGroup);
            // 用于创建 Channel 实例的类
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new MyWebSocketChannelInitializer());
            log.info("服务端开启等待客户端连接...");

            // ChannelFuture：异步 Channel I/O 操作的结果
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            // 等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
