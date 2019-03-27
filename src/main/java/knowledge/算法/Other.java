package knowledge.算法;

import org.junit.Test;

public class Other {

    /**
     * 交换位置
     */
    @Test
    public void swap() {
        int i = 6;
        int j = 9;

        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
        System.out.println("i = " + i);
        System.out.println("j = " + j);

        i = i + j;
        j = i - j;
        i = i - j;
        System.out.println("i = " + i);
        System.out.println("j = " + j);
    }

    /**
     * 求 a + aa + aaa + aaaa + aaaaa = ?
     */
    @Test
    public void sumOfSeq() {
        int a = 4;
        int n = 0;
        int sum = 0;
        for (int i = 1; i <= 5; i++) {
            n = n * 10 + a;
            sum += n;
            if (i == 1) {
                System.out.print(n);
            } else {
                System.out.print("+" + n);
            }
        }
        System.out.println("=" + sum);
    }

}
