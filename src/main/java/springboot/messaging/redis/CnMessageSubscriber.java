package springboot.messaging.redis;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第二种：自定义订阅者（自定义消息监听器）
 *
 * @author ljh
 * created on 2021/9/2 2:48
 */
@Slf4j
public class CnMessageSubscriber {

    private final AtomicInteger counter = new AtomicInteger();

    public void onMessage(String message) {
        log.info("CnMessageSubscribe: {}", message);
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }
}
