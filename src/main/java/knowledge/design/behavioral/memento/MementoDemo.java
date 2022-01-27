package knowledge.design.behavioral.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Scanner;
import java.util.Stack;

/**
 * 备忘录模式：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态
 * 使用场景：快照、撤销
 * 使用实例：
 * {@link  java.io.Serializable} 模拟备忘录
 * <p>
 * 角色：
 * 原发器 Originator
 * 备忘录 Memento
 * 负责人 Caretaker
 * <p>
 * 缺点：资源消耗大
 * <p>
 * Command：https://refactoringguru.cn/design-patterns/memento
 * Java设计模式：http://c.biancheng.net/view/1400.html
 * 菜鸟模式：https://www.runoob.com/design-pattern/memento-pattern.html
 * 设计模式之美：备忘录模式：对于大对象的备份和恢复，如何优化内存和时间的消耗？
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
     */
    public static void main(String[] args) {
        SnapshotHolder snapshotHolder = new SnapshotHolder();
        InputText inputText = new InputText();
        System.out.println("请输入：");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (":list".equals(input)) {
                System.out.println(inputText.getText());
            } else if (":undo".equals(input)) {
                Snapshot snapshot = snapshotHolder.popSnapshot();
                inputText.restoreSnapshot(snapshot);
            } else {
                snapshotHolder.pushSnapshot(inputText.createSnapshot());
                inputText.append(input);
            }
        }
    }

    /**
     * Originator
     */
    static class InputText {
        private final StringBuilder text = new StringBuilder();

        public String getText() {
            return text.toString();
        }

        public void append(String input) {
            text.append(input);
        }

        public Snapshot createSnapshot() {
            return new Snapshot(text.toString());
        }

        public void restoreSnapshot(Snapshot snapshot) {
            this.text.replace(0, this.text.length(), snapshot.text);
        }
    }

    /**
     * Memento
     */
    @Getter
    @AllArgsConstructor
    static class Snapshot {
        private final String text;
    }

    /**
     * Caretaker
     */
    static class SnapshotHolder {
        private final Stack<Snapshot> snapshots = new Stack<>();

        public Snapshot popSnapshot() {
            return snapshots.pop();
        }

        public void pushSnapshot(Snapshot snapshot) {
            snapshots.push(snapshot);
        }
    }
}
