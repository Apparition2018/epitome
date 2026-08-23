package org.ljh.freemarker.controller;

import org.ljh.freemarker.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FreeMarker 演示控制器
 *
 * <p>说明：模板数据统一转换为 {@code Map}（而非直接传 record），
 * 避免依赖 FreeMarker 对 record / LocalDate 的扩展配置，保持简单可靠。
 *
 * @author ljh
 * @since 2026/08/23
 */
@Controller
public class DemoController {

    /**
     * 首页：渲染共享变量 + 简单的表达式/内置函数
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello FreeMarker!");
        model.addAttribute("count", 42);
        return "index";
    }

    /**
     * 用户列表：遍历、条件判断、空值兜底
     */
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", toMapList(User.demo()));
        model.addAttribute("emptyList", List.of());
        return "users";
    }

    /**
     * 用户详情：路径变量、数据格式化、null 安全
     */
    @GetMapping("/users/{id}")
    public String userDetail(@PathVariable Long id, Model model) {
        User user = User.demo().stream()
                .filter(u -> u.id().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("user", user == null ? null : toMap(user));
        model.addAttribute("unknown", null);
        return "detail";
    }

    /**
     * 表单回显：演示 ${...} 转义 与 <#escape> 安全输出
     */
    @GetMapping("/form")
    public String form(@RequestParam(defaultValue = "") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "form";
    }

    /**
     * 错误页：演示状态码与异常信息渲染
     */
    @GetMapping("/error-demo")
    public String errorDemo() {
        throw new IllegalStateException("人为触发的异常，用于演示错误页渲染");
    }

    /** 将 User 列表转换为 Map 列表，供 FreeMarker 模板安全访问 */
    private static List<Map<String, Object>> toMapList(List<User> users) {
        return users.stream().map(DemoController::toMap).toList();
    }

    /** 将 User record 转换为 Map（createdAt 用原生 Date，FreeMarker ?date 可直接格式化） */
    private static Map<String, Object> toMap(User u) {
        return Map.of(
                "id", u.id(),
                "name", u.name(),
                "email", u.email(),
                "roles", u.roles(),
                "createdAt", toDate(u.createdAt()),
                "balance", u.balance(),
                "admin", u.admin()
        );
    }

    private static Date toDate(java.time.LocalDate localDate) {
        return java.util.Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
    }
}
