package springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import springboot.listener.redis.RedisReceiver;
import springboot.listener.redis.RedisTopicEnum;
import springboot.listener.redis.Subscriber;

import java.util.List;

/**
 * RedisConfig
 *
 * @author ljh
 * created on 2021/9/1 10:56R
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    private final transient List<Subscriber> subscriberList;

    public RedisConfig(List<Subscriber> subscriberList) {
        this.subscriberList = subscriberList;
    }

    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * Redis 消息监听器容器
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        if (subscriberList != null) {
            subscriberList.forEach(subscriber -> {
                MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(subscriber);
                messageListenerAdapter.setSerializer(jackson2JsonRedisSerializer());
                redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic(subscriber.getTopic()));
            });
        }
        redisMessageListenerContainer.addMessageListener(redisReceiver(), new PatternTopic(RedisTopicEnum.TOPIC_MIME.getTopic()));
        redisMessageListenerContainer.setTopicSerializer(new StringRedisSerializer());
        return redisMessageListenerContainer;
    }

    @Bean
    public MessageListenerAdapter redisReceiver() {
        return new MessageListenerAdapter(new RedisReceiver(), "receiveMessage");
    }

}
