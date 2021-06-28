package jar.hutool.other;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * BitMapBloomFilter        布隆过滤器
 * 优点：空间效率和查询时间都远远超过一般的算法
 * 缺点：有一定的误识别率和删除困难
 * https://hutool.cn/docs/#/bloomFilter/%E6%A6%82%E8%BF%B0
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/bloomfilter/BitMapBloomFilter.html
 *
 * @author Arsenal
 * created on 2020/11/21 17:11
 */
public class BitMapBloomFilterDemo {

    private static final int SIZE = 2000;

    @Test
    public void testBitMapBloomFilter() {
        BitMapBloomFilter bitMapBloomFilter = new BitMapBloomFilter(SIZE);
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            bitMapBloomFilter.add(random.nextInt(SIZE * 2) + "");
        }
        int x = random.nextInt(SIZE * 2);
        Assert.isTrue(bitMapBloomFilter.contains(x + ""));
    }
}
