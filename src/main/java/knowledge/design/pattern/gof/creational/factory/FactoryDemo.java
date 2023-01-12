package knowledge.design.pattern.gof.creational.factory;

import knowledge.suggestions.Suggestions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPathFactory;
import java.net.URLStreamHandlerFactory;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.*;

/**
 * 工厂模式
 * <p>使用场景：封装创建细节
 * <pre>
 * 1 创建复杂对象
 * 2 复用现有对象
 * </pre>
 * 角色：
 * <pre>
 * 抽象工厂 Factory：工厂方法有一个创建 Product 的方法，抽象工厂有多个创建不同 Product 的方法
 * 具体工厂 ConcreteFactory：简单工厂没有抽象工厂角色，只有一个具体工厂类
 * 抽象产品 Product：工厂方法只有一个 Product，抽象工厂有多个 Product
 * 具体产品 ConcreteProduct
 * </pre>
 * 优点：符合单一职责原则、迪米特法则
 * <p>开闭原则：
 * <pre>
 * 1 简单工厂：违反
 * 2 工厂方法：符合
 * 3 抽象工厂：只增加 ConcreteProduct 符合；增加 Product 违反
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://www.zhihu.com/question/27125796/answer/1615074467">简单工厂 vs 工厂方法 vs 抽象工厂</a>
 * <a href="https://zhuanlan.zhihu.com/p/392524169">设计模式最佳套路5 —— 愉快地使用工厂方法模式</a>
 * 枚举实现工厂模式 {@link Suggestions#test088()}
 * </pre>
 *
 * @author ljh
 * @since 2022/1/23 0:49
 */
public class FactoryDemo {

    /**
     * 简单工厂/静态工厂方法
     * <p>使用场景：同一类型的不同产品，创建过程简单
     * <p>使用实例：
     * <pre>
     * 1 {@link Calendar#getInstance(TimeZone, Locale)}
     * 2 {@link ResourceBundle#getBundle(String, Locale)}
     * 3 {@link NumberFormat#getInstance(Locale)}
     * 4 {@link Charset#forName(String)}
     * 5 {@link EnumSet#of(Enum)}
     * </pre>
     *
     * @see <a href="http://c.biancheng.net/view/8385.html">Java设计模式</a>
     */
    static class SimpleFactoryDemo {
        @Test
        public void testSimpleFactory() {
            Button button = GUIFactory.createButton("mac");
            if (button != null) button.paint();
        }

        static class GUIFactory {
            public static Button createButton(String name) {
                switch (name) {
                    case "mac":
                        return new MacButton();
                    case "windows":
                        return new WindowsButton();
                    default:
                        return null;
                }
            }
        }
    }

    /**
     * 工厂方法模式：定义一个创建对象的接口，将创建对象的过程推迟到子类
     * <p>使用场景：同一类型的不同产品，创建过程复杂
     * <p>使用实例：
     * <pre>
     * 1 {@link Collection#iterator()}
     * 2 {@link URLStreamHandlerFactory#createURLStreamHandler(String)}
     * 3 {@link JAXBContext#createMarshaller()}
     * 4 {@link FactoryBean#getObject()}
     * 5 {@link AbstractFactoryBean#getObject()}
     * </pre>
     *
     * @see <a href="https://refactoringguru.cn/design-patterns/factory-method">Factory Method</a>
     * @see <a href="http://c.biancheng.net/view/1348.html">Java设计模式</a>
     */
    static class FactoryMethodDemo {
        @Test
        public void testFactoryMethod() {
            GUIFactory macFactory = new MacFactory();
            macFactory.createButton().paint();
        }

        /**
         * Factory
         */
        interface GUIFactory {
            Button createButton();
        }

        static class MacFactory implements GUIFactory {
            @Override
            public Button createButton() {
                return new MacButton();
            }
        }

        static class WindowsFactory implements GUIFactory {
            @Override
            public Button createButton() {
                return new WindowsButton();
            }
        }
    }

    /**
     * 抽象工厂模式：提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类
     * <p>使用场景：多个类型的不同产品
     * <p>使用实例：
     * <pre>
     * 1 {@link Connection#createStatement()} {@link Connection#prepareStatement(String)} {@link Connection#prepareCall(String)}
     * 2 {@link DocumentBuilderFactory#newInstance()}
     * 3 {@link TransformerFactory#newInstance()}
     * 4 {@link XPathFactory#newInstance()}
     * 5 {@link BeanFactory#getBean(String, Class)}
     * </pre>
     *
     * @see <a href="https://refactoringguru.cn/design-patterns/abstract-factory">Abstract Factory</a>
     * @see <a href="http://c.biancheng.net/view/1351.html">Java设计模式</a>
     * @see <a href="https://www.runoob.com/design-pattern/abstract-factory-pattern.html">菜鸟教程</a>
     */
    static class AbstractFactoryDemo {
        /**
         * <a href="https://refactoringguru.cn/design-patterns/abstract-factory/java/example">跨平台 GUI 组件</a>
         */
        @Test
        public void testAbstractFactory() {
            GUIFactory guiFactory;
            if (System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("mac")) {
                guiFactory = new MacFactory();
            } else {
                guiFactory = new WindowsFactory();
            }
            Button button = guiFactory.createButton();
            button.paint();
        }

        /**
         * Product
         */
        interface Checkbox {
            void paint();
        }

        static class MacCheckbox implements Checkbox {
            @Override
            public void paint() {
                System.out.println("MacCheckbox paint");
            }
        }

        static class WindowsCheckbox implements Checkbox {
            @Override
            public void paint() {
                System.out.println("WindowsCheckbox paint");
            }
        }

        /**
         * Factory
         */
        interface GUIFactory {
            Button createButton();

            Checkbox createCheckbox();
        }

        static class MacFactory implements GUIFactory {
            @Override
            public Button createButton() {
                return new MacButton();
            }

            @Override
            public Checkbox createCheckbox() {
                return new MacCheckbox();
            }
        }

        static class WindowsFactory implements GUIFactory {
            @Override
            public Button createButton() {
                return new WindowsButton();
            }

            @Override
            public Checkbox createCheckbox() {
                return new WindowsCheckbox();
            }
        }
    }

    /**
     * Product
     */
    interface Button {
        void paint();
    }

    static class MacButton implements Button {
        @Override
        public void paint() {
            System.out.println("MacOSButton paint");
        }
    }

    static class WindowsButton implements Button {
        @Override
        public void paint() {
            System.out.println("WindowsButton paint");
        }
    }
}
