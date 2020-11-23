package jar.apache.commons.beanutils;

import l.demo.Demo;
import l.demo.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * BeanUtils
 * http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.4/apidocs/org/apache/commons/beanutils/BeanUtils.html
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class BeanUtilsDemo extends Demo {
    
    @Test
    public void testBeanUtils() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        // JavaBean → JavaBean
        Person person = new Person();
        BeanUtils.copyProperties(person, personList.get(0));
        p(String.format("%s的 id 是 %s", person.getName(), person.getId())); // 张三的 id 是 1

        // JavaBean → Map
        Map<String, String> map = BeanUtils.describe(person);
        p(map); // {otherInfo=null, gender=null, name=张三, id=1, age=null, home=null}

        // Map → JavaBean
        BeanUtils.populate(person, map);
        p(String.format("%s的 id 是 %s", person.getName(), person.getId())); // 张三的 id 是 1

        BeanUtils.setProperty(person, "id", 2);
        BeanUtils.setProperty(person, "name", "李四");
        p(person); // Person{id=2, name='李四', age=0}
    }

    /**
     * 设置 JavaBean 参数
     */
    @Test
    public void setProperty() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        // 1.得到 JavaBean 的一个字节码对象
        Class<?> clazz = Class.forName("l.demo.Person$Student");

        // 2.生成该字节码的一个对象
        Object obj = clazz.newInstance();

        // 4.注册一个日期格式转换器
        ConvertUtils.register(new DateLocaleConverter(), Date.class);

        // 3.使用工具对该对象赋值
        BeanUtils.setProperty(obj, "id", "1");
        BeanUtils.setProperty(obj, "name", "John");
        BeanUtils.setProperty(obj, "age", "18");
        BeanUtils.setProperty(obj, "birth", "2000-01-01"); // String → Date 不能自动转换，需注册一个转换器

        p(obj); // Student{id=1, name='John', age=18, birth=2000-01-01}
    }

    /**
     * HttpServletRequest → JavaBean
     * <p>
     * https://www.cnblogs.com/vmax-tam/p/4159985.html
     */
    @SuppressWarnings({"unchecked", "unused"})
    public static <T> T requestToBean(HttpServletRequest request, Class<T> clazz) {

        // 创建 JavaBean 对象
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 得到请求中的每个参数
        Enumeration<String> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            // 获得参数名
            String name = e.nextElement();

            // 获得参数值
            String value = request.getParameter(name);

            // 把参数拷贝到 JavaBean 中
            try {
                BeanUtils.setProperty(obj, name, value);
            } catch (IllegalAccessException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }

        return (T) obj;
    }

}
