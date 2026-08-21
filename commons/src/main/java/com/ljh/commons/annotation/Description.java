package com.ljh.commons.annotation;

import java.lang.annotation.*;

/**
 * Description
 *
 * @author ljh
 * @since 2026/8/21 16:21
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Description {
    String desc();
}
