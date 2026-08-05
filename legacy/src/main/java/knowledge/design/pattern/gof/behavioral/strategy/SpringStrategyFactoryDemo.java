package knowledge.design.pattern.gof.behavioral.strategy;

import knowledge.design.pattern.gof.behavioral.template.method.TemplateMethodDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring + 策略模式 + 简单工厂
 *
 * @author ljh
 * @see TemplateMethodDemo
 * @since 2026/4/27 12:23
 */
@Service
public class SpringStrategyFactoryDemo {

    @Autowired
    private NotificationFactory notificationFactory;

    public void completeOrder(String orderId, String type) {
        // 1. 处理订单逻辑...
        System.out.println("订单 " + orderId + " 处理完成");

        // 2. 根据前端传来的 type（EMAIL/SMS），动态获取策略并执行
        NotificationStrategy strategy = notificationFactory.getStrategy(type);
        strategy.send("您的订单已完成！");
    }
}

/** Context */
@Component
class NotificationFactory {

    // 关键点：Spring 会自动收集所有实现类
    private final Map<String, NotificationStrategy> strategyMap = new ConcurrentHashMap<>();

    // 通过构造函数初始化 Map
    public NotificationFactory(List<NotificationStrategy> strategies) {
        strategies.forEach(strategy -> strategyMap.put(strategy.supportType(), strategy));
    }

    public NotificationStrategy getStrategy(String type) {
        NotificationStrategy strategy = strategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("暂不支持的通知类型: " + type);
        }
        return strategy;
    }
}

/** Strategy */
interface NotificationStrategy {

    String supportType();

    void send(String message);
}

/** ConcreteStrategy */
@Service
class EmailNotification implements NotificationStrategy {
    @Override
    public String supportType() {
        return "EMAIL";
    }

    @Override
    public void send(String message) {
        System.out.println("发送邮件通知: " + message);
    }
}

/** ConcreteStrategy */
@Service
class SmsNotification implements NotificationStrategy {
    @Override
    public String supportType() {
        return "SMS";
    }

    @Override
    public void send(String message) {
        System.out.println("发送短信通知: " + message);
    }
}
