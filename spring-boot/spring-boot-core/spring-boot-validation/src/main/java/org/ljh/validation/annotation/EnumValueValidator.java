package org.ljh.validation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * {@link EnumValue} 的校验器实现。
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private Class<? extends Enum<?>> enumClass;
    private boolean allowNull;

    @Override
    public void initialize(EnumValue annotation) {
        this.enumClass = annotation.enumClass();
        this.allowNull = annotation.allowNull();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return allowNull;
        }
        if (enumClass == null) {
            return false;
        }
        // 将值转为字符串与枚举常量名比对
        String text = String.valueOf(value);
        for (Enum<?> e : enumClass.getEnumConstants()) {
            if (e.name().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
