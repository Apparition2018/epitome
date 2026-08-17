package spring.api.beans;

import com.ljh.commons.Person;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

import java.util.Map;

import static com.ljh.commons.Demo.p;
import static com.ljh.commons.Demo.personList;

/**
 * BeanWrapper
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/qlqwjy/p/16264681.html">Spring BeanWrapper 的使用</a>
 * @since 2023/1/19 15:14
 */
public class BeanWrapperDemo {

    public static void main(String[] args) {
        Person person = personList.getFirst();
        p(person);          // Person{id=1, name='张三'}

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(person);

        beanWrapper.setPropertyValue(new PropertyValue("name", "A"));
        p(person);          // Person{id=1, name='A'}

        beanWrapper.setPropertyValue("name", "B");
        p(person);          // Person{id=1, name='B'}

        Map<String, String> map = Map.of("name", "C");
        beanWrapper.setPropertyValues(map);
        p(person);          // Person{id=1, name='C'}

        Object wrappedInstance = beanWrapper.getWrappedInstance();
        p(wrappedInstance); // Person{id=1, name='C'}
        p(person == wrappedInstance);   // true
    }
}
