package org.ljh.mqtt;

import org.junit.jupiter.api.Test;
import org.ljh.mqtt.service.MqttMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MqttTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MqttMessageSender mqttMessageSender;

    @Test
    public void test() throws InterruptedException {
        new CountDownLatch(1).await();
    }

    @Test
    public void sendMsg() {
        mqttMessageSender.sendMsg("java/c", "test");
    }

    @Test
    public void sendDeviceStatus() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", 1);
        map.put("status", 1);
        String json = objectMapper.writeValueAsString(map);
        mqttMessageSender.sendMsg("ljh/iot/lamp/status", json);
        new CountDownLatch(1).await();
    }
}
