package knowledge.annotation;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static l.demo.Demo.p;

/**
 * 用户自定义注解
 * Annotation 是 Java5 引入的新特征。
 * 它提供了一种安全的类似注释的机制，用来将任何的信息或元数据（metadata）与程序元素（类、方法、成员变量等）进行关联。
 * 为程序的元素（类、方法、成员变量）加上更直观更明了的说明，这些说明信息是与程序的业务逻辑无关，并且供指定的工具或框架使用。
 * Annotation 像一种修饰符一样，应用于包、类型、构造方法、方法、成员变量、参数及本地变量的声明语句中。
 * Annotation 是附加在代码中的一些元信息，用于一些工具在编译、运行时进行解析和使用，起到说明、配置的功能。
 * Annotation 不会也不能影响代码的实际逻辑，仅仅起到辅助性的作用。
 * <p>
 * Annotation 实现原理与自定义注解例子：https://www.cnblogs.com/acm-bingzi/p/javaAnnotation.html
 * RuoYi 导入导出 (ExcelUtil)：http://doc.ruoyi.vip/ruoyi-vue/document/htsc.html#%E5%AF%BC%E5%85%A5%E5%AF%BC%E5%87%BA
 *
 * @author ljh
 * created on 2020/9/18 18:01
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
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
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
            e.printStackTrace();
        }
    }

    /**
     * 使用自定义注解
     */
    @Getter
    @Setter
    @Description(desc = "Apple")
    public static class Apple {
        @FruitName("Apple")
        private String appleName;

        @FruitColor(fruitColor = FruitColor.Color.RED)
        private String appleColor;

        @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路89号红富士大厦")
        private String appleProvider;

        @Description(desc = "displayName")
        @SuppressWarnings("unused")
        public void displayName() {
            p("水果名称是：苹果");
        }
    }

    /**
     * 水果名称注解
     * <p>
     * 使用 @interface 关键字定义注解
     * 注解类可以没有成员，没有成员的注解成为标识注解
     * <p>
     * 4种元注解：
     * `@Documented 注解是否包含在 JavaDoc 中
     * `@Retention  生命周期 (SOURCE / CLASS / RUNTIME)，什么时候使用注解
     * `@Target     作用域，注解用于什么地方
     * `@Inherited  是否允许子类继承该注解 (子类会继承父类的类注解，不会继承父类的方法注解)；当类的继承层次较深时，不建议使用
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface FruitName {
        /**
         * 成员以无参无异常方式声明
         * 合法的成员类型包括 String，Class，Annotation，Enumeration，原始类型
         * 如果注解只有一个成员，则成员名必须取名为value()，在使用时可以忽略成员名和赋值号(=)
         * 可以使用 default 为成员指定一个默认值
         */
        String value() default "";
    }

    /**
     * 水果颜色注解
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface FruitColor {
        // 颜色枚举
        enum Color {
            BLUE, RED, GREEN
        }

        // 颜色属性
        Color fruitColor() default Color.GREEN;
    }

    /**
     * 水果供应商注解
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface FruitProvider {
        // 供应商编号
        int id() default -1;

        // 供应商名称
        String name() default "";

        // 供应商地址
        String address() default "";
    }

    @Target({ElementType.METHOD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface Description {

        String desc();
    }
}