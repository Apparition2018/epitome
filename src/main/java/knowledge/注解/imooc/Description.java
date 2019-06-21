package knowledge.注解.imooc;

import java.lang.annotation.*;

/*
 * 《全面解析Java注解》
 *
 * 使用注解的语法：
 * @<注解名>(<成员名1>=<成员值1>,<成员名2>=<成员值2>,...)
 */

@Target({ElementType.METHOD, ElementType.TYPE}) // 作用域
@Retention(RetentionPolicy.RUNTIME)             // 生命周期 (SOURCE / CLASS / RUNTIME)
@Inherited                                      // 允许子类继承 (子类会继承父类的类注解，不会继承父类的方法注解)
@Documented                                     // 生成javadoc时会包含注解
// 使用 @interface 关键字定义注解
// 注解类可以没有成员，没有成员的注解成为标识注解
public @interface Description {

    // 成员以无参无异常方式声明
    // 合法的成员类型包括原始类型、String、Class、Annotation和Enumeration
    // 如果注解只有一个成员，则成员名必须取名为value()，在使用时可以忽略成员名和赋值号(=)
    String desc();

    String author() default "234607";

    // 可以用 default 为成员指定一个默认值
    int age() default 18;
}