package knowledge.design.pattern.gof.behavioral.mediator;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 中介模式：用一个中介对象来封装一系列对象之间的交互，各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互
 * <p>使用场景：多个对象之间存在复杂的网状关系，且难以复用
 * <p>使用实例：
 * <pre>
 * 1 {@link Timer} 所有 scheduleXXX()
 * 2 {@link Executor#execute(Runnable)}
 * 3 {@link ExecutorService} invokeXXX() 和 submit()
 * 4 {@link ScheduledExecutorService} 所有 scheduleXXX()
 * 5 {@link Method#invoke(Object, Object...)}
 * 6 {@link DispatcherServlet}
 * </pre>
 * 角色:
 * <pre>
 * 抽象中介者 Mediator：定义与 Colleague 通用交流方法
 * 具体中介者 ConcreteMediator：接收所有 Colleague 的引用，定义多种 Colleague 之间的关系
 * 抽象同事 Colleague：持有 Mediator 的引用
 * 具体同事 ConcreteColleague
 * </pre>
 * 优点：符合单一职责原则、迪米特法则
 * <p>单例中介模式：
 * <pre>
 * 1 不定义 Mediator，用 Singleton 代替 ConcreteMediator
 * 2 Colleague 不再持有 Mediator，使用时直接获取 singletonMediator.getInstance()
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://refactoringguru.cn/design-patterns/mediator">Mediator</a>
 * <a href="http://c.biancheng.net/view/1393.html">Java设计模式</a>
 * <a href="https://www.runoob.com/design-pattern/mediator-pattern.html">菜鸟模式</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class MediatorDemo {

    @Test
    public void testMediator() {
        final UnitedNationsSecurityCouncil UNSC = new UnitedNationsSecurityCouncil();
        China china = new China("China", UNSC);
        USA usa = new USA("USA", UNSC);
        Iraq iraq = new Iraq("Iraq", UNSC);
        UNSC.addCountry(china, usa, iraq);

        usa.declare("Iraq did have WMD.");
        iraq.declare("All we have is oil.");
    }


    /**
     * Colleague
     */
    @AllArgsConstructor
    static abstract class Country {
        private final String name;
        private final UnitedNations unitedNations;

        public void declare(String message) {
            unitedNations.declare(message, this);
        }

        public void getMessage(String message, Country country) {
            System.out.printf("%s get message from %s: %s%n", this.name, country.name, message);
        }
    }

    static class USA extends Country {
        public USA(String name, UnitedNations unitedNations) {
            super(name, unitedNations);
        }
    }

    static class Iraq extends Country {
        public Iraq(String name, UnitedNations unitedNations) {
            super(name, unitedNations);
        }
    }

    static class China extends Country {
        public China(String name, UnitedNations unitedNations) {
            super(name, unitedNations);
        }
    }

    /**
     * Mediator
     */
    static abstract class UnitedNations {
        protected abstract void declare(String message, Country country);
    }

    @Setter
    static class UnitedNationsSecurityCouncil extends UnitedNations {
        private List<Country> countries = new ArrayList<>();

        private void addCountry(Country... countries) {
            this.countries.addAll(List.of(countries));
        }

        @Override
        public void declare(String message, Country country) {
            for (Country c : countries) {
                if (!Objects.equals(c, country)) {
                    c.getMessage(message, country);
                }
            }
        }
    }
}
