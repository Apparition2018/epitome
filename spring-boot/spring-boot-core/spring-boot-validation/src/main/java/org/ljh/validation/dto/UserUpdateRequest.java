package org.ljh.validation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import org.ljh.validation.group.Create;
import org.ljh.validation.group.Update;

/**
 * 更新用户请求体，演示分组校验差异：
 * <ul>
 *   <li>id：新增时须为 null，更新时不能为 null</li>
 *   <li>username / email / address：仅新增时强校验，更新时允许为空（空则不更新）</li>
 * </ul>
 */
public record UserUpdateRequest(

        @Null(groups = Create.class, message = "新增时 id 必须为空")
        @NotNull(groups = Update.class, message = "更新时 id 不能为空")
        Long id,

        @Size(min = 3, max = 20, message = "用户名长度必须在 3-20 之间")
        String username,

        @Email(message = "邮箱格式不正确")
        String email,

        @Valid
        Address address
) {
}
