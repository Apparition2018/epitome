package knowledge.design.behavioral.strategy;

/**
 * 策略模式：定义了一系列的算法，并将每一个算法封装起来，而且它们还可以互相替换
 * 环境角色 Context
 * 抽象策略角色 Strategy
 * 具体策略角色 ConcreteStrategy
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class StrategyDemo {
    public static void main(String[] args) {
        Context context = new Context(new AddOperation());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new SubtractOperation());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new MultiplyOperation());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }

    interface Strategy {
        int doOperation(int num1, int num2);
    }

    private static class AddOperation implements Strategy {

        @Override
        public int doOperation(int num1, int num2) {
            return num1 + num2;
        }
    }

    private static class SubtractOperation implements Strategy {
        @Override
        public int doOperation(int num1, int num2) {
            return num1 - num2;
        }
    }

    private static class MultiplyOperation implements Strategy {
        @Override
        public int doOperation(int num1, int num2) {
            return num1 * num2;
        }
    }

    private static class Context {
        private Strategy strategy;

        public Context(Strategy strategy) {
            this.strategy = strategy;
        }

        public int executeStrategy(int num1, int num2) {
            return strategy.doOperation(num1, num2);
        }
    }
}