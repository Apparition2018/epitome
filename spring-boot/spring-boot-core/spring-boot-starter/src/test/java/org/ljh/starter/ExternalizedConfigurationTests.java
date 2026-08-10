package org.ljh.starter;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config">Externalized Configuration</a> 外部化配置
 * <pre>
 * 0 @PropertySource             加载自定义 .properties 文件到 Environment
 * 1 <a href="https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config.typesafe-configuration-properties">@ConfigurationProperties</a> 类型安全、最推荐
 * 2 @Value                     简单字段注入
 * 3 <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-environment">Environment</a>              编程式读取
 * 4 Binder                     底层绑定 API（高级用法）
 * </pre>
 */
@SpringBootTest
// 加载自定义 .properties 文件到 Environment
@PropertySource("classpath:app.properties")
@EnableConfigurationProperties(ExternalizedConfigurationTests.AppProperties.class)
public class ExternalizedConfigurationTests {

    // ===================================================================
    // 1. @ConfigurationProperties — 类型安全，最推荐
    // ===================================================================

    @Autowired
    private AppProperties appProperties;

    @Test
    void configurationProperties() {
        assertThat(appProperties.getName()).isEqualTo("epitome");
        assertThat(appProperties.getVersion()).isEqualTo("1.0.0");
    }

    @Data
    @Component
    @ConfigurationProperties(prefix = "app")
    static class AppProperties {
        private String name;
        private String version;
        private List<String> authors;
    }

    // ===================================================================
    // 2. @Value — 简单字段注入
    // ===================================================================

    @Value("${app.name}")
    private String appName;

    @Value("${app.nonexistent:en_US}")
    private String defaultLocale;

    @Value("${random.int}")
    private int randomInt;

    @Value("${random.uuid}")
    private String randomUuid;

    @Test
    void valueInjection() {
        assertThat(appName).isEqualTo("epitome");
    }

    @Test
    void valueWithDefault() {
        assertThat(defaultLocale).isEqualTo("en_US");
    }

    @Test
    void randomValues() {
        assertThat(randomInt).isNotZero();
        assertThat(randomUuid).matches("[a-f0-9-]{36}");
    }

    // ===================================================================
    // 3. Environment — 编程式读取
    // ===================================================================

    @Autowired
    private Environment environment;

    @Test
    void environmentGetProperty() {
        String name = environment.getProperty("app.name");
        assertThat(name).isEqualTo("epitome");
    }

    @Test
    void environmentGetPropertyWithDefault() {
        String missing = environment.getProperty("app.nonexistent", "defaultVal");
        assertThat(missing).isEqualTo("defaultVal");
    }

    @Test
    void environmentGetRequiredProperty() {
        String version = environment.getRequiredProperty("app.version", String.class);
        assertThat(version).isEqualTo("1.0.0");
    }

    // ===================================================================
    // 4. Binder — 底层绑定 API（高级用法）
    // ===================================================================

    @Test
    void binderBindSimpleValue() {
        Binder binder = Binder.get(environment);
        String name = binder.bind("app.name", String.class).get();
        assertThat(name).isEqualTo("epitome");
    }

    @Test
    void binderBindToList() {
        Binder binder = Binder.get(environment);
        List<String> authors = binder.bind("app.authors", Bindable.listOf(String.class)).get();
        assertThat(authors).contains("Alice", "Bob");
    }

    @Test
    void binderBindToMap() {
        Binder binder = Binder.get(environment);
        Map<String, String> contacts = binder.bind("app.contacts", Bindable.mapOf(String.class, String.class)).get();
        assertThat(contacts).containsEntry("dev", "dev@example.com");
    }
}
