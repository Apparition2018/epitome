package jar.hutool.util;

import cn.hutool.core.util.ReflectUtil;
import l.demo.Animal.Cat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static l.demo.Demo.p;

/**
 * <a href="https://hutool.cn/docs/#/core/工具类/反射工具-ReflectUtil">ReflectUtil</a> 反射工具
 * <p><a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/core/util/ReflectUtil.html">ReflectUtil api</a>
 *
 * @author ljh
 * @since 2020/10/29 13:38
 */
public class ReflectUtilDemo {

    public static final String X = "X";

    public static void main(String[] args) {
        //********** 1.动态创建对象 **********//
        Cat cat = ReflectUtil.newInstanceIfPossible(Cat.class);
        cat.setName("白猫");
        cat.setAge(3);
        cat.setFoot(4);

        //********** 2.Field **********//
        Field ageField = null;
        Field staticField = null;
        // 动态获取类中声明的属性，包括父类
        Field[] fields = ReflectUtil.getFields(Cat.class);
        ReflectUtil.getFieldMap(Cat.class);
        // 动态获取类中声明的属性，true 包括父类，false 不包括父类
        ReflectUtil.getFieldsDirectly(Cat.class, false);
        // 判断是否有某一个属性
        if (ReflectUtil.hasField(Cat.class, "age")) {
            // 动态查找一个属性
            ageField = ReflectUtil.getField(Cat.class, "age");
        }
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                staticField = field;
            }
        }
        // 动态设置一个属性
        if (null != ageField) {
            //// 改变访问限制
            ReflectUtil.setAccessible(ageField);
            ReflectUtil.setFieldValue(cat, ageField, 4);
        }
        // 动态获取一个静态属性的值
        p(ReflectUtil.getStaticFieldValue(staticField));
        // 动态获取所有字段值
        p(ReflectUtil.getFieldsValue(cat));
        p();

        //********** 3.Method **********//
        // 动态过滤获取类中声明的方法，包括父类
        ReflectUtil.getMethods(Cat.class, method -> Modifier.isPrivate(method.getModifiers()));
        // 动态获取类中声明的方法
        Method[] methods = ReflectUtil.getMethodsDirectly(Cat.class, false, false);
        for (Method method : methods) {
            // 判断空参数方法
            if (ReflectUtil.isEmptyParam(method)) {
                p("empty param method: " + method.getName());
            }
            // 判断 equals 方法
            if (ReflectUtil.isEqualsMethod(method)) {
                p("equals method: " + method.getName());
            }
            // 判断 hash code 方法
            if (ReflectUtil.isHashCodeMethod(method)) {
                p("hash code method: " + method.getName());
            }
            // 判断 to string 方法
            if (ReflectUtil.isToStringMethod(method)) {
                p("to string method: " + method.getName());
            }
        }
        // 动态过滤获取类中声明的公有方法，包括父类
        ReflectUtil.getPublicMethods(Cat.class, method -> "setAge".equals(method.getName()));
        // 动态过滤获取类中声明的构造方法，包括父类
        ReflectUtil.getConstructors(Cat.class);
        // 动态过滤获取类中声明的构造方法
        ReflectUtil.getMethodsDirectly(Cat.class, false, false);
        // 动态去重获取类中声明的方法名，包括父类
        ReflectUtil.getMethodNames(Cat.class);
        // 动态查找一个方法
        ReflectUtil.getMethod(Cat.class, "setAge", int.class);
        // 动态查找一个方法
        ReflectUtil.getMethodByName(Cat.class, "setAge");
        // 动态查找一个方法
        Method method = ReflectUtil.getMethodOfObj(cat, "setAge", 0);
        // 动态调用方法
        ReflectUtil.invokeWithCheck(cat, method, 3);
        p("cat.getA() = " + cat.getAge());
    }
}
