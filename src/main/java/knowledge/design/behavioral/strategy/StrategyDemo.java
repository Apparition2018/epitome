package knowledge.design.behavioral.strategy;

/**
 * 策略模式：定义了一系列的算法，并将每一个算法封装起来，而且它们还可以互相替换
 * 适用场合：
 * 1.存在多个类，它们之间的区别仅在于它们的行为
 * 2.需要在几种算法中选择一种
 * 3.一个对象有很多行为
 * 使用场景：
 * 1.自定义 Comparator
 * 2.ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
 *      - BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler)
 *      - 中的 RejectedExecutionHandler，有四个实现：https://blog.csdn.net/yangsen159/article/details/103146038
 * 优点：
 * 1.算法可以自由切换
 * 2.扩展性良好
 * 3.避免多重 if-else 判断
 * 缺点：
 * 1.类的数量增多
 * 2.上层必须知道有哪些策略，才能确定适用哪一个策略
 * 角色:
 * 环境角色 Context
 * 抽象策略角色 Strategy
 * 具体策略角色 ConcreteStrategy
 * <p>
 * 《JAVA与模式》之策略模式：https://www.cnblogs.com/java-my-life/archive/2012/05/10/2491891.html
 * 策略模式 | 菜鸟教程：https://www.runoob.com/design-pattern/strategy-pattern.html
 * 策略模式原来这么简单！ - 知乎：https://zhuanlan.zhihu.com/p/53517416
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class StrategyDemo {
    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic(new Add());
        System.out.println("10 + 5 = " + arithmetic.calculate(10, 5));

        arithmetic = new Arithmetic(new Subtract());
        System.out.println("10 - 5 = " + arithmetic.calculate(10, 5));

        arithmetic = new Arithmetic(new Multiply());
        System.out.println("10 * 5 = " + arithmetic.calculate(10, 5));

        arithmetic = new Arithmetic(new Divide());
        System.out.println("10 / 5 = " + arithmetic.calculate(10, 5));
    }

    /**
     * 策略角色
     */
    interface ArithmeticStrategy {
        int doOperation(int num1, int num2);
    }

    /**
     * 具体策略角色
     */
    static class Add implements ArithmeticStrategy {

        @Override
        public int doOperation(int num1, int num2) {
            return num1 + num2;
        }
    }

    static class Subtract implements ArithmeticStrategy {
        @Override
        public int doOperation(int num1, int num2) {
            return num1 - num2;
        }
    }

    static class Multiply implements ArithmeticStrategy {
        @Override
        public int doOperation(int num1, int num2) {
            return num1 * num2;
        }
    }

    static class Divide implements ArithmeticStrategy {
        @Override
        public int doOperation(int num1, int num2) {
            return num1 / num2;
        }
    }

    /**
     * 环境角色
     */
    static class Arithmetic {
        private ArithmeticStrategy strategy;

        public Arithmetic(ArithmeticStrategy strategy) {
            this.strategy = strategy;
        }

        public int calculate(int num1, int num2) {
            return strategy.doOperation(num1, num2);
        }
    }
}