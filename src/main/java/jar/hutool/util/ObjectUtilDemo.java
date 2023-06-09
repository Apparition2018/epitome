package jar.hutool.util;

import cn.hutool.core.util.ObjectUtil;
import l.demo.Demo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/对象工具-ObjectUtil">ObjectUtil</a>
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/ObjectUtil.html">ObjectUtil api</a>
 *
 * @author ljh
 * @since 2020/10/27 12:51
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
        // 如果对象实现 Cloneable 接口，调用其 clone()，如果实现 Serializable 接口，执行深度克隆，否则返回 null
        p(ObjectUtil.clone(map));               // {1=A, 2=B, 3=C}
        // 返回克隆后的对象，如果克隆失败，返回原对象
        p(ObjectUtil.cloneIfPossible(map));     // {1=A, 2=B, 3=C}
        // 序列化后拷贝流的方式克隆，对象必须实现 Serializable 接口
        p(ObjectUtil.cloneByStream(map));       // {1=A, 2=B, 3=C}

        // 序列化，对象必须实现 Serializable 接口
        byte[] serialize = ObjectUtil.serialize(map);
        // 反序列化，对象必须实现 Serializable 接口
        p(ObjectUtil.deserialize(serialize));   // {1=A, 2=B, 3=C}

        // 比较
        p(ObjectUtil.compare(null, 1));         // -1
        p(ObjectUtil.compare(null, 1, true));   // 1

        // 获取类型的泛型的类型，TypeUtil.getTypeArgument(type, 0)
        p(ObjectUtil.getTypeArgument(map, 0));  // class java.lang.Integer
        p(ObjectUtil.getTypeArgument(map, 1));  // class java.lang.String
        p(ObjectUtil.getTypeArgument(map, 2));  // null

        // 是否为基本类型
        p(ObjectUtil.isBasicType(map));         // false

        // 是否为有效的数字，Double 和 Float 的 POSITIVE_INFINITY, NEGATIVE_INFINITY, NaN 返回 false
        p(ObjectUtil.isValidIfNumber(map));     // true
    }

    @Test
    public void nullAndEmpty() {
        p(ObjectUtil.isNull(null));              // true
        p(ObjectUtil.isNotNull(null));           // false
        p(ObjectUtil.isEmpty(map));              // false
        p(ObjectUtil.isNotEmpty(map));           // true
        p(ObjectUtil.isAllEmpty(map));           // false
        p(ObjectUtil.isAllNotEmpty(map));        // true

        Object[] arr = new Object[]{1, StringUtils.EMPTY, "2", StringUtils.EMPTY, 3};
        // 多少位 Empty
        p(ObjectUtil.emptyCount(arr));          // 2
        // 是否有 Empty
        p(ObjectUtil.hasEmpty(arr));            // true

        // default
        p(ObjectUtil.defaultIfNull(null, (s) -> StringUtils.trim(String.valueOf(s)), "1")); // 1
        p(ObjectUtil.defaultIfNull(null, "1")); // 1
        p(ObjectUtil.defaultIfEmpty(null, "1"));// 1
        p(ObjectUtil.defaultIfBlank(null, "1"));// 1
    }
}
