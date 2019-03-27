package knowledge.算法;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;

public class Random {

    /**
     * 生成 n 位随机字符数组
     */
    @Test
    public void test() {
        char[] chs = new char[RandomUtils.nextInt(4, 10)];
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}; // 随机字符范围数组
        int len = letters.length;
        boolean[] flags = new boolean[len];
        for (int i = 0; i < chs.length; i ++) {
            int index;
            do {
                index = (int) (Math.random() * len);
            } while (flags[index]);
            chs[i] = letters[index];
            flags[index] = true;
        }

        System.out.println(Arrays.toString(chs));

    }

}
