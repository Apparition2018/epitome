package knowledge.泛型;

import l.demo.Demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 泛型（Generic）的引入加强了参数类型的安全性，减少了类型的转换.
 * 但有一点需要注意：Java 的泛型在编译器有效，在运行期被删除，也就是说所有泛型参数类型在编译后都会被清除掉
 * <p>
 * 好处：
 * 1.避免 JVM 的大换血。如果 JVM 将泛型类型延续到运行期，那么到运行期时 JVM 就需要进行大量的重构工作了，提高了运行期的效率
 * 2.版本兼容。 在编译期擦除可以更好地支持原生类型 (Raw Type)
 */
public class TypeErasure extends Demo {
    public static void main(String[] args) {
//		List<? extends E>, List<? super E> 擦除后的类型为 List<E>。
//		List<T extends Serializable & Cloneable> 擦除后类型为 List<Serializable>


        // （1） 泛型的 class 对象是相同的
        List<String> ls = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        p(ls.getClass() == li.getClass()); // true

        // （2） 泛型数组初始化时不能声明泛型类型
        // List<String>[] list = new List<String>[]; // 编译不通过

        // （3） instanceof 不允许存在泛型参数
        List<String> list = new ArrayList<>();
        // p(list instanceof List<String>) // 编译不通过
    }
}

