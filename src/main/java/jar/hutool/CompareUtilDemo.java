package jar.hutool;

import cn.hutool.core.comparator.CompareUtil;

/**
 * <a href="https://hutool.cn/docs/#/core//比较器/比较工具-CompareUtil">CompareUtil</a>    比较工具
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/comparator/CompareUtil.html">CompareUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 23:26
 */
public class CompareUtilDemo {

    public static void main(String[] args) {
        // compare(T o1, T o2[, boolean isNullGreater])
        // true null 最大，false null 最小，默认 null 最小
        System.out.println(CompareUtil.compare(null, "A", true));
    }
}
