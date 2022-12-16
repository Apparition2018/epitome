package jar.jedis;

import l.demo.Demo;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * Jedis
 *
 * @author ljh
 * @since 2021/4/30 22:49
 */
public class JedisDemo extends Demo {

    @Test
    public void singleton() {
        // 1.新建 Jedis 对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 2.保存数据
        jedis.set("name", "ljh");
        // 3.获取数据
        String value = jedis.get("name");
        p("value = " + value);
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
            p("value = " + value);
        }
    }

    @Test
    public void hash() {
        Jedis jedis = JedisUtils.getResource();
        // hset
        jedis.hset("Hash:1", "id", "1");
        jedis.hset("Hash:1", "name", "张三");
        // hmset
        Map<String, String> map = new HashMap<>();
        map.put("id", "2");
        map.put("name", "李四");
        jedis.hmset("Hash:2", map);

        // hget
        p("Hash:1 = [" + jedis.hget("Hash:1", "id") + ", " + jedis.hget("Hash:1", "name") + "]");
        // hmget
        p("Hash:2 = " + jedis.hmget("Hash:2", "id", "name"));

        jedis.close();
    }

    /**
     * pipeline 无原子性
     * multi    有原子性
     */
    @Test
    public void pipeline() {
        Jedis jedis = JedisUtils.getResource();

        final String TASK1 = "normal";
        stopWatch.start(TASK1);
        for (int i = 1; i <= THOUSAND; i++) {
            jedis.set(TASK1 + i, "i");
            jedis.del((TASK1 + i));
        }
        stopWatch.stop();

        final String TASK2 = "multi";
        stopWatch.start(TASK2);
        String[] setArr = new String[THOUSAND * 2];
        String[] delArr = new String[THOUSAND];
        for (int i = 1; i <= THOUSAND; i++) {
            setArr[i * 2 - 2] = TASK2 + i;
            setArr[i * 2 - 1] = String.valueOf(i);
            delArr[i - 1] = TASK2 + i;
        }
        jedis.mset(setArr);
        jedis.del(delArr);
        stopWatch.stop();

        final String TASK3 = "pipeline";
        stopWatch.start(TASK3);
        Pipeline pipeline = jedis.pipelined();
        for (int i = 1; i <= THOUSAND; i++) {
            pipeline.set(TASK3 + i, String.valueOf(i));
            pipeline.del((TASK3 + i));
        }
        pipeline.syncAndReturnAll();
        stopWatch.stop();

        p(stopWatch.prettyPrint());
        // ---------------------------------------------
        // ns         %     Task name
        // ---------------------------------------------
        // 433078700  097%  normal
        // 002830100  001%  multi
        // 012681600  003%  pipeline
    }
}
