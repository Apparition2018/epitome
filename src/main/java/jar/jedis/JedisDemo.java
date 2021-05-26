package jar.jedis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    private static JedisPool jedisPool;
    
    @BeforeEach
    public void initJedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(30);
        poolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
    }

    /**
     * https://www.cnblogs.com/xinhuaxuan/p/9256763.html
     */
    @Test
    public void hash() {
        Jedis jedis = jedisPool.getResource();
        // hset
        jedis.hset("Hash:1", "id", "1");
        jedis.hset("Hash:1", "name", "张三");
        // hmset
        Map<String, String> map = new HashMap<>();
        map.put("id", "2");
        map.put("name", "李四");
        jedis.hmset("Hash:2", map);

        // hget
        System.out.println("Hash:1 = [" + jedis.hget("Hash:1", "id") + ", " + jedis.hget("Hash:1", "name") + "]");
        // hmget
        System.out.println("Hash:2 = " + jedis.hmget("Hash:2", "id", "name"));
    }
}
