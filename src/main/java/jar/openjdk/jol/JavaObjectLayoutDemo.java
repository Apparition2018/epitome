package jar.openjdk.jol;

import l.demo.Demo;
import org.openjdk.jol.info.ClassLayout;

/**
 * JOL: Java Object Layout
 * 1.Object Header：对象头。① Mark Word，② Class Pointer
 * 2.Instance Data：实例数据
 * 3.Padding：对齐填充，填充至8字节的整数倍
 * <p>
 * Java 对象头内存模型：https://blog.csdn.net/weixin_44141495/article/details/108520566
 * Object o = new Object()占多少个字节？：http://t.zoukankan.com/dijia478-p-14677243.html
 * Object o = new Object()占多少个字节？：https://blog.51cto.com/u_15368284/5116350
 *
 * @author ljh
 * created on 2022/7/5 2:02
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
