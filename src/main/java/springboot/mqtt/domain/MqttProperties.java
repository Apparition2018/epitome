package springboot.mqtt.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MQTT 配置属性
 *
 * @author ljh
 * @since 2026/7/13 10:21
 */
@Data
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private String username;
    private String password;
    private String url;
    private String subscribeClientId;
    private String subscribeTopics;
    private String publishClientId;
}
