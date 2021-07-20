package jar.jedis.case_;

import jar.jedis.JedisUtils;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Redis Set 类型用例
 *
 * @author ljh
 * created on 2021/5/28 14:23
 */
public class RedisSetCase {

    /**
     * 关注的人
     */
    static class Following {
        
        private static final String FOLLOWING_KEY = "following";
        private static final String JACK_FOLLOWING_KEY = FOLLOWING_KEY.concat(":Jack");
        private static final String MARY_FOLLOWING_KEY = FOLLOWING_KEY.concat(":Mary");
        private static final String ROSE_FOLLOWING_KEY = FOLLOWING_KEY.concat(":Rose");
        
        @Test
        public void testFollowing() {
            Jedis jedis = JedisUtils.getResource();
            // 设置关注的人
            jedis.sadd(JACK_FOLLOWING_KEY, "Mary", "Rose", "King", "Lucy");
            jedis.sadd(MARY_FOLLOWING_KEY, "Jack", "Rose", "King", "Tony");
            jedis.sadd(ROSE_FOLLOWING_KEY, "Andy", "King", "Tony");
            // Jack 和 Mary 共同关注的人：[King, Rose]
            System.out.println("Jack 和 Mary 共同关注的人：" + jedis.sinter(JACK_FOLLOWING_KEY, MARY_FOLLOWING_KEY));
            // Jack 可能认识的人：[Tony]
            Set<String> mayKnowPeople = jedis.sdiff(MARY_FOLLOWING_KEY, JACK_FOLLOWING_KEY);
            mayKnowPeople.retainAll(jedis.sdiff(ROSE_FOLLOWING_KEY, JACK_FOLLOWING_KEY));
            System.out.println("Jack 可能认识的人：" + mayKnowPeople);
        }
    }
}
