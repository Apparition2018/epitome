package jar.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * JedisUtils
 *
 * @author ljh
 * @since 2021/5/27 10:10
 */
public class JedisUtils {

    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxIdle(10);
        poolConfig.setMinIdle(2);
        poolConfig.setTimeBetweenEvictionRuns(Duration.of(3, ChronoUnit.SECONDS));
        poolConfig.setMinEvictableIdleTime(Duration.of(3, ChronoUnit.SECONDS));
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setBlockWhenExhausted(true);
        jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
    }

    public static Jedis getResource() {
        return jedisPool.getResource();
    }
}
