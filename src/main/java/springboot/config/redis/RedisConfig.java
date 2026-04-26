package springboot.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import springboot.messaging.redis.CnMessageSubscriber;
import springboot.messaging.redis.RedisTopicEnum;
import springboot.messaging.redis.Subscriber;
import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import tools.jackson.databind.jsontype.PolymorphicTypeValidator;

import java.util.List;

/**
 * RedisConfig
 * <pre>
 * RuoYi (RedisConfig)
 * <a href="https://mp.weixin.qq.com/s/45S-_XIWgkR6qYbFKll5RQ">Spring Boot 缓存应用实践</a>
 * </pre>
 *
 * @author ljh
 * @since 2021/9/1 10:56R
 */
@Configuration
public class RedisConfig implements CachingConfigurer {

    @Bean
    public RedisSerializer<Object> jacksonJsonRedisSerializer() {
        // 1. 定义多态类型校验器（Jackson 3 强制要求更安全的校验）
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build();
        // 2. 构建 ObjectMapper
        ObjectMapper objectMapper = JsonMapper.builder()
            .changeDefaultVisibility(vc -> vc.withVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY))
            .activateDefaultTyping(ptv, DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
            .build();
        return new JacksonJsonRedisSerializer<>(objectMapper, Object.class);
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
            // .entryTtl(Duration.ofSeconds(600L))
            // .disableCachingNullValues()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonJsonRedisSerializer()));
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jacksonJsonRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    private final List<Subscriber> subscriberList;

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
        // 第一种：通过依赖注入所有实现了 MessageListener 的类（建议使用）
        if (CollectionUtils.isNotEmpty(subscriberList)) {
            subscriberList.forEach(subscriber -> redisMessageListenerContainer.addMessageListener(subscriber, new PatternTopic(subscriber.getTopic())));
        }
        // 第二种：通过 new MessageListenerAdapter() 生成 MessageListener
        redisMessageListenerContainer.addMessageListener(cnMessageListenerAdapter(), new PatternTopic(RedisTopicEnum.CN_MESSAGE_TOPIC.getTopic()));
        return redisMessageListenerContainer;
    }

    @Bean
    public MessageListenerAdapter cnMessageListenerAdapter() {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new CnMessageSubscriber(), "onMessage");
        messageListenerAdapter.setSerializer(jacksonJsonRedisSerializer());
        return messageListenerAdapter;
    }
}
