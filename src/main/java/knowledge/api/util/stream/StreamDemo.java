package knowledge.api.util.stream;

import knowledge.api.util.FunctionDemo;
import l.demo.Demo;
import l.demo.Person;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html">Stream</a>
 * <pre>
 * Stream 支持元素流功能性操作的类，是 JDK8 的新特性
 * Stream 应该只允许被中间或终端操作操作一次，若 Stream 被检测到重用，将抛出 IllegalStateException。
 * 如果实在要重复操作同一个 Stream，可以通过 Supplier 获取新的 Stream {@link FunctionDemo.SupplierDemo#testSupplier()}
 * </pre>
 * 参考：
 * <pre>
 * <a href="https://www.runoob.com/java/java8-streams.html">JDK8 Stream</a>
 * <a href="https://www.runoob.com/java/java9-stream-api-improvements.html">JDK9 改进的 Stream</a>
 * <a href="https://blog.csdn.net/u011001723/article/details/52794455">深入浅出 parallelStream</a>
 * </pre>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class StreamDemo extends Demo {

    /** 创建 */
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
        // Stream.ofNullable()，JDK9 引入
        stream = Stream.ofNullable(100);
        // Stream.builder().add()...build()
        stream = Stream.builder().add(1).add(2).add(3).add(4).add(5).build();
        // Stream.iterate()，JDK9 引入
        stream = Stream.iterate(3, x -> x < 10, x -> x + 3);
        // XxxStream.boxed()
        stream = new Random().ints(0, 10).limit(9).boxed();

        //********** 6.合并两个 Stream **********//
        // Stream.concat()
        stream = Stream.concat(stream, stream);
    }

    /**
     * intermediate operation
     * <p>中间操作
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
                // 3 2 7 5
                .peek(i -> System.out.print(i + " ")).toList();
        p(StringUtils.CR);

        // filter()         过滤
        // sorted()         排序
        // limit()          限数
        Stream.of(9, 8, 7, 6, 5, 4, 3, 2, 1).limit(7)
                .filter(n -> n % 2 == 0).sorted()
                .forEach(n -> System.out.print(n + " ")); // 4 6 8
        p(StringUtils.CR);

        // sequential()     串行
        // parallel()       并行
        // skip()           跳过
        Stream.of(9, 7, 5, 3, 1).parallel().skip(2).forEach(i -> System.out.print(i + " ")); // 3 1 5
        p(StringUtils.CR);

        // takeWhile()      返回子集知道断言返回false，JDK9 引入
        // dropWhile()      断言返回false开始返回子集，JDK9 引入
        Stream.of(9, 3, 5, 7, 1).takeWhile(i -> i != 1).dropWhile(i -> i != 3).forEach(i -> System.out.print(i + " ")); // 3 5 7

        // close()          关闭 Stream 对象
        Stream.of(9, 7, 5, 3, 1).close();
    }

    /**
     * terminal operation
     * <p>终端操作
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
        p(Stream.of(1, 2, 3).map(i -> List.of(i, i * 2, i * 3)).collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll));
    }

    /**
     * other operation
     * <p>其它操作
     */
    @Test
    public void other() {
        // Optional<T>	    findFirst()                 返回 Optional(first)，空 Stream 返回 Optional.empty()
        p(Stream.of(arr).findFirst().orElse(null));     // 1
        // Optional<T>	    findAny()                   串行返回 Optional(first)，并行返回 Optional(any)，空 Stream 返回 Optional.empty()
        p(Stream.of(arr).findAny().orElse(null));       // 1

        // boolean          allMatch(IntPredicate)      全匹配
        p(Stream.of(arr).allMatch(i -> i > 5));         // false
        // boolean          anyMatch(IntPredicate)      任意一个匹配
        p(Stream.of(arr).anyMatch(i -> i > 5));         // true
        // boolean          noneMatch(IntPredicate)     全匹配
        p(Stream.of(arr).noneMatch(i -> i > 5));        // false

        // XxxStream        mapToXxx(XxxFunction<? super T>)                        经过 XxxFunction Stream → XxxStream
        // 当流操作均为数值操作时，将普通流转换成数值流，能获得较高的效率
        IntStream intStream = Stream.of(arr).mapToInt(value -> value);
        // XxxStream        flatMapToInt(Function<? super T, ? extends IntStream>)  经过 Function Stream → XxxStream
        IntStream intStream2 = Stream.of(arr).flatMapToInt(IntStream::of);
    }

    private final List<Person> personList = List.of(
            new Person("王五", 22, "男"),
            new Person("李四", 21, "女"),
            new Person("张三", 20, "男"),
            new Person("张三", 23, "男"));

    /** 对象集合按属性去重 */
    @Test
    public void testDistinct() {
        // 使用 collectingAndThen 先执行归约操作（List → Set 实现去重），再执行 Function 操作（Set → List）
        p(personList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>((p1, p2) -> new CompareToBuilder().append(p1.getName(), p2.getName()).toComparison())), ArrayList::new)));

        // 如果不需要转成 List，可以不使用 collectingAndThen
        p(personList.stream().collect(Collectors.toCollection(() -> new TreeSet<>((p1, p2) -> new CompareToBuilder().append(p1.getName(), p2.getName()).toComparison()))));

        // 当 name 为 null，这种方法会报 NullPointerException
        p(personList.stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName)))));
    }

    /** 对象集合按属性去重2 ??? */
    @Test
    public void testDistinct2() {
        // 单属性去重
        p(personList.stream().filter(this.distinctByKey(Person::getName)).collect(Collectors.toList()));
        // 多属性去重
        p(personList.stream().filter(this.distinctByKey(p -> Stream.of(p.getName(), p.getGender()))).collect(Collectors.toList()));
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
