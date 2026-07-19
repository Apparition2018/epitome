package org.ljh.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.ljh.redis.pubsub.RedisTopicEnum;
import org.ljh.redis.stream.RedisStreamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
 * redis messaging
 *
 * @author ljh
 * @since 2021/9/2 2:17
 */
@Slf4j
@SpringBootTest
public class RedisMessagingTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis 发布订阅
     *
     * @see <a href="https://blog.didispace.com/spring-boot-learning-25-5-5/">使用 Redis 的发布订阅功能</a>
     * @see <a href="https://www.cnblogs.com/ywbmaster/p/13595837.html">Springboot+Redis（发布订阅模式）跨多服务器实战</a>
     */
    @Test
    public void testPubSub() {
        redisTemplate.convertAndSend(RedisTopicEnum.EN_MESSAGE_TOPIC.getTopic(), "I Love U");
        redisTemplate.convertAndSend(RedisTopicEnum.CN_MESSAGE_TOPIC.getTopic(), "我爱你");
    }

    @Test
    public void testStreamListener() {
        redisTemplate.opsForStream().createGroup(RedisStreamConstants.KEY, RedisStreamConstants.GROUP_A);
        redisTemplate.opsForStream().createGroup(RedisStreamConstants.KEY, RedisStreamConstants.GROUP_B);

        Map<String, String> map = Map.of("a", "1", "b", "2", "c", "3");
        RecordId recordId = redisTemplate.opsForStream().add(RedisStreamConstants.KEY, map);
        log.info("recordId: {}", recordId);

        redisTemplate.opsForStream().destroyGroup(RedisStreamConstants.KEY, RedisStreamConstants.GROUP_A);
        redisTemplate.opsForStream().destroyGroup(RedisStreamConstants.KEY, RedisStreamConstants.GROUP_B);
        redisTemplate.delete(RedisStreamConstants.KEY);
    }
}
