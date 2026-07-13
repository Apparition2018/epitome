package springboot.mqtt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import springboot.mqtt.domain.MqttProperties;
import springboot.mqtt.handler.ReceiverMessageHandler;

/**
 * MqttInboundConfig
 *
 * @author ljh
 * @since 2026/7/13 21:37
 */
@Profile("mqtt")
@Configuration
public class MqttInboundConfig {

    private final MqttProperties mqttProperties;
    private final MqttPahoClientFactory mqttPahoClientFactory;
    private final ReceiverMessageHandler receiverMessageHandler;

    public MqttInboundConfig(MqttProperties mqttProperties, MqttPahoClientFactory mqttPahoClientFactory, ReceiverMessageHandler receiverMessageHandler) {
        this.mqttProperties = mqttProperties;
        this.mqttPahoClientFactory = mqttPahoClientFactory;
        this.receiverMessageHandler = receiverMessageHandler;
    }

    @Bean
    public MessageChannel messageInboundChannel() {
        return new DirectChannel();
    }

    // 配置入栈适配器，作用：设置订阅主题，以及指定消息的相关属性
    @Bean
    public MessageProducer messageProducer() {
        MqttPahoMessageDrivenChannelAdapter messageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(
            mqttProperties.getUrl(), mqttProperties.getSubscribeClientId(), mqttPahoClientFactory, mqttProperties.getSubscribeTopics().split(","));
        messageDrivenChannelAdapter.setQos(1);
        messageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
        messageDrivenChannelAdapter.setOutputChannel(messageInboundChannel());
        return messageDrivenChannelAdapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "messageInboundChannel")
    public MessageHandler messageHandler() {
        return receiverMessageHandler;
    }
}
