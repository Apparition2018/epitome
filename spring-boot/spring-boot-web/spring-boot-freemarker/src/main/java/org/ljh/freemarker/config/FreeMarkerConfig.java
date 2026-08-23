package org.ljh.freemarker.config;

import org.springframework.boot.freemarker.autoconfigure.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Map;

/**
 * FreeMarker 配置
 *
 * <p>Spring Boot 通过 {@link FreeMarkerAutoConfiguration}
 * 已自动装配 FreeMarker（模板位置、后缀、字符集等见 application.yml）。此处仅演示如何：
 * <ul>
 *     <li>自定义 ViewResolver 的后缀与前缀</li>
 *     <li>为所有模板注入静态共享变量（${appName}）</li>
 * </ul>
 *
 * <p>注意：一旦自定义 {@link FreeMarkerViewResolver}，会替换 Boot 默认视图解析器，需显式配置各属性。
 *
 * <p>说明：本模块的模板数据统一使用 {@code Map} 而非 Java record，因此无需额外配置
 * FreeMarker 的 {@code ObjectWrapper}（避免引入对 record / java.time 的扩展依赖，保持简单可靠）。
 *
 * @author ljh
 * @since 2026/08/23
 */
@Configuration
public class FreeMarkerConfig {

    @Bean
    public ViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setOrder(0);
        return resolver;
    }

    /**
     * 覆盖默认的 FreeMarkerConfigurer，注入共享静态变量
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("classpath:/templates/");
        configurer.setDefaultEncoding("UTF-8");
        // setFreemarkerVariables 参数为 Map<String, Object>，目标类型推断使 V 自动为 Object
        configurer.setFreemarkerVariables(Map.of("appName", "Spring Boot + FreeMarker"));
        return configurer;
    }
}
