package jar.google.guava.hash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * <a href="https://github.com/google/guava/wiki/HashingExplained#bloomfilter">BloomFilter</a>  布隆过滤器
 * <p>参考：
 * <pre>
 * <a href="http://www.ibloger.net/article/3343.html">Guava hashing</a>
 * <a href="https://guava.dev/releases/snapshot-jre/api/docs/com/google/common/hash/BloomFilter.html">BloomFilter api</a>
 * </pre>
 *
 * @author ljh
 * @since 2022/7/21 11:01
 */
public class BloomFilterDemo {

    private static final int SIZE = 64;

    public static void main(String[] args) {
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), SIZE, 0.001);
        for (int i = 0; i < 10; i++) {
            bloomFilter.put(i + "");
        }
        System.err.println(bloomFilter.mightContain("9"));  // true
        System.err.println(bloomFilter.mightContain("10")); // false
    }
}
