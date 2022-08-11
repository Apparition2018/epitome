package jar.jedis.data.type;

import jar.jedis.JedisUtils;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

/**
 * Redis Bitmap
 * 1.二值状态统计；对象权限 ?
 * 2.SETBIT 和 GETBIT O(1)；BITTOP O(n)
 * https://redis.io/docs/data-types/bitmaps/
 * https://segmentfault.com/a/1190000008188655
 *
 * @author ljh
 * created on 2021/5/27 9:51
 */
public class RedisBitmap {

    /**
     * 活跃用户统计
     */
    static class ActiveUserStats {
        private static final String USER_LOGIN_KEY = "user:login";
        private static final String USER_LOGIN_20210501 = USER_LOGIN_KEY.concat(":20210501");
        private static final String USER_LOGIN_20210502 = USER_LOGIN_KEY.concat(":20210502");
        private static final String USER_LOGIN_20210503 = USER_LOGIN_KEY.concat(":20210503");

        public static void main(String[] args) {
            Jedis jedis = JedisUtils.getResource();
            // 20210501，id 为 1、2、3 的用户登录了
            jedis.setbit(USER_LOGIN_20210501, 1, true);
            jedis.setbit(USER_LOGIN_20210501, 2, true);
            jedis.setbit(USER_LOGIN_20210501, 3, true);
            // 20210501，id 为 1、2、4 的用户登录了
            jedis.setbit(USER_LOGIN_20210502, 1, true);
            jedis.setbit(USER_LOGIN_20210502, 2, true);
            jedis.setbit(USER_LOGIN_20210502, 4, true);
            // 20210501，id 为 1、3、5 的用户登录了
            jedis.setbit(USER_LOGIN_20210503, 1, true);
            jedis.setbit(USER_LOGIN_20210503, 3, true);
            jedis.setbit(USER_LOGIN_20210503, 5, true);
            // 20210501 活跃用户数：3
            System.out.println("20210501 活跃用户数：" + jedis.bitcount(USER_LOGIN_20210501));
            // 总活跃用户数：5
            jedis.bitop(BitOP.OR, USER_LOGIN_KEY, USER_LOGIN_20210501, USER_LOGIN_20210502, USER_LOGIN_20210503);
            System.out.println("总活跃用户数：" + jedis.bitcount(USER_LOGIN_KEY));
        }
    }

    /**
     * 在线用户统计
     */
    static class OnlineUserStats {
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
