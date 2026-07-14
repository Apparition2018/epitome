package springboot;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import springboot.mqtt.service.MqttMessageSender;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Mqtt
 *
 * @author ljh
 * @since 2026/7/13 3:15
 */
@SpringBootTest(classes = EpitomeApplication.class)
@TestPropertySource(properties = "spring.profiles.include=mqtt")
public class MqttTest {

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
    public void sendDeviceStatus() {
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", 1);
        map.put("status", 1);
        String json = JSON.toJSONString(map);
        mqttMessageSender.sendMsg("ljh/iot/lamp/server/status", json);
    }

    // 1. 智能灯泡设备上线以后向MQTT服务端发送消息，后端服务从MQTT中获取消息记录设备信息到数据库中
    // 2. 后端微服务向MQTT服务端发送开灯或者关灯消息，设备端从MQTT中获取消息控制灯泡的开关状态
    // 3. 设备端对灯泡进行开关操作的时候向MQTT中发送消息，后端服务获取MQTT消息记录灯泡的开关状态
}
