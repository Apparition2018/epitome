package org.ljh.validation.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 方法级校验：类上标注 @Validated 后，方法参数/返回值上的校验注解才会生效。
 * 触发时抛出 {@link jakarta.validation.ConstraintViolationException}。
 */
@Service
@Validated
public class UserService {

    public String getUserById(@Min(value = 1, message = "id 必须大于等于 1") Long id) {
        return "user-" + id;
    }

    @NotBlank(message = "方法返回值不能为空白")
    public String getUsername(@NotBlank(message = "参数 name 不能为空") String name) {
        return name;
    }
}
