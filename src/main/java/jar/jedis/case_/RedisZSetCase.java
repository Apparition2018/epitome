package jar.jedis.case_;

import jar.jedis.JedisUtils;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Redis ZSet 类型用例
 *
 * @author ljh
 * created on 2021/5/28 16:30
 */
public class RedisZSetCase {

    /**
     * 热门书籍
     */
    public static class HotBooks {

        private static final String HOT_BOOKS_KEY = "hot_articles";
        private static final String HOT_BOOKS_20210501 = HOT_BOOKS_KEY.concat(":20210501");
        private static final String HOT_BOOKS_20210502 = HOT_BOOKS_KEY.concat(":20210502");
        private static final String HOT_BOOKS_20210503 = HOT_BOOKS_KEY.concat(":20210503");

        @Test
        public void testHotBooks() {
            Jedis jedis = JedisUtils.getResource();
            Map<String, Double> bookMap = new HashMap<>();
            bookMap.put(BookEnum.SG.getName(), 0D);
            bookMap.put(BookEnum.XY.getName(), 0D);
            bookMap.put(BookEnum.HL.getName(), 0D);
            bookMap.put(BookEnum.SH.getName(), 0D);
            jedis.zadd(HOT_BOOKS_20210501, bookMap);
            jedis.zadd(HOT_BOOKS_20210502, bookMap);
            jedis.zadd(HOT_BOOKS_20210503, bookMap);
            IntStream.rangeClosed(1, 10000).forEach(i -> readBook(jedis, HOT_BOOKS_20210501));
            IntStream.rangeClosed(1, 10000).forEach(i -> readBook(jedis, HOT_BOOKS_20210502));
            IntStream.rangeClosed(1, 10000).forEach(i -> readBook(jedis, HOT_BOOKS_20210503));
            // 20210521 四大名著排名：[[水浒传,2542.0], [红楼梦,2516.0], [西游记,2491.0], [三国演义,2451.0]]
            System.out.println("20210521 四大名著排名：" + jedis.zrevrangeWithScores(HOT_BOOKS_20210501, 0, 3));
            // 统计总排名
            jedis.zunionstore(HOT_BOOKS_KEY, HOT_BOOKS_20210501, HOT_BOOKS_20210502, HOT_BOOKS_20210503);
            // 四大名著总排名：[[水浒传,7589.0], [西游记,7512.0], [三国演义,7489.0], [红楼梦,7410.0]]
            System.out.println("四大名著总排名：" + jedis.zrevrangeWithScores(HOT_BOOKS_KEY, 0, 3));
        }

        private void readBook(Jedis jedis, String key) {
            jedis.zincrby(key, 1, BookEnum.getRandomBookName());
        }
    }

    @Getter
    private enum BookEnum {

        SG("三国演义", 1),
        XY("西游记", 2),
        HL("红楼梦", 3),
        SH("水浒传", 4);

        private String name;
        private int code;

        BookEnum(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public static String getRandomBookName() {
            return values()[new Random().nextInt(values().length)].getName();
        }
    }
}
