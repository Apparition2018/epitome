package jar.jedis.data.type;

import jar.jedis.JedisUtils;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * Redis Stream
 * 1.事件溯源（如跟踪用户操作、点击等）；传感器监控（如现场设备的读数）；通知（如将每个用户的通知记录存储在单独的 Stream 中）
 * 2.XADD O(1)；访问任何单个条目 O(n)
 * https://redis.io/docs/data-types/streams/
 * https://redis.io/docs/data-types/streams-tutorial/
 *
 * @author 梁杰辉
 * created on 2022/8/12 2:57
 */
public class RedisStream {

    /**
     * 消息队列
     * 使用前提：业务足够简单，数据丢失不敏感，消息挤压率小
     */
    static class MessageQueue {

        @Test
        public void testMessageQueue() {
            Jedis jedis = JedisUtils.getResource();
        }
    }

}
