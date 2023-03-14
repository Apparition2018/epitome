package knowledge.api.util.stream;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html">IntStream</a>
 * <p>支持顺序和并行聚合操作的 int 序列，类似的还有 LongStream, DoubleStream
 * <p>大部分 API 跟 Stream 一样 {@link StreamDemo}
 * <pre>
 * PrimitiveIterator.OfInt      iterator()
 * Spliterator.OfInt            spliterator()
 * </pre>
 * 参考：<a href="https://blog.csdn.net/qq_31865983/article/details/106443244">IntStream 用法全解</a>
 *
 * @author ljh
 * @since 2020/11/6 14:43
 */
public class IntStreamDemo {

    @Test
    public void testIntStream() {
        IntStream intStream;

        // ints()                               通过 Random 的 ints() 生成 IntStream                     
        intStream = new Random().ints(0, 10).limit(9);
        // IntStream.range(start, end)          范围内元素，不包括 end
        intStream = IntStream.range(1, 11);
        // IntStream.rangeClosed(start, end)    范围内元素，包括 end
        intStream = IntStream.rangeClosed(1, 10);

        // average()                            平均数              
        System.out.println(intStream.average());
        // sum()                                总和
        System.out.println(intStream.sum());
    }

    @Test
    public void testToStream() {
        IntStream intStream = IntStream.rangeClosed(1, 10);

        // IntStream → Stream
        Stream<String> stream1 = intStream.mapToObj(String::valueOf);
        Stream<Integer> stream2 = intStream.boxed();

        // IntStream → LongStream
        LongStream longStream1 = intStream.asLongStream();
        LongStream longStream2 = intStream.mapToLong(i -> (long) i);

        // IntStream → DoubleStream
        DoubleStream doubleStream1 = intStream.asDoubleStream();
        DoubleStream doubleStream2 = intStream.mapToDouble(i -> (double) i);
    }
}
