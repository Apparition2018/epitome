package org.ljh.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验注解：校验字段值必须匹配指定枚举类的某个枚举常量名。
 */
@Documented
@Constraint(validatedBy = EnumValueValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValue {

    /** 目标枚举类型 */
    Class<? extends Enum<?>> enumClass();

    /** 是否允许为 null（默认允许，null 视为合法） */
    boolean allowNull() default true;

    String message() default "取值不在允许的枚举范围内";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
