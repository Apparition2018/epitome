package knowledge.design.pattern.gof.behavioral.iterator;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 迭代器模式：不暴露聚合对象底层实现形式（数组、链表、栈和树等）的情况下，按顺序访问其各个元素
 * <p>使用场景：
 * <pre>
 * 1 为聚合对象提供多种遍历方式
 * 2 为不同的聚合结构提供统一的遍历方式
 * </pre>
 * 使用实例：
 * <pre>
 * 1 {@link Iterator} 的所有实现：<a href="https://blog.csdn.net/LoveLion/article/details/9992799">遍历聚合对象中的元素——迭代器模式（五）</a>
 * 2 {@link Scanner}
 * 3 {@link Enumeration} 的所有实现
 * </pre>
 * 角色：
 * <pre>
 * 抽象聚合 Aggregate：定义 add(), remove(), Iterator iterator()
 * 具体聚合 ConcreteAggregate
 * 抽象迭代器 Iterator：定义 hasNext(), next()
 * 具体迭代器 ConcreteIterator：持有 Aggregate 底层实现形式的引用，实现遍历算法，并记录遍历信息，如当前位置等
 * </pre>
 * 优点：符合单一职责原则、开闭原则
 * <p>扩展：
 * <pre>
 * 1 内部类迭代器：https://blog.csdn.net/LoveLion/article/details/9992731
 * 2 与 Factory Method 联合使用，使得 ConcreteAggregate 能获取不同 ConcreteIterator
 * 3 与 Memento 联合使用，实现回滚
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://refactoringguru.cn/design-patterns/iterator">Iterator</a>
 * <a href="http://c.biancheng.net/view/1395.html">Java设计模式</a>
 * 设计模式之美：迭代器模式（下）：如何设计实现一个支持“快照”功能的iterator？
 * </pre>
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class IteratorDemo {
}
