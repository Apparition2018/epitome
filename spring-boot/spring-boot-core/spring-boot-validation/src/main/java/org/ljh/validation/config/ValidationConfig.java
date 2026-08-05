package org.ljh.validation.config;

import jakarta.validation.Validator;
import org.hibernate.validator.BaseHibernateValidatorConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 校验相关 Bean 配置：
 * <ul>
 *   <li>{@code validator}：HibernateValidator 开启 failFast，遇第一个错误立即返回</li>
 *   <li>{@code methodValidationPostProcessor}：开启 Service 方法级校验 AOP</li>
 * </ul>
 */
@Configuration
public class ValidationConfig {

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        // 快速失败：默认 false（收集全部错误），true 则遇到第一个错误即返回
        factory.getValidationPropertyMap().put(
                BaseHibernateValidatorConfiguration.FAIL_FAST, "true");
        return factory;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(Validator validator) {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator);
        return processor;
    }
}
