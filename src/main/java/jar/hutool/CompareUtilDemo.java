package jar.hutool;

import cn.hutool.core.comparator.CompareUtil;
import org.junit.jupiter.api.Test;

/**
 * CompareUtil      比较工具
 * https://hutool.cn/docs/#/core/%E6%AF%94%E8%BE%83%E5%99%A8/%E6%AF%94%E8%BE%83%E5%B7%A5%E5%85%B7-CompareUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/comparator/CompareUtil.html
 *
 * @author Arsenal
 * created on 2020/11/19 23:26
 */
public class CompareUtilDemo {

    @Test
    public void testCompareUtil() {
        // compare(T o1, T o2[, boolean isNullGreater])
        // true null 最大，false null 最小，默认 null 最小
        System.out.println(CompareUtil.compare(null, "A", true));
    }
}
