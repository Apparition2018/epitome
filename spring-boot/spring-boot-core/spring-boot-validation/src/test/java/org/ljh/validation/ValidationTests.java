package org.ljh.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ljh.validation.dto.Address;
import org.ljh.validation.dto.UserCreateRequest;
import org.ljh.validation.dto.UserUpdateRequest;
import org.ljh.validation.group.Create;
import org.ljh.validation.group.Update;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 编程式校验演示，不依赖 Spring 容器，直接使用 Bean Validation API。
 */
class ValidationTests {

    // 默认校验器（收集全部错误）
    private final Validator validator = defaultValidator();

    private static Validator defaultValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            return factory.getValidator();
        }
    }

    private static UserCreateRequest validRequest() {
        return new UserCreateRequest(
                "alice",
                "abc12345",
                "alice@example.com",
                25,
                new BigDecimal("1234.50"),
                LocalDate.of(1998, 5, 20),
                "VIP",
                List.of("篮球", "编程"),
                new Address("广东", "深圳", "南山"),
                true);
    }

    @Test
    @DisplayName("合法数据不应有任何校验错误")
    void validDataShouldPass() {
        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(validRequest());
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("非法数据应收集到全部校验错误")
    void invalidDataShouldCollectAllErrors() {
        UserCreateRequest bad = new UserCreateRequest(
                "",
                "short",
                "not-an-email",
                -1,
                new BigDecimal("999999999999.999"),
                LocalDate.now().plusDays(1),
                "SUPER",
                List.of(""),
                new Address("", "", null),
                false);

        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(bad);
        assertFalse(violations.isEmpty());
        System.out.println("==== 收集到的全部错误 ====");
        for (ConstraintViolation<UserCreateRequest> v : violations) {
            System.out.println(v.getPropertyPath() + " -> " + v.getMessage());
        }
    }

    @Test
    @DisplayName("分组校验应体现 Create 与 Update 的约束差异")
    void groupValidationShouldDifferBetweenCreateAndUpdate() {
        // 新增分组：id 必须为 null，否则报错
        UserUpdateRequest createReq = new UserUpdateRequest(100L, "alice", null, null);
        assertFalse(validator.validate(createReq, Create.class).isEmpty(), "Create 分组下 id 不为 null 应校验失败");

        // 更新分组：id 不能为 null
        UserUpdateRequest updateReq = new UserUpdateRequest(null, "alice", "bad-email", null);
        Set<ConstraintViolation<UserUpdateRequest>> updateViolations = validator.validate(updateReq, Update.class);
        assertFalse(updateViolations.isEmpty(), "Update 分组下 id 为 null 应校验失败");

        // 更新分组：email 非空时才校验格式
        UserUpdateRequest updateReq2 = new UserUpdateRequest(1L, null, "alice@example.com", null);
        assertTrue(validator.validate(updateReq2, Update.class).isEmpty());
        System.out.println("Create 与 Update 分组校验差异演示通过");
    }
}
