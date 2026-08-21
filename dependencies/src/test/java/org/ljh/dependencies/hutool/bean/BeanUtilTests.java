package org.ljh.dependencies.hutool.bean;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.ListUtil;
import com.ljh.commons.Person;
import com.ljh.commons.Person.Home;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.ljh.commons.Demo.p;
import static com.ljh.commons.Demo.personList;

/**
 * <a href="https://doc.hutool.cn/pages/BeanUtil/">BeanUtil</a> Bean工具
 * <p><a href="https://plus.hutool.cn/apidocs/cn/hutool/core/bean/BeanUtil.html">BeanUtil api</a>
 *
 * @author ljh
 * @since 2020/11/9 14:54
 */
public class BeanUtilTests {

    @Test
    public void test() {
        // static void	    descForEach(Class<?>, Consumer<? super PropDesc>)
        // 遍历 Bean 的属性，进行 Consumer 操作
        BeanUtil.descForEach(Person.class, propDesc -> p(propDesc.getFieldName()));

        Person person = personList.getFirst();
        person.setOtherInfo(ListUtil.toList("医生", "爸爸"));
        person.setHome(new Home().setAddress("北京").setTel("010"));
        p(person); // Person{id=1, name='张三', otherInfo=[医生, 爸爸], home=Home(address='GD', tel='123')}

        // static void	    setFieldValue(Object bean, fieldNameOrIndex, Object value)
        // 通过反射设置字段值，支持 Map 和 Collection
        BeanUtil.setFieldValue(person, "name", "张叄");
        // static Object    getFieldValue(Object bean, fieldNameOrIndex)
        // 通过反射获取字段值，支持 Map 和 Collection
        p(BeanUtil.getFieldValue(person, "name")); // 张叄

        // static void	    setProperty(Object bean, String expression, Object value)
        // 通过表达式设置字段值，支持 Map 和 Collection
        BeanUtil.setProperty(person, "home.address", "ZS");
        // static <T> T	    getProperty(Object bean, String expression)
        // 通过表达式获取字段值，支持 Map 和 Collection
        p(BeanUtil.getProperty(person, "otherInfo[1]")); // 爸爸

        // Bean → Map
        Map<String, Object> personMap = BeanUtil.beanToMap(person);
        p(personMap);
        // Map → Bean
        p(BeanUtil.mapToBean(personMap, Person.class, true, CopyOptions.create()));
        // Map → Bean
        p(BeanUtil.fillBeanWithMap(personMap, person, true, false));
        // Map → Bean
        p(BeanUtil.toBean(personMap, Person.class));

        // static <T> T	    copyProperties(Object source, Class<T> tClass[, String... ignoreProperties])
        // 复制属性
        Person copyPerson = BeanUtil.copyProperties(person, Person.class, "home");
        p(copyPerson);
        // static void	    copyProperties(Object source, Object target[, String... ignoreProperties/boolean ignoreCase/CopyOptions copyOptions])
        // 复制属性
        BeanUtil.copyProperties(person, copyPerson, "home");
        p(copyPerson);
    }
}
