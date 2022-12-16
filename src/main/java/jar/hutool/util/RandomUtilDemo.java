package jar.hutool.util;

import cn.hutool.core.util.RandomUtil;
import l.demo.Demo;
import org.junit.jupiter.api.Test;

/**
 * RandomUtil   随机工具
 * 封装 java.lang.Random
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E9%9A%8F%E6%9C%BA%E5%B7%A5%E5%85%B7-RandomUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/RandomUtil.html
 *
 * @author ljh
 * @since 2020/11/19 16:18
 */
public class RandomUtilDemo extends Demo {

    /**
     * static Random                getRandom(boolean isSecure)
     * static ThreadLocalRandom     getRandom()                         线程隔离的随机数生成器
     * static SecureRandom          createSecureRandom(byte[] seed)     强加密随机数生成器(RNG)
     * static SecureRandom          getSecureRandom()                   强加密随机数生成器(RNG)
     * <p>
     * static <T> WeightRandom<T>	weightRandom(Iterable<WeightRandom.WeightObj<T>> weightObjs)    带有权重的随机数生成器
     * static <T> WeightRandom<T>	weightRandom(WeightRandom.WeightObj<T>[] weightObjs)            带有权重的随机数生成器
     */
    @Test
    public void testRandomUtil() {
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
