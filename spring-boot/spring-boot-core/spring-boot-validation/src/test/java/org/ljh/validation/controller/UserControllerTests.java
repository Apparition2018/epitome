package org.ljh.validation.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ljh.validation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller 层校验测试（Spring MVC 切片测试，自动触发校验）。
 * 与 {@code ValidationTest}（编程式校验）互补，覆盖两条链路：
 * <ul>
 *   <li>链路一：{@code @RequestBody} + {@code @Validated(groups)} → {@code MethodArgumentNotValidException}</li>
 *   <li>链路二：{@code @PathVariable}/{@code @RequestParam} 参数上 {@code @Validated} →
 *       {@code HandlerMethodValidationException}</li>
 * </ul>
 */
@WebMvcTest(UserController.class)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    // ---------- 链路二：@PathVariable 校验（HandlerMethodValidationException） ----------

    @Test
    @DisplayName("路径参数小于 1 应校验失败，返回 400")
    void pathVariableOutOfRangeShouldFail() throws Exception {
        mockMvc.perform(get("/users/0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("handler_method"))
                .andExpect(jsonPath("$.errors.id").exists());
    }

    @Test
    @DisplayName("路径参数合法应通过，并返回用户信息")
    void pathVariableValidShouldPass() throws Exception {
        when(userService.getUserById(1L)).thenReturn("user-1");

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.user").value("user-1"));
    }

    // ---------- 链路二：@RequestParam 校验（HandlerMethodValidationException） ----------

    @Test
    @DisplayName("缺少必填查询参数 name 应返回 400")
    void missingRequiredQueryParamShouldFail() throws Exception {
        // 缺少必填 @RequestParam 抛 MissingServletRequestParameterException，
        // 未在 GlobalExceptionHandler 中处理，故返回 400 但无结构化 JSON 体。
        mockMvc.perform(get("/users"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("查询参数 email 格式非法应校验失败，返回 400")
    void invalidEmailQueryParamShouldFail() throws Exception {
        mockMvc.perform(get("/users").param("name", "alice").param("email", "not-an-email"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("handler_method"))
                .andExpect(jsonPath("$.errors.email").exists());
    }

    @Test
    @DisplayName("合法查询参数应通过")
    void validQueryParamsShouldPass() throws Exception {
        mockMvc.perform(get("/users").param("name", "alice").param("email", "alice@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    // ---------- 链路一：@RequestBody 校验（MethodArgumentNotValidException） ----------

    @Test
    @DisplayName("POST 请求体违反 Create 分组约束应返回 400")
    void invalidCreateBodyShouldFail() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "",
                                  "password": "short",
                                  "email": "not-an-email",
                                  "age": -1,
                                  "balance": 1234.50,
                                  "birthday": "1998-05-20",
                                  "userType": "VIP",
                                  "hobbies": ["篮球"],
                                  "address": {"province": "广东", "city": "深圳", "street": "南山"},
                                  "agreed": true
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("method_argument"))
                .andExpect(jsonPath("$.errors.username").exists())
                .andExpect(jsonPath("$.errors.password").exists())
                .andExpect(jsonPath("$.errors.email").exists())
                .andExpect(jsonPath("$.errors.age").exists());
    }

    @Test
    @DisplayName("POST 合法请求体应通过并返回创建结果")
    void validCreateBodyShouldPass() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "alice",
                                  "password": "abc12345",
                                  "email": "alice@example.com",
                                  "age": 25,
                                  "balance": 1234.50,
                                  "birthday": "1998-05-20",
                                  "userType": "VIP",
                                  "hobbies": ["篮球"],
                                  "address": {"province": "广东", "city": "深圳", "street": "南山"},
                                  "agreed": true
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.action").value("create"))
                .andExpect(jsonPath("$.username").value("alice"));
    }

    @Test
    @DisplayName("PUT 请求体缺少 Update 分组必填的 id 应返回 400")
    void invalidUpdateBodyShouldFail() throws Exception {
        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": null,
                                  "username": "alice"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type").value("method_argument"))
                .andExpect(jsonPath("$.errors.id").exists());
    }

    @Test
    @DisplayName("PUT 合法请求体（含 Update 分组 id）应通过")
    void validUpdateBodyShouldPass() throws Exception {
        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 1,
                                  "username": "alice",
                                  "email": "alice@example.com"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.action").value("update"))
                .andExpect(jsonPath("$.id").value(1));
    }
}
