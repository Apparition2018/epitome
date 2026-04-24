package jar.jedis;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import static l.demo.Demo.p;

/**
 * Redis 实现分布式锁
 * <p><a href="https://www.cnblogs.com/moxiaotao/p/10829799.html">Redis 分布式锁的正确实现方式</a>：SETNX + Lua 脚本
 * <p>或直接使用 Redisson
 *
 * @author ljh
 * @since 2021/5/11 1:47
 */
public class JedisDistributedLocks {

    private static final String MONEY = "MONEY";
    private static final String MONEY_LOCK = "MONEY_LOCK";
    private static final String LOCK_SUCCESS = "OK";
    private static final long RELEASE_SUCCESS = 1L;
    private static final int LOCK_EXPIRE_SECONDS = 3;

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testDistributedLocks(boolean useLock) throws InterruptedException {
        try (Jedis jedis = JedisUtils.getResource()) {
            jedis.set(MONEY, "1000");
        }
        CountDownLatch latch = new CountDownLatch(10);
        Runnable runnable = useLock ? this.deductWithLock(latch) : this.deductWithoutLock(latch);
        for (int i = 1; i <= 10; i++) {
            new Thread(runnable, String.valueOf(i)).start();
        }
        latch.await();
        try (Jedis jedis = JedisUtils.getResource()) {
            p(Integer.parseInt(jedis.get(MONEY)));
        }
    }

    /**
     * 没有锁的时候的情况
     */
    private Runnable deductWithoutLock(CountDownLatch latch) {
        return () -> {
            try (Jedis jedis = JedisUtils.getResource()) {
                this.deduct(jedis);
            } finally {
                latch.countDown();
            }
        };
    }

    /**
     * 有锁的时候的情况
     */
    private Runnable deductWithLock(CountDownLatch latch) {
        return () -> {
            String lockValue = UUID.randomUUID().toString();
            boolean locked = false;
            try (Jedis jedis = JedisUtils.getResource()) {
                DistributedLockManager lockManager = new DistributedLockManager();
                while (!locked) {
                    locked = lockManager.tryLock(jedis, MONEY_LOCK, lockValue, LOCK_EXPIRE_SECONDS);
                    if (!locked) {
                        Thread.sleep(50);
                    }
                }
                try {
                    deduct(jedis);
                } finally {
                    lockManager.releaseLock(jedis, MONEY_LOCK, lockValue);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } finally {
                latch.countDown();
            }
        };
    }

    private void deduct(Jedis jedis) {
        jedis.decrBy(MONEY, 100);
        p("计算" + Thread.currentThread().getName());
    }

    private static class DistributedLockManager {

        public boolean tryLock(Jedis jedis, String key, String val, int ttlSeconds) {
            SetParams setParams = new SetParams();
            setParams.nx();
            setParams.ex(ttlSeconds);
            String result = jedis.set(key, val, setParams);
            return LOCK_SUCCESS.equals(result);
        }

        public boolean releaseLock(Jedis jedis, String key, String val) {
            // 获取当前锁(key)对应的值，判断是否等于传入的标识(val)
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                "then return redis.call('del', KEYS[1]) " +
                "else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(val));
            return Objects.equals(RELEASE_SUCCESS, result);
        }

        // 锁续期
        public boolean renewLock(Jedis jedis, String key, String val, int ttlSeconds) {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                "then return redis.call('expire', KEYS[1], ARGV[2]) " +
                "else return 0 end";
            Object result = jedis.eval(
                script, Collections.singletonList(key), Arrays.asList(val, String.valueOf(ttlSeconds)));
            return Objects.equals(RELEASE_SUCCESS, result);
        }
    }
}
