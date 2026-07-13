package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import springboot.mqtt.service.MqttMessageSender;

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
}
