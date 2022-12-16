package springboot.messaging.redis;

import lombok.extern.slf4j.Slf4j;

/**
 * 第二种：自定义订阅者（自定义消息监听器）
 *
 * @author ljh
 * @since 2021/9/2 2:48
 */
@Slf4j
public class CnMessageSubscriber {

    public void onMessage(String message) {
        log.info("CnMessageSubscribe: {}", message);
    }
}
