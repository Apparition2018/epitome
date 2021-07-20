package jar.jedis.case_;

import jar.jedis.JedisUtils;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Redis List 类型用例
 *
 * @author ljh
 * created on 2021/5/27 14:41
 */
public class RedisListCase {

    /**
     * 最近浏览商品
     */
    static class RecentlyViewedGoods {

        private static final String RECENTLY_VIEWED_GOODS_KEY = "recently_viewed:goods:%s";

        @Test
        public void testRecentlyViewedGoods() {
            Jedis jedis = JedisUtils.getResource();
            String userKey = String.format(RECENTLY_VIEWED_GOODS_KEY, "Jack");
            // 模拟之前已浏览了id为1~10的商品
            IntStream.rangeClosed(1, 10).forEach(i -> jedis.lpush(userKey, String.valueOf(i)));
            // 浏览商品8
            viewGoods(jedis, userKey, 8);
            // Jack 最近浏览商品 id 为：[8, 10, 9, 7, 6, 5, 4, 3, 2, 1]
            System.out.println("Jack 最近浏览商品 id 为：" + getRecentlyViewedGoods(jedis, userKey));
        }

        /**
         * 用户浏览商品 
         * @param key       Redis Key
         * @param goodsId   商品ID
         */
        private void viewGoods(Jedis jedis, String key, Integer goodsId) {
            // 先删除该商品之前的浏览记录
            jedis.lrem(key, 1, goodsId.toString());
            jedis.lpush(key, goodsId.toString());
            // 保留 20 条记录
            jedis.ltrim(key, 0, 19);
            jedis.expire(key, 60L * 60 * 24 * 15);
        }

        /**
         * 获取最近浏览商品
         * @param key       Redis Key
         */
        private List<String> getRecentlyViewedGoods(Jedis jedis, String key) {
            return jedis.lrange(key, 0, jedis.llen(key));
        }
    }
}
