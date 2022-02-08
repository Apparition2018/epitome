package knowledge.design.pattern.gof.behavioral.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Scanner;
import java.util.Stack;

/**
 * 备忘录模式：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态
 * 使用场景：快照、撤销
 * 使用实例：
 * 1.{@link  java.io.Serializable} 模拟备忘录
 * 2.Spring Web Flow：StateManageableMessageContext#createMessagesMemento()
 * <p>
 * 角色：
 * 原发器 Originator：定义 createMemento(), restoreMemento()
 * 备忘录 Memento：不能修改，可声明为 final
 * 负责人 Caretaker：用列表、堆栈等集合存储并管理 Memento
 * <p>
 * 优点：符合单一职责原则
 * 缺点：资源消耗大
 * <p>
 * Command：https://refactoringguru.cn/design-patterns/memento
 * Java设计模式：http://c.biancheng.net/view/1400.html
 * 菜鸟模式：https://www.runoob.com/design-pattern/memento-pattern.html
 *
 * @author ljh
 * created on 2020/9/26 2:51
 */
public class MementoDemo {

    /**
     * 编写一个小程序，可以接收命令行的输入
     * 1.输入 :list，命令行输出内存文本的内容
     * 2.输入 :undo，撤销上一次输入到内存文本的内容
     * 3.输入 其它，追加到内存文本
     * 4.输入 :stop，退出
     * 设计模式之美：备忘录模式：对于大对象的备份和恢复，如何优化内存和时间的消耗？
     */
    public static void main(String[] args) {
        MementoHolder mementoHolder = new MementoHolder();
        InputText inputText = new InputText();
        System.out.println("请输入：");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit && scanner.hasNext()) {
            String input = scanner.next();
            if (":list".equals(input)) {
                System.out.println(inputText.getText());
            } else if (":undo".equals(input)) {
                Memento memento = mementoHolder.popMemento();
                inputText.restoreMemento(memento);
            } else if (":exit".equals(input)) {
                exit = true;
            } else {
                mementoHolder.pushMemento(inputText.createMemento());
                inputText.append(input);
            }
        }
    }

    /**
     * Originator
     */
    @Getter
    static class InputText {
        private String text = "";

        public void append(String input) {
            text = text + input;
        }

        public Memento createMemento() {
            return new Memento(text);
        }

        public void restoreMemento(Memento memento) {
            if (memento != null) this.text = memento.text;
        }
    }

    /**
     * Memento
     */
    @Getter
    @AllArgsConstructor
    static class Memento {
        private final String text;
    }

    /**
     * Caretaker
     */
    static class MementoHolder {
        private final Stack<Memento> mementos = new Stack<>();

        public Memento popMemento() {
            if (mementos.isEmpty()) return null;
            return mementos.pop();
        }

        public void pushMemento(Memento memento) {
            mementos.push(memento);
        }
    }

    /**
     * 原型备忘录 (不需要 Memento 角色了)
     */
    static class PrototypeMementoDemo {

        public static void main(String[] args) {
            MementoHolder mementoHolder = new MementoHolder();
            InputText inputText = new InputText();
            System.out.println("请输入：");
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            while (!exit && scanner.hasNext()) {
                String input = scanner.next();
                if (":list".equals(input)) {
                    System.out.println(inputText.getText());
                } else if (":undo".equals(input)) {
                    InputText memento = mementoHolder.popMemento();
                    inputText.restoreMemento(memento);
                } else if (":exit".equals(input)) {
                    exit = true;
                } else {
                    mementoHolder.pushMemento(inputText.createMemento());
                    inputText.append(input);
                }
            }
        }

        /**
         * Originator
         */
        @Getter
        static class InputText implements Cloneable {
            private String text = "";

            public void append(String input) {
                text = text + input;
            }

            @Override
            public InputText clone() {
                try {
                    return (InputText) super.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public InputText createMemento() {
                return this.clone();
            }

            public void restoreMemento(InputText memento) {
                if (memento != null) this.text = memento.text;
            }
        }

        /**
         * Caretaker
         */
        static class MementoHolder {
            private final Stack<InputText> mementos = new Stack<>();

            public InputText popMemento() {
                if (mementos.isEmpty()) return null;
                return mementos.pop();
            }

            public void pushMemento(InputText memento) {
                mementos.push(memento);
            }
        }
    }
}