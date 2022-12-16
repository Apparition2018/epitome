package jar.apache.commons.lang3.reflect;

import com.fasterxml.jackson.annotation.JsonIgnore;
import l.demo.Demo;
import l.demo.Person;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Reflect
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/reflect/FieldUtils.html
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/reflect/ConstructorUtils.html
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/reflect/MethodUtils.html
 *
 * @author ljh
 * @since 2022/11/9 10:40
 */
public class ReflectDemo extends Demo {

    private Person person;

    @BeforeEach
    public void testConstructorUtils() throws Exception {
        Object[] args2 = {1, "Mary"};
        Class<?>[] parameterTypes = {Integer.class, String.class};
        // static <T> T         invokeConstructor(Class<T> klass, Object[] args, Class<?>[] parameterTypes)
        person = ConstructorUtils.invokeConstructor(Person.class, args2, parameterTypes);
    }

    @Test
    public void testFieldUtils() throws IllegalAccessException {
        // static List<Field>	getAllFieldsList(Class<?> cls)
        // 相当于 clazz.getDeclaredFields()
        p(FieldUtils.getAllFieldsList(Person.class));

        // static Field	        getDeclaredField(Class<?> cls, String fieldName, boolean forceAccess)
        // 相当于 clazz.getDeclaredField(String name); field.setAccessible(true);
        p(FieldUtils.getDeclaredField(Person.class, "name", true));                 // private java.lang.String l.demo.Person.name

        // static List<Field>	getFieldsListWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls)
        p(FieldUtils.getFieldsListWithAnnotation(Person.class, JsonIgnore.class));  // []


        // static void	        writeDeclaredField(Object target, String fieldName, Object value, boolean forceAccess)
        // 一般用于给对象的动态字段赋值
        FieldUtils.writeDeclaredField(person, "name", "张三丰", true);

        // static Object	    readDeclaredField(Object target, String fieldName, boolean forceAccess)
        // 一般用于获取对象的动态字段
        p(FieldUtils.readDeclaredField(person, "name", true));                      // 张三丰
    }

    @Test
    public void testMethodUtils() throws Exception {
        p(MethodUtils.invokeMethod(person, true, "getName"));                       // Mary
    }
}
