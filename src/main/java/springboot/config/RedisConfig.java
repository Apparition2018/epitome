package springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import springboot.messaging.redis.CnMessageSubscriber;
import springboot.messaging.redis.RedisTopicEnum;
import springboot.messaging.redis.Subscriber;

import java.util.List;

/**
 * RedisConfig
 * RuoYi (RedisConfig)
 * Spring Boot 缓存应用实践：https://mp.weixin.qq.com/s/45S-_XIWgkR6qYbFKll5RQ
 *
 * @author ljh
 * created on 2021/9/1 10:56R
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

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
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                // .entryTtl(Duration.ofSeconds(600L))
                // .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private final transient List<Subscriber> subscriberList;

    public RedisConfig(List<Subscriber> subscriberList) {
        this.subscriberList = subscriberList;
    }

    /**
     * Redis 消息监听器容器
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.setTopicSerializer(RedisSerializer.string());
        if (subscriberList != null) {
            subscriberList.forEach(subscriber -> {
                // 第一种：MessageListenerAdapter(Object delegate)
                MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(subscriber);
                messageListenerAdapter.afterPropertiesSet();
                messageListenerAdapter.setSerializer(jackson2JsonRedisSerializer());
                redisMessageListenerContainer.addMessageListener(cnMessageListenerAdapter(), new PatternTopic(subscriber.getTopic()));
            });
        }
        redisMessageListenerContainer.addMessageListener(cnMessageListenerAdapter(), new PatternTopic(RedisTopicEnum.TOPIC_CN_MESSAGE.getTopic()));
        return redisMessageListenerContainer;
    }

    @Bean
    public MessageListenerAdapter cnMessageListenerAdapter() {
        // 第二种：MessageListenerAdapter(Object delegate, String defaultListenerMethod)
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(cnMessageSubscriber(), "onMessage");
        messageListenerAdapter.setSerializer(RedisSerializer.string()); // 为什么不能设置 Jackson2JsonRedisSerializer ?
        return messageListenerAdapter;
    }

    @Bean
    public CnMessageSubscriber cnMessageSubscriber() {
        return new CnMessageSubscriber();
    }

}
