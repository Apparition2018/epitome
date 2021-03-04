package jar.netty.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * 接收/处理/响应客户端 websocket 请求的核心业务处理类
 *
 * @author Arsenal
 * created on 2021/3/5 1:16
 */
@Slf4j
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker webSocketServerHandshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";

    /**
     * 服务端处理客户端 websocket 请求的核心方法
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        // 处理客户端向服务端发起 http 握手请求的业务
        if (o instanceof FullHttpRequest) {
            handleHttpRequest(channelHandlerContext, (FullHttpRequest) o);
            // 处理 websocket 连接业务
        } else if (o instanceof WebSocketFrame) {
            handleWebsocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    /**
     * 处理客户端与服务端之间的 websocket 业务
     */
    private void handleWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame webSocketFrame) {
        // 判断是否为关闭 websocket 指令
        if (webSocketFrame instanceof CloseWebSocketFrame) {
            webSocketServerHandshaker.close(ctx.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
        }
        // 判断是否为 ping 消息
        if (webSocketFrame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }
        // 判断是否为二进制消息，如果是则抛出异常
        if (!(webSocketFrame instanceof TextWebSocketFrame)) {
            log.info("目前不支持二进制消息");
            throw new RuntimeException("【" + this.getClass().getName() + "】不支持消息");
        }
        // 返回应该消息
        // 获取客户端向服务端发送的消息
        String requestText = ((TextWebSocketFrame) webSocketFrame).text();
        log.info("服务端收到客户端的消息===>>>" + requestText);
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + " ===>>> " + requestText);
        // 群发，服务端向每个连接上来的客户端群发消息
        NettyConfig.group.writeAndFlush(textWebSocketFrame);
    }

    /**
     * 处理客户端向服务端发起 http 握手请求的业务
     */
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (!request.decoderResult().isSuccess() || !("websocket".equals(request.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
        webSocketServerHandshaker = webSocketServerHandshakerFactory.newHandshaker(request);
        if (webSocketServerHandshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            webSocketServerHandshaker.handshake(ctx.channel(), request);
        }

    }

    /**
     * 服务端向客户端响应消息
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, DefaultFullHttpResponse response) {
        if (response.status().code() != HttpStatus.OK.value()) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(byteBuf);
            byteBuf.release();
        }
        // 服务端向客户端发送数据
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(response);
        if (response.status().code() != HttpStatus.OK.value()) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }

    }

    /**
     * 客户端与服务端创建连接的时候调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        log.info("客户端与服务端连接开启...");
    }

    /**
     * 客户端与服务断开连接的时候调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        log.info("客户端与服务端连接关闭");
    }

    /**
     * 服务端接收客户端发送改过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 工程出现异常的时候调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
