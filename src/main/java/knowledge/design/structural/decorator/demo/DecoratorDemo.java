package knowledge.design.structural.decorator.demo;

/**
 * 装饰器模式：允许向一个现有的对象添加新的功能，同时又不改变其结构，符合开闭原则（对扩展开放，对修改关闭）
 * 主要解决：我们为了扩展一个类经常使用继承方式实现，随着扩展功能的增多，子类会变得很多
 * 使用场景：Java IO
 * 关键代码：装饰类继承和引用 Component，重写或扩展方法
 * 优点：
 * 1.装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展或删除实现类的功能。
 * 2.通过对不同的装饰类排列组合，可以创造出很多不同的组合。
 * 缺点：多层装饰比较复杂；比继承产生更多的对象
 * 角色：
 * 抽象构建角色 Component
 * 具体构建角色 ConcreteComponent
 * 装饰角色 Decorator
 * 具体装饰角色 ConcreteDecorator
 * <p>
 * 《JAVA与模式》之装饰模式：http://www.cnblogs.com/java-my-life/archive/2012/04/20/2455726.html
 * 装饰器模式 | 菜鸟教程：https://www.runoob.com/design-pattern/decorator-pattern.html
 *
 * @author Arsenal
 * created on 2020/9/26 2:51
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        TheGreatestSage sage = new Monkey();
        TheGreatestSage bird = new Bird(sage);
        TheGreatestSage fish = new Fish(bird);

        fish.move();
    }

    /**
     * 抽象构建角色
     */
    interface TheGreatestSage {

        void move();
    }

    /**
     * 具体构建角色
     */
    private static class Monkey implements TheGreatestSage {

        @Override
        public void move() {
            System.out.println("Monkey Move");
        }
    }

    /**
     * 抽象装饰角色
     */
    private static class Change implements TheGreatestSage {

        private TheGreatestSage sage;

        public Change(TheGreatestSage sage) {
            this.sage = sage;
        }

        @Override
        public void move() {
            sage.move();
        }
    }

    /**
     * 具体装饰角色
     */
    private static class Fish extends Change {

        public Fish(TheGreatestSage sage) {
            super(sage);
        }

        @Override
        public void move() {
            super.move();
            System.out.println("Fish Move");
        }
    }

    /**
     * 具体装饰角色
     */
    private static class Bird extends Change {

        public Bird(TheGreatestSage sage) {
            super(sage);
        }

        @Override
        public void move() {
            super.move();
            System.out.println("Bird Move");
        }
    }
}
