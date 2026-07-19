package org.ljh.redis.pubsub.config;

import org.ljh.redis.pubsub.CnMessageSubscriber;
import org.ljh.redis.pubsub.RedisTopicEnum;
import org.ljh.redis.pubsub.Subscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;

/**
 * RedisPubSubListenerConfig
 *
 * @author ljh
 * @since 2021/9/1 10:56
 */
@Configuration
public class RedisPubSubListenerConfig {

    private final List<Subscriber> subscriberList;

    public RedisPubSubListenerConfig(List<Subscriber> subscriberList) {
        this.subscriberList = subscriberList;
    }

    /**
     * Redis 消息监听器容器
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory, RedisSerializer<Object> jacksonJsonRedisSerializer) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.setTopicSerializer(RedisSerializer.string());
        // 第一种：通过依赖注入所有实现了 MessageListener 的类（建议使用）
        if (subscriberList != null && !subscriberList.isEmpty()) {
            subscriberList.forEach(subscriber -> redisMessageListenerContainer.addMessageListener(subscriber, new PatternTopic(subscriber.getTopic())));
        }
        // 第二种：通过 new MessageListenerAdapter() 生成 MessageListener
        redisMessageListenerContainer.addMessageListener(cnMessageListenerAdapter(jacksonJsonRedisSerializer), new PatternTopic(RedisTopicEnum.CN_MESSAGE_TOPIC.getTopic()));
        return redisMessageListenerContainer;
    }

    @Bean
    public MessageListenerAdapter cnMessageListenerAdapter(RedisSerializer<Object> jacksonJsonRedisSerializer) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new CnMessageSubscriber(), "onMessage");
        messageListenerAdapter.setSerializer(jacksonJsonRedisSerializer);
        return messageListenerAdapter;
    }
}
