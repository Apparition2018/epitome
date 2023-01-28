package spring.api.beans;

import l.demo.Demo;
import l.demo.Person;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanWrapper
 * <p>参考：<a href="https://www.cnblogs.com/qlqwjy/p/16264681.html">Spring BeanWrapper 的使用</a>
 *
 * @author ljh
 * @since 2023/1/19 15:14
 */
public class BeanWrapperDemo extends Demo {

    public static void main(String[] args) {
        Person person = personList.get(0);
        p(person);          // Person{id=1, name='张三'}

        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(person);

        beanWrapper.setPropertyValue(new PropertyValue("name", "A"));
        p(person);          // Person{id=1, name='A'}

        beanWrapper.setPropertyValue("name", "B");
        p(person);          // Person{id=1, name='B'}

        Map<String, String> map = new HashMap<>();
        map.put("name", "C");
        beanWrapper.setPropertyValues(map);
        p(person);          // Person{id=1, name='C'}

        Object wrappedInstance = beanWrapper.getWrappedInstance();
        p(wrappedInstance); // Person{id=1, name='C'}
        p(person == wrappedInstance);   // true
    }
}
