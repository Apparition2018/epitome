package jar.hutool.bloomfilter;

import cn.hutool.bloomfilter.BitSetBloomFilter;

import java.util.stream.IntStream;

import static l.demo.Demo.p;

/**
 * <a href="https://doc.hutool.cn/pages/bloomFilter/">BloomFilter</a>   布隆过滤器
 * <pre>
 * 一个很长的二进制向量和一系列随机映射函数，用于检索一个元素是否在一个集合中
 * 判断为不存在，则肯定不存在；判断为存在，有概率不存在
 * </pre>
 * 优点：空间效率和查询时间都远远超过一般的算法<br/>
 * 缺点：有一定的误识别率和删除困难
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/bloomfilter/BitSetBloomFilter.html">BitSetBloomFilter api</a>
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/bloomfilter/BitMapBloomFilter.html">BitMapBloomFilter api</a>
 *
 * @author ljh
 * @since 2020/11/21 17:11
 */
public class BloomFilterDemo {

    private static final int SIZE = 64;
    private static final int CNT = 10;

    public static void main(String[] args) {
        BitSetBloomFilter bloomFilter = new BitSetBloomFilter(SIZE, CNT, 3);
        IntStream.rangeClosed(1, CNT).forEach(i -> bloomFilter.add(String.valueOf(i)));
        p(bloomFilter.getFalsePositiveProbability());   // 0.00302689531306664
        p(bloomFilter.contains("9"));                   // true
        p(bloomFilter.contains("10"));                  // false
    }
}
