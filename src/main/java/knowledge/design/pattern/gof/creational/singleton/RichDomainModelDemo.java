package knowledge.design.pattern.gof.creational.singleton;

import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 充血模型：Rich Domain Model，实体类不仅有属性，还包含属于该实体的业务行为
 *
 * @author ljh
 * @since 2026/4/27 10:19
 */
public class RichDomainModelDemo {
}

class Order {
    private Long id;
    private Long amount;

    public void calculateTotal() {
        // 问题：Order 不是 Spring Bean，无法 @Autowired 注入配置服务
        // 解决：通过一个单例的“上下文持有者”来间接获取 Spring Bean
        GlobalConfigHolder config = GlobalConfigHolder.getInstance();
        double discount = config.getDiscountRate();

        this.amount = (long) (this.amount * discount);
    }
}

@Component
class GlobalConfigHolder implements ApplicationContextAware {
    @Getter
    private static GlobalConfigHolder instance;
    private ApplicationContext context;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext ctx) {
        context = ctx;
        instance = this;
    }

    public double getDiscountRate() {
        // 实际逻辑可能从 Spring 容器中的获取某个 Service
        return context.getBean(DiscountService.class).getCurrentRate();
    }
}

@Service
class DiscountService {
    public double getCurrentRate() {
        return 0.3;
    }
}
