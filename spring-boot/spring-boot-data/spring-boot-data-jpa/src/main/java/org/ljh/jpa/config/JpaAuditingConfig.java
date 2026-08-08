package org.ljh.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * JPA 审计配置
 * <p>开启 {@code @EnableJpaAuditing}，使 {@code @CreatedDate}/{@code @LastModifiedDate} 等审计注解自动生效。</p>
 *
 * @author ljh
 * @since 2026/8/8
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    /**
     * 提供当前操作人。演示中固定返回 "system"，
     * 真实场景可从 SecurityContext / 请求上下文中获取。
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("system");
    }
}
