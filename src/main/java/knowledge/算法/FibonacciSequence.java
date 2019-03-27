package knowledge.算法;

/**
 * 斐波那契数列
 * <p>
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584 ...
 * <p>
 * 第0项是0，第1项是1，从第3项开始，每一项等于前两项之和
 */
public class FibonacciSequence {

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    private static long fibonacci(long num) {
        return (num == 0 || num == 1) ? num : fibonacci(num - 1) + fibonacci(num - 2);
    }

}
