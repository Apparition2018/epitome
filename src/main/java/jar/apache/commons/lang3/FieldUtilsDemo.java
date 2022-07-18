package jar.apache.commons.lang3;

import com.fasterxml.jackson.annotation.JsonIgnore;
import l.demo.Demo;
import l.demo.Person;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;

/**
 * FieldUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/reflect/FieldUtils.html
 *
 * @author ljh
 * created on 2021/4/12 18:31
 */
public class FieldUtilsDemo extends Demo {

    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException {
        // static List<Field>	getAllFieldsList(Class<?> cls)
        // 相当于 clazz.getDeclaredFields()
        p(FieldUtils.getAllFieldsList(Person.class));

        // static Field	        getDeclaredField(Class<?> cls, String fieldName, boolean forceAccess)
        // 相当于 clazz.getDeclaredField(String name); field.setAccessible(true);
        p(FieldUtils.getDeclaredField(Person.class, "name", true));         // private java.lang.String l.demo.Person.name

        // static List<Field>	getFieldsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls)
        p(FieldUtils.getFieldsListWithAnnotation(Person.class, JsonIgnore.class));              // []


        // static void	        writeDeclaredField(Object target, String fieldName, Object value, boolean forceAccess)
        // 一般用于给对象的动态字段赋值
        FieldUtils.writeDeclaredField(personList.get(0), "name", "张三丰", true);

        // static Object	    readDeclaredField(Object target, String fieldName, boolean forceAccess)
        // 一般用于获取对象的动态字段
        p(FieldUtils.readDeclaredField(personList.get(0), "name", true));   // 张三丰
    }
}
