package jar.apache.commons.beanutils;

import l.demo.Demo;
import l.demo.Person;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * <a href="http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/PropertyUtils.html">PropertyUtils</a>
 *
 * @author ljh
 * @since 2020/11/14 21:29
 */
public class PropertyUtilsDemo extends Demo {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person person = new Person();
        // JavaBean → JavaBean
        PropertyUtils.copyProperties(person, personList.get(0));
        p(person); // Person{id=1, name='张三'}

        // JavaBean → Map
        Map<String, Object> map = PropertyUtils.describe(person);
        p(map); // {otherInfo=null, gender=null, name=张三, id=1, age=null, home=null}

        PropertyUtils.setProperty(person, "id", 2);
        PropertyUtils.setProperty(person, "name", "李四");
        p(person); // Person{id=2, name='李四'}
    }
}
