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
        p(mi); // 20

        mi.increment();
        p(mi); // 21
    }

    public static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
