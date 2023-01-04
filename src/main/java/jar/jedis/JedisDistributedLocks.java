package jar.jedis;

import l.demo.Demo;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Redis 实现分布式锁
 * <p>SETNX + Lua 脚本
 * <p><a href="https://www.cnblogs.com/moxiaotao/p/10829799.html">Redis 分布式锁的正确实现方式</a>
 *
 * @author ljh
 * @since 2021/5/11 1:47
 */
public class JedisDistributedLocks extends Demo {

    private static final String MONEY = "MONEY";
    private static final String MONEY_LOCK = "MONEY_LOCK";
    private static final String LOCK_SUCCESS = "OK";

    /**
     * 没有锁的时候的情况
     */
    private Runnable notLock() {
        return () -> {
            try (Jedis jedis = JedisUtils.getResource()) {
                cal(jedis);
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
            try (Jedis jedis = JedisUtils.getResource()) {
                calWithLock(lockInitial, jedis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private void cal(Jedis jedis) {
        int money = Integer.parseInt(jedis.get(MONEY));
        jedis.set(MONEY, String.valueOf(money - 100));
        p("计算" + Thread.currentThread().getName());
    }

    private void calWithLock(LockInitial lockInitial, Jedis jedis) {
        String lockValue = String.valueOf(lockInitial.getRandom().nextInt());
        if (lockInitial.tryLock(jedis, MONEY_LOCK, lockValue, DateUtils.MILLIS_PER_SECOND)) {
            cal(jedis);
            lockInitial.releaseLock(jedis, MONEY_LOCK, lockValue);
        } else {
            calWithLock(lockInitial, jedis);
        }
    }

    @Test
    public void test() throws InterruptedException {
        try (Jedis jedis = JedisUtils.getResource()) {
            jedis.set(MONEY, "1000");
        }
        // Runnable runnable = this.lock();
        Runnable runnable = this.notLock();
        for (int i = 1; i <= 10; i++) {
            Thread thread = new Thread(runnable, String.valueOf(i));
            thread.start();
        }
        TimeUnit.SECONDS.sleep(1);
        try (Jedis jedis = JedisUtils.getResource()) {
            p(Integer.parseInt(jedis.get(MONEY)));
        }
    }

    private interface Lock {
        boolean tryLock(Jedis jedis, String key, String val, Long ttl);

        boolean releaseLock(Jedis jedis, String key, String val);
    }

    static class LockInitial implements Lock {

        public static Random random = new Random();

        @Override
        public boolean tryLock(Jedis jedis, String key, String val, Long ttl) {
            SetParams setParams = new SetParams();
            setParams.nx();
            setParams.ex(ttl);
            String result = jedis.set(key, val, setParams);
            return LOCK_SUCCESS.equals(result);
        }

        @Override
        public boolean releaseLock(Jedis jedis, String key, String val) {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(val));
            return Objects.equals(val, result);
//            if (val.equals(jedis.get(key))) {
//                return jedis.del(key) > 0;
//            }
//            return false;
        }

        public Random getRandom() {
            return random;
        }
    }
}
