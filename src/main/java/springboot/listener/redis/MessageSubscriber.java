package springboot.listener.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

/**
 * 消息订阅者
 *
 * @author ljh
 * created on 2021/9/1 21:30
 */
@Slf4j
@Component
public class MessageSubscriber implements Subscriber {

    @Override
    public String getTopic() {
        return RedisTopicEnum.TOPIC_MESSAGE.getTopic();
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        log.info("MessageSubscriber: {}", message.toString());
    }
}
