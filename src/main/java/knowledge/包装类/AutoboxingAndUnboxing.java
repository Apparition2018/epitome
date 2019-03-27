package knowledge.包装类;

import org.junit.Test;

/**
 * 自动装箱与拆箱
 *
 * Java 5 开始提供了自动装箱和自动拆箱
 */
public class AutoboxingAndUnboxing {

    @Test
    public void auto() {
        Integer num1 = 10;  // 自动装箱，Integer num1 = Integer.valueOf(10);
        int num2 = num1;    // 自动拆箱，int num2 = num1.intValue();

        System.out.println(num1 == num2);
    }


}
