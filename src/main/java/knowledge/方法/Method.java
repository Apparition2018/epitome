package knowledge.方法;

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
    Method() {
        max = Integer.MIN_VALUE;
    }

    public static int max(int num1, int num2) {
        return num1 > num2 ? num1 : num2;
    }

    /**
     * 可变参数 (varargs)
     * <p>
     * JDK 1.5 开始，Java支持传递同类型的可变参数给一个方法 （数量可变）
     * 一个方法中只能指定一个可变参数，它必须是方法的最后一个参数
     * 可变参数的各个参数用 "," 隔开，也可以直接传入一个数组
     */
    public static int getMax(int... nums) {
        for (int num : nums) {
            max = num > max ? num : max;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(getMax(3, 7, 1, 5, 9)); // 9

        int[] arr = {3, 7, 1, 5, 9};
        System.out.println(getMax(arr));                   // 9
    }

}
