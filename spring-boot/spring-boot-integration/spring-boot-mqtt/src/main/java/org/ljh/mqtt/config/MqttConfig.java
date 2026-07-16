package org.ljh.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.ljh.mqtt.domain.MqttProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

/**
 * MqttConfig
 *
 * @author ljh
 * @since 2026/7/13 11:051
 */
@Configuration
@EnableConfigurationProperties(MqttProperties.class)
public class MqttConfig {

    private final MqttProperties mqttProperties;

    public MqttConfig(MqttProperties mqttProperties) {
        this.mqttProperties = mqttProperties;
    }

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory mqttPahoClientFactory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(mqttProperties.getUsername());
        options.setPassword(mqttProperties.getPassword().toCharArray());
        options.setServerURIs(new String[]{mqttProperties.getUrl()});
        mqttPahoClientFactory.setConnectionOptions(options);
        return mqttPahoClientFactory;
    }
}
