package jar.hutool;

import cn.hutool.core.util.ObjectUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * ObjectUtil
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%AF%B9%E8%B1%A1%E5%B7%A5%E5%85%B7-ObjectUtil
 *
 * @author ljh
 * created on 2020/10/27 12:51
 */
public class ObjectUtilDemo extends Demo {

    @Test
    public void testObjectUtil() {
        // 相等
        p(ObjectUtil.equal(null, null));        // true
        p(ObjectUtil.notEqual(null, null));     // false

        // 长度，支持 CharSequence, Collection, Map, Iterator, Enumeration, Array
        p(ObjectUtil.length(map));              // 3

        // 包含，支持 String, Collection, Map, Iterator, Enumeration, Array
        p(ObjectUtil.contains(map, "A"));       // true

        // 克隆
        // 如果对象实现Cloneable接口，调用其 clone()，如果实现 Serializable 接口，执行深度克隆，否则返回 null
        p(ObjectUtil.clone(map));               // {1=A, 2=B, 3=C}
        // 返回克隆后的对象，如果克隆失败，返回原对象
        p(ObjectUtil.cloneIfPossible(map));     // {1=A, 2=B, 3=C}
        // 序列化后拷贝流的方式克隆，对象必须实现 Serializable 接口
        p(ObjectUtil.cloneByStream(map));       // {1=A, 2=B, 3=C}

        // 序列化 和 反序列化 ?
        byte[] serialize = ObjectUtil.serialize(map);
        p(serialize);                           // [-84, -19, 0, 5, ..., 120]

        // 比较
        p(ObjectUtil.compare(null, 1));         // -1
        p(ObjectUtil.compare(null, 1, true));   // 1

        // ???
        p(ObjectUtil.getTypeArgument(map, 0));  // class java.lang.Integer
        p(ObjectUtil.getTypeArgument(map, 1));  // class java.lang.String
        p(ObjectUtil.getTypeArgument(map, 2));  // null

        // 是否为基本类型
        p(ObjectUtil.isBasicType(map));         // false

        // 是否为???
        p(ObjectUtil.isValidIfNumber(map));     // true
    }

    @Test
    public void testNullAndEmpty() {
        p(ObjectUtil.isNull(null));                         // true
        p(ObjectUtil.isNotNull(null));                      // false
        p(ObjectUtil.isEmpty(map));                         // false
        p(ObjectUtil.isNotEmpty(map));                      // true
        p(ObjectUtil.isAllEmpty(map));                      // false
        p(ObjectUtil.isAllNotEmpty(map));                   // true

        // 多少位 Empty
        p(ObjectUtil.emptyCount(1, "", "2", "", 3));        // 2
        // 是否有 Empty
        p(ObjectUtil.hasEmpty(1, "", "2", "", 3));          // true

        // default
        p(ObjectUtil.defaultIfNull(1, Object::new, 1));     // java.lang.Object@68c4039c
        p(ObjectUtil.defaultIfNull(null, 1));               // 1
        p(ObjectUtil.defaultIfEmpty("1", String::new, "1"));//
        p(ObjectUtil.defaultIfEmpty(null, "1"));            // 1
        p(ObjectUtil.defaultIfBlank(null, "1"));            // 1
    }
}
