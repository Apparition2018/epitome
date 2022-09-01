package knowledge.api.util.stream;

import knowledge.api.util.FunctionDemo;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream
 * Stream 支持元素流功能性操作的类，是 JDK1.8 的新特性。
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 * <p>
 * Stream 应该只允许被中间或终端操作操作一次，若 Stream 被检测到重用，将抛出 IllegalStateException。
 * 如果实在要重复操作同一个 Stream，可以通过 Supplier 获取新的 Stream {@link FunctionDemo.SupplierDemo#testSupplier()}
 * <p>
 * 函数式编程：https://blog.csdn.net/icarusliu/article/details/79495534
 * Stream reduce() 和 Collect()：https://blog.csdn.net/icarusliu/article/details/79504602
 * 深入浅出 parallelStream：https://blog.csdn.net/u011001723/article/details/52794455
 * http://www.runoob.com/java/java8-streams.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class StreamDemo extends Demo {

    /**
     * 创建
     */
    @Test
    public void createStream() throws IOException {
        Stream<?> stream;

        //********** 1.Collection **********//
        // stream()
        stream = list.stream();
        // parallelStream()         默认线程数等于 CPU 核心数
        stream = list.parallelStream();

        //********** 2.通过数组 **********//
        // Stream.of()
        stream = Stream.of(arr);
        // Arrays.stream()
        stream = Arrays.stream(arr);

        //********** 3.指定生成函数 **********//
        // Stream.generate()
        stream = Stream.generate(() -> new Random().nextInt(100)).limit(9);
        // Stream.iterate()
        stream = Stream.iterate(0, i -> ++i).limit(9);

        //********** 4.文件 **********//
        stream = Files.lines(Paths.get(DEMO_FILE_PATH));

        //********** 5.其它 **********//
        // Stream.empty()
        stream = Stream.empty();
        // Stream.build().add()...build()
        stream = Stream.builder().add(1).add(2).add(3).add(4).add(5).build();
        // XxxStream.boxed()
        stream = new Random().ints(0, 10).limit(9).boxed();

        //********** 6.合并两个 Stream **********//
        // Stream.concat()
        stream = Stream.concat(stream, stream);
    }

    /**
     * intermediate operation
     * 中间操作
     * <p>
     * 返回值为 Stream 的操作都是惰性求值
     * https://blog.csdn.net/zebe1989/article/details/82692508st
     */
    @Test
    public void intermediate() {
        // distinct()       去重
        // map()            元素一对一执行，有返回
        // flatMap()        元素一对一执行，返回元素的 Stream
        // peek()           元素一对一执行，无返回
        List<Integer> list = Stream.of(3, 2, 2, 3, 7, 3, 5)
                .distinct()
                .map(i -> i * i)
                .flatMap((Function<Integer, Stream<Integer>>) i -> Stream.of((int) Math.sqrt(i)))
                .peek(n -> System.out.print(n + " ")) // 3 2 7 5
                .collect(Collectors.toList());
        p("\n");

        // filter()         过滤
        // sorted()         排序
        // limit()          返回指定元素个数的流
        Stream.of(9, 8, 7, 6, 5, 4, 3, 2, 1).limit(7)
                .filter(n -> n % 2 == 0).sorted()
                .forEach(n -> System.out.print(n + " ")); // 4 6 8
        p("\n");

        // sequential()     返回串行 Stream 对象
        // parallel()       返回并行 Stream 对象
        // skip()           跳过指定个数元素
        Stream.of(9, 7, 5, 3, 1).parallel().skip(2).forEach(n -> System.out.print(n + " ")); // 3 1 5

        // close()          关闭 Stream 对象
        Stream.of(9, 7, 5, 3, 1).close();
    }

    /**
     * terminal operation
     * 终端操作
     */
    @Test
    public void terminal() {
        // count()          返回元素的个数
        p(Stream.of(9, 7, 5, 3, 1).count()); // 5

        // forEach()        迭代所有元素
        Stream.of(9, 7, 5, 3, 1).forEach(n -> System.out.print(n + " ")); // 9 7 5 3 1
        p();

        // forEachOrdered() 有序迭代所有元素
        Stream.of(9, 7, 5, 3, 1).parallel().forEachOrdered(n -> System.out.print(n + " ")); // 9 7 5 3 1
        p();

        // isParallel       判断当前 Stream 是否并行
        p(Stream.of(9, 7, 5, 3, 1).isParallel()); // false

        // toArray()        返回所有元素的数组
        p(Stream.of(9, 7, 5, 3, 1).toArray()); // [9, 7, 5, 3, 1]

        // max()            返回元素中最大的 Optional 对象
        // min()            返回元素中最小的 Optional 对象
        // findFirst()      返回第一个元素的 Optional 对象
        p(Stream.of(3, 1, -1, -3).max(Integer::compare).get());    // 3
        p(Stream.of(3, 1, -1, -3).min(Integer::compare).get());    // -3
        p(Stream.of(3, 1, -1, -3).findFirst().get());              // 3

        // reduce()
        p(Stream.of(1, 2, 3, 4, 5, 6).reduce((i1, i2) -> i1 + 2).get()); // 11
        p(Stream.of(1, 2, 3, 4, 5, 6).reduce(7, (i1, i2) -> i1 + 2));    // 19

        // collect()
        List<Integer> list = Stream.of(9, 7, 5, 3, 1).collect(Collectors.toList());
        // collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
        p(Stream.of(1, 2, 3).map(i -> Arrays.asList(i, i * 2, i * 3)).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll));
    }

    /**
     * other operation
     * 其它操作
     */
    @Test
    public void other() {
        // Optional<T>	    findFirst()                 返回 Optional(first)，空 Stream 返回 Optional.empty()
        p(Stream.of(arr).findFirst().orElse(null));     // 1
        // Optional<T>	    findAny()                   串行返回 Optional(first)，并行返回 Optional(any)，空 Stream 返回 Optional.empty()
        p(Stream.of(arr).findAny().orElse(null));       // 1

        // boolean          allMatch(IntPredicate)      // 全匹配
        p(Stream.of(arr).allMatch(i -> i > 5));         // false
        // boolean          anyMatch(IntPredicate)      // 任意一个匹配
        p(Stream.of(arr).anyMatch(i -> i > 5));         // true
        // boolean          noneMatch(IntPredicate)     // 全匹配
        p(Stream.of(arr).noneMatch(i -> i > 5));        // false

        // XxxStream        mapToXxx(XxxFunction<? super T>)                        经过 XxxFunction Stream → XxxStream
        // 当流操作均为数值操作时，将普通流转换成数值流，能获得较高的效率
        IntStream intStream = Stream.of(arr).mapToInt(value -> value);
        // XxxStream        flatMapToInt(Function<? super T, ? extends IntStream>)  经过 Function Stream → XxxStream
        IntStream intStream2 = Stream.of(arr).flatMapToInt(IntStream::of);
    }
}
