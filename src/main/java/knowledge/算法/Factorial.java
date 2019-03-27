package knowledge.算法;

/**
 * 阶乘
 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println(factorial(10));
    }

    private static long factorial(long num) {
        return num <= 1 ? 1 : num * factorial(num - 1);
    }

}
