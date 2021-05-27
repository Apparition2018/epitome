package jar.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisUtils
 *
 * @author ljh
 * created on 2021/5/27 10:10
 */
public class JedisUtils {

    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(30);
        poolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
    }

    public static Jedis getResource() {
        return jedisPool.getResource();
    }
}
