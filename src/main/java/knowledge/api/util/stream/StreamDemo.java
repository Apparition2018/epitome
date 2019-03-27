package knowledge.api.util.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.*;

/**
 * Stream
 * <p>
 * Stream 是 Java8 的新特性
 * Stream 是单向的，不可往复的，数据只能遍历一次，遍历过一次后即用尽了
 * <p>
 * https://blog.csdn.net/icarusliu/article/details/79495534
 * https://blog.csdn.net/icarusliu/article/details/79504602
 * http://www.runoob.com/java/java8-streams.html
 * https://blog.csdn.net/u011001723/article/details/52794455
 */
public class StreamDemo {

    /**
     * Stream 对象的创建
     */
    @Test
    public void createStream() {
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        Stream stream;

        // 通过 Stream 中的 empty() 创建
        stream = Stream.empty();

        // 通过 Collection 中的 stream() 创建
        stream = list.stream();

        // 通过 Collection 中的 parallelStream() 创建
        stream = list.parallelStream();

        // 通过 Stream 中的 of 创建
        stream = Stream.of(list);

        // 通过 Stream 中的 iterate() 创建
        stream = Stream.iterate(1, integer -> integer + 1).limit(10);
        System.out.println(stream.collect(Collectors.toList())); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // 通过 Stream 中的 generate() 创建
        stream = Stream.generate(() -> (int) (Math.random() * 100)).limit(10);
        System.out.println(stream.collect(Collectors.toList())); // [44, 32, 27, 58, 74, 98, 75, 30, 47, 59]

        Random random = new Random();

        // 通过 Random 中的 xxxs() 创建
        IntStream ints = random.ints();
        LongStream longs = random.longs();
        DoubleStream doubles = random.doubles();

    }

    /**
     * 中间操作
     * intermediate operation
     */
    @Test
    public void intermediate() {
        // distinct()       去重
        // map()            元素一对一执行，有返回
        // peek()           元素一对一执行，无返回
        List<Integer> list = Stream.of(3, 2, 2, 3, 7, 3, 5)
                .map(i -> i * i).distinct()
                .peek(n -> System.out.print(n + " ")) //  https://segmentfault.com/q/1010000004681887
                .collect(Collectors.toList());
        System.out.println("\n");

        // filter()         过滤
        // sorted()         排序
        // limit()          返回指定元素个数的流
        new Random().ints(0, 20).limit(10)
                .filter(n -> n % 2 == 0).sorted()
                .forEach(n -> System.out.print(n + " ")); // 8 8 10 14
        System.out.println("\n");

        // sequential()     返回串行 Stream 对象
        // parallel()       返回并行 Stream 对象
        // skip()           跳过指定个数元素
        Stream<Integer> s = Stream.of(9, 7, 5, 3, 1);
        s.parallel().skip(2).forEach(n -> System.out.print(n + " ")); // 3 1 5

        // close()          关闭 Stream 对象
        s = Stream.of(9, 7, 5, 3, 1);
        s.close();
        s.sequential().forEach(n -> System.out.print(n + " ")); // stream has already been operated upon or closed
    }

    /**
     * 终端操作
     * terminal operation
     */
    @Test
    public void terminal() {
        // count()          返回元素的个数
        System.out.println(Stream.of(9, 7, 5, 3, 1).count()); // 5

        // forEach()        迭代所有元素
        Stream.of(9, 7, 5, 3, 1).forEach(n -> System.out.print(n + " ")); // 9 7 5 3 1
        System.out.println();

        // forEachOrdered() 有序迭代所有元素
        Stream.of(9, 7, 5, 3, 1).parallel().forEachOrdered(n -> System.out.print(n + " ")); // 9 7 5 3 1
        System.out.println();

        // isParallel       判断当前 Stream 是否并行
        System.out.println(Stream.of(9, 7, 5, 3, 1).isParallel()); // false

        // toArray()        返回所有元素的数组
        Object[] arr = Stream.of(9, 7, 5, 3, 1).toArray();
        System.out.println(Arrays.toString(arr)); // [9, 7, 5, 3, 1]

        // max()            返回元素中最大的 Optional 对象
        // min()            返回元素中最小的 Optional 对象
        // findFirst()      返回第一个元素的 Optional 对象
        System.out.println(Stream.of(3, 1, -1, -3).max(Integer::compare).get());    // 3
        System.out.println(Stream.of(3, 1, -1, -3).min(Integer::compare).get());    // -3
        System.out.println(Stream.of(3, 1, -1, -3).findFirst().get());              // 3

        // reduce()         https://blog.csdn.net/icarusliu/article/details/79504602
        Integer sum;
        sum = Stream.of(1, 2, 3, 4, 5, 6).reduce((i1, i2) -> i1 + 2).get();
        sum = Stream.of(1, 2, 3, 4, 5, 6).reduce(7, (i1, i2) -> i1 + 2);

        // collect()        https://blog.csdn.net/icarusliu/article/details/79504602
        List<Integer> list = Stream.of(9, 7, 5, 3, 1).collect(Collectors.toList());
    }

    /**
     * Collectors
     * Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。
     */
    @Test
    public void collectors() {
        List<Integer> list = Stream.of(9, 7, 5, 3, 1).collect(Collectors.toList());
        System.out.println(list);   // [9, 7, 5, 3, 1]

        String str = Stream.of("9", "7", "5", "3", "1").collect(Collectors.joining(","));
        System.out.println(str);    // 9,7,5,3,1
    }

    /**
     * summaryStatistics
     */
    @Test
    public void statistics() {
        IntSummaryStatistics stats = Stream.of(3, 2, 2, 3, 7, 3, 5).mapToInt(x -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());  // 7
        System.out.println("列表中最小的数 : " + stats.getMin());  // 2
        System.out.println("所有数之和 : " + stats.getSum()); // 25
        System.out.println("平均数 : " + stats.getAverage()); // 3.5714285714285716
    }

}