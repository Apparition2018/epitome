package org.ljh.validation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 嵌套对象，用于演示 @Valid 递归校验。
 */
public record Address(
        @NotBlank(message = "省份不能为空")
        String province,

        @NotBlank(message = "城市不能为空")
        String city,

        @Size(max = 200, message = "详细地址最长 200 字")
        String detail
) {
}
