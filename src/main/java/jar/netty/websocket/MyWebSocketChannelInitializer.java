package jar.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 初始化连接时候的各个组件
 *
 * @author Arsenal
 * created on 2021/3/5 2:30
 */
public class MyWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast("http-codec", new HttpServerCodec());
        channelPipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        channelPipeline.addLast("http-chunked", new ChunkedWriteHandler());
        channelPipeline.addLast("handler", new MyWebSocketHandler());
    }
}
