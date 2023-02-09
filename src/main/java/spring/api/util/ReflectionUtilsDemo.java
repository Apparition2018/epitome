package spring.api.util;

import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * ReflectionUtils
 *
 * @author ljh
 * @since 2021/9/8 11:32
 */
public class ReflectionUtilsDemo extends Demo {

    @Test
    public void testReflectionUtils() {
        // Field handling
        Field field = ReflectionUtils.findField(Person.class, "name", String.class);
        ReflectionUtils.makeAccessible(Objects.requireNonNull(field));
        ReflectionUtils.setField(field, personList.get(0), MY_NAME);
        p(ReflectionUtils.getField(field, personList.get(0)));

        // Method handling
        Method[] methods = ReflectionUtils.getDeclaredMethods(Person.class);
        for (Method method : methods) {
            if (ReflectionUtils.isEqualsMethod(method)) {
                p(method.getName());
            }
        }

        Method method = ReflectionUtils.findMethod(Person.class, "setName", String.class);
        ReflectionUtils.makeAccessible(Objects.requireNonNull(method));
        ReflectionUtils.invokeMethod(method, personList.get(0), "ljh2");
        p(personList.get(0).getName());

        ReflectionUtils.doWithMethods(Person.class, method1 -> {
            if (method1.getName().startsWith("set")) {
                p(method1.getName());
            }
        });
    }
}
