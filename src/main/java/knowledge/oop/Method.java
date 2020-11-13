package knowledge.oop;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * 方法
 * <p>
 * 修饰符 返回值类型 方法名 参数类型 方法体
 */
public class Method {

    private static int max;

    /**
     * 构造方法
     * <p>
     * 当一个对象被创建时，构造方法用来初始化对象
     * 构造方法和它所在类的名字相同
     * 构造方法没有返回值
     * Java 会提供一个默认的无参构造方法
     * 一旦定义了自定义构造方法，默认构造方法就会失效
     */
    public Method() {
        max = Integer.MAX_VALUE;
    }

    public int max(int num1, int num2) {
        return Math.max(num1, num2);
    }

    final int min(int num1, int num2) {
        return Math.min(num1, num2);
    }

    /**
     * 可变参数 (varargs)
     * <p>
     * JDK 1.5 开始，Java 支持传递同类型的可变参数给一个方法
     * 一个方法中只能指定一个可变参数，它必须是方法的最后一个参数
     * 可变参数的各个参数用 "," 隔开，也可以直接传入一个数组
     */
    public int getMax(int... nums) {
        IntStream.range(0, nums.length).forEach(i -> max = Math.max(nums[i], max));
        return max;
    }

    @Test
    public void testVarargs() {
        System.out.println(getMax(3, 7, 1, 5, 9));  // 9

        int[] arr = {3, 7, 1, 5, 9};
        System.out.println(getMax(arr));            // 9
    }

    /**
     * 方法重载
     * 1.方法名相同，参数列表不同，返回值类型无关
     * 2."编译期绑定"，看引用类型（编译时多态）
     * 3.一个类的多态性表现
     * 4.可以声明新的或更广的检查异常
     */
    private int max(int num1, int num2, int num3) {
        return max(num1, max(num2, num3));
    }

    private static class ChildMethod extends Method {

        /**
         * 方法重写
         * 1.方法名相同，参数列表相同，返回值类型相同，访问权限>=父
         * 2."运行时绑定"，看对象类型（编译时多态）
         * 3.子类与父类的一种多态性表现
         * 4.不能抛出新的检测性异常，或比重写方法更广泛的检测性异常
         * <p>
         * 5.final 方法不能被子类重写和再次声明
         * 6.static 方法不能被子类重写，但可以再次声明
         * 7.构造方法不能被重写
         */
        public int max(int num1, int num2) {
            return num1 > num2 ? num1 : num2;
        }
    }

}
