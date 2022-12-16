package jar.hutool.other;

import cn.hutool.bloomfilter.BitSetBloomFilter;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * BloomFilter      布隆过滤器
 * 一个很长的二进制向量和一系列随机映射函数，用于检索一个元素是否在一个集合中
 * 判断为不存在，则肯定不存在；判断为存在，有概率不存在
 * <p>
 * 优点：空间效率和查询时间都远远超过一般的算法
 * 缺点：有一定的误识别率和删除困难
 * <p>
 * https://hutool.cn/docs/#/bloomFilter/%E6%A6%82%E8%BF%B0
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/bloomfilter/BitSetBloomFilter.html
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/bloomfilter/BitMapBloomFilter.html
 *
 * @author ljh
 * @since 2020/11/21 17:11
 */
public class BloomFilterDemo extends Demo {

    private static final int SIZE = 64;
    private static final int CNT = 10;

    @Test
    public void testBloomFilter() {
        BitSetBloomFilter bloomFilter = new BitSetBloomFilter(SIZE, CNT, 3);
        IntStream.rangeClosed(1, CNT).forEach(i -> bloomFilter.add(i + ""));
        p(bloomFilter.getFalsePositiveProbability());   // 0.00302689531306664
        p(bloomFilter.contains("9"));                   // true
        p(bloomFilter.contains("10"));                  // false
    }
}
