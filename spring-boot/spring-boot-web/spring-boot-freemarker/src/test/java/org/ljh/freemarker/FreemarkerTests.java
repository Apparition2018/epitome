package org.ljh.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FreeMarker 渲染测试
 *
 * <p>说明：为避免依赖 {@code @AutoConfigureMockMvc}（其所在的 spring-boot-test-autoconfigure
 * 在当前依赖链下解析不稳定），本测试直接注入 {@link FreeMarkerConfigurer}，
 * 通过 FreeMarker 自身 {@link Template#process} 渲染并断言输出，仅依赖 @SpringBootTest + AssertJ。
 *
 * @author ljh
 * @since 2026/08/23
 */
@SpringBootTest
@DisplayName("FreeMarker 渲染测试")
class FreemarkerTests {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private String render(String templateName, Object dataModel) throws Exception {
        Configuration cfg = freeMarkerConfigurer.getConfiguration();
        Template template = cfg.getTemplate(templateName);
        StringWriter out = new StringWriter();
        template.process(dataModel, out);
        return out.toString();
    }

    @Test
    @DisplayName("index.ftlh：渲染共享变量与表达式")
    void index() throws Exception {
        String html = render("index.ftlh", Map.of(
                "appName", "Spring Boot + FreeMarker",
                "message", "Hello FreeMarker!",
                "count", 42
        ));
        assertThat(html).contains("Hello FreeMarker!");
        assertThat(html).contains("Spring Boot + FreeMarker");
        assertThat(html).contains("42");
    }

    @Test
    @DisplayName("users.ftlh：遍历输出所有用户")
    void users() throws Exception {
        // FreeMarker 原生支持 java.util.Date 的 ?date；LocalDate 默认不被识别，故用 GregorianCalendar（month 从 0 起）
        List<Map<String, Object>> users = List.of(
                Map.of("id", 1, "name", "张三", "email", "zhangsan@example.com", "roles", List.of("admin"), "balance", new BigDecimal("128.50"), "createdAt", new GregorianCalendar(2020, 2, 5).getTime(), "admin", true),
                Map.of("id", 2, "name", "李四", "email", "lisi@example.com", "roles", List.of("user"), "balance", new BigDecimal("0.00"), "createdAt", new GregorianCalendar(2021, 6, 12).getTime(), "admin", false),
                Map.of("id", 3, "name", "王五", "email", "wangwu@example.com", "roles", List.of("viewer"), "balance", new BigDecimal("3200.75"), "createdAt", new GregorianCalendar(2023, 10, 20).getTime(), "admin", false)
        );
        String html = render("users.ftlh", Map.of(
                "appName", "Spring Boot + FreeMarker",
                "users", users,
                "emptyList", List.of()
        ));
        assertThat(html).contains("张三").contains("李四").contains("王五");
        assertThat(html).contains("管理员");
        assertThat(html).contains("¥128.50");
    }

    @Test
    @DisplayName("form.ftlh：HTML 特殊字符被转义，防止 XSS")
    void escapeHtml() throws Exception {
        String html = render("form.ftlh", Map.of(
                "keyword", "<script>alert(1)</script>"
        ));
        assertThat(html).contains("&lt;script&gt;alert(1)&lt;/script&gt;");
        assertThat(html).doesNotContain("<script>alert(1)</script>");
    }
}
