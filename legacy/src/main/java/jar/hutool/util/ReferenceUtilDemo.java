package jar.hutool.util;

import cn.hutool.core.util.ReferenceUtil;

import java.lang.ref.Reference;

/**
 * <a href="https://doc.hutool.cn/pages/ReferenceUtil/">ReferenceUtil</a>   引用工具
 * <pre>
 * 1 SoftReference  软引用，在 GC 报告内存不足时会被 GC 回收
 * 2 WeakReference  弱引用，在 GC 时发现弱引用会回收其对象
 * 3 PhantomReference 虚引用，在 GC 时发现虚引用对象，会将 PhantomReference 插入 ReferenceQueue。
 *   此时对象未被真正回收，要等到 ReferenceQueue 被真正处理后才会被回收。
 * </pre>
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/ReferenceUtil.html">ReferenceUtil api</a>
 *
 * @author ljh
 * @since 2020/11/19 16:50
 */
public class ReferenceUtilDemo {

    public static void main(String[] args) {
        String str = new String("hello");
        Reference<String> weakReference = ReferenceUtil.create(ReferenceUtil.ReferenceType.WEAK, str);
    }
}
