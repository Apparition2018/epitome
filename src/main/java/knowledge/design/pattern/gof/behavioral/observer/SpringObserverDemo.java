package knowledge.design.pattern.gof.behavioral.observer;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Async;

/**
 * SpringObserver
 * <p>JDK 委派事件模型 (Delegation Event Model) 三个参与者：
 * <pre>
 * 1 Event Source：事件源，发起事件的主体
 * 2 Event Object：事件状态对象，传递的信息载体，可以是事件源本身，一般作为参数存在于 Listener 的方法之中
 * 3 Event Listener：事件监听器，当它监听到 event object 产生的时候，它就调用相应的方法，进行处理
 * </pre>
 * Spring 的事件驱动模型由三部分组成：
 * <pre>
 * 1 事件：ApplicationEvent，继承自 JDK 的 EventObject，所有事件将继承它，并通过 source 得到事件源。
 * 2 事件发布者：ApplicationEventPublisher 及 ApplicationEventMulticaster 接口，使用这个接口，我们的 Service 就拥有了发布事件的能力。
 * 3 事件订阅者：ApplicationListener，继承自 JDK 的 EventListener，所有监听器将继承它
 * </pre>
 * <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#context-functionality-events-annotation">@EventListener 注解使用</a>
 * {@link springboot.init.SpringStartupRunningLogic.ExampleBean#contextStarted(ContextRefreshedEvent)}
 * <p>Spring 内置事件：
 * <pre>
 * ContextRefreshedEvent        ApplicationContext 初始化或刷新时引发的事件
 * ContextStartedEvent          ApplicationContext 启动时引发的事件
 * ContextStoppedEvent          ApplicationContext 停止时引发的事件
 * ContextClosedEvent           ApplicationContext 关闭时引发的事件
 * RequestHandledEvent          ApplicationContext 中处理请求时引发的事件。仅适用于使用 Spring 的 DispatcherServlet 的 web 应用程序
 * ServletRequestHandledEvent   RequestHandledEvent 的子类，用于添加特定于 servlet 的上下文信息
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#context-functionality-events">Standard and Custom Events</a>
 * <a href="https://zhuanlan.zhihu.com/p/141069636">Springboot学习（二）观察者模式</a>
 * <a href="https://blog.csdn.net/qq_36306640/article/details/90047885">Spring 内置事件</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/12/3 14:54
 */
public class SpringObserverDemo {

    /**
     * Spring
     */
    static class SpringListener {

        @Test
        public void testSpringListener() {
            // 获取 IOC 容器
            try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()) {
                // 注册监听者
                applicationContext.register(SmsListener.class);
                applicationContext.register(EmailListener.class);
                // 刷新容器
                applicationContext.refresh();
                // 事件发布
                applicationContext.publishEvent(new OrderEvent(this, "用户下单成功"));
            }
        }


        /**
         * 事件源
         * <p>事件状态对象
         */
        static class OrderEvent extends ApplicationEvent {

            private static final long serialVersionUID = 6763968214834834166L;
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
        static class SmsListener implements ApplicationListener<OrderEvent> {

            @Async
            @Override
            public void onApplicationEvent(OrderEvent event) {
                System.out.println(Thread.currentThread() + "...短信监听到..." + event.getMessage() + "......" + event.getSource());
            }
        }

        /**
         * 监听者
         */
        static class EmailListener implements ApplicationListener<OrderEvent> {

            @Async
            @Override
            public void onApplicationEvent(OrderEvent event) {
                System.out.println(Thread.currentThread() + "...邮件监听到..." + event.getMessage() + "......" + event.getSource());
            }
        }
    }
}
