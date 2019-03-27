package knowledge.面向对象;

/**
 * 多态
 * <p>
 * 多态是同一个行为具有不同表现形式或形态的能力
 * <p>
 * 优点：
 * 1.消除类型之间的耦合关系
 * 2.可替换性
 * 3.可扩充性
 * 4.接口性
 * 5.灵活性
 * 6.简化性
 * <p>
 * 三个必要条件：
 * 1.继承
 * 2.重写
 * 3.父类引用指向子类对象
 */
public class Polymorphic {

    public static void main(String[] args) {

        Animal a = new Cat();   // 向上转型，父类引用指向子类对象
        a.eat();                // 调用的是 Cat 的 eat
        Cat c = (Cat) a;        // 向下转型
        c.work();               // 调用的是 Cat 的 work

    }

}

abstract class Animal {
    abstract void eat();
}

class Cat extends Animal {

    void eat() {
        System.out.println("吃鱼");
    }

    public void work() {
        System.out.println("抓老鼠");
    }

}

class Dog extends Animal {

    void eat() {
        System.out.println("吃骨头");
    }

    public void work() {
        System.out.println("看家");
    }

}