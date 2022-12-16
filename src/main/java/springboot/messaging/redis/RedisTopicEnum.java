package springboot.messaging.redis;

import lombok.Getter;

/**
 * RedisTopicEnum
 *
 * @author ljh
 * @since 2021/9/1 20:35
 */
@Getter
public enum RedisTopicEnum {

    EN_MESSAGE_TOPIC("topic:en_message", "英文消息 Topic"),
    CN_MESSAGE_TOPIC("topic:cn_message", "中文消息 Topic");

    private final String topic;
    private final String desc;

    RedisTopicEnum(String topic, String desc) {
        this.topic = topic;
        this.desc = desc;
    }
}
