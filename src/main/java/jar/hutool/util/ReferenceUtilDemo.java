package jar.hutool.util;

import cn.hutool.core.util.ReferenceUtil;
import org.junit.jupiter.api.Test;

import java.lang.ref.Reference;

/**
 * ReferenceUtil    引用工具
 * <p>
 * 1.SoftReference  软引用，在 GC 报告内存不足时会被 GC 回收
 * 2.WeakReference  弱引用，在 GC 时发现弱引用会回收其对象
 * 3.PhantomReference 虚引用，在 GC 时发现虚引用对象，会将 PhantomReference 插入 ReferenceQueue。
 * -    此时对象未被真正回收，要等到 ReferenceQueue 被真正处理后才会被回收。
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%BC%95%E7%94%A8%E5%B7%A5%E5%85%B7-ReferenceUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/ReferenceUtil.html
 *
 * @author ljh
 * @since 2020/11/19 16:50
 */
public class ReferenceUtilDemo {

    @Test
    public void testReferenceUtil() {
        String str = new String("hello");
        Reference<String> weakReference = ReferenceUtil.create(ReferenceUtil.ReferenceType.WEAK, str);
    }
}
