package jar.google.guava.hash;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

/**
 * BloomFilter      布隆过滤器
 *
 * @author ljh
 * @since 2022/7/21 11:01
 */
public class BloomFilterDemo {

    private static final int SIZE = 64;

    @Test
    public void testBloomFilter() {
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), SIZE, 0.001);
        for (int i = 0; i < 10; i++) {
            bloomFilter.put(i + "");
        }
        System.err.println(bloomFilter.mightContain("9"));  // true
        System.err.println(bloomFilter.mightContain("10")); // false
    }
}
