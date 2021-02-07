package knowledge.api.util;

import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * Function
 *
 * @author ljh
 * created on 2020/9/9 15:13
 */
public class FunctionDemo extends Demo {

    /**
     * Function<T, R>
     * 接收一个参数 T，返回一个值 R
     */
    private static class FunctionDemo2 {

        public static void main(String[] args) {
            Function<Integer, Integer> plusSelf = i -> i + i;
            Function<Integer, Integer> multiSelf = i -> i * i;
            p(plusSelf.apply(3));                   // 3 + 3 = 6
            p(multiSelf.compose(plusSelf).apply(3));// (3 + 3) * (3 + 3) = 36，先执行 plusSelf，再执行 mulSelf
            p(multiSelf.apply(plusSelf.apply(3)));  // (3 + 3) * (3 + 3) = 36
            p(multiSelf.andThen(plusSelf).apply(3));// (3 * 3） + (3 * 3) = 18
            p(plusSelf.apply(multiSelf.apply(3)));  // (3 * 3） + (3 * 3) = 18，先执行 mulSelf，再执行 plusSelf
        }
    }

    /**
     * BiFunction<T, U, R>
     * 接收两个参数 T, U，返回一个值 R
     */
    private static class BiFunctionDemo {

        public static void main(String[] args) {
            BiFunction<Integer, Integer, Integer> plus = Integer::sum;
            Function<Integer, Integer> plusSelf = i -> i + i;
            BiFunction<Integer, Integer, Integer> multi = (x, y) -> x * y;
            p(multi.andThen(plusSelf).apply(1, 2));                 // (1 * 2) + (1 * 2) = 4 
            p(plus.apply(multi.apply(1 ,2), multi.apply(1 ,2)));    // (1 * 2) + (1 * 2) = 4
            
        }
    }

    /**
     * BinaryOperator<T>
     * BiFunction 的一个特例，接收和返回的参数都是同一个类型
     */
    private static class BinaryOperatorDemo {

        public static void main(String[] args) {
            BinaryOperator<Integer> plus = Integer::sum;
            Function<Integer, Integer> plusSelf = i -> i + i;
            BinaryOperator<Integer> multi = (x, y) -> x * y;
            p(multi.andThen(plusSelf).apply(1, 2));                 // (1 * 2) + (1 * 2) = 4 
            p(plus.apply(multi.apply(1 ,2), multi.apply(1 ,2)));    // (1 * 2) + (1 * 2) = 4
        }
    }

    /**
     * Predicate<T>
     * 接受一个参数 T，返回一个 Boolean，用来进行判断是否符合条件
     * 该接口包含多种默认方法来将 Predicate 组合成其他复杂的逻辑（比如：与，或，非）。
     * https://blog.csdn.net/w605283073/article/details/89410918
     */
    private static class PredicateDemo {

        public static void main(String[] args) {
            p("\n输出所有数据:");
            eval(list, n -> true);          // 1 2 3 4 5 6 7 8 9 

            p("\n输出所有偶数:");
            eval(list, n -> n % 2 == 0);    // 2 4 6 8 

            p("\n输出大于 3 的数字:");
            eval(list, n -> n > 3);         // 4 5 6 7 8 9 

            p("\n输出大于 3 且为偶数的数字:");
            Predicate<Integer> predicate1 = n -> n % 2 == 0;
            Predicate<Integer> predicate2 = n -> n > 3;
            eval(list, predicate1.and(predicate2)); // 4 6 8 
        }

        private static void eval(List<Integer> list, Predicate<Integer> predicate) {
            for (Integer n : list) {
                if (predicate.test(n)) {
                    System.out.print(n + " ");
                }
            }
        }
    }

    /**
     * Consumer<T>
     * 接收一个参数 T，不返回结果，用来对参数进行任意(消费)处理
     * https://www.jianshu.com/p/63771441ba31
     */
    private static class ConsumerDemo {

        public static void main(String[] args) {
            Consumer<String> consumer1 = o -> {
                System.out.println("length: " + o.length());
            };

            Consumer<String> consumer2 = o -> {
                System.out.println("isStartsWithA: " + o.startsWith(o));
            };

            consumer1.andThen(consumer2).accept("ABC");
            // length: 3
            // isStartsWithA: true
        }
    }

    /**
     * Supplier<T>
     * 不接受参数，返回一个新的对象 T
     * https://blog.csdn.net/m0_37779570/article/details/81871197
     */
    private static class SupplierDemo {

        /**
         * 一个 Stream 只允许被中间或终端操作操作一次，
         * 可通过 Supplier 的 get() 来获取新的 Stream，再对其进行操作
         */
        @Test
        public void testSupplier() {
            //********** Supplier get() **********
            Supplier<Stream<Integer>> streamSupplier = () -> Stream.of(1, 2, 3);
            streamSupplier.get().forEach(x -> System.out.print(x + " "));
            streamSupplier.get().forEach(x -> System.out.print(x + 3 + " "));
            // 1 2 3 4 5 6 ，因为 supplier.get() 每次都会获取一个新的对象，所以不会抛异常
            p();

            //********** Stream **********
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
            Supplier<Person.Student> supplier = Person.Student::new;
            p(supplier.get());
            p(supplier.get());
        }
    }
}
