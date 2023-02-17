package jar.jedis.data.type;

import jar.jedis.JedisUtils;
import l.demo.Demo;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.stream.IntStream;

/**
 * <a href="https://redis.io/docs/data-types/hyperloglogs/">Redis HyperLogLog</a>
 * <p>恒定占用 12 KB，标准误差 0.81%，可估计 2^64 个基数，读取时间恒定，合并 O(n)
 *
 * @author ljh
 * @since 2022/8/12 0:12
 */
public class RedisHyperLogLog extends Demo {

    /**
     * 独立访客：一天访问网站的访客数
     */
    private static class UniqueVisitor {
        private static final String UV_KEY = "uv";

        public static void main(String[] args) {
            Jedis jedis = JedisUtils.getResource();
            jedis.del(UV_KEY);
            Pipeline pipeline = jedis.pipelined();
            // 模拟100万个访客
            IntStream.rangeClosed(1, MILLION).forEach(i -> pipeline.pfadd(UV_KEY, "user" + i));
            pipeline.sync();
            long uv = jedis.pfcount(UV_KEY);
            // 访客数：1001788
            p(String.format("访客数：%s", uv));
            // 误差率：-0.001788
            p(String.format("误差率：%s", (MILLION - uv) * 1F / MILLION));
        }
    }
}
