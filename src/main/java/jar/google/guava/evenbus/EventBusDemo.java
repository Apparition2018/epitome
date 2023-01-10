package jar.google.guava.evenbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.Executors;

/**
 * <a href="https://github.com/google/guava/wiki/EventBusExplained">EventBus</a>
 * <p><a href="https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/eventbus/EventBus.html">EventBus api</a>
 *
 * @author ljh
 * @since 2022/1/26 10:21
 */
public class EventBusDemo {

    public static void main(String[] args) {
        // 同步阻塞
        // EventBus eventBus = new EventBus();
        // 异步非阻塞
        EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(10));
        // 注册订阅者
        eventBus.register(new FirstSubscriber());
        eventBus.register(new SecondSubscriber());
        // 向所有订阅者 post 事件
        eventBus.post("event");
    }

    static class FirstSubscriber {
        @Subscribe
        private void update(String event) {
            System.out.println("first update");
        }
    }

    static class SecondSubscriber {
        @Subscribe
        private void update(String event) {
            System.out.println("second update");
        }
    }
}
