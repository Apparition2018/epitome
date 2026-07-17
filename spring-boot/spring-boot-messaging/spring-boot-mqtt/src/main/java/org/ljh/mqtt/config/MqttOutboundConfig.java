package org.ljh.mqtt.config;

import org.ljh.mqtt.domain.MqttProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * MqttOutboundConfig
 *
 * @author ljh
 * @since 2026/7/14 2:00
 */
@Configuration
public class MqttOutboundConfig{

    private final MqttProperties mqttProperties;
    private final MqttPahoClientFactory mqttPahoClientFactory;

    public MqttOutboundConfig(MqttProperties mqttProperties, MqttPahoClientFactory mqttPahoClientFactory) {
        this.mqttProperties = mqttProperties;
        this.mqttPahoClientFactory = mqttPahoClientFactory;
    }

    @Bean
    public MessageChannel messageOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "messageOutboundChannel")
    public MessageHandler messageOutboundHandler() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(mqttProperties.getUrl(), mqttProperties.getPublishClientId(), mqttPahoClientFactory);
        messageHandler.setDefaultQos(0);
        messageHandler.setDefaultTopic("default");
        messageHandler.setAsync(true);
        return messageHandler;
    }
}
