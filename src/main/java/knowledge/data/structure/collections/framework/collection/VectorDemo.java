package knowledge.data.structure.collections.framework.collection;

import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Vector 向量
 * Vector 实现 List
 * Vector 是同步的。可以实现可增长的对象数组。
 * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Vector.html
 * <p>
 * void	        trimToSize()                    对此向量的容量进行微调，使其等于向量的当前大小 {@link ListDemo#testArrayList()}
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class VectorDemo extends Demo {

    private static final Vector<String> V;

    static {
        // Vector()                             构造一个空向量，使其内部数据数组的大小为 10，其标准容量增量为零
        // Vector(Collection<? extends E> c)    构造一个包含指定 collection 中的元素的向量，这些元素按其 collection 的迭代器返回元素的顺序排列
        // Vector(int initialCapacity[, int capacityIncrement])     使用指定的初始容量和容量增量构造一个空的向量
        V = new Vector<>(Arrays.asList("0 1 2 3 4 5 6 7 8 9".split(" ")));
        p(V);
    }

    @Test
    public void testVector() {
        // int	            capacity()                  返回此向量的当前容量
        p(V.capacity());        // 10

        // E	            elementAt(int index)        返回指定索引处的组件
        p(V.elementAt(6));      // 6

        // E	            firstElement()              返回此向量的第一个组件（位于索引 0) 处的项
        p(V.firstElement());    // 0
        // E	            lastElement()               返回此向量的最后一个组件
        p(V.lastElement());     // 9
    }

    /**
     * 添加
     */
    @Test
    public void add() {
        // void	            addElement(E obj)               将指定的组件添加到此向量的末尾，将其大小增加 1
        V.addElement("10");
        p(V); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // void	            insertElementAt(E obj, int index)   将指定对象作为此向量中的组件插入到指定的 index 处
        V.insertElementAt("-1", 0);
        p(V); // [-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    @Test
    public void set() {
        // void	            setElementAt(E obj, int index)  将此向量指定 index 处的组件设置为指定的对象
        V.setElementAt("-1", 0);
        p(V);       // [-1, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        // void	            setSize(int newSize)            设置此向量的大小
        V.setSize(7);
        p(V.size());// 7
        p(V);       // [-1, 1, 2, 3, 4, 5, 6]
    }

    /**
     * protected  void	    removeRange(int fromIndex, int toIndex)     从此 List 中移除其索引位于 fromIndex（包括）与 toIndex（不包括）之间的所有元素
     */
    @Test
    public void remove() {
        // boolean	        removeElement(Object obj)       从此向量中移除变量的第一个（索引最小的）匹配项
        boolean b = V.removeElement("0");
        p(b);       // true
        p(V);       // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // void	            removeElementAt(int index)      删除指定索引处的组件
        V.removeElementAt(V.size() - 1);
        p(V);       // [1, 2, 3, 4, 5, 6, 7, 8]

        // void	            removeAllElements()             从此向量中移除全部组件，并将其大小设置为零
        V.removeAllElements();
        p(V);       // []
    }

    /**
     * void	                copyInto(Object[] anArray)      将此向量的组件复制到指定的数组中
     */
    @Test
    public void copyInto() {
        String[] arr = new String[V.size()];
        V.copyInto(arr);
        p(arr); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    /**
     * Enumeration<E>	    elements()                      返回此向量的组件的枚举
     * <p>
     * PS：Enumeration 接口的功能与 Iterator 接口的功能重复。新的实现应该优先考虑使用 Iterator 而不是 Enumeration。
     */
    @Test
    public void elements() {
        Enumeration<String> elements = V.elements();
        while (elements.hasMoreElements()) {
            p(elements.nextElement());
        }
    }
}
