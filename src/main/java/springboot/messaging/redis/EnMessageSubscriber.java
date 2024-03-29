package springboot.messaging.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

/**
 * 第一种：订阅者继承 MessageListener（建议使用）
 *
 * @author ljh
 * @since 2021/9/1 21:30
 */
@Slf4j
@Component
public class EnMessageSubscriber implements Subscriber {

    @Override
    public String getTopic() {
        return RedisTopicEnum.EN_MESSAGE_TOPIC.getTopic();
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("EnMessageSubscriber: {}", message.toString());
    }
}
