package springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import springboot.messaging.redis.RedisTopicEnum;
import springboot.messaging.redis.stream.RedisStreamConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * RedisListenerTest
 *
 * @author ljh
 * created on 2021/9/2 2:17
 */
@Slf4j
@SpringBootTest
public class RedisListenerTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis 发布订阅
     * <p>
     * 使用 Redis 的发布订阅功能：https://blog.didispace.com/spring-boot-learning-25-5-5/
     * Springboot+Redis（发布订阅模式）跨多服务器实战：https://www.cnblogs.com/ywbmaster/p/13595837.html
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

        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        RecordId recordId = redisTemplate.opsForStream().add(RedisStreamConstants.KEY, map);
        log.info("recordId: {}", recordId);

        redisTemplate.opsForStream().destroyGroup(RedisStreamConstants.KEY, RedisStreamConstants.GROUP_A);
        redisTemplate.opsForStream().destroyGroup(RedisStreamConstants.KEY, RedisStreamConstants.GROUP_B);
        redisTemplate.delete(RedisStreamConstants.KEY);
    }
}