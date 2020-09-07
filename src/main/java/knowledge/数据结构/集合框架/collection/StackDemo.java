package knowledge.数据结构.集合框架.collection;

import l.demo.Demo;
import org.junit.Test;

import java.util.Stack;

/**
 * Stack
 * Stack 继承 Vector
 * Stack 类表示后进先出（LIFO）的对象堆栈。它通过五个操作对类 Vector 进行了扩展 ，允许将向量视为堆栈。
 * https://jdk6.net/util/Stack.html
 * <p>
 * Deque 接口及其实现提供了 LIFO 堆栈操作的更完整和更一致的集合，应该优先使用 Deque，而非 stack。
 */
public class StackDemo extends Demo {

    @Test
    public void testStack() {
        Stack<Integer> st = new Stack<>();

        // E	push(E item)        把项压入堆栈顶部
        st.push(42);
        st.push(66);
        p(st);              // [42, 66]

        // E	peek()              查看堆栈顶部的对象，但不从堆栈中移除它
        p(st.peek());       // 66

        // int	search(Object o)    返回对象在堆栈中的位置，以 1 为基数
        p(st.search(42));   // 2
        p(st.search(44));   // -1

        // E	pop()               移除堆栈顶部的对象，并作为此函数的值返回该对象
        st.pop();
        st.pop();
        p(st);              // []
    }

}
