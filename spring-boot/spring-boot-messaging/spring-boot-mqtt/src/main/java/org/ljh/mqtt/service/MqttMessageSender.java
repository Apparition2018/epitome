package org.ljh.mqtt.service;

import org.ljh.mqtt.gatway.MqttGateway;
import org.springframework.stereotype.Component;

/**
 * MqttMessageSender
 *
 * @author ljh
 * @since 2026/7/14 2:12
 */
@Component
public class MqttMessageSender {

    private final MqttGateway mqttGateway;

    public MqttMessageSender(MqttGateway mqttGateway) {
        this.mqttGateway = mqttGateway;
    }

    public void sendMsg(String topic, String msg) {
        mqttGateway.sendMsgToMqtt(topic, msg);
    }

    public void sendMsg(String topic, int qos, String msg) {
        mqttGateway.sendMsgToMqtt(topic, qos, msg);
    }
}
