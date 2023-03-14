package jar.jedis.data.type;

import jar.jedis.JedisUtils;
import l.demo.Demo;
import lombok.Getter;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * <a href="https://redis.io/docs/data-types/sorted-sets/">Redis Sorted Set</a>
 * <pre>
 * 1 排行榜；限流
 * 2 大多数集合操作 O(log(n))；ZRANEG O(long(n) + m)
 * 3 用于索引其它数据；当需要索引和查询数据，可考虑使用 RedisSearch 和 RedisJSON
 * </pre>
 *
 * @author ljh
 * @since 2021/5/28 16:30
 */
public class RedisSortedSet extends Demo {

    /**
     * 热门书籍
     */
    private static class HotBooks {
        private static final String HOT_BOOKS_KEY = "hot_articles";
        private static final String HOT_BOOKS_20210501 = HOT_BOOKS_KEY.concat(":20210501");
        private static final String HOT_BOOKS_20210502 = HOT_BOOKS_KEY.concat(":20210502");
        private static final String HOT_BOOKS_20210503 = HOT_BOOKS_KEY.concat(":20210503");

        public static void main(String[] args) {
            Jedis jedis = JedisUtils.getResource();
            Map<String, Double> bookMap = new HashMap<>();
            bookMap.put(BookEnum.SG.getName(), 0D);
            bookMap.put(BookEnum.XY.getName(), 0D);
            bookMap.put(BookEnum.HL.getName(), 0D);
            bookMap.put(BookEnum.SH.getName(), 0D);
            jedis.zadd(HOT_BOOKS_20210501, bookMap);
            jedis.zadd(HOT_BOOKS_20210502, bookMap);
            jedis.zadd(HOT_BOOKS_20210503, bookMap);
            IntStream.rangeClosed(1, THOUSAND).forEach(i -> readBook(jedis, HOT_BOOKS_20210501));
            IntStream.rangeClosed(1, THOUSAND).forEach(i -> readBook(jedis, HOT_BOOKS_20210502));
            IntStream.rangeClosed(1, THOUSAND).forEach(i -> readBook(jedis, HOT_BOOKS_20210503));
            // 20210521 四大名著排名：[[红楼梦,271.0], [水浒传,257.0], [三国演义,240.0], [西游记,232.0]]
            p("20210521 四大名著排名：" + jedis.zrevrangeWithScores(HOT_BOOKS_20210501, 0, 3));
            // 统计总排名
            jedis.zunionstore(HOT_BOOKS_KEY, HOT_BOOKS_20210501, HOT_BOOKS_20210502, HOT_BOOKS_20210503);
            // 四大名著总排名：[[红楼梦,761.0], [三国演义,755.0], [西游记,751.0], [水浒传,733.0]]
            p("四大名著总排名：" + jedis.zrevrangeWithScores(HOT_BOOKS_KEY, 0, 3));
        }

        private static void readBook(Jedis jedis, String key) {
            jedis.zincrby(key, 1, BookEnum.getRandomBookName());
        }
    }

    @Getter
    private enum BookEnum {
        SG("三国演义", 1),
        XY("西游记", 2),
        HL("红楼梦", 3),
        SH("水浒传", 4);

        private final String name;
        private final int code;

        BookEnum(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public static String getRandomBookName() {
            return values()[new Random().nextInt(values().length)].getName();
        }
    }
}
