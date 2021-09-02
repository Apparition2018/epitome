package springboot.messaging.redis;

import lombok.Getter;

/**
 * RedisTopicEnums
 *
 * @author Administrator
 * created on 2021/9/1 20:35
 */
@Getter
public enum RedisTopicEnum {

    TOPIC_EN_MESSAGE("topic:en_message", "英文消息 Topic"),
    TOPIC_CN_MESSAGE("topic:cn_message", "中文消息 Topic");

    private final String topic;
    private final String desc;

    RedisTopicEnum(String topic, String desc) {
        this.topic = topic;
        this.desc = desc;
    }
}
