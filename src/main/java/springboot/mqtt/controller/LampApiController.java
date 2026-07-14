package springboot.mqtt.controller;

import com.alibaba.fastjson2.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.mqtt.service.MqttMessageSender;

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

    private final MqttMessageSender mqttMessageSender;

    public LampApiController(MqttMessageSender mqttMessageSender) {
        this.mqttMessageSender = mqttMessageSender;
    }

    @GetMapping(value = "/{deviceId}/{status}")
    public String sendDeviceStatus(@PathVariable String deviceId, @PathVariable Integer status) {
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", deviceId);
        map.put("status", status);
        String json = JSON.toJSONString(map);
        mqttMessageSender.sendMsg("ljh/iot/lamp/server/status", json);
        return "ok";
    }
}
