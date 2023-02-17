package jar.jedis.data.type;

import jar.jedis.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * <a href="https://redis.io/docs/data-types/sets/">Redis Set</a>
 * <pre>
 * 1 常用于唯一数据，集合操作
 * 2 最大长度 2^32 - 1
 * 3 大多数集合操作 O(1)；SMEMBERS O(n)，可考虑使用 SCAN 代替
 * 4 当关注内存使用量和不需要完美的精度，可考虑使用 Bloom 过滤器和 Cuckoo 过滤器
 * 5 用作索引；当需要索引和查询数据，可考虑使用 RedisSearch 和 RedisJSON
 * </pre>
 *
 * @author ljh
 * @since 2021/5/28 14:23
 */
public class RedisSet {

    /**
     * 关注的人
     */
    private static class Following {

        private static final String FOLLOWING_KEY = "following";
        private static final String JACK_FOLLOWING_KEY = FOLLOWING_KEY.concat(":Jack");
        private static final String MARY_FOLLOWING_KEY = FOLLOWING_KEY.concat(":Mary");
        private static final String ROSE_FOLLOWING_KEY = FOLLOWING_KEY.concat(":Rose");

        public static void main(String[] args) {
            Jedis jedis = JedisUtils.getResource();
            // 设置关注的人
            jedis.sadd(JACK_FOLLOWING_KEY, "Mary", "Rose", "King", "Lucy");
            jedis.sadd(MARY_FOLLOWING_KEY, "Jack", "Rose", "King", "Tony");
            jedis.sadd(ROSE_FOLLOWING_KEY, "Andy", "King", "Tony");
            // Jack 关注了4个人
            System.out.printf("Jack 关注了%s个人%n", jedis.scard(JACK_FOLLOWING_KEY));
            // Jack 是否关注了 Tony：false
            System.out.println("Jack 是否关注了 Tony：" + (jedis.sismember(JACK_FOLLOWING_KEY, "Tony") ? "true" : "false"));
            // Jack 和 Mary 共同关注的人：[King, Rose]
            System.out.println("Jack 和 Mary 共同关注的人：" + jedis.sinter(JACK_FOLLOWING_KEY, MARY_FOLLOWING_KEY));
            // Jack 可能认识的人：[Tony]
            Set<String> mayKnowPeople = jedis.sdiff(MARY_FOLLOWING_KEY, JACK_FOLLOWING_KEY);
            mayKnowPeople.retainAll(jedis.sdiff(ROSE_FOLLOWING_KEY, JACK_FOLLOWING_KEY));
            System.out.println("Jack 可能认识的人：" + mayKnowPeople);
        }
    }
}
