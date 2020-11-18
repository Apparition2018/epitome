package knowledge.api.util;

import l.demo.Demo;
import org.junit.Test;

import java.util.Arrays;
import java.util.Spliterator;

/**
 * Spliterator
 * Spliterator 和 Iterator 一样，也用于遍历数据源中的元素，但它是为了并行执行而设计的。
 * 可以遍历 Array, Collection, IO Channel, Generator Function
 * <p>
 * default Comparator<? super T>	getComparator()         返回 Comparator
 * <p>
 * Java8 中 Spliterator 详解：https://blog.csdn.net/sl1992/article/details/100149187
 * https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html
 *
 * @author Arsenal
 * created on 2020/11/7 1:08
 */
public class SpliteratorDemo extends Demo {

    @Test
    public void testSpliterator() {
        Spliterator<Integer> spliterator = Arrays.spliterator(arr);

        // int	            characteristics()
        // 返回 Spliterator 拥有的特征值
        p(spliterator.characteristics());               // 17488

        // default boolean	hasCharacteristics(int characteristics)
        // 判断是否包含指定特征
        p(spliterator.hasCharacteristics(
                Spliterator.ORDERED));                  // true

        // long	            estimateSize()
        // 返回估算还剩多少个元素需要遍历，最大返回 Long.MAX_VALUE
        p(spliterator.estimateSize());                  // 9
        // default long	    getExactSizeIfKnown()
        // 便捷方法，如果 Spliterator 拥有 SIZED，则返回 estimateSize()，否者返回 -1
        p(spliterator.getExactSizeIfKnown());           // 9

        // boolean          tryAdvance(Consumer<? super T> action)
        // 如果存在剩余的元素，经过 Consumer 处理，返回 true;否则返回 false
        p(spliterator.tryAdvance(i -> {
        }));            // true
        p(spliterator.estimateSize());                  // 8

        // default void	    forEachRemaining(Consumer<? super T> action)
        // 顺序地经过 Consumer 处理，直到所有元素已被处理或抛出异常。默认调用 tryAdvance()
        spliterator.forEachRemaining(System.out::print);// 23456789
        p(spliterator.estimateSize());                  //
    }
}
