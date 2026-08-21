package com.ljh.commons.annotation;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.ljh.commons.Demo.p;

/**
 * Apple
 *
 * @author ljh
 * @since 2026/8/21 16:19
 */
@Getter
@Setter
@Description(desc = "Apple")
public class Apple {
    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;

    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路89号红富士大厦")
    private String appleProvider;

    @Description(desc = "displayName")
    public void displayName() {
        p("水果名称是：苹果");
    }


    /**
     * 水果名称注解
     * <pre>
     * 使用 @interface 关键字定义注解
     * 注解类可以没有成员，没有成员的注解成为标识注解
     * </pre>
     * 元注解：
     * <pre>
     * 1 @Documented 注解是否包含在 JavaDoc 中
     * 2 @Retention 生命周期 (SOURCE / CLASS / RUNTIME)，什么时候使用注解
     * 3 @Target 作用域，注解用于什么地方
     * 4 @Inherited 是否允许子类继承该注解 (子类会继承父类的类注解，不会继承父类的方法注解)；当类的继承层次较深时，不建议使用
     * 5 @Repeatable 可重复注释，JDK8 引入
     * </pre>
     */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FruitName {
        /**
         * <pre>
         * 成员以无参无异常方式声明
         * 合法的成员类型包括 String，Class，Annotation，Enumeration，原始类型
         * 如果注解只有一个成员，则成员名必须取名为value()，在使用时可以忽略成员名和赋值号(=)
         * 可以使用 default 为成员指定一个默认值
         * </pre>
         */
        String value() default StringUtils.EMPTY;
    }

    /** 水果颜色注解 */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FruitColor {
        // 颜色枚举
        enum Color {
            BLUE, RED, GREEN
        }

        // 颜色属性
        Color fruitColor() default Color.GREEN;
    }

    /** 水果供应商注解 */
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FruitProvider {
        // 供应商编号
        int id() default -1;

        // 供应商名称
        String name() default StringUtils.EMPTY;

        // 供应商地址
        String address() default StringUtils.EMPTY;
    }
}
