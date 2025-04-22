package jar.hutool.util;

import cn.hutool.core.util.RandomUtil;
import l.demo.Demo;

/**
 * <a href="https://doc.hutool.cn/pages/RandomUtil/">RandomUtil</a> 随机工具
 * <p>封装 java.lang.Random
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/RandomUtil.html">RandomUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 16:18
 */
public class RandomUtilDemo extends Demo {

    /**
     * <pre>{@code
     * static Random                getRandom(boolean isSecure)
     * static ThreadLocalRandom     getRandom()                         线程隔离的随机数生成器
     * static SecureRandom          createSecureRandom(byte[] seed)     强加密随机数生成器(RNG)
     * static SecureRandom          getSecureRandom()                   强加密随机数生成器(RNG)
     *
     * static <T> WeightRandom<T>   weightRandom(Iterable<WeightRandom.WeightObj<T>> weightObjs)    带有权重的随机数生成器
     * static <T> WeightRandom<T>   weightRandom(WeightRandom.WeightObj<T>[] weightObjs)            带有权重的随机数生成器}
     * </pre>
     */
    public static void main(String[] args) {
        p(RandomUtil.randomInt(100, 1000));
        p(RandomUtil.randomInts(3));
        // 随机字符串，只包括数字和字母
        p(RandomUtil.randomString(3));
        // 随机字符串，只包括数字和字母，且排除指定字符
        p(RandomUtil.randomStringWithoutStr(3, "0123456789"));
        p(RandomUtil.randomEle(list));
        p(RandomUtil.randomEle(list, 3));
    }
}
