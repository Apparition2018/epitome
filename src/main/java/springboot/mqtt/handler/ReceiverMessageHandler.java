package springboot.mqtt.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * ReceiverMessageHandler
 *
 * @author ljh
 * @since 2026/7/13 21:45
 */
@Component
public class ReceiverMessageHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        System.out.println(message);
        Object payload = message.getPayload();
        System.out.println(payload);
        MessageHeaders headers = message.getHeaders();
        String topic = Objects.requireNonNull(headers.get("mqtt_receivedTopic")).toString();
        System.out.println(topic);
    }
}
