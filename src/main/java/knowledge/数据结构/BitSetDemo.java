package knowledge.数据结构;

import l.demo.Demo;
import org.junit.Test;

import java.util.BitSet;

/**
 * BitSet (未完待续)
 * 实现了一个按需增长的位向量。位 set 的每个组件都有一个 boolean 值。
 * https://jdk6.net/util/BitSet.html
 * https://blog.csdn.net/jiangnan2014/article/details/53735429
 * <p>
 * boolean	    get(int bitIndex)                   返回指定索引处的位值
 * BitSet	    get(int fromIndex, int toIndex)     返回一个新的 BitSet，它由此 BitSet 中从 fromIndex（包括）到 toIndex（不包括）范围内的位组成
 * <p>
 * void	        clear()                             将此 BitSet 中的所有位设置为 false
 * void	        clear(int bitIndex)                 将索引指定处的位设置为 false
 * void	        clear(int fromIndex, int toIndex)   将指定的 fromIndex（包括）到指定的 toIndex（不包括）范围内的位设置为 false
 * <p>
 * void	        flip(int bitIndex)                  将指定索引处的位设置为其当前值的补码
 * void	        flip(int fromIndex, int toIndex)    将指定的 fromIndex（包括）到指定的 toIndex（不包括）范围内的每个位设置为其当前值的补码
 * <p>
 * int	        cardinality()                       返回此 BitSet 中设置为 true 的位数
 */
public class BitSetDemo extends Demo {

    @Test
    public void testBitSet() {
        // BitSet([int nbits])      创建一个位 set，它的初始大小足以显式表示索引范围在 0 到 nbits-1 的位
        BitSet bs1 = new BitSet(16);
        BitSet bs2 = new BitSet(16);
        BitSet bs3 = new BitSet(16);

        for (int i = 0; i < 16; i++) {
            // void	    set(int bitIndex[, boolean value])                  将指定索引处的位设置为指定的值
            // void	    set(int fromIndex, int toIndex[, boolean value])    将指定的 fromIndex（包括）到指定的 toIndex（不包括）范围内的位设置为指定的值
            if (i % 2 == 0) bs1.set(i);
            if (i % 5 != 0) bs2.set(i);
            if (i % 3 != 0) bs3.set(i);
        }

        p(bs1); // bs1: {0, 2, 4, 6, 8, 10, 12, 14}
        p(bs2); // bs2: {1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14}
        p(bs3); // bs3: {1, 2, 4, 5, 7, 8, 10, 11, 13, 14}

        // void	        and(BitSet set)             逻辑与
        bs2.and(bs1);
        p(bs2); // bs2: {2, 4, 6, 8, 12, 14}

        // void	        or(BitSet set)              逻辑或
        bs2.or(bs1);
        p(bs2); // bs2: {0, 2, 4, 6, 8, 10, 12, 14}

        // void	        xor(BitSet set)             逻辑异或
        bs2.xor(bs3);
        p(bs2); // bs2: {0, 1, 5, 6, 7, 11, 12, 13}
    }
}
