package jar.apache.commons.beanutils;

import l.demo.Person;
import org.apache.commons.beanutils.MethodUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * MethodUtils
 * http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/MethodUtils.html
 *
 * @author Arsenal
 * created on 2020/11/14 20:08
 */
public class MethodUtilsDemo {

    @Test
    public void testMethodUtils() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person person = new Person();
        Method method = MethodUtils.getAccessibleMethod(Person.class, "setId", Integer.class);
        method.invoke(person, 1);
        MethodUtils.invokeMethod(person, "setName", "张三");
        System.out.println(person);
    }
}
