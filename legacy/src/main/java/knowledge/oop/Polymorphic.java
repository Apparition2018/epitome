package knowledge.oop;

import l.demo.Animal;
import l.demo.Animal.Cat;

/**
 * 多态
 * <pre>
 * 多态是同一个行为具有不同表现形式或形态的能力
 * java 语言是一门静态多分派，动态单分派的语言
 * </pre>
 * 优点：
 * <pre>
 * 1 消除类型之间的耦合关系
 * 2 可替换性
 * 3 可扩充性
 * 4 接口性
 * 5 灵活性
 * 6 简化性
 * </pre>
 * 三个必要条件：
 * <pre>
 * 1 继承
 * 2 重写
 * 3 父类引用指向子类对象
 * </pre>
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/mengchunchen/p/7860397.html">静态分派 vs 动态分派</a>
 * @see <a href="https://www.zhihu.com/question/30082151">几句话解析 Java 多态</a>
 * @since 2019/8/8 19:39
 */
public class Polymorphic {

    public static void main(String[] args) {

        Animal a = new Cat();   // 向上转型，父类引用指向子类对象
        a.eat();                // 调用的是 Cat 的 eat
        Cat c = (Cat) a;        // 向下转型
        c.work();               // 调用的是 Cat 的 work

    }

    /**
     * 静态分派     重载
     */
    private static class StaticDispatch {

        public static void main(String[] args) {
            Human man = new Man();
            Human woman = new Woman();
            sayHello(man);
            sayHello(woman);
        }

        private static abstract class Human {
        }

        private static class Man extends Human {
        }

        private static class Woman extends Human {
        }

        public static void sayHello(Human guy) {
            System.out.println("hello, guy!");
        }

        public static void sayHello(Man man) {
            System.out.println("hello, gentlemen!");
        }

        public static void sayHello(Woman lady) {
            System.out.println("hello, lady!");
        }
    }

    /**
     * 动态分派     重写
     */
    private static class DynamicDispatch {

        public static void main(String[] args) {
            Human man = new Man();
            Human woman = new Woman();
            man.sayHello();
            woman.sayHello();
            man = new Woman();
            man.sayHello();
        }

        private static abstract class Human {
            protected abstract void sayHello();
        }

        private static class Man extends Human {
            @Override
            protected void sayHello() {
                System.out.println("man say hello!");
            }
        }

        private static class Woman extends Human {
            @Override
            protected void sayHello() {
                System.out.println("woman say hello!");
            }
        }
    }
}
