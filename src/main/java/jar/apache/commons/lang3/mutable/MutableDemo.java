package jar.apache.commons.lang3.mutable;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;

/**
 * 可变包装类
 *
 * 基本类型都有相应的包装类型，但是包装类型不能参加加、减、乘、除运算。
 */
public class MutableDemo {

    @Test
    public void mutable() {
        MutableInt mi = new MutableInt(10);
        mi.add(10);
        System.out.println(mi); // 20

        mi.increment();
        System.out.println(mi); // 21
    }

}
