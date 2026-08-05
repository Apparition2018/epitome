package org.ljh.validation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.ljh.validation.annotation.EnumValue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 新增用户请求体，集中演示最常用的校验注解。
 */
public record UserCreateRequest(

        @NotBlank(message = "用户名不能为空")
        @Size(min = 3, max = 20, message = "用户名长度必须在 3-20 之间")
        String username,

        @NotBlank(message = "密码不能为空")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,32}$", message = "密码需 8-32 位且同时包含字母和数字")
        String password,

        @NotBlank(message = "邮箱不能为空")
        @Email(message = "邮箱格式不正确")
        String email,

        @Min(value = 1, message = "年龄最小为 1")
        @Max(value = 150, message = "年龄最大为 150")
        Integer age,

        @Positive(message = "余额必须为正数")
        @Digits(integer = 8, fraction = 2, message = "余额最多 8 位整数、2 位小数")
        BigDecimal balance,

        @NotNull(message = "生日不能为空")
        @Past(message = "生日必须是过去时间")
        LocalDate birthday,

        @EnumValue(enumClass = UserType.class, message = "用户类型只能为 NORMAL 或 VIP")
        String userType,

        @NotEmpty(message = "爱好不能为空")
        List<@NotBlank(message = "爱好不能为空字符串") @Size(max = 10, message = "单个爱好最长 10 字") String> hobbies,

        @NotNull(message = "地址不能为空")
        @Valid
        Address address,

        @AssertTrue(message = "必须同意服务条款")
        boolean agreed
) {

    public enum UserType {
        NORMAL, VIP
    }
}
