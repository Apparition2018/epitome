package other.设计模式.structural.decorator.demo;

/**
 * Component: 抽象构建角色
 * ConcreteComponent: 具体构建角色
 * Decorator: 装饰角色
 * ConcreteDecorator: 具体装饰角色
 *
 * 装饰器模式：动态地给一个对象添加新的职责
 *
 * http://www.cnblogs.com/java-my-life/archive/2012/04/20/2455726.html
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        TheGreatestSage sage = new Monkey();

        TheGreatestSage bird = new Bird(sage);
        TheGreatestSage fish = new Fish(bird);

        fish.move();
    }
}

// 抽象构建角色
interface TheGreatestSage { // 大圣
    public void move();
}

// 具体构建角色
class Monkey implements TheGreatestSage { // 大圣本尊

    @Override
    public void move() {
        System.out.println("Monkey Move");
    }
}

// 抽象装饰角色
class Change implements TheGreatestSage {

    private TheGreatestSage sage;

    public Change(TheGreatestSage sage) {
        this.sage = sage;
    }

    @Override
    public void move() {
        sage.move();
    }
}

// 具体装饰角色
class Fish extends Change { // 鱼儿

    public Fish(TheGreatestSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        System.out.println("Fish Move");
    }
}

// 具体装饰角色
class Bird extends Change { // 鸟儿

    public Bird(TheGreatestSage sage) {
        super(sage);
    }

    @Override
    public void move() {
        System.out.println("Bird Move");
    }
}