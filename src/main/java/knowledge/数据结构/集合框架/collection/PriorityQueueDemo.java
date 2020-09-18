package knowledge.数据结构.集合框架.collection;

import l.demo.Demo;
import l.demo.Person;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * PriorityQueue
 * PriorityQueue → AbstractQueue → Queue
 * 一个基于优先级堆的无界优先级队列。
 * 优先级队列的元素按照其自然顺序进行排序，或者根据构造队列时提供的 Comparator 进行排序。
 * 优先级队列不允许使用 null 元素。
 * 依靠自然顺序的优先级队列还不允许插入不可比较的对象（这样做可能导致 ClassCastException）。
 * https://jdk6.net/util/AbstractQueue.html
 * <p>
 */
public class PriorityQueueDemo extends Demo {

    @Test
    public void testPriorityQueue() {
        p("----- 小顶堆 -----");
        PriorityQueue<Person> queue = new PriorityQueue<>();
        addData(queue);
        while (!queue.isEmpty()) {
            p(queue.poll());
            // Person{name='张三', age=18}
            // Person{name='李四', age=22}
            // Person{name='王五', age=25}
            // Person{name='赵六', age=30}
        }

        p("----- 大顶堆 -----");
        queue = new PriorityQueue<>((o1, o2) -> o2.getAge() - o1.getAge());
        addData(queue);
        while (!queue.isEmpty()) {
            p(queue.poll());
            // Person{name='赵六', age=30}
            // Person{name='王五', age=25}
            // Person{name='李四', age=22}
            // Person{name='张三', age=18}
        }
    }

    public void addData(PriorityQueue<Person> queue) {
        queue.add(new Person("张三", 18));
        queue.add(new Person("李四", 22));
        queue.add(new Person("王五", 25));
        queue.add(new Person("赵六", 30));
    }

}