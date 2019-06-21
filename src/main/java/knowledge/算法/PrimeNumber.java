package knowledge.算法;

import java.util.Random;

public class PrimeNumber {

    /**
     * 输出 n 内的质数（每行输出10个）
     */
    public static void main(String[] args) {
        Random ran = new Random();
        int n = ran.nextInt(50) + 51;
        System.out.println("输出" + n + "内的质数：");

        int count = 0;
        for (int i = 2; i <= n; i++) {
            boolean flag = false;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.print(i + " ");
                if (++count % 10 == 0) {
                    System.out.println();
                }
            }
        }
    }

}
