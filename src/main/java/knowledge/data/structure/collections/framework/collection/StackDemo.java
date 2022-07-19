package knowledge.data.structure.collections.framework.collection;

import org.junit.jupiter.api.Test;

/**
 * Stack
 *
 * @author ljh
 * created on 2022/7/19 14:19
 */
public class StackDemo {

    /**
     * Stack
     * Stack 继承 Vector
     * Stack 类表示后进先出（LIFO）的对象堆栈。它通过五个操作对类 Vector 进行了扩展 ，允许将向量视为堆栈。
     * https://tool.oschina.net/uploads/apidocs/jdk-zh/java/util/Stack.html
     */
    @Test
    public void testStack() {
        DequeDemo dequeDemo = new DequeDemo();
        dequeDemo.testDequeAsStack();
    }
}
