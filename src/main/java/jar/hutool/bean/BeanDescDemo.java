package jar.hutool.bean;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import l.demo.Person;

import java.util.Collection;
import java.util.Map;

import static l.demo.Demo.p;

/**
 * <a href="https://hutool.cn/docs/#/core/JavaBean/Bean描述-BeanDesc">BeanDesc</a>    Bean 描述
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/bean/BeanDesc.html">BeanDesc api</a>
 * <p>可堪称 JDK 的 BeanInfo 的强化版本
 *
 * @author ljh
 * @since 2022/1/19 9:07
 */
public class BeanDescDemo {

    public static void main(String[] args) {
        BeanDesc beanDesc = BeanUtil.getBeanDesc(Person.class);
        // String	            getName()                       获取 Bean 的全类名
        p(beanDesc.getName());                      // l.demo.Person
        // String	            getSimpleName()                 获取 Bean 的简单类名
        p(beanDesc.getSimpleName());                // Person
        // Collection<PropDesc>	getProps()                      获取字段属性列表    
        Collection<PropDesc> props = beanDesc.getProps();
        // Map<String,PropDesc>	getPropMap(boolean ignoreCase)  获取字段名-字段属性 Map
        Map<String, PropDesc> propMap = beanDesc.getPropMap(false);
        // PropDesc	            getProp(String fieldName)       获取属性
        p(beanDesc.getProp("home").getFieldName()); // home
        // Field	            getField(String fieldName)      获得字段名对应的字段对象
        p(beanDesc.getField("home").getName());     // home
        // Method	            getGetter(String fieldName)     获取 Getter
        p(beanDesc.getGetter("home").getName());    // getHome
        // Method	            getSetter(String fieldName)     获取 Setter
        p(beanDesc.getSetter("home").getName());    // setHome

        /* PropDesc */
        Person person = new Person();
        beanDesc.getProp("name").setValue(person, "张三");
        p(person.getName());    // 张三
    }
}
