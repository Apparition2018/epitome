package knowledge.types.generic;

import l.demo.Demo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Generics 泛型
 * <p>
 * Java 泛型是 JDK 5 中引入的一个新特性，泛型提供了编译时类型安全检测机制，该机制允许程序员在编译时检测到非法的类型。
 * <p>
 * PECS法则：生产者（Producer）使用哦 extends，消费者（Consumer）使用 super
 * 1.生产者: 如果你需要一个提供 E 类型元素的集合，使用泛型通配符<? extends E>。它好比一个生产者，可以提供数据。
 * 2.消费者: 如果你需要一个只能装入 E 类型元素的集合，使用泛型通配符<? super E>。它好比一个消费者，可以消费你提供的数据。
 * 3.既是生产者也是消费者: 既要存储又要读取，那就别使用泛型通配符。
 * <p>
 * 不能用基本类型实例化类型参数：
 * 其原因是类型擦除。例如：Pair<double> 擦除之后，Pair 类含有 Object 类型的域，而 Object 不能存储 double 值。
 * 这体现了 Java 语言中基本类型的独立状态。
 * <p>
 * https://www.cnblogs.com/songhuiqiang/p/10631268.html
 *
 * @author ljh
 * created on 2019/9/9 00:51
 */
public class Generic<T> extends Demo {

    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    /**
     * 泛型类
     * 1.在类名后面添加了类型参数声明部分
     * 2.泛型类的类型参数声明部分也包含一个或多个类型参数，参数间用逗号隔开。一个泛型参数，也被称为一个类型变量，是用于指定一个泛型类型名称的标识符
     */
    @Test
    public void genericsClass() {
        Generic<Integer> iBox = new Generic<>();
        Generic<Double> dBox = new Generic<>();

        iBox.add(1);
        dBox.add(1.1);

        p(iBox.get());
        p(dBox.get());
    }

    @Test
    public void genericsMethod() {
        Double[] dArr = {1.1, 2.2, 3.3, 4.4};
        Character[] cArr = {'H', 'E', 'L', 'L', 'O'};

        printArray(dArr);
        printArray(cArr);
    }

    /**
     * 泛型方法
     * 1.所有泛型方法声明都有一个类型参数声明部分（由尖括号分隔），该类型参数声明部分在方法返回类型之前（在下面例子中的<E>）。
     * 2.每一个类型参数声明部分包含一个或多个类型参数，参数间用逗号隔开。一个泛型参数，也被称为一个类型变量，是用于指定一个泛型类型名称的标识符。
     * 3.类型参数能被用来声明返回值类型，并且能作为泛型方法得到的实际参数类型的占位符。
     * 4.泛型方法体的声明和其他方法一样。注意类型参数只能代表引用型类型，不能是原始类型。
     */
    private static <E> void printArray(E[] inputArray) {
        for (E e : inputArray) {
            System.out.printf("%s ", e);
        }
        p();
    }

    /**
     * ? 通配符
     */
    @Test
    public void wildcard() {
        List<String> name = new ArrayList<>();
        List<Integer> age = new ArrayList<>();

        name.add("icon");
        age.add(18);

        getData(name);
        getData(age);
    }

    private void getData(List<?> data) {
        p(data.get(0));
    }

    /**
     * <? extends E> 是 Upper Bound（上限）的通配符，用来限制元素的类型的上限
     */
    @Test
    public void upperBound() {
        List<? extends Number> list;

        list = new ArrayList<Integer>();    // 编译通过
        // list = new ArrayList<Object>();  // 编译不通过

        // 写入
        // <? extends Fruit> 只是告诉编译器集合中元素的类型上限，就是说它的类型是不确定的。
        // 为了类型安全，编译器只能阻止添加元素了
        // list.add(1); // 编译不通过

        // 读取
        // 无论 x 指向什么，编译器都可以确定获取的元素是 Number 类型，所以读取集合中的元素是允许的
        if (list.size() != 0) {
            Number x = list.get(0);
        }
    }

    /**
     * <? super E> 是 Lower Bound（下限）的通配符 ，用来限制元素的类型下限
     */
    @Test
    public void lowerBound() {
        List<? super Number> list;

        // list = new ArrayList<Integer>(); // 编译不通过
        list = new ArrayList<Object>();     // 编译通过

        // 写入
        // 我们无法确定往 list 中装的元素具体是哪个类型，但可以确定一定是 Number 的子类，所以对于下面的添加是允许的
        list.add(1);
        list.add((byte) 2);

        // 读取
        // 我们无法确定的获取的元素具体是什么类型，只能确定一定是 Number 的子类，因此我们获取元素只能进行强制类型转换
        Number obj = (Number) list.get(0);
    }

}
