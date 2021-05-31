package jar.jedis.usecase;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 * <p>
 * Redis分布式锁的正确实现方式：https://www.cnblogs.com/moxiaotao/p/10829799.html
 *
 * @author Arsenal
 * created on 2021/5/11 1:47
 */
public class RedisDistributedLocks {

    public static final String MONEY = "MONEY";
    public static final String MONEY_LOCK = "MONEY_LOCK";
    public static final JedisPool JEDIS_POOL;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(5);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(false);
        config.setBlockWhenExhausted(true);
        JEDIS_POOL = new JedisPool(config, "127.0.0.1", 6379);
    }

    /**
     * 没有锁的时候的情况
     */
    private Runnable notLock() {
        return () -> {
            try (Jedis jedis = JEDIS_POOL.getResource()) {
                calImpl(jedis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * 有锁的时候的情况
     */
    private Runnable lock() {
        LockInitial lockInitial = new LockInitial();
        return () -> {
            try (Jedis jedis = JEDIS_POOL.getResource()) {
                cal(lockInitial, jedis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private void cal(LockInitial lockInitial, Jedis jedis) {
        String lockValue = String.valueOf(lockInitial.getRandom().nextInt());
        if (lockInitial.tryLock(jedis, MONEY_LOCK, lockValue, 1000L)) {
            calImpl(jedis);
            lockInitial.releaseLock(jedis, MONEY_LOCK, lockValue);
        } else {
            cal(lockInitial, jedis);
        }
    }

    private void calImpl(Jedis jedis) {
        int money = Integer.parseInt(jedis.get(MONEY));
        jedis.set(MONEY, String.valueOf(money - 100));
        System.out.println("计算" + Thread.currentThread().getName());
    }

    @Test
    public void test() throws InterruptedException {
        try (Jedis jedis = JEDIS_POOL.getResource()) {
            jedis.set(MONEY, "1000");
        }
        Runnable runnable = lock();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable, String.valueOf(i));
            thread.start();
        }
        TimeUnit.SECONDS.sleep(3);
        try (Jedis jedis = JEDIS_POOL.getResource()) {
            System.out.println(Integer.parseInt(jedis.get(MONEY)));
        }
    }

    private interface Lock {
        boolean tryLock(Jedis jedis, String key, String val, Long ttl);

        boolean releaseLock(Jedis jedis, String key, String val);
    }

    private static class LockInitial implements Lock {

        public static Random random = new Random();

        @Override
        public boolean tryLock(Jedis jedis, String key, String val, Long ttl) {
            SetParams setParams = new SetParams();
            setParams.ex(ttl);
            setParams.nx();
            String result = jedis.set(key, val, setParams);
            return "OK".equals(result);
        }

        @Override
        public boolean releaseLock(Jedis jedis, String key, String val) {
            if (val.equals(jedis.get(key))) {
                return jedis.del(key) > 0;
            }
            return false;
        }

        public Random getRandom() {
            return random;
        }
    }
}
