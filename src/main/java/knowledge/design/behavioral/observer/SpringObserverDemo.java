package knowledge.design.behavioral.observer;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;

/**
 * SpringObserver
 * <p>
 * 事件机制的参与者有三种角色:
 * 1.Event Source：事件源，发起事件的主体。
 * 2.Event Object：事件状态对象，传递的信息载体，以是事件源本身，一般作为参数存在于 Listener 的方法之中。
 * 3.Event Listener：事件监听器，当它监听到 event object 产生的时候，它就调用相应的方法，进行处理。
 * <p>
 * Spring 的事件驱动模型由三部分组成：
 * 1.事件：ApplicationEvent，继承自 JDK 的 EventObject，所有事件将继承它，并通过 source 得到事件源。
 * 2.事件发布者：ApplicationEventPublisher 及 ApplicationEventMulticaster 接口，使用这个接口，我们的 Service 就拥有了发布事件的能力。
 * 3.事件订阅者：ApplicationListener，继承自 JDK 的 EventListener，所有监听器将继承它
 * <p>
 * Springboot学习（二）观察者模式：https://zhuanlan.zhihu.com/p/141069636
 *
 * @author ljh
 * created on 2020/12/3 14:54
 */
public class SpringObserverDemo {

    /**
     * Spring
     */
    public static class SpringListener {

        @Test
        public void test() {
            // 获取 IOC 容器
            AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
            // 注册监听者
            applicationContext.register(SmsListener.class);
            applicationContext.register(EmailListener.class);
            // 刷新容器
            applicationContext.refresh();
            // 事件发布
            applicationContext.publishEvent(new OrderEvent(this, "用户下单成功"));
        }


        /**
         * 事件源
         * 事件状态对象
         */
        private static class OrderEvent extends ApplicationEvent {

            private String message;

            public OrderEvent(Object source, String message) {
                super(source);
                this.message = message;
            }

            @Override
            public Object getSource() {
                return super.getSource();
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }

        /**
         * 监听者
         */
        private static class SmsListener implements ApplicationListener<OrderEvent> {

            @Async
            @Override
            public void onApplicationEvent(OrderEvent event) {
                System.out.println(Thread.currentThread() + "...短信监听到..." + event.getMessage() + "......" + event.getSource());
            }
        }

        /**
         * 监听者
         */
        private static class EmailListener implements ApplicationListener<OrderEvent> {

            @Async
            @Override
            public void onApplicationEvent(OrderEvent event) {
                System.out.println(Thread.currentThread() + "...邮件监听到..." + event.getMessage() + "......" + event.getSource());
            }
        }
    }

}
