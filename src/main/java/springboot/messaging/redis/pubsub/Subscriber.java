package springboot.messaging.redis.pubsub;

import org.springframework.data.redis.connection.MessageListener;

/**
 * 订阅者基类
 *
 * @author ljh
 * @since 2021/9/1 17:59
 */
public interface Subscriber extends MessageListener {

    default String getType() {
        return this.getClass().getSimpleName();
    }

    String getTopic();
}
