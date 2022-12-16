package jar.google.guava.util.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * RateLimiterDemo
 * <p>
 * Guava RateLimiter 限流原理解析：https://zhuanlan.zhihu.com/p/60979444
 * Guava RateLimiter 限流器入门到精通：https://blog.51cto.com/alex4dream/3944594
 *
 * @author ljh
 * @since 2022/6/30 8:25
 */
public class RateLimiterDemo {

    /**
     * 平滑突发限流
     */
    @Test
    public void testSmoothBursty() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(5);
        for (int i = 0; i < 5; i++) {
            // double           acquire()           从 RateLimiter 获得一个许可，阻断直到请求可以被批准。返回睡眠时间
            // boolean          tryAcquire()        从 RateLimiter 立即获得一个许可。返回是否成功
            System.out.println("get 1 tokens: " + rateLimiter.acquire());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("get 1 tokens: " + rateLimiter.acquire());
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    /**
     * 平滑预热限流
     */
    @Test
    public void testSmoothWarmingUp() {
        RateLimiter rateLimiter = RateLimiter.create(2, 3, TimeUnit.SECONDS);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println("get 1 tokens: " + rateLimiter.acquire());
                System.out.println("end");
            }
        }
    }
}
