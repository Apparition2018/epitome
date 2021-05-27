package jar.jedis.usecase;

import jar.jedis.JedisUtils;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

/**
 * Redis Bit 类型用例
 *
 * @author ljh
 * created on 2021/5/27 9:51
 */
public class BitRedisUseCase {

    /**
     * 活跃用户统计
     * 使用Redis bitmap统计活跃用户：http://www.321332211.com/thread?topicId=221
     */
    public static class ActiveUserStats {

        private static final String USER_LOGIN_KEY = "user:login";
        private static final String DAY_20210501 = USER_LOGIN_KEY.concat(":20210501");
        private static final String DAY_20210502 = USER_LOGIN_KEY.concat(":20210502");
        private static final String DAY_20210503 = USER_LOGIN_KEY.concat(":20210503");

        public static void main(String[] args) {
            Jedis jedis = JedisUtils.getResource();
            // 20210501，id 为 1、2、3 的用户登录了
            jedis.setbit(DAY_20210501, 1, true);
            jedis.setbit(DAY_20210501, 2, true);
            jedis.setbit(DAY_20210501, 3, true);
            // 20210501，id 为 1、2、4 的用户登录了
            jedis.setbit(DAY_20210502, 1, true);
            jedis.setbit(DAY_20210502, 2, true);
            jedis.setbit(DAY_20210502, 4, true);
            // 20210501，id 为 1、3、5 的用户登录了
            jedis.setbit(DAY_20210503, 1, true);
            jedis.setbit(DAY_20210503, 3, true);
            jedis.setbit(DAY_20210503, 5, true);
            // 20210501 活跃用户数：3
            System.out.println("20210501 活跃用户数：" + jedis.bitcount(DAY_20210501));
            // 总活跃用户数：5
            jedis.bitop(BitOP.OR, USER_LOGIN_KEY, DAY_20210501, DAY_20210502, DAY_20210503);
            System.out.println("总活跃用户数：" + jedis.bitcount(USER_LOGIN_KEY));
        }
    }

    /**
     * 在线用户统计
     */
    public static class OnlineUserStats {
        
        private static final String USER_ONLINE_KEY = "user:online";

        public static void main(String[] args) {
            Jedis jedis = JedisUtils.getResource();
            // 设置用户在线状态
            jedis.setbit(USER_ONLINE_KEY, 1, true);
            jedis.setbit(USER_ONLINE_KEY, 2, true);
            jedis.setbit(USER_ONLINE_KEY, 3, false);
            jedis.setbit(USER_ONLINE_KEY, 4, true);
            jedis.setbit(USER_ONLINE_KEY, 5, false);
            // 在线用户数：3
            System.out.println("在线用户数：" + jedis.bitcount(USER_ONLINE_KEY));
        }
    }
}
