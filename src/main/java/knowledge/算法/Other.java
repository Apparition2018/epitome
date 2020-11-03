package knowledge.算法;

import l.demo.Demo;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class Other extends Demo {

    /**
     * 交换位置
     */
    @Test
    public void testSwap() {
        int i = 6;
        int j = 9;

        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
        p("i = " + i);
        p("j = " + j);

        i = i + j;
        j = i - j;
        i = i - j;
        p("i = " + i);
        p("j = " + j);
    }

    /**
     * 求 a + aa + aaa + aaaa + aaaaa = ?
     */
    @Test
    public void testSumOfSeq() {
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
        p("=" + sum);
    }

    /**
     * 输出 n 内的质数（每行输出10个）
     */
    @Test
    public void testPrimeNumber() {
        java.util.Random ran = new Random();
        int n = ran.nextInt(50) + 51;
        p("输出" + n + "内的质数：");

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
                    p();
                }
            }
        }
    }

    /**
     * 99 乘法表
     */
    @Test
    public void testMultiplicationTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "×" + i + "=" + i * j + "\t");// \t 跳到下一个TAB位置
            }
            p();
        }
    }

    /**
     * 生成 m ~ n 位随机字符数组
     */
    @Test
    public void testGenRanArr() {
        char[] chs = new char[RandomUtils.nextInt(4, 10)];
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; // 随机字符范围数组
        int len = letters.length;
        boolean[] flags = new boolean[len];
        for (int i = 0; i < chs.length; i++) {
            int index;
            do {
                index = (int) (Math.random() * len);
            } while (flags[index]);
            chs[i] = letters[index];
            flags[index] = true;
        }

        p(chs);
    }

    /**
     * 打印平行四边形
     */
    @Test
    public void testPrintParallelogram() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 10; k++) {
                System.out.print("*");
            }
            p();
        }
    }

    /**
     * 打印菱形
     */
    @Test
    public void testPrintDiamond() {
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i + 1; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            p();
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 4 * 2 - 1 - i * 2; j++) {
                System.out.print("*");
            }
            p();
        }
    }

}