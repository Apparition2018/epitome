package jar.jedis.usecase;

import jar.jedis.JedisUtils;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

/**
 * Redis List 类型用例
 * <p>
 * redis 存储list_Redis经典案例场景：https://blog.csdn.net/weixin_39530437/article/details/111616754
 *
 * @author ljh
 * created on 2021/5/27 14:41
 */
public class ListRedisUseCase {

    /**
     * 最近浏览商品
     */
    public static class RecentlyViewedGoods {

        private static final String RECENTLY_VIEWED_GOODS_KEY = "recently_viewed:goods:%s";

        @Test
        public void testRecentlyViewedGoods() {
            Jedis jedis = JedisUtils.getResource();
            viewGoods(jedis, 1L, 1L);
            
        }

        private void viewGoods(Jedis jedis, Long userId, Long goodsId) {
            String key = String.format(RECENTLY_VIEWED_GOODS_KEY, userId);
            // 先删除该商品之前的浏览记录
            jedis.lrem(key, 1, goodsId.toString());
            jedis.lpush(key, goodsId.toString());
            // 保留 20 条记录
            jedis.ltrim(key, 0, 19);
            jedis.expire(key, 60L * 60 * 24 * 15);
        }
    }
}
