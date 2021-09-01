package springboot.listener.redis;

import org.springframework.data.redis.connection.MessageListener;

/**
 * 订阅者基类
 *
 * @author ljh
 * created on 2021/9/1 17:59
 */
public interface RedisSubscriber extends MessageListener {

    default String getType() {
        return this.getClass().getSimpleName();
    }

    String getTopic();
}
