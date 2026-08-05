package jar.apache.commons.beanutils;

import l.demo.Person;
import org.apache.commons.beanutils.ConstructorUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * <a href="http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/ConstructorUtils.html">ConstructorUtils</a>
 *
 * @author ljh
 * @since 2020/11/14 21:20
 */
public class ConstructorUtilsDemo {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor<Person> constructor = ConstructorUtils.getAccessibleConstructor(Person.class, new Class[]{Integer.class, String.class});
        System.out.println(constructor.newInstance(1, "John"));

        System.out.println(ConstructorUtils.invokeConstructor(Person.class, new Object[]{1, "John"}));
    }
}
