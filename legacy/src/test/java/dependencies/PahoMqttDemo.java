package dependencies;

import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static l.demo.Demo.p;

/**
 * Paho Mqtt
 *
 * @author ljh
 * @since 2026/7/12 18:37
 */
public class PahoMqttDemo {

    @Test
    public void receiveMsg() throws MqttException {
        MqttClient mqttClient = this.createConnection();
        mqttClient.setCallback(new MqttCallback() {

            @Override
            public void disconnected(MqttDisconnectResponse disconnectResponse) {
                p("disconnected");
            }

            @Override
            public void mqttErrorOccurred(MqttException exception) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                p("topic ----> " + topic);
                byte[] payload = message.getPayload();
                p("msg   ----> " + new String(payload, StandardCharsets.UTF_8));
            }

            @Override
            public void deliveryComplete(IMqttToken token) {
                p("deliveryComplete");
            }

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void authPacketArrived(int reasonCode, MqttProperties properties) {

            }
        });
        mqttClient.subscribe("java/b", 2);
        while (true) ;
    }

    @Test
    public void sendMsg() throws MqttException {
        MqttClient mqttClient = this.createConnection();
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload("hello mqtt java client".getBytes(StandardCharsets.UTF_8));
        mqttMessage.setQos(2);
        mqttClient.publish("java/a", mqttMessage);
        while (true) ;
    }

    private MqttClient createConnection() throws MqttException {
        String serverURI = "tcp://192.168.119.128:1883";
        String clientId = "paho_client_1";

        MqttClient mqttClient = new MqttClient(serverURI, clientId, new MemoryPersistence());
        MqttConnectionOptions mqttConnectionOptions = new MqttConnectionOptions();
        mqttConnectionOptions.setUserName("ljh");
        mqttConnectionOptions.setPassword("123".getBytes(StandardCharsets.UTF_8));
        mqttConnectionOptions.setCleanStart(true);
        mqttClient.connect(mqttConnectionOptions);
        p("已连接，clientId=" + clientId);
        return mqttClient;
    }
}
