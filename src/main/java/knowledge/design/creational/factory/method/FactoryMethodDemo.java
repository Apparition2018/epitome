package knowledge.design.creational.factory.method;

import org.junit.Test;

/**
 * 工厂模式：定义一个创建对象的接口，让子类来决定哪些类需要被实例化，使一个类的实例化推迟到子类。工厂模式是抽象工厂的一种常见情况。
 * 应用场合：计划不同条件下创建不同实例；一个对象的创建过程比较复杂；对象的创建和使用解耦；
 * 使用场景：1.数据存储
 * 关键代码：抽象产品
 * 优点：
 * 1.只要知道名称就可以创建一个对象
 * 2.扩展性高，想增加一个产品，只需扩展一个工厂类
 * 3.屏蔽产品的具体实现，调用者只关心产品的接口
 * 缺点：每次增加一个产品，就需要增加一个具体类和对象实现的工厂，使系统中类的个数成倍增加
 * <p>
 * 工厂模式 | 菜鸟教程：https://www.runoob.com/design-pattern/factory-pattern.html
 * 工厂模式-场景以及优缺点：https://www.cnblogs.com/aspirant/p/8980573.html
 * 简单工厂模式、工厂方法模式和抽象工厂模式有何区别？ - 知乎：https://www.zhihu.com/question/27125796
 * https://www.imooc.com/learn/261
 *
 * @author ljh
 * created on 2019/11/1 9:36
 */
public class FactoryMethodDemo {

    /**
     * 工厂方法模式
     * 增加一种动物，只需增加一种动物的工厂类
     */
    @Test
    public void testFactoryMethod() {
        AnimalFactory f = new DogFactory();
        Animal a = f.createAnimal();
        a.eat();
    }

    /**
     * 简单/静态工厂模式
     * 工厂方法模式的简化
     * 增加一种动物，需要修改原来有工厂类的代码，不符合开闭原则
     */
    @Test
    public void testSimpleFactory() {
        Animal a = AnimalFactory2.createAnimal("dog");
        if (null != a) a.eat();
    }

    /**
     * 简单/静态工厂
     */
    public static class AnimalFactory2 {
        public static Dog createDog() {
            return new Dog();
        }

        public static Cat createCat() {
            return new Cat();
        }

        public static Animal createAnimal(String type) {
            if ("dog".equals(type)) {
                return new Dog();
            } else if ("cat".equals(type)) {
                return new Cat();
            } else {
                return null;
            }
        }
    }

    /**
     * 工厂方法工厂
     */
    private interface AnimalFactory {

        Animal createAnimal();
    }

    private static class CatFactory implements AnimalFactory {
        @Override
        public Animal createAnimal() {
            return new Cat();
        }
    }

    private static class DogFactory implements AnimalFactory {

        @Override
        public Animal createAnimal() {
            return new Dog();
        }

    }

    private abstract static class Animal {

        public abstract void eat();
    }

    private static class Cat extends Animal {

        @Override
        public void eat() {
            System.out.println("猫吃鱼");
        }

    }

    private static class Dog extends Animal {

        @Override
        public void eat() {
            System.out.println("狗吃肉");
        }

    }

}
