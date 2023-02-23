package jar.openjdk.jol;

import l.demo.Demo;
import org.openjdk.jol.info.ClassLayout;

/**
 * JOL: Java Object Layout
 * <p>在 JVM 中，Java 对象保存在堆中时，由以下三部分组成：
 * <pre>
 * 1 Object Header（对象头）
 *   1.1 Mark Word：8字节；hashCode、GC 分代年龄、锁偏向位、锁标记位等
 *   1.2 Class Pointer：8字节，开启指针压缩时4自己；对象类型指针
 *   1.3 Length：4字节；是数组才有，记录数组长度
 * 2 Instance Data（实例数据）：包含对象的所有成员变量
 * 3 Padding（对齐填充）：对象大小填充至8字节的倍数
 * </pre>
 * <a href="https://blog.csdn.net/zwx900102/article/details/108108555">Object o = new Object()占多少个字节？</a>
 *
 * @author ljh
 * @since 2022/7/5 2:02
 */
public class JavaObjectLayoutDemo extends Demo {

    public static void main(String[] args) {
        Object obj = new Object();
        p(ClassLayout.parseInstance(obj).toPrintable());
        // java.lang.Object object internals:
        // OFF  SZ   TYPE DESCRIPTION               VALUE
        //   0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
        //   8   4        (object header: class)    0xf80001e5
        //  12   4        (object alignment gap)
        // Instance size: 16 bytes
        // Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

        obj = 2;
        p(ClassLayout.parseInstance(obj).toPrintable());
        // java.lang.Integer object internals:
        // OFF  SZ   TYPE DESCRIPTION               VALUE
        //   0   8        (object header: mark)     0x0000000000000009 (non-biasable; age: 1)
        //   8   4        (object header: class)    0xf80022bf
        //  12   4    int Integer.value             2
        // Instance size: 16 bytes
        // Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

        obj = 3L;
        p(ClassLayout.parseInstance(obj).toPrintable());
        // java.lang.Long object internals:
        // OFF  SZ   TYPE DESCRIPTION               VALUE
        //   0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
        //   8   4        (object header: class)    0xf8002306
        //  12   4        (alignment/padding gap)
        //  16   8   long Long.value                3
        // Instance size: 24 bytes
        // Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
    }
}
