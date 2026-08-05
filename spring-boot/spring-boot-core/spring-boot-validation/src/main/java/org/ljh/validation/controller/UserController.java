package org.ljh.validation.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.ljh.validation.dto.UserCreateRequest;
import org.ljh.validation.dto.UserUpdateRequest;
import org.ljh.validation.group.Create;
import org.ljh.validation.group.Update;
import org.ljh.validation.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller 层校验：
 * <ul>
 *   <li>{@code @RequestBody} 使用 {@code @Validated(groups)} 触发
 *       {@code MethodArgumentNotValidException}</li>
 *   <li>{@code @PathVariable}/{@code @RequestParam} 在参数上 {@code @Validated} 后触发
 *       {@code HandlerMethodValidationException}</li>
 * </ul>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** 新增：走 Create 分组 */
    @PostMapping
    public Map<String, Object> create(@Validated(Create.class) @RequestBody UserCreateRequest request) {
        return Map.of("action", "create", "username", request.username());
    }

    /** 更新：走 Update 分组 */
    @PutMapping
    public Map<String, Object> update(@Validated(Update.class) @RequestBody UserUpdateRequest request) {
        return Map.of("action", "update", "id", request.id());
    }

    /** 路径参数校验 */
    @GetMapping("/{id}")
    public Map<String, Object> get(@Validated @PathVariable @Min(value = 1, message = "id 必须大于等于 1") Long id) {
        return Map.of("id", id, "user", userService.getUserById(id));
    }

    /** 查询参数校验 */
    @GetMapping
    public Map<String, Object> query(
            @Validated @RequestParam @NotBlank(message = "name 不能为空") String name,
            @Validated @RequestParam(required = false) @Email(message = "email 格式不正确") String email) {
        return Map.of("name", name, "email", email);
    }
}
