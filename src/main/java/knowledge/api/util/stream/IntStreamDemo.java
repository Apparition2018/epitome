package knowledge.api.util.stream;

import l.demo.Demo;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * IntStream
 * IntStream 支持顺序和并行聚合操作的 int 序列
 * <p>
 * IntStream 用法全解：https://blog.csdn.net/qq_31865983/article/details/106443244
 * 玩转Java8Stream（四、IntStream）：https://www.jianshu.com/p/461429a5edc9
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html
 *
 * @author ljh
 * created on 2020/11/6 14:43
 */
public class IntStreamDemo extends Demo {

    /**
     * 创建
     */
    @Test
    public void create() {
        IntStream intStream;

        //********** 1.具体元素 **********//
        // IntStream.of()
        intStream = IntStream.of(1, 2, 3, 4, 5);
        // IntStream.builder.add()...build()
        intStream = IntStream.builder().add(1).add(2).add(3).add(4).add(5).build();

        //********** 2.范围内元素 **********//
        // IntStream.range()
        intStream = IntStream.range(1, 6);
        // IntStream.rangeClosed()
        intStream = IntStream.rangeClosed(1, 5);

        //********** 3.合并两个 Stream **********//
        // IntStream.concat()
        intStream = IntStream.concat(intStream, intStream);

        //********** 4.指定生成函数 **********//
        // IntStream.generate()
        intStream = IntStream.generate(() -> new Random().nextInt(100)).limit(5);
        // IntStream.iterate()
        intStream = IntStream.iterate(0, operand -> ++operand).limit(5);
    }

    @Test
    public void intermediate() {
        
    }
}
