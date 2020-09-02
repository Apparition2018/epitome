package knowledge.数据结构.集合框架.collection;

import org.junit.Test;

import java.util.Stack;

/**
 * Stack
 * <p>
 * Stack 是 Vector 的子类
 * Stack 实现了一个后进先出 (LIFO) 的数据结构
 * <p>
 * Deque 接口及其实现提供了 LIFO 堆栈操作的更完整和更一致的 set，应该优先使用此 set，而非 stack
 */
public class StackDemo {

    @Test
    public void stack() {
        Stack<Integer> st = new Stack<>();

        // E	push(E item)
        // 把项压入堆栈顶部
        st.push(42);
        st.push(66);
        p(st);              // [42, 66]

        // E	peek()
        // 查看堆栈顶部的对象，但不从堆栈中移除它
        p(st.peek());       // 66

        // int	search(Object o)
        // 返回对象在堆栈中的位置，以 1 为基数
        p(st.search(42));// 2
        p(st.search(44));// -1

        // E	pop()
        // 移除堆栈顶部的对象，并作为此函数的值返回该对象
        st.pop();
        st.pop();
        p(st);              // []
    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
