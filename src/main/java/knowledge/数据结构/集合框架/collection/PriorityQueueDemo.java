package knowledge.数据结构.集合框架.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * PriorityQueue
 * <p>
 * 一个基于优先级堆的无界优先级队列。
 * 优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序，具体取决于所使用的构造方法。
 * 优先级队列不允许使用 null 元素。
 * 依靠自然顺序的优先级队列还不允许插入不可比较的对象（这样做可能导致 ClassCastException）。
 */
public class PriorityQueueDemo {

    @Getter
    @Setter
    @AllArgsConstructor
    class Student implements Comparable<Student> {
        private int score;
        private String name;

        public String toString() {
            return "姓名：" + name + "-" + score + "分";
        }

        @Override
        public int compareTo(Student o) {
            return new CompareToBuilder().append(score, o.score).toComparison();
        }
    }

    @Test
    public void test() {
        System.out.println("----- 小顶堆 -----");
        PriorityQueue<Student> queue = new PriorityQueue<>();
        queue.add(new Student(95, "张三"));
        queue.add(new Student(89, "李四"));
        queue.add(new Student(67, "王五"));
        queue.add(new Student(92, "赵六"));

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
            // 姓名：王五-67分
            // 姓名：李四-89分
            // 姓名：赵六-92分
            // 姓名：张三-95分
        }

        System.out.println("----- 大顶堆 -----");
        queue = new PriorityQueue<>((o1, o2) -> o2.getScore() - o1.getScore());
        queue.add(new Student(95, "张三"));
        queue.add(new Student(89, "李四"));
        queue.add(new Student(67, "王五"));
        queue.add(new Student(92, "赵六"));

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
            // 姓名：张三-95分
            // 姓名：赵六-92分
            // 姓名：李四-89分
            // 姓名：王五-67分
        }

    }
}