package jar.google.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

/**
 * EventBus
 * <p>
 * https://github.com/google/guava/wiki/EventBusExplained
 * https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/eventbus/EventBus.html
 *
 * @author ljh
 * @since 2022/1/26 10:21
 */
public class EventBusDemo {

    @Test
    public void testEventBus() {
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
