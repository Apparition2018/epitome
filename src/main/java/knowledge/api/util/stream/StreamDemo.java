package knowledge.api.util.stream;

import l.demo.Demo;
import l.demo.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.*;

/**
 * Stream
 * Stream 支持元素流功能性操作的类，是 JDK8 的新特性。
 * https://www.matools.com/file/manual/jdk_api_1.8_google/java/util/stream/Stream.html
 * <p>
 * Stream 应该只允许被中间或终端操作操作一次，如 Stream 被检测到重用，将抛出 IllegalStateException。
 * 如果实在要重复操作同一个 Stream，可以通过 Supplier 获取新的 Stream (参考 SupplierDemo testSupplier())
 * <p>
 * https://blog.csdn.net/icarusliu/article/details/79495534
 * https://blog.csdn.net/icarusliu/article/details/79504602
 * https://blog.csdn.net/u011001723/article/details/52794455
 * http://www.runoob.com/java/java8-streams.html
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
     * 创建 Stream 对象
     */
    @Test
    public void createStream() {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        Stream<?> stream;

        // 通过 Collection 中的 stream() 创建
        stream = list.stream();

        // 通过 Collection 中的 parallelStream() 创建
        stream = list.parallelStream();

        // 通过 Stream 中的 empty() 创建
        stream = Stream.empty();

        // 通过 Stream 中的 of 创建
        stream = Stream.of(list);

        // 通过 Stream 中的 iterate() 创建，无限连续有序 Stream
        stream = Stream.iterate(0, integer -> integer + 2).limit(5);
        p(stream.collect(Collectors.toList())); // [0, 2, 4, 6, 8]

        // 通过 Stream 中的 generate() 创建，无限连续无序 Stream
        stream = Stream.generate(() -> (int) (Math.random() * 100)).limit(10);
        p(stream.collect(Collectors.toList())); // [44, 32, 27, 58, 74, 98, 75, 30, 47, 59]

        // 通过 Random 中的 xxxs() 创建
        IntStream ints = new Random().ints(0, 20).limit(10);
        p(ints.boxed().collect(Collectors.toList()));   // [11, 4, 5, 4, 4, 17, 8, 17, 0, 16]
        LongStream longs = new Random().longs(0, 20).limit(10);
        p(longs.boxed().collect(Collectors.toList()));  // [9, 15, 4, 18, 5, 7, 2, 10, 1, 15]
        DoubleStream doubles = new Random().doubles(0, 20).limit(5);
        p(doubles.boxed().collect(Collectors.toList()));// [13.614316700842945, 12.76938839840684, 5.622812500500778, 17.309614249352602, 18.441259588808695]

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
        // peek()           元素一对一执行，无返回
        List<Integer> list = Stream.of(3, 2, 2, 3, 7, 3, 5)
                .map(i -> i * i).distinct()
                .peek(n -> System.out.print(n + " ")) // 9 4 49 25
                .collect(Collectors.toList());
        p("\n");

        // filter()         过滤
        // sorted()         排序
        // limit()          返回指定元素个数的流
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).limit(7)
                .filter(n -> n % 2 == 0).sorted()
                .forEach(n -> System.out.print(n + " ")); // 2 4 6 8 
        p("\n");

        // sequential()     返回串行 Stream 对象
        // parallel()       返回并行 Stream 对象
        // skip()           跳过指定个数元素
        Stream.of(9, 7, 5, 3, 1).parallel().skip(2).forEach(n -> System.out.print(n + " ")); // 3 1 5;

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
        Integer sum;
        sum = Stream.of(1, 2, 3, 4, 5, 6).reduce((i1, i2) -> i1 + 2).get();
        sum = Stream.of(1, 2, 3, 4, 5, 6).reduce(7, (i1, i2) -> i1 + 2);

        // collect()
        List<Integer> list = Stream.of(9, 7, 5, 3, 1).collect(Collectors.toList());
    }

    /**
     * Collectors
     * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。
     */
    @Test
    public void collectors() {
        List<Integer> list = Stream.of(9, 7, 5, 3, 1).collect(Collectors.toList());
        p(list);   // [9, 7, 5, 3, 1]

        String str = Stream.of("9", "7", "5", "3", "1").collect(Collectors.joining(","));
        p(str);    // 9,7,5,3,1
    }

    /**
     * XXXSummaryStatistics
     */
    @Test
    public void statistics() {
        IntSummaryStatistics stats = Stream.of(3, 2, 2, 3, 7, 3, 5).mapToInt(x -> x).summaryStatistics();

        p("列表中最大的数 : " + stats.getMax());  // 7
        p("列表中最小的数 : " + stats.getMin());  // 2
        p("所有数之和 : " + stats.getSum()); // 25
        p("平均数 : " + stats.getAverage()); // 3.5714285714285716
    }

}