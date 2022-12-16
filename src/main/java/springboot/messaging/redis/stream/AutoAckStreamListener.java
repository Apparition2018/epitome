package springboot.messaging.redis.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * AutoAckStreamListener
 *
 * @author ljh
 * @since 2022/8/29 8:52
 */
@Slf4j
@Component
public class AutoAckStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

    private final RedisTemplate<String, Object> redisTemplate;

    public AutoAckStreamListener(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        String stream = message.getStream();
        RecordId recordId = message.getId();
        Map<String, String> value = message.getValue();
        log.info("auto ack, group: [{}}] 接收到一个消息 stream: {}, id:{}, value: {}", RedisStreamConstants.GROUP_B, stream, recordId, value);
        redisTemplate.opsForStream().delete(Objects.requireNonNull(stream), recordId.getValue());
    }
}
