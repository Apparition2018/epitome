package knowledge.design.pattern.gof.behavioral.iterator;

/**
 * 迭代器模式：不暴露聚合对象底层实现形式（数组、链表、栈和树等）的情况下，按顺序访问其各个元素
 * 使用场景：
 * 1.为聚合对象提供多种遍历方式
 * 2.为不同的聚合结构提供统一的遍历方式
 * 使用实例：
 * 1.{@link java.util.Iterator} 的所有实现：https://blog.csdn.net/LoveLion/article/details/9992799
 * 2.{@link java.util.Scanner}
 * 3.{@link java.util.Enumeration} 的所有实现
 * <p>
 * 角色：
 * 抽象聚合 Aggregate：定义 add(), remove(), Iterator iterator()
 * 具体聚合 ConcreteAggregate
 * 抽象迭代器 Iterator：定义 hasNext(), next()
 * 具体迭代器 ConcreteIterator：持有 Aggregate 底层实现形式的引用，实现遍历算法，并记录遍历信息，如当前位置等
 * <p>
 * 优点：符合单一职责原则、开闭原则
 * 扩展：
 * 1.内部类迭代器：https://blog.csdn.net/LoveLion/article/details/9992731
 * 2.与 Factory Method 联合使用，使得 ConcreteAggregate 能获取不同 ConcreteIterator
 * 3.与 Memento 联合使用，实现回滚
 * <p>
 * Iterator：https://refactoringguru.cn/design-patterns/iterator
 * Java设计模式：http://c.biancheng.net/view/1395.html
 * 设计模式之美：迭代器模式（下）：如何设计实现一个支持“快照”功能的iterator？
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class IteratorDemo {
}
