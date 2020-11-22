package knowledge.api.util.stream;

import l.demo.Demo;
import l.demo.Person;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream
 * Stream 支持元素流功能性操作的类，是 JDK8 的新特性。
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 * <p>
 * Stream 应该只允许被中间或终端操作操作一次，如 Stream 被检测到重用，将抛出 IllegalStateException。
 * 如果实在要重复操作同一个 Stream，可以通过 Supplier 获取新的 Stream (参考 SupplierDemo testSupplier())
 * <p>
 * https://blog.csdn.net/icarusliu/article/details/79495534
 * https://blog.csdn.net/icarusliu/article/details/79504602
 * https://blog.csdn.net/u011001723/article/details/52794455
 * http://www.runoob.com/java/java8-streams.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class StreamDemo extends Demo {

    @Test
    public void testStream() {

        // List<Object> -> List<Integer>
        List<Integer> idList = personList.stream().map(Person::getId).collect(Collectors.toList());
        p(idList);  // [1, 2, 3]

        // List<Object> -> Map<Integer, Object>
        Map<Integer, Person> map = personList.stream().collect(Collectors.toMap(Person::getId, person -> person));
        p(map);     // {1=Person{id=1, name='张三'}, 2=Person{id=2, name='李四'}, 3=Person{id=3, name='王五'}}
    }


    /**
     * 创建
     */
    @Test
    public void createStream() {
        Stream<?> stream;

        //********** 1.通过 Collection **********//
        // stream()
        stream = list.stream();
        // parallelStream()
        stream = list.parallelStream();

        //********** 2.具体元素 **********//
        // Stream.of()
        stream = Stream.of(arr);
        // Stream.build().add()...build()
        stream = Stream.builder().add(1).add(2).add(3).add(4).add(5).build();
        // Stream.empty()
        stream = Stream.empty();

        //********** 3.指定生成函数 **********//
        // Stream.generate()
        stream = Stream.generate(() -> new Random().nextInt(100)).limit(9);
        // Stream.iterate()
        stream = Stream.iterate(0, i -> ++i).limit(9);

        //********** 3.其他 Stream 装箱 **********//
        // XxxStream.boxed()
        stream = new Random().ints(0, 10).limit(9).boxed();

        //********** 5.合并两个 Stream **********//
        // Stream.concat()
        stream = Stream.concat(stream, stream);
    }

    /**
     * intermediate operation
     * 中间操作
     * <p>
     * 返回值为 Stream 的操作都是惰性求值
     * https://blog.csdn.net/zebe1989/article/details/82692508
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
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).limit(7)
                .filter(n -> n % 2 == 0).sorted()
                .forEach(n -> System.out.print(n + " ")); // 2 4 6
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
        p(Stream.of(1, 2, 3, 4, 5, 6).reduce((i1, i2) -> i1 + 2).get()); // 19
        p(Stream.of(1, 2, 3, 4, 5, 6).reduce(7, (i1, i2) -> i1 + 2));    // 19

        // collect()
        List<Integer> list = Stream.of(9, 7, 5, 3, 1).collect(Collectors.toList());
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
        IntStream intStream = Stream.of(arr).mapToInt(value -> value);
        // XxxStream        flatMapToInt(Function<? super T, ? extends IntStream>)  经过 Function Stream → XxxStream
        IntStream intStream2 = Stream.of(arr).flatMapToInt(IntStream::of);
    }

    /**
     * Collectors
     * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。
     */
    @Test
    public void collectors() {
        // toCollection(), toList(), toSet(), toMap(), toConcurrentMap()
        List<Integer> list = Stream.of(9, 7, 5, 3, 1).collect(Collectors.toList());
        p(list);   // [9, 7, 5, 3, 1]

        // joining()
        String str = Stream.of("9", "7", "5", "3", "1").collect(Collectors.joining(","));
        p(str);    // 9,7,5,3,1
    }

}