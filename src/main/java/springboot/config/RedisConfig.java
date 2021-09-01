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

import java.util.Map;

/**
 * RedisConfig
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
     * Springboot+Redis（发布订阅模式）跨多服务器实战：https://www.cnblogs.com/ywbmaster/p/13595837.html
     * https://blog.csdn.net/qq_27242695/article/details/113308502
     * https://www.jianshu.com/p/af48762fc5f9
     * Springboot2 整合redis发布订阅 解决订阅多个频道重复代码过多 创建很多bean问题：https://blog.csdn.net/he37176427/article/details/108849041
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);

        /**
         * 加入消息监听器(可以加入多个主题监听器，监听器也可以监听多个主题)
         **/
        // 加入WebSocket监听器
        final String TOPIC_NAME = "TEST_TOPIC"; // 订阅主题
        MessageListenerAdapter webSocketListenerAdapter = webSocketListenerAdapter();
        redisMessageListenerContainer.addMessageListener(webSocketListenerAdapter, new PatternTopic(TOPIC_NAME));

        /**
         * 设置序列化对象
         * 特别注意：1. 发布的时候需要设置序列化；订阅方也需要设置序列化
         *                   2. 设置序列化对象必须放在[加入消息监听器]这一步后面，否则会导致接收器接收不到消息
         */
        redisMessageListenerContainer.setTopicSerializer(jackson2JsonRedisSerializer);

        return redisMessageListenerContainer;
    }


    @Bean
    Map<String, MessageListenerAdapter> messageListenerAdapterMap(RedisMessageReceiver redisMessageReceiver) {
        Map<String, MessageListenerAdapter> map = new HashMap<>(16);
        map.put(Constants.IOC_DISABLE_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "iocDisable"));
        map.put(Constants.API_SUB_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "apiSub"));
        map.put(Constants.IOC_ADD_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "iocAdd"));
        map.put(Constants.SOURCE_SUB_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "sourceSub"));
        map.put(Constants.PLUGIN_SUB_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "pluginSub"));
        map.put(Constants.IOC_REFRESH_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "iocRefresh"));
        map.put(Constants.TTPS_SUB_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "ttpsSub"));
        map.put(Constants.SOURCE_API_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "sourceApi"));
        map.put(Constants.PROXYCONF_SUB_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "proxyConfSub"));
        map.put(Constants.SYSLOG_CONFIDENCE_CHANNEL, new MessageListenerAdapter(redisMessageReceiver, "syslogConfidence"));
        map.put("test_pubsub", new MessageListenerAdapter(redisMessageReceiver, "test"));
        //重要 调用一次afterPropertiesSet 设置method.invoke
        //否则invoke为空 会nullPoint
        map.forEach((k, v) -> v.afterPropertiesSet());
        return map;
    }
}
