package knowledge.注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * 用户自定义注解
 * <p>
 * Annotation 是 Java5 引入的新特征。
 * 它提供了一种安全的类似注释的机制，用来将任何的信息或元数据（metadata）与程序元素（类、方法、成员变量等）进行关联。
 * 为程序的元素（类、方法、成员变量）加上更直观更明了的说明，这些说明信息是与程序的业务逻辑无关，并且供指定的工具或框架使用。
 * Annotation 像一种修饰符一样，应用于包、类型、构造方法、方法、成员变量、参数及本地变量的声明语句中。
 * <p>
 * Annotation 是附加在代码中的一些元信息，用于一些工具在编译、运行时进行解析和使用，起到说明、配置的功能。
 * Annotation 不会也不能影响代码的实际逻辑，仅仅起到辅助性的作用。
 * <p>
 * 4种元注解：
 * Documented   注解是否包含在 JavaDoc 中
 * Retention    什么时候使用注解
 * Target       注解用于什么地方
 * Inherited    是否允许子类继承该注解
 * <p>
 * https://www.cnblogs.com/acm-bingzi/p/javaAnnotation.html
 */
public class UserDefinedAnnotation {

    public static void main(String[] args) {
        getFruitInfo(Apple.class);
    }

    private static void getFruitInfo(Class<?> clazz) {
        String strFruitName = "水果名称：";
        String strFruitColor = "水果颜色：";
        String strFruitProvider = "供应商信息：";

        Field[] fields = clazz.getDeclaredFields();

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
                System.out.println(strFruitName);
            } else if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                strFruitColor += fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            } else if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvider += " 供应商编号：" + fruitProvider.id() + "; 供应商名称：" + fruitProvider.name() + "; 供应商地址：" + fruitProvider.address();
                System.out.println(strFruitProvider);
            }
        }
    }
}

/**
 * 使用自定义注解
 */
class Apple {
    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;

    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路89号红富士大厦")
    private String appleProvider;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public void displayName() {
        System.out.println("水果名称是：苹果");
    }
}

/**
 * 水果名称注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FruitName {
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