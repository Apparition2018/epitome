package knowledge.reflect;

import l.demo.Person;
import l.demo.Person.Home;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static l.demo.Demo.p;

/**
 * <a href="https://www.cnblogs.com/dreammyle/p/5610906.html">通过反射实现 JavaBean 与 Map 对象互相转化</a>
 *
 * @author ljh
 * @since 2020/11/11 19:18
 */
public class ReflectExercise {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        /* bean2Map */
        Person person = new Person().setId(1).setName("张三").setAge(18).setGender("男")
                .setOtherInfo(Collections.singletonList("程序员")).setHome(new Home().setAddress("北京").setTel("010"));
        Map<String, Object> map = bean2Map(person);
        p(map); // {otherInfo=[程序员], serialVersionUID=1, gender=男, name=张三, id=1, age=18, home=Person.Home(address=广东中山, tel=123456)}

        /* map2Bean */
        p(map2Bean(map, Person.class)); // Person{id=1, name='张三', age=18, gender='男', otherInfo=[程序员], home=Home(address='广东中山', tel='123456')}
    }

    /**
     * JavaBean → Map
     */
    private static Map<String, Object> bean2Map(Object obj) throws IllegalAccessException {
        if (null == obj) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

    /**
     * Map → JavaBean
     */
    private static Object map2Bean(Map<String, Object> map, Class<?> beanClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (null == map) {
            return null;
        }

        Object obj = beanClass.getConstructor().newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true); // 改变访问权限
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }
}
