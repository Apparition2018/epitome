package springboot.mqtt.handler;

import org.jspecify.annotations.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import springboot.mqtt.service.TbLampService;

import java.util.Objects;

/**
 * ReceiverMessageHandler
 *
 * @author ljh
 * @since 2026/7/13 21:45
 */
@Component
public class ReceiverMessageHandler implements MessageHandler {

    private final TbLampService tbLampService;

    public ReceiverMessageHandler(TbLampService tbLampService) {
        this.tbLampService = tbLampService;
    }

    @Override
    public void handleMessage(@NonNull Message<?> message) throws MessagingException {
        System.out.println(message);
        Object payload = message.getPayload();
        MessageHeaders headers = message.getHeaders();
        String topic = Objects.requireNonNull(headers.get("mqtt_receivedTopic")).toString();
        System.out.println("topic: " + topic);
        if ("ljh/iot/lamp/line".equals(topic)) {
            tbLampService.updateStatus(payload.toString());
        }
    }
}
