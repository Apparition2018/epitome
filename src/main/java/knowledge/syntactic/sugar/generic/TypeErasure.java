package knowledge.syntactic.sugar.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static l.demo.Demo.p;

/**
 * Java 的泛型采用的是擦除法实现的伪泛型，因为 Java 开始是不支持泛型的，为了兼容以前的库不得不使用擦除法
 * <pre>
 * {@code
 * List<String>, List<Integer>, List<T>     →   List
 * List<String>[]                           →   List[]
 * List<? extends E>, List<? super E>       →   List<E>
 * List<T extends Serializable & Cloneable> →   List<Serializable>}
 * </pre>
 * 为什么不能用基本类型实例化类型参数？因为类型擦除，比如 {@code Pair<double>} 擦除之后，Pair 类含有 Object 类型的域，而 Object 不能存储 double 值，这体现了 Java 语言中基本类型的独立状态
 *
 * @author ljh
 * @since 2019/9/9 00:51
 */
public class TypeErasure {
    public static void main(String[] args) {
        // 1.泛型的 class 对象是相同的
        List<String> ls = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        p(ls.getClass() == li.getClass());  // true
        p(Objects.equals(ls, li));          // ture

        // 2.泛型数组初始化时不能声明泛型类型
        // List<String>[] list = new List<String>[]; // 编译不通过

        // 3.instanceof 不允许存在泛型参数
        List<String> list = new ArrayList<>();
        // p(list instanceof List<String>) // 编译不通过
    }
}

