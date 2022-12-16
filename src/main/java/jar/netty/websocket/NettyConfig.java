package jar.netty.websocket;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * NettyConfig
 *
 * @author ljh
 * @since 2021/3/5 1:14
 */
public class NettyConfig {

    /**
     * 存储每一个客户端接入进来的 channel 对象，并在提供各种批量操作
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
