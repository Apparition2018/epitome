package jar.google.guava.hash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import l.demo.Demo;

import java.nio.charset.Charset;

/**
 * <a href="https://github.com/google/guava/wiki/HashingExplained#bloomfilter">BloomFilter</a>  布隆过滤器
 *
 * @author ljh
 * @since 2022/7/21 11:01
 */
public class BloomFilterDemo extends Demo {

    public static void main(String[] args) {
        // 初始化：把所有数据加入布隆过滤器
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 100_000, 0.001);
        for (int i = 0; i < 10; i++) {
            bloomFilter.put(String.valueOf(i));
        }
        // 问布隆过滤器：这个数据存在吗？
        // true 表示大概存在
        ae(bloomFilter.mightContain("9"), true);
        // false 表示100%不存在
        ae(bloomFilter.mightContain("10"), false);
    }
}
