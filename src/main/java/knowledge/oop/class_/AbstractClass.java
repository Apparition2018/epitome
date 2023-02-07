package knowledge.oop.class_;

/**
 * 抽象类
 * <pre>
 * 1 不能实例化
 * 2 不一定包含抽象方法，但是有抽象方法的类必定是抽象类
 * 3 抽象类的子类必须重写父类的抽象方法，或者声明自身为抽象类
 * </pre>
 * <pre>
 * 比较           抽象类     接口
 * 成员变量         √        ×
 * 构造方法         √        ×
 * 静态代码块       √        ×
 * 多继承/多实现     ×        √
 * 使用动机      代码复用   实现多态
 *             类的抽象   行为的抽象
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://www.iteye.com/blog/yinny-1152430">接口 vs 抽象类</a>
 * <a href="https://mp.weixin.qq.com/s/viaZtRXp3tCPtmph9bRKCA">接口 vs 抽象类</a>
 * <a href="https://www.zhihu.com/question/36909455/answer/303566988">抽象类实现接口有什么意义？</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class AbstractClass {

    public static void main(String[] args) {
    }

    interface Action {
        default void eat() {
            System.out.println("吃");
        }

        void move();
    }

    abstract static class Dog implements Action {
        @Override
        public void move() {
            System.out.println("跑");
        }
    }

    abstract static class Bird implements Action {
        @Override
        public void move() {
            System.out.println("飞");
        }
    }

    static class Parrot extends Bird {
        private static final String name = "鹦鹉";

        private void ability() {
            System.out.println("放羊");
        }
    }

    static class Sheepdog extends Bird {
        private static final String name = "牧羊犬";

        private void ability() {
            System.out.println("模仿说话");
        }
    }
}
