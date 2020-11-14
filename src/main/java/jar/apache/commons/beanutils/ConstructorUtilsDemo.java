package jar.apache.commons.beanutils;

import l.demo.Person;
import org.apache.commons.beanutils.ConstructorUtils;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ConstructorUtils
 * http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/ConstructorUtils.html
 *
 * @author Arsenal
 * created on 2020/11/14 21:20
 */
public class ConstructorUtilsDemo {

    @Test
    public void testConstructorUtils() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<Person> constructor = ConstructorUtils.getAccessibleConstructor(Person.class, new Class[]{Integer.class, String.class});
        System.out.println(constructor.newInstance(1, "John"));

        System.out.println(ConstructorUtils.invokeConstructor(Person.class, new Object[]{1, "John"}));
    }
}
