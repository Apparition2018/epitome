package jar.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis
 *
 * @author Arsenal
 * created on 2021/4/30 22:49
 */
public class JedisDemo {

    @Test
    public void singleton() {
        // 1.新建 Jedis 对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.保存数据
        jedis.set("name", "ljh");
        // 3.获取数据
        String value = jedis.get("name");
        System.out.println("value = " + value);
        // 4.释放资源
        jedis.close();
    }

    @Test
    public void pool() {
        // 1.获得连接池的配置对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(30);
        poolConfig.setMaxIdle(10);
        // 2.新建连接池
        try (JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
             // 3.获取 Jedis 对象
             Jedis jedis = jedisPool.getResource()) {
            jedis.set("name", "ljh");
            String value = jedis.get("name");
            System.out.println("value = " + value);
        }
    }
}
