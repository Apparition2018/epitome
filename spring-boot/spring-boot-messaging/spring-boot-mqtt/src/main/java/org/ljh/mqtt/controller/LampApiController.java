package org.ljh.mqtt.controller;

import org.ljh.mqtt.service.MqttMessageSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * LampApiController
 *
 * @author ljh
 * @since 2026/7/14 13:48
 */
@RestController
@RequestMapping("/api/lamp")
public class LampApiController {

    private final ObjectMapper objectMapper;
    private final MqttMessageSender mqttMessageSender;

    public LampApiController(ObjectMapper objectMapper, MqttMessageSender mqttMessageSender) {
        this.objectMapper = objectMapper;
        this.mqttMessageSender = mqttMessageSender;
    }

    @RequestMapping(value = "/{deviceId}/{status}")
    public String sendDeviceStatus(@PathVariable String deviceId, @PathVariable Integer status) {
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", deviceId);
        map.put("status", status);
        String json = objectMapper.writeValueAsString(map);
        mqttMessageSender.sendMsg("ljh/iot/lamp/server/status", json);
        return "ok";
    }
}
