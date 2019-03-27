package knowledge.数据结构;

import org.junit.Test;

import java.util.BitSet;

/**
 * BitSet
 * <p>
 * 一个 BitSet 类创建一种特殊类型的数组来保存位值。
 * BitSet 中数组大小会随需要增加。
 */
public class BitSetDemo {

    /**
     * BitSet([int nbits])
     * 创建一个位 set，它的初始大小足以显式表示索引范围在 0 到 nbits-1 的位
     */
    @Test
    public void bitSet() {
        // 创建 BitSet
        BitSet bs1 = new BitSet(16);
        BitSet bs2 = new BitSet(16);
        BitSet bs3 = new BitSet(16);

        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) bs1.set(i);
            if (i % 5 != 0) bs2.set(i);
            if (i % 3 != 0) bs3.set(i);
        }

        System.out.println(bs1); // bs1: {0, 2, 4, 6, 8, 10, 12, 14}
        System.out.println(bs2); // bs2: {1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14}
        System.out.println(bs3); // bs3: {1, 2, 4, 5, 7, 8, 10, 11, 13, 14}

        // AND
        bs2.and(bs1);
        System.out.println(bs2); // bs2: {2, 4, 6, 8, 12, 14}

        // OR
        bs2.or(bs1);
        System.out.println(bs2); // bs2: {0, 2, 4, 6, 8, 10, 12, 14}

        // XOR
        bs2.xor(bs3);
        System.out.println(bs2); // bs2: {0, 1, 5, 6, 7, 11, 12, 13}
    }

    /**
     * void	and(BitSet set)
     * 对此目标位 set 和参数位 set 执行逻辑与操作
     */
    @Test
    public void and() {
        bitSet();
    }

    /**
     * void	or(BitSet set)
     * 对此位 set 和位 set 参数执行逻辑或操作
     */
    @Test
    public void or() {
        bitSet();
    }

    /**
     * void	xor(BitSet set)
     * 对此位 set 和位 set 参数执行逻辑异或操作
     */
    @Test
    public void xor() {
        bitSet();
    }
}
