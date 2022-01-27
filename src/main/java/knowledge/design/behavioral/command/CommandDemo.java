package knowledge.design.behavioral.command;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 命令模式：将请求封装为对象，使请求的发送者和接收者解耦，从而增强请求的能力
 * 使用场景：控制请求的执行，如异步、延迟、排队执行、撤销/重做、记录日志等
 * 使用实例：
 * 1.{@link Runnable} 的所有实现
 * 2.{@link javax.swing.Action} 的所有实现
 * <p>
 * 角色:
 * 调用者 Invoker：接收 Command 的引用，请求时调用 command.execute()
 * 抽象命令 Command：通常仅声明一个 execute()
 * 具体命令 ConcreteCommand：实现 execute()，把工作委派给 Receiver
 * 接收者 Receiver
 * <p>
 * 优点：符合单一职责原则、开闭原则
 * TODO-LJH: 2022/1/26 与组合模式联合使用，构成宏命令
 * <p>
 * Command：https://refactoringguru.cn/design-patterns/command
 * Java设计模式：http://c.biancheng.net/view/1380.html
 * 菜鸟模式：https://www.runoob.com/design-pattern/command-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class CommandDemo {

    /**
     * 计算器：加法
     */
    @Test
    public void testCommand() {
        Calculator calculator = new Calculator();
        Adder adder = new Adder();
        calculator.compute(new AddCommand(adder, 1));
        calculator.compute(new AddCommand(adder, 2));
    }

    /**
     * Invoker
     */
    static class Calculator {
        public void compute(Command command) {
            command.execute();
        }
    }

    /**
     * Command
     */
    static abstract class Command {
        protected final Adder adder;
        protected final int value;

        private Command(Adder adder, int value) {
            this.adder = adder;
            this.value = value;
        }

        protected abstract void execute();
    }

    /**
     * ConcreteCommand
     */
    static class AddCommand extends Command {
        public AddCommand(Adder adder, int value) {
            super(adder, value);
        }

        public void execute() {
            System.out.println("执行运算，运算结果为：" + adder.add(value));
        }
    }

    /**
     * Receiver
     */
    static class Adder {
        private int result = 0;

        public int add(int value) {
            return result += value;
        }
    }

    /**
     * 宏命令 (Macro Command) / 组合命令模式
     */
    static class CompositeCommandDemo {
        @Test
        public void testCompositeCommand() {
            Adder adder = new Adder();
            CompositeInvoker compositeInvoker = new CompositeInvoker(adder, 3);
            compositeInvoker.add(new AddCommand(adder, 1));
            compositeInvoker.add(new AddCommand(adder, 2));
            compositeInvoker.execute();
        }

        /**
         * Invoker / Composite
         */
        static class CompositeInvoker extends Command {
            private final List<Command> children = new ArrayList<>();

            private CompositeInvoker(Adder adder, int value) {
                super(adder, value);
            }

            public void add(Command command) {
                children.add(command);
            }

            public void remove(Command command) {
                children.remove(command);
            }

            public Command getChild(int i) {
                return children.get(i);
            }

            @Override
            public void execute() {
                for (Command child : children) child.execute();
            }
        }
    }

    /**
     * 命令队列
     */
    static class CommandQueueDemo {
        @Test
        public void testCommandQueue() {
            Calculator calculator = new Calculator();
            Adder adder = new Adder();
            CommandQueue commandQueue = new CommandQueue();
            commandQueue.addCommand(new AddCommand(adder, 1));
            commandQueue.addCommand(new AddCommand(adder, 2));
            calculator.compute(commandQueue);
        }

        /**
         * Invoker
         */
        static class Calculator {
            public void compute(CommandQueue commandQueue) {
                commandQueue.execute();
            }
        }

        static class CommandQueue {
            private final List<Command> commandList = new ArrayList<>();

            public void addCommand(Command command) {
                commandList.add(command);
            }

            public void execute() {
                for (Command command : commandList) {
                    command.execute();
                }
            }
        }
    }

    /**
     * 备忘录命令模式
     */
    static class MementoCommandDemo {
        /**
         * 计算器：加法、撤销
         * https://blog.csdn.net/LoveLion/article/details/8806509
         */
        @Test
        public void testMementoCommand() {
            Calculator calculator = new Calculator();
            Adder adder = new Adder();
            calculator.compute(new AddCommand(adder, 1));
            calculator.compute(new AddCommand(adder, 2));
            calculator.undo();
            calculator.undo();
            calculator.undo();
        }

        /**
         * Invoker
         */
        static class Calculator {
            private final CommandHistory history = new CommandHistory();

            public void compute(Command command) {
                System.out.println("执行运算，运算结果为：" + command.execute());
                history.push(command);
            }

            public void undo() {
                if (history.isEmpty()) return;
                Command command = history.pop();
                if (command != null) System.out.println("执行撤销，运算结果为：" + command.undo());
            }
        }

        /**
         * Command / Memento
         */
        static abstract class Command {
            protected final Adder adder;
            protected final int value;
            private int backup;

            private Command(Adder adder, int value) {
                this.adder = adder;
                this.value = value;
            }

            protected abstract int execute();

            protected void backup() {
                backup = adder.result;
            }

            private int undo() {
                return backup;
            }
        }

        /**
         * ConcreteCommand
         */
        static class AddCommand extends Command {
            public AddCommand(Adder adder, int value) {
                super(adder, value);
            }

            public int execute() {
                backup();
                return adder.add(value);
            }
        }

        /**
         * Caretaker
         */
        static class CommandHistory {
            private final Stack<Command> history = new Stack<>();

            public void push(Command command) {
                history.push(command);
            }

            public Command pop() {
                return history.pop();
            }

            public boolean isEmpty() {
                return history.isEmpty();
            }
        }
    }

}
