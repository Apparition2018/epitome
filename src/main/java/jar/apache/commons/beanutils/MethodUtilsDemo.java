package jar.apache.commons.beanutils;

import l.demo.Person;
import org.apache.commons.beanutils.MethodUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <a href="http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/MethodUtils.html">MethodUtils</a>
 *
 * @author ljh
 * @since 2020/11/14 20:08
 */
public class MethodUtilsDemo {

    @Test
    public void testMethodUtils() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person person = new Person();
        // static Method	    getAccessibleMethod(Class<?> clazz, String methodName, Class<?> parameterType)
        // 返回一个具有给定名和单个参数的可访问方法
        Method method = MethodUtils.getAccessibleMethod(Person.class, "setId", Integer.class);
        method.invoke(person, 1);
        // static Object	    invokeMethod(Object object, String methodName, Object arg)
        // 调用参数类型与对象类型匹配的方法
        MethodUtils.invokeMethod(person, "setName", "张三");
        System.out.println(person);
    }
}
