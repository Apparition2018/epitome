package jar.hutool.util;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.TypeUtil;
import l.demo.Demo;
import l.demo.Person;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * TypeUtil     泛型类型工具
 * https://hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E6%B3%9B%E5%9E%8B%E7%B1%BB%E5%9E%8B%E5%B7%A5%E5%85%B7-TypeUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/util/TypeUtil.html
 *
 * @author ljh
 * created on 2020/10/29 12:48
 */
public class TypeUtilDemo extends Demo {

    @Test
    public void testTypeUtil() {
        Method method;
        method = ReflectUtil.getMethod(Person.class, "setOtherInfo", List.class);
        // getParamType(Method[, index])        获取方法参数的类型
        // getParamTypes(Method)                获取方法参数的类型
        p(TypeUtil.getParamType(method, 0));    // java.util.List<java.lang.String>
        
        method = ReflectUtil.getMethod(Person.class, "getOtherInfo");
        // getReturnType(Method method)         获取方法返回值的类型
        Type type = TypeUtil.getReturnType(method);
        p(type);                                // java.util.List<java.lang.String>

        // getTypeArgument(Type[, index])       获取类型的泛型的类型
        // getTypeArguments(Type type)          获取类型的泛型的类型
        p(TypeUtil.getTypeArgument(type, 0));   // class java.lang.String
        
        // isUnknown(Type)                      是否未知类型，null 和 TypeVariable 都视为未知类型
        p(TypeUtil.isUnknown(type));            // false
    }
    
}
