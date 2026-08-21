package knowledge.annotation;

import com.ljh.commons.annotation.Apple;
import com.ljh.commons.annotation.Apple.FruitColor;
import com.ljh.commons.annotation.Apple.FruitName;
import com.ljh.commons.annotation.Apple.FruitProvider;
import com.ljh.commons.annotation.Description;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.ljh.commons.Demo.p;

/**
 * 用户自定义注解
 * <pre>
 * Annotation 是 JDK5 引入的新特性
 * 它提供了一种安全的类似注释的机制，用来将任何的信息或元数据（metadata）与程序元素（类、方法、成员变量等）进行关联。
 * 为程序的元素（类、方法、成员变量）加上更直观更明了的说明，这些说明信息是与程序的业务逻辑无关，并且供指定的工具或框架使用。
 * Annotation 像一种修饰符一样，应用于包、类型、构造方法、方法、成员变量、参数及本地变量的声明语句中。
 * Annotation 是附加在代码中的一些元信息，用于一些工具在编译、运行时进行解析和使用，起到说明、配置的功能。
 * Annotation 不会也不能影响代码的实际逻辑，仅仅起到辅助性的作用。
 * </pre>
 *
 * @author ljh
 * @see <a href="https://www.cnblogs.com/acm-bingzi/p/javaAnnotation.html">Annotation 实现原理与自定义注解例子</a>
 * @see <a href="http://doc.ruoyi.vip/ruoyi-vue/document/htsc.html#导入导出">RuoYi 导入导出 (ExcelUtil)</a>
 * @since 2020/9/18 18:01
 */
public class CustomAnnotation {

    @Test
    public void testTargetField() {
        String strFruitName = "水果名称：";
        String strFruitColor = "水果颜色：";
        String strFruitProvider = "供应商信息：";

        Field[] fields = Apple.class.getDeclaredFields();

        for (Field field : fields) {
            /*
             * boolean	    isAnnotationPresent(Class<? extends Annotation> annotationClass)
             * 如果指定类型的注释存在于此元素上，则返回 true，否则返回 false
             */
            if (field.isAnnotationPresent(FruitName.class)) {
                /*
                 * <T extends Annotation> T     getAnnotation(Class<T> annotationClass)
                 * 如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null
                 */
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName += fruitName.value();
                p(strFruitName);
            } else if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(Apple.FruitColor.class);
                strFruitColor += fruitColor.fruitColor().toString();
                p(strFruitColor);
            } else if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvider += " 供应商编号：" + fruitProvider.id() + "; 供应商名称：" + fruitProvider.name() + "; 供应商地址：" + fruitProvider.address();
                p(strFruitProvider);
            }
        }
    }

    @Test
    public void testTargetTypeAndMethod() {
        try {
            // 1.使用类加载器加载类
            Class<?> clazz = Class.forName("l.demo.Animal$Cat");
            // 2.找到类上面的注解
            // boolean	    isAnnotationPresent(Class<? extends Annotation> annotationClass)
            // 如果指定类型的注释存在于此元素上，则返回 true，否则返回 false
            if (clazz.isAnnotationPresent(Description.class)) {
                // 3.拿到注解实例
                //<A extends Annotation> A	    getAnnotation(Class<A> annotationClass)
                // 如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null
                Description description = clazz.getAnnotation(Description.class);
                p(description.desc());
            }

            // 4.找到方法上的注解
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Description.class)) {
                    Description description = method.getAnnotation(Description.class);
                    p(description.desc());
                }
            }

            // 另外一种解析方法
            for (Method method : methods) {
                // Annotation[]	    getAnnotations()
                // 返回此元素上存在的所有注释
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Description) {
                        p(((Description) annotation).desc());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
