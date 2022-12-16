package springboot.config.redis;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import springboot.messaging.redis.stream.AutoAckStreamListener;
import springboot.messaging.redis.stream.NonAutoAckStreamListener;
import springboot.messaging.redis.stream.RedisStreamConstants;

import java.time.Duration;

/**
 * RedisStreamListenerConfig
 *
 * @author ljh
 * @since 2022/8/29 11:07
 */
@Slf4j
@Configuration
public class RedisStreamListenerConfig {
    private final NonAutoAckStreamListener nonAutoAckStreamListener;

    private final AutoAckStreamListener autoAckStreamListener;

    public RedisStreamListenerConfig(NonAutoAckStreamListener nonAutoAckStreamListener, AutoAckStreamListener autoAckStreamListener) {
        this.nonAutoAckStreamListener = nonAutoAckStreamListener;
        this.autoAckStreamListener = autoAckStreamListener;
    }

    @Bean
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        var containerOptions = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                .builder()
                .batchSize(100)
                // .executor(Executors.newCachedThreadPool())
                // .errorHandler(e -> log.error("stream messaging error: {}", e.getMessage(), e))
                .pollTimeout(Duration.ofSeconds(2))
                .build();

        var container = StreamMessageListenerContainer.create(connectionFactory, containerOptions);
        // group-a，不自动 ack
        container.receive(Consumer.from(RedisStreamConstants.GROUP_A, RedisStreamConstants.CONSUMER_A),
                StreamOffset.create(RedisStreamConstants.KEY, ReadOffset.lastConsumed()), nonAutoAckStreamListener);
        // group-b，不自动 ack
        container.receiveAutoAck(Consumer.from(RedisStreamConstants.GROUP_B, RedisStreamConstants.CONSUMER_A),
                StreamOffset.create(RedisStreamConstants.KEY, ReadOffset.lastConsumed()), autoAckStreamListener);
        return container;
    }
}
