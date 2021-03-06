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
        // ChannelPipeline：ChannelHandlers 列表，用于处理或拦截 Channel 的 inbound 或者 outbound
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        // HttpServerCodec：HttpRequestDecoder 和 HttpResponseEncoder 的组合
        channelPipeline.addLast("http-codec", new HttpServerCodec());
        // HttpObjectAggregator：将 HttpMessage 及其后面的 HttpContents 聚合为一个单独的 FullHttpRequest 或 FullHttpResponse
        channelPipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        // ChunkedWriteHandler：对异步写入大数据流的支持，既不消耗大量内存，也不获取 OutOfMemoryError
        channelPipeline.addLast("http-chunked", new ChunkedWriteHandler());
        channelPipeline.addLast("handler", new MyWebSocketHandler());
    }
}
