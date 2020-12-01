package knowledge.syntactic.sugar;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * 语法糖
 * Java 中常用的语法糖主要有泛型、变长参数、自动拆装箱、方法变长参数、枚举、内部类、条件编译、断言、for-each、try-with-resources、lambda 等
 * Java 语法糖详解：https://www.cnblogs.com/helloworld2048/p/10916453.html
 *
 * @author ljh
 * created on 2020/9/24 11:57
 */
public class SyntacticSugar {

    /**
     * 可变参数 (varargs)
     * <p>
     * JDK1.5 开始，Java 支持传递同类型的可变参数给一个方法
     * 一个方法中只能指定一个可变参数，它必须是方法的最后一个参数
     * 可变参数的各个参数用 "," 隔开，也可以直接传入一个数组
     */
    public int getMax(int... nums) {
        IntStream.range(0, nums.length).forEach(i -> max = Math.max(nums[i], max));
        return max;
    }

    private static int max;

    @Test
    public void testVarargs() {
        System.out.println(getMax(3, 7, 1, 5, 9));  // 9

        int[] arr = {3, 7, 1, 5, 9};
        System.out.println(getMax(arr));            // 9
    }

}
