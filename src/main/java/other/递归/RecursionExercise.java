package other.递归;

public class RecursionExercise {

    public static void main(String[] args) {
        System.out.println(getBinary(3));
        System.out.println(doFactorial(4));
    }

    /**
     * 计算二进制中1的个数
     */
    private static int getBinary(int num) {
        if (num == 1) {
            return 1;
        } else if (num % 2 == 0) {
            return getBinary(num / 2);
        } else {
            return 1 + getBinary(num / 2);
        }
    }

    /**
     * 求n!的算法
     */
    private static long doFactorial(long n) {
        if (n < 1) {
            System.out.println("error");
            return 0;
        } else if (n == 1 || n == 2) {
            return n;
        } else {
            return n * doFactorial(n - 1);
        }
    }
}
