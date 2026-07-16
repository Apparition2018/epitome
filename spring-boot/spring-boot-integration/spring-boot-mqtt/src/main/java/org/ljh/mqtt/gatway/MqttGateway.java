package org.ljh.mqtt.gatway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * MqttGateway
 *
 * @author ljh
 * @since 2026/7/14 2:09
 */
@MessagingGateway(defaultRequestChannel = "messageOutboundChannel")
public interface MqttGateway {

    void sendMsgToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void sendMsgToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
