package org.ljh.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

/**
 * JPA 配置。
 * <p>说明：{@code EntityManagerFactory}、{@code JpaTransactionManager} 均由
 * spring-boot-starter-data-jpa 自动装配，无需手工重复声明；这里的配置只补充一个
 * 规范的异常翻译后处理器——把 JPA 的 {@code PersistenceException}（及其子类）翻译成
 * Spring 统一的 {@code DataAccessException} 体系，便于上层按异常类型统一捕获。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@Configuration
public class JpaConfig {

    /**
     * 注册持久化异常翻译后置处理器（static：需在配置类实例化前生效）。
     *
     * @return 异常翻译后置处理器
     */
    @Bean
    public static PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
