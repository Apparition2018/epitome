package jar.jedis.data.type;

/**
 * Redis Stream
 * <pre>
 * 1 事件溯源（如跟踪用户操作、点击等）；传感器监控（如现场设备的读数）；通知（如将每个用户的通知记录存储在单独的 Stream 中）
 * 2 XADD O(1)；访问任何单个条目 O(n)
 * </pre>
 *
 * @author ljh
 * @see <a href="https://redis.io/docs/data-types/streams/">Redis Streams</a>
 * @see <a href="https://redis.io/docs/data-types/streams-tutorial/">Redis Streams tutorial</a>
 * @see <a href="https://mp.weixin.qq.com/s/gCUT5TcCQRAxYkTJfTRjJw">Redis 消息队列发展历程</a>
 * @since 2022/8/12 2:57
 */
public class RedisStream {

    /**
     * 消息队列
     * <p>使用前提：业务足够简单，数据丢失不敏感，消息挤压率小
     */
    private static class MessageQueue {
    }
}
