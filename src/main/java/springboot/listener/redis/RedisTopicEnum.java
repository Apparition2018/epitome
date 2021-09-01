package springboot.listener.redis;

import lombok.Getter;

/**
 * RedisTopicEnums
 *
 * @author Administrator
 * created on 2021/9/1 20:35
 */
@Getter
public enum RedisTopicEnum {

    TOPIC_MESSAGE("topic:message", "消息 Topic"),
    TOPIC_MIME("topic:mine", "我的 Topic");

    private final String topic;
    private final String desc;

    RedisTopicEnum(String topic, String desc) {
        this.topic = topic;
        this.desc = desc;
    }
}
