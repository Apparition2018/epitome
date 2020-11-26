package knowledge.api.lang.string;

import org.junit.Test;

import static l.demo.Demo.p;

/**
 * StringBuffer     线性安全，长度可变
 * StringBuilder    非线性安全，速度稍快，长度可变
 * String           被 final 修饰，不能被继承使用，一旦声明不能被改变；重写 equals
 * <p>
 * 类似 String 的方法：
 * <p>
 * int	            length()
 * char	            charAt(int index)
 * void             getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
 * int	            indexOf(int ch, int fromIndex)
 * CharSequence	    subSequence(int start, int end)
 * String	        substring(int start[, int end])
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class StringBufferBuilderDemo {

    /**
     * StringBuffer/StringBuilder   append(XXX xxx)
     * 将 XXX 参数的字符串表示形式追加到此序列
     */
    @Test
    public void append() {
        StringBuilder sb = new StringBuilder("Hello");

        p(sb.append(" World!")); // Hello World!
    }

    /**
     * StringBuffer/StringBuilder	reverse()
     * 将此字符序列用其反转形式取代
     */
    @Test
    public void reverse() {
        p(new StringBuilder("Hello")); // olleH
    }

    /**
     * StringBuffer/StringBuilder	delete(int start, int end)
     * 移除此序列的子字符串中的字符
     * <p>
     * StringBuilder/StringBuilder	deleteCharAt(int index)
     * 移除此序列指定位置上的 char
     */
    @Test
    public void delete() {
        StringBuilder sb = new StringBuilder("Hello");

        p(sb.delete(3, 5));    // Hel
        p(sb.deleteCharAt(1)); // Hl
    }

    /**
     * StringBuffer/StringBuilder	insert(int offset, XXX xxx[, int start, int end])
     * 将 XXX 参数的字符串表示形式插入此序列中
     */
    @Test
    public void insert() {
        StringBuilder sb = new StringBuilder("Hello");

        p(sb.insert(5, " World!")); // Hello World!
    }

    /**
     * StringBuffer/StringBuilder	replace(int start, int end, String str)
     */
    @Test
    public void replace() {
        StringBuilder sb = new StringBuilder("abc123cba");

        p(sb.replace(3, 6, "321")); // abc321cba
    }

    /**
     * int	capacity()
     * 返回当前容量
     */
    @Test
    public void capacity() {
        StringBuilder sb = new StringBuilder("Hello");

        p(sb.capacity()); // 21

        sb.setLength(10);
        p(sb);

    }

    /**
     * void setCharAt(int index, char ch)
     * 将给定索引处的字符设置为 ch
     */
    @Test
    public void setCharAt() {
        StringBuilder sb = new StringBuilder("AbC");

        sb.setCharAt(1, 'B');
        p(sb); // ABC
    }


}
