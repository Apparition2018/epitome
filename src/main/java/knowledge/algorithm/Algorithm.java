package knowledge.algorithm;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static l.demo.Demo.p;

/**
 * 算法
 * <p>参考：<a href="https://mp.weixin.qq.com/s/nQ0aNLPcwb2enmrlqCsvOg">「时间」与「空间」复杂度</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class Algorithm {

    /** 求 a + aa + aaa + aaaa + aaaaa = ? */
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

    /** 输出 n 内的质数（每行输出10个） */
    @Test
    public void testPrimeNumber() {
        Random random = new Random();
        int n = random.nextInt(50) + 51;
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
                System.out.print(i + StringUtils.SPACE);
                if (++count % 10 == 0) {
                    p();
                }
            }
        }
    }

    /** 99 乘法表 */
    @Test
    public void testMultiplicationTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                // \t 跳到下一个TAB位置
                System.out.print(j + "×" + i + "=" + i * j + "\t");
            }
            p();
        }
    }

    /** 生成 m ~ n 位随机字符数组 */
    @Test
    public void testGenRanArr() {
        char[] chs = new char[RandomSource.XO_RO_SHI_RO_128_PP.create().nextInt(4, 10)];
        // 随机字符范围数组
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int len = letters.length;
        boolean[] flags = new boolean[len];
        for (int i = 0; i < chs.length; i++) {
            int index;
            do {
                index = new Random().nextInt(len);
            } while (flags[index]);
            chs[i] = letters[index];
            flags[index] = true;
        }
        p(chs);
    }
}
