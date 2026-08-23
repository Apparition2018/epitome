package org.ljh.dependencies.group.apache.commons.lang3.mutable;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.jupiter.api.Test;

/**
 * Mutable      可变包装类
 * <p>基本类型都有相应的包装类型，但是包装类型不能参加加、减、乘、除运算。
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class MutableTests {

    @Test
    public void test() {
        MutableInt mi = new MutableInt(10);
        mi.add(10);
        System.out.println(mi); // 20

        mi.increment();
        System.out.println(mi); // 21
    }
}
