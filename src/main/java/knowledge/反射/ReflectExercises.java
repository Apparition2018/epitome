package knowledge.反射;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.cnblogs.com/dreammyle/p/5610906.html
 */
public class ReflectExercises {

    /**
     * JavaBean <=> Map
     */
    @Test
    public void test() {

        Letter letter = new Letter();

        letter.setA("a1");
        letter.setB("b2");
        letter.setC("c3");

        try {
            Map<String, Object> map = bean2Map(letter);

            System.out.println(map);

            letter = (Letter) map2Bean(map, letter.getClass());
            letter.setC("c3*");

            System.out.println(letter.getC());

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    /**
     * Map → JavaBean
     */
    private Object map2Bean(Map<String, Object> map, Class<?> beanClass) throws IllegalAccessException, InstantiationException {

        if (map == null) {
            return null;
        }

        Object obj = beanClass.newInstance();

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

    /**
     * JavaBean → Map
     */
    private Map<String, Object> bean2Map(Object obj) throws IllegalAccessException {

        if (obj == null) {
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

}