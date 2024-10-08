package knowledge.oop.class_;

import org.apache.commons.lang3.StringUtils;

import java.util.stream.IntStream;

/**
 * 不可变类
 * <pre>
 * 创建该类的实例后，该实例的实例变量是不可改变的。
 * Java 提供的 8 个包装类和 String 类都是不可变类。
 * </pre>
 * <pre>
 * 1 确保所有方法不被重载。使用 final 修饰类（强不可变类），或者 final 修饰所有方法（弱不可变类）
 * 2 所有成员变量都是 private final
 * 3 不提供 setter 方法
 * 4 通过构造器初始化所有成员，如果某一个类成员不是原始变量 (primitive) 或者不可变类，必须通过在成员初始化 (in) 或者 get 方法 (out) 时通过深度 clone 方法，来确保类的不可变。
 * </pre>
 * 优点：线程安全
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/yg_zhang/p/4355354.html">可变类 vs 不可变类</a>
 * @since 2020/11/10 19:19
 */
public final class ImmutableClass {

    private final int[] arr;

    ImmutableClass(int[] arr) {
        this.arr = arr.clone();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Numbers are: ");
        IntStream.range(0, arr.length).forEach(i -> sb.append(arr[i]).append(StringUtils.SPACE));
        return sb.toString();
    }

    public int[] getArr() {
        return arr;
    }
}
