package springboot.listener.redis;

import lombok.extern.slf4j.Slf4j;

/**
 * RedisReceiver
 *
 * @author ljh
 * created on 2021/9/2 2:48
 */
@Slf4j
public class RedisReceiver {

    public void receiveMessage(String message) {
        log.info("RedisReceiver: {}", message);
    }
}
