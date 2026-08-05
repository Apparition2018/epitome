package jar.jedis.data.type;

import jar.jedis.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.stream.IntStream;

/**
 * <a href="https://redis.io/docs/data-types/lists/">Redis List</a>
 * <pre>
 * 1 常用于实现 stack、queue，为后台工作系统构建队列管理
 * 2 最大长度 2^32 - 1
 * 3 访问头部和尾部 O(1)；操作命令 O(n)：如 LINDEX、LINSERT、LSET
 * 4 需要存储和处理不确定的一些列时间时，可考虑使用 Stream
 * </pre>
 *
 * @author ljh
 * @since 2021/5/27 14:41
 */
public class RedisList {

    /**
     * 最近浏览商品
     */
    private static class RecentlyViewedGoods {
        private static final String RECENTLY_VIEWED_GOODS_KEY = "recently_viewed:goods:%s";
        private static Jedis jedis = jedis = JedisUtils.getResource();

        public static void main(String[] args) {
            String userKey = String.format(RECENTLY_VIEWED_GOODS_KEY, "Jack");
            // 模拟之前已浏览了 id 为 1~10 的商品
            IntStream.rangeClosed(1, 10).forEach(i -> jedis.lpush(userKey, String.valueOf(i)));
            // 浏览商品 4,8,5
            viewGoods(userKey, 4);
            viewGoods(userKey, 8);
            viewGoods(userKey, 5);
            // Jack 最近浏览商品 id 为：[5, 8, 4, 10, 9, 7, 6, 3, 2, 1]
            System.out.println("Jack 最近浏览商品 id 为：" + getRecentlyViewedGoods(userKey));
        }

        /**
         * 浏览商品
         *
         * @param key     Redis Key
         * @param goodsId 商品ID
         */
        private static void viewGoods(String key, Integer goodsId) {
            // 先删除该商品之前的浏览记录
            jedis.lrem(key, 1, goodsId.toString());
            jedis.lpush(key, goodsId.toString());
            // 保留 20 条记录
            jedis.ltrim(key, 0, 19);
            jedis.expire(key, 60L * 60 * 24 * 15);
        }

        /**
         * 获取最近浏览商品
         *
         * @param key Redis Key
         * @return 最近浏览商品列表
         */
        private static List<String> getRecentlyViewedGoods(String key) {
            return jedis.lrange(key, 0, jedis.llen(key));
        }
    }
}
