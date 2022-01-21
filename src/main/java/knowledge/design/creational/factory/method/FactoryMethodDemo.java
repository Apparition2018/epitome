package knowledge.design.creational.factory.method;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Calendar;

/**
 * 工厂模式：定义一个创建对象的接口，让子类来决定哪些类需要被实例化，使一个类的实例化推迟到子类。工厂模式是抽象工厂的一种常见情况。
 * 使用场景：计划不同条件下创建不同实例；一个对象的创建过程比较复杂；对象的创建和使用解耦
 * 使用实例：
 * 1.Spring IOC {@link org.springframework.context.ApplicationContext}
 * 2.{@link java.util.ResourceBundle#getBundle(String)}
 * 3.{@link java.net.URLStreamHandlerFactory#createURLStreamHandler(String)}
 * <p>
 * 角色：
 * <p>
 * 优点：符合单一职责原则、开闭原则、迪米特法则
 * 1.只要知道名称就可以创建一个对象
 * 2.扩展性高，想增加一个产品，只需扩展一个工厂类
 * 3.屏蔽产品的具体实现，调用者只关心产品的接口
 * <p>
 * Factory-Method：https://refactoringguru.cn/design-patterns/factory-method
 * Java设计模式：http://c.biancheng.net/view/1348.html
 * 菜鸟教程：https://www.runoob.com/design-pattern/factory-pattern.html
 * 简单工厂 vs 工厂方法 vs 抽象工厂：https://www.zhihu.com/question/27125796
 *
 * @author ljh
 * created on 2019/11/1 9:36
 */
public class FactoryMethodDemo {

    /**
     * 简单工厂/静态工厂方法
     * 使用场景：需要创建产品的类型较少
     * 使用实例：
     * 1.{@link Calendar#getInstance()}
     * 2.{@link NumberFormat#getInstance()}
     * 3.{@link java.util.EnumSet#of(Enum)}
     * 4.{@link java.nio.charset.Charset#forName(String)}
     * 5.{@link org.springframework.beans.factory.BeanFactory}
     * 角色：Factory、Product、ConcreteProduct
     * 优点：符合迪米特法则
     * 缺点：违反开闭原则
     * Java设计模式：http://c.biancheng.net/view/8385.html
     */
    static class SimpleFactoryDemo {

        @Test
        public void testSimpleFactory() {
            Animal animal = AnimalFactory.createAnimal("dog");
            if (animal != null) animal.eat();
        }

        /**
         * Factory
         */
        abstract static class AnimalFactory {
            public static Animal createAnimal(String name) {
                switch (name) {
                    case "dog":
                        return new Dog();
                    case "cag":
                        return new Cat();
                    default:
                        return null;
                }
            }

            public static Dog createDog() {
                return new Dog();
            }

            public static Cat createCat() {
                return new Cat();
            }
        }
    }

    @Test
    public void testFactoryMethod() {
        AnimalFactory animalFactory = new DogFactory();
        Animal animal = animalFactory.createAnimal();
        animal.eat();
    }

    /**
     * AbstractFactory
     */
    interface AnimalFactory {
        Animal createAnimal();
    }

    /**
     * ConcreteFactory
     */
    static class CatFactory implements AnimalFactory {
        @Override
        public Animal createAnimal() {
            return new Cat();
        }
    }

    /**
     * ConcreteFactory
     */
    static class DogFactory implements AnimalFactory {
        @Override
        public Animal createAnimal() {
            return new Dog();
        }
    }

    /**
     * Product
     */
    abstract static class Animal {
        public abstract void eat();
    }

    /**
     * ConcreteProduct
     */
    static class Cat extends Animal {
        @Override
        public void eat() {
            System.out.println("猫吃鱼");
        }
    }

    /**
     * ConcreteProduct
     */
    static class Dog extends Animal {
        @Override
        public void eat() {
            System.out.println("狗吃肉");
        }
    }
}
