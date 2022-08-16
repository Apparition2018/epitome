package jar.jedis.data.type;

import jar.jedis.JedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XReadParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis Stream
 * 1.事件溯源（如跟踪用户操作、点击等）；传感器监控（如现场设备的读数）；通知（如将每个用户的通知记录存储在单独的 Stream 中）
 * 2.XADD O(1)；访问任何单个条目 O(n)
 * https://redis.io/docs/data-types/streams/
 * https://redis.io/docs/data-types/streams-tutorial/
 * Redis消息队列发展历程：https://mp.weixin.qq.com/s/gCUT5TcCQRAxYkTJfTRjJw
 *
 * @author ljh
 * created on 2022/8/12 2:57
 */
public class RedisStream {

    /**
     * 消息队列
     * 使用前提：业务足够简单，数据丢失不敏感，消息挤压率小
     */
    static class MessageQueue {
        private static final String MESSAGE_KEY = "messaging";
        private static final String GROUP_A = "group-a";
        private static final String GROUP_B = "group-b";

        public static void main(String[] args) {
            Jedis jedis = JedisUtils.getResource();
            // 销毁消费者组
            jedis.xgroupDestroy(MESSAGE_KEY, GROUP_A);
            jedis.xgroupDestroy(MESSAGE_KEY, GROUP_B);
            // 创建消费者组，已存在会抛出异常
            jedis.xgroupCreate(MESSAGE_KEY, GROUP_A, StreamEntryID.LAST_ENTRY, true);
            jedis.xgroupCreate(MESSAGE_KEY, GROUP_B, StreamEntryID.LAST_ENTRY, true);

            listening(jedis, MESSAGE_KEY);
        }

        private static void listening(Jedis jedis, String key) {
            Map<String, StreamEntryID> entryIdMap = new HashMap<>();
            entryIdMap.put(key, StreamEntryID.LAST_ENTRY);
            for (int i = 0; i < 2; i++) {
                new Thread(() -> {
                    while (true) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(500);
                            // 读取
                            List<Map.Entry<String, List<StreamEntry>>> entryList = jedis.xread(XReadParams.xReadParams().block(0), entryIdMap);
                            System.out.println(entryList.get(0).getValue().get(0).getFields());
                            // 删除
                            jedis.xdel(key, entryList.get(0).getValue().get(0).getID());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        }
    }
}
