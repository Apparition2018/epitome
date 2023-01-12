package knowledge.design.pattern.other.behavioral;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href="http://www.code2succeed.com/pub-sub/">发布订阅模式</a>
 * <p>角色：
 * <pre>
 * 发布者 Publisher
 * 经纪人 Broker
 * 订阅者 Subscriber
 * </pre>
 *
 * @author ljh
 * @since 2022/1/26 14:25
 */
public class PublishSubscribeDemo {

    @Test
    public void testPublishSubscribe() {
        /* 初始化 publishers, subscribers, broker */
        Publisher javaPublisher = new Publisher();
        Publisher pythonPublisher = new Publisher();

        Subscriber javaSubscriber = new Subscriber();
        Subscriber pythonSubscriber = new Subscriber();
        Subscriber allLanguagesSubscriber = new Subscriber();

        Broker broker = new Broker();

        /* 声明 messages，然后发布到 Broker */
        Message javaMsg1 = new Message("Java", "Core Java Concepts");
        Message javaMsg2 = new Message("Java", "Spring MVC: Dependency Inject and AOP");
        Message javaMsg3 = new Message("Java", "JPA & Hibernate");
        javaPublisher.publish(javaMsg1, broker);
        javaPublisher.publish(javaMsg2, broker);
        javaPublisher.publish(javaMsg3, broker);

        Message pythonMsg1 = new Message("Python", "Easy and Powerful programming language");
        Message pythonMsg2 = new Message("Python", "Advanced Python message");
        pythonPublisher.publish(pythonMsg1, broker);
        pythonPublisher.publish(pythonMsg2, broker);

        /* addSubscriber */
        javaSubscriber.addSubscriber("Java", broker);
        pythonSubscriber.addSubscriber("Python", broker);
        allLanguagesSubscriber.addSubscriber("Java", broker);
        allLanguagesSubscriber.addSubscriber("Python", broker);

        /* unSubscribe */
        pythonSubscriber.unSubscribe("Python", broker);

        /* 向 subscribers 广播 messages */
        broker.broadcast();

        /* 打印 subscribers 收到的消息 */
        System.out.println("Messages of Java Subscriber are: ");
        javaSubscriber.printMessages();
        System.out.println("\nMessages of Python Subscriber are: ");
        pythonSubscriber.printMessages();
        System.out.println("\nMessages of All Languages Subscriber are: ");
        allLanguagesSubscriber.printMessages();

        /* 发布新 messages */
        Message javaMsg4 = new Message("Java", "JSP and Servlets");
        Message javaMsg5 = new Message("Java", "Struts framework");
        javaPublisher.publish(javaMsg4, broker);
        javaPublisher.publish(javaMsg5, broker);

        /* 指定 subscriber 获取指定 topic 消息，并打印 */
        javaSubscriber.getMessagesForSubscriberOfTopic("Java", broker);
        System.out.println("\nMessages of Java Subscriber now are: ");
        javaSubscriber.printMessages();
    }

    interface IPublisher {
        void publish(Message message, Broker broker);
    }

    static class Publisher implements IPublisher {
        @Override
        public void publish(Message message, Broker broker) {
            broker.addMessageToQueue(message);
        }
    }

    @Getter
    @Setter
    static abstract class ISubscriber {
        private List<Message> subscriberMessages = new ArrayList<>();

        protected abstract void addSubscriber(String topic, Broker broker);

        protected abstract void unSubscribe(String topic, Broker broker);

        protected abstract void getMessagesForSubscriberOfTopic(String topic, Broker broker);

        protected void printMessages() {
            for (Message message : subscriberMessages) {
                System.out.println("Message Topic → " + message.getTopic() + " : " + message.getPayload());
            }
        }
    }

    static class Subscriber extends ISubscriber {
        @Override
        public void addSubscriber(String topic, Broker broker) {
            broker.addSubscriber(topic, this);
        }

        @Override
        public void unSubscribe(String topic, Broker broker) {
            broker.removeSubscriber(topic, this);
        }

        @Override
        public void getMessagesForSubscriberOfTopic(String topic, Broker broker) {
            broker.getMessageForSubscriberOfTopic(topic, this);
        }
    }

    static class Broker {
        Map<String, Set<Subscriber>> subscriberTopicMap = new HashMap<>();

        Queue<Message> messageQueue = new LinkedList<>();

        public void addMessageToQueue(Message message) {
            messageQueue.add(message);
        }

        public void addSubscriber(String topic, Subscriber subscriber) {
            Set<Subscriber> subscribers;
            if (subscriberTopicMap.containsKey(topic)) {
                subscribers = subscriberTopicMap.get(topic);
            } else {
                subscribers = new HashSet<>();
            }
            subscribers.add(subscriber);
            subscriberTopicMap.put(topic, subscribers);
        }

        public void removeSubscriber(String topic, Subscriber subscriber) {
            if (subscriberTopicMap.containsKey(topic)) {
                Set<Subscriber> subscribers = subscriberTopicMap.get(topic);
                subscribers.remove(subscriber);
                subscriberTopicMap.put(topic, subscribers);
            }
        }

        public void getMessageForSubscriberOfTopic(String topic, Subscriber subscriber) {
            if (messageQueue.isEmpty()) {
                System.out.println("No messages from publishers to display");
            } else {
                while (!messageQueue.isEmpty()) {
                    Message message = messageQueue.remove();

                    if (message.getTopic().equalsIgnoreCase(topic)) {
                        Set<Subscriber> subscribersOfTopic = subscriberTopicMap.get(topic);

                        for (Subscriber _subscriber : subscribersOfTopic) {
                            if (Objects.equals(_subscriber, subscriber)) {
                                List<Message> subscriberMessages = subscriber.getSubscriberMessages();
                                subscriberMessages.add(message);
                                subscriber.setSubscriberMessages(subscriberMessages);
                            }
                        }
                    }
                }
            }
        }

        public void broadcast() {
            if (messageQueue.isEmpty()) {
                System.out.println("No messages from publishers to display");
            } else {
                while (!messageQueue.isEmpty()) {
                    Message message = messageQueue.remove();
                    String topic = message.getTopic();

                    Set<Subscriber> subscribersOfTopic = subscriberTopicMap.get(topic);

                    for (Subscriber subscriber : subscribersOfTopic) {
                        List<Message> subscriberMessages = subscriber.getSubscriberMessages();
                        subscriberMessages.add(message);
                        subscriber.setSubscriberMessages(subscriberMessages);
                    }
                }
            }
        }
    }

    @Data
    @AllArgsConstructor
    static class Message {
        private String topic;
        private String payload;
    }
}
