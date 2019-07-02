package jar.apache.commons.beanutils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * BeanUtils
 *
 * http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.2/apidocs/org/apache/commons/beanutils/BeanUtils.html
 */
public class BeanUtilsDemo {

    /**
     * JavaBean → JavaBean
     */
    @Test
    public void copyProperties() throws InvocationTargetException, IllegalAccessException {

        Student s1 = new Student();
        s1.setId(1);
        s1.setName("John");
        s1.setAge(18);

        Student s2 = new Student();
        BeanUtils.copyProperties(s2, s1);

        System.out.println(s2.getName() + "的id是" + s2.getId() + "，年龄是" + s2.getAge() + "岁");
    }

    /**
     * JavaBean → Map
     */
    @Test
    public void describe() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Student s = new Student();
        s.setId(1);
        s.setName("John");
        s.setAge(18);

        Map<String, String> map = BeanUtils.describe(s);
        System.out.println(map); // {name=John, birth=null, id=1, class=class jar.apache.commons.beanutils.Student, age=18}
    }

    /**
     * Map → JavaBean
     */
    @Test
    public void populate() throws InvocationTargetException, IllegalAccessException {

        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "John");
        map.put("age", "18");

        Student s = new Student();
        BeanUtils.populate(s, map);

        System.out.println(s.getName() + "的id是" + s.getId() + "，年龄是" + s.getAge() + "岁");
    }

    /**
     * 设置 JavaBean 参数
     */
    @Test
    public void setProperty() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        // 1.得到 JavaBean 的一个字节码对象
        Class clazz = Class.forName("jar.apache.commons.beanutils.Student");

        // 2.生成该字节码的一个对象
        Object obj = clazz.newInstance();

        // 4.注册一个日期格式转换器
        ConvertUtils.register(new DateLocaleConverter(), Date.class);

        // 3.使用工具对该对象赋值
        BeanUtils.setProperty(obj, "id", "1");
        BeanUtils.setProperty(obj, "name", "John");
        BeanUtils.setProperty(obj, "age", "18");
        BeanUtils.setProperty(obj, "birth", "2000-01-01"); // String -> Date 不能自动转换，需注册一个转换器

        System.out.println(obj);
    }

    /**
     * HttpServletRequest → JavaBean
     * <p>
     * https://www.cnblogs.com/vmax-tam/p/4159985.html
     */
    @SuppressWarnings("unchecked")
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
