package knowledge.算法.backtracking;

/**
 * 数字组合
 * 从n个数中选取r个数的所有组合
 * <p>
 * https://www.cnblogs.com/yoona-lin/p/10510518.html
 * https://www.cnblogs.com/xiaonongpiaoliang/p/5289243.html
 */
public class CombinationOfNumber {
    private static int N = 6;
    private static int R = 3;
    public static int[] a = new int[N];
    private static int[] b = new int[R];

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            a[i] = i + 1;
        }

        comb(N, R);
    }

    /**
     * 回溯法
     */
    private static void comb(int n, int r) {
        for (int i = n; i >= r; i--) {
            b[r - 1] = a[i - 1];
            if (r > 1) {
                comb(i - 1, r - 1);
            } else {
                for (int j = R - 1; j >= 0; j--)
                    System.out.print(b[j] + " ");
                System.out.println();
            }
        }
    }
}