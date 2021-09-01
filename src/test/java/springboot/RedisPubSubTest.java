package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import springboot.listener.redis.RedisTopicEnum;

/**
 * 使用 Redis 的发布订阅功能：https://blog.didispace.com/spring-boot-learning-25-5-5/
 * Springboot+Redis（发布订阅模式）跨多服务器实战：https://www.cnblogs.com/ywbmaster/p/13595837.html
 * 第一种：订阅者继承 MessageListener：https://blog.csdn.net/qq_27242695/article/details/113308502
 * 第二种：自定义订阅者：https://www.jianshu.com/p/af48762fc5f9
 *
 * @author ljh
 * created on 2021/9/2 2:17
 */
@SpringBootTest
public class RedisPubSubTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisPubSub() {
        redisTemplate.convertAndSend(RedisTopicEnum.TOPIC_MESSAGE.getTopic(), "I Love U");
        redisTemplate.convertAndSend(RedisTopicEnum.TOPIC_MIME.getTopic(), "我爱你");
    }
}
