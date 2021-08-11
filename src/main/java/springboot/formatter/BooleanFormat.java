package springboot.formatter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BooleanFormat
 *
 * @author ljh
 * created on 2021/8/11 10:53
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface BooleanFormat {
    String[] values() default {};
}
