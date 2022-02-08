package knowledge.design.pattern.gof.creational.factory;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 工厂模式
 * 使用场景：封装创建细节
 * 1.创建复杂对象
 * 2.复用现有对象
 * <p>
 * 角色：
 * 抽象工厂 Factory：工厂方法有一个创建 Product 的方法，抽象工厂有多个创建不同 Product 的方法
 * 具体工厂 ConcreteFactory：简单工厂没有抽象工厂角色，只有一个具体工厂类
 * 抽象产品 Product：工厂方法只有一个 Product，抽象工厂有多个 Product
 * 具体产品 ConcreteProduct
 * <p>
 * 优点：符合单一职责原则、迪米特法则
 * 开闭原则：
 * 1.简单工厂：违反
 * 2.工厂方法：符合
 * 3.抽象工厂：只增加 ConcreteProduct 符合；增加 Product 违反
 * <p>
 * 简单工厂 vs 工厂方法 vs 抽象工厂：https://www.zhihu.com/question/27125796/answer/1615074467
 * 设计模式最佳套路5 —— 愉快地使用工厂方法模式：https://zhuanlan.zhihu.com/p/392524169
 *
 * @author ljh
 * created on 2022/1/23 0:49
 */
public class FactoryDemo {

    /**
     * 简单工厂/静态工厂方法
     * 使用场景：同一类型的不同产品，创建过程简单
     * 使用实例：
     * 1.{@link java.util.Calendar#getInstance(TimeZone, Locale)}
     * 2.{@link java.util.ResourceBundle#getBundle(String, Locale)}
     * 3.{@link java.text.NumberFormat#getInstance(Locale)}
     * 4.{@link java.nio.charset.Charset#forName(String)}
     * 5.{@link java.util.EnumSet#of(Enum)}
     * 6.{@link org.springframework.beans.factory.BeanFactory#getBean(String, Class)}
     * 7.{@link org.springframework.context.ApplicationContext#getBean(String, Class)}
     * <p>
     * Java设计模式：http://c.biancheng.net/view/8385.html
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
     * 使用场景：同一类型的不同产品，创建过程复杂
     * 使用实例：
     * 1.{@link java.util.Collection#iterator()}
     * 2.{@link java.net.URLStreamHandlerFactory#createURLStreamHandler(String)}
     * 3.{@link javax.xml.bind.JAXBContext#createMarshaller()}
     * 4.{@link org.springframework.beans.factory.FactoryBean#getObject()}
     * 5.{@link org.springframework.beans.factory.config.AbstractFactoryBean#getObject()}
     * <p>
     * Factory Method：https://refactoringguru.cn/design-patterns/factory-method
     * Java设计模式：http://c.biancheng.net/view/1348.html
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
     * 使用场景：多个类型的不同产品
     * 使用实例：
     * 1.{@link Connection#createStatement()} {@link Connection#prepareStatement(String)} {@link Connection#prepareCall(String)}
     * 2.{@link javax.xml.parsers.DocumentBuilderFactory#newInstance()}
     * 3.{@link javax.xml.transform.TransformerFactory#newInstance()}
     * 4.{@link javax.xml.xpath.XPathFactory#newInstance()}
     * <p>
     * Abstract Factory：https://refactoringguru.cn/design-patterns/abstract-factory
     * Java设计模式：http://c.biancheng.net/view/1351.html
     * 菜鸟教程：https://www.runoob.com/design-pattern/abstract-factory-pattern.html
     */
    static class AbstractFactoryDemo {
        /**
         * 跨平台 GUI 组件
         * https://refactoringguru.cn/design-patterns/abstract-factory/java/example
         */
        @Test
        public void testAbstractFactory() {
            GUIFactory guiFactory;
            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
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
