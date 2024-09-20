package knowledge.design.pattern.gof.behavioral.command;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.StatementCallback;

import javax.swing.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 命令模式：将请求封装为对象，使请求的发送者和接收者解耦，从而增强请求的能力
 * <p>使用场景：控制请求的执行，如异步、延迟、排队执行、撤销/重做、记录日志等
 * <p>使用实例：
 * <pre>
 * 1 {@link Runnable} 的所有实现
 * 2 {@link Action} 的所有实现
 * 3 {@link JdbcTemplate#query(String, ResultSetExtractor)}
 *      Invoker         {@link JdbcTemplate#execute(StatementCallback)}
 *      Command         {@link StatementCallback}
 *      Receiver        {@link Statement}
 * </pre>
 * 角色:
 * <pre>
 * 调用者 Invoker：接收 Command 的引用，请求时调用 command.execute()
 * 抽象命令 Command：通常仅声明一个 execute()
 * 具体命令 ConcreteCommand：实现 execute()，把工作委派给 Receiver
 * 接收者 Receiver
 * </pre>
 * 优点：符合单一职责原则、开闭原则
 *
 * @author ljh
 * @see <a href="https://refactoringguru.cn/design-patterns/command">Command</a>
 * @see <a href="http://c.biancheng.net/view/1380.html">Java设计模式</a>
 * @see <a href="https://www.runoob.com/design-pattern/command-pattern.html">菜鸟模式</a>
 * @since 2020/9/26 2:51
 */
public class CommandDemo {

    /**
     * 计算器：加法
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Adder adder = new Adder();
        calculator.compute(new AddCommand(adder, 1));
        calculator.compute(new AddCommand(adder, 2));
    }

    /**
     * Invoker
     */
    private static class Calculator {
        public void compute(Command command) {
            command.execute();
        }
    }

    /**
     * Command
     */
    private static abstract class Command {
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
    private static class AddCommand extends Command {
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
    private static class Adder {
        private int result = 0;

        public int add(int value) {
            return result += value;
        }
    }

    /**
     * 宏命令 (Macro Command) / 组合命令模式
     */
    private static class CompositeCommandDemo {
        public static void main(String[] args) {
            Adder adder = new Adder();
            CompositeInvoker compositeInvoker = new CompositeInvoker(adder, 3);
            compositeInvoker.add(new AddCommand(adder, 1));
            compositeInvoker.add(new AddCommand(adder, 2));
            compositeInvoker.execute();
        }

        /**
         * Invoker / Composite
         */
        private static class CompositeInvoker extends Command {
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
    private static class CommandQueueDemo {
        public static void main(String[] args) {
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
        private static class Calculator {
            public void compute(CommandQueue commandQueue) {
                commandQueue.execute();
            }
        }

        private static class CommandQueue {
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
     * <p>实现撤销/历史记录，也可用原型命令模式实现
     */
    private static class MementoCommandDemo {
        /**
         * <a href="https://blog.csdn.net/LoveLion/article/details/8806509">计算器：加法、撤销</a>
         */
        public static void main(String[] args) {
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
        private static class Calculator {
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
        private static class AddCommand extends Command {
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
        private static class CommandHistory {
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
