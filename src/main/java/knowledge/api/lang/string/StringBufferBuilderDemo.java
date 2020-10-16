package knowledge.api.lang.string;

import l.demo.Demo;
import org.junit.Test;

/**
 * StringBuffer     线性安全
 * StringBuilder    非线性安全，速度稍快
 * <p>
 * 类似 String 的方法：
 * <p>
 * int	        length()
 * char	        charAt(int index)
 * void         getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)
 * int	        indexOf(int ch, int fromIndex)
 * CharSequence	subSequence(int start, int end)
 * String	    substring(int start[, int end])
 */
public class StringBufferBuilderDemo extends Demo {

    /**
     * StringBuffer/StringBuilder   append(XXX xxx)
     * 将 XXX 参数的字符串表示形式追加到此序列
     */
    @Test
    public void append() {
        StringBuilder sb = new StringBuilder("hello");

        p(sb.append(" world")); // hello world
    }

    /**
     * StringBuffer/StringBuilder	reverse()
     * 将此字符序列用其反转形式取代
     */
    @Test
    public void reverse() {
        StringBuilder sb = new StringBuilder("hello");

        p(sb.reverse()); // olleh
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
        StringBuilder sb = new StringBuilder("hello");

        p(sb.delete(3, 5));    // hel
        p(sb.deleteCharAt(1)); // hl
    }

    /**
     * StringBuffer/StringBuilder	insert(int offset, XXX xxx[, int start, int end])
     * 将 XXX 参数的字符串表示形式插入此序列中
     */
    @Test
    public void insert() {
        StringBuilder sb = new StringBuilder("hello");

        p(sb.insert(5, " world")); // hello world
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
        StringBuilder sb = new StringBuilder("hello");

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
