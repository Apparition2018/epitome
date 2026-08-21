package org.ljh.dependencies.google.guava;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

/**
 * <a href="https://github.com/google/guava/wiki/EventBusExplained">EventBus</a>
 * <p><a href="https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/eventbus/EventBus.html">EventBus api</a>
 *
 * @author ljh
 * @since 2022/1/26 10:21
 */
public class EventBusTests {

    @Test
    public void test() {
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

    private static class FirstSubscriber {
        @Subscribe
        private void update(String event) {
            System.out.println("first update");
        }
    }

    private static class SecondSubscriber {
        @Subscribe
        private void update(String event) {
            System.out.println("second update");
        }
    }
}
