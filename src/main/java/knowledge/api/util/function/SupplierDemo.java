package knowledge.api.util.function;

import l.demo.Demo;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Supplier<T>
 * 不接受参数，返回一个新的对象
 * https://blog.csdn.net/m0_37779570/article/details/81871197
 *
 * @author ljh
 * created on 2020/9/9 11:49
 */
public class SupplierDemo extends Demo {

    /**
     * 一个 Stream 只允许被中间或终端操作操作一次，
     * 可通过 Supplier 的 get() 来获取新的 Stream，再对其进行操作
     */
    @Test
    public void testSupplier() {
        p("\n********** Supplier get() *********");
        Supplier<Stream<Integer>> streamSupplier = () -> Stream.of(1, 2, 3);
        streamSupplier.get().forEach(x -> System.out.print(x + " "));
        streamSupplier.get().forEach(x -> System.out.print(x + 3 + " "));
        // 1 2 3 4 5 6 ，因为 supplier.get() 每次都会获取一个新的对象，所以不会抛异常

        p("\n********* Stream **********");
        Stream<Integer> stream = Stream.of(1, 2, 3);
        stream.forEach(x -> System.out.print(x + " "));
        stream.forEach(x -> System.out.print(x + 3 + " "));
        // 1 2 3
        // IllegalStateException: stream has already been operated upon or closed
    }

    /**
     * 实现不用传参的工厂模式
     * https://www.cnblogs.com/webor2006/p/8243874.html
     */
    @Test
    public void testFactoryPattern() {
        Supplier<Student> supplier = Student::new;
        p(supplier.get());
        p(supplier.get());
    }

}
