package springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * spring boot 整合 jackson
 * https://blog.csdn.net/weixin_38413579/article/details/82562634
 */
@Configuration
public class JacksonConfig {

    @Bean("getObjectMapper")
    @Primary // 自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
    @ConditionalOnMissingBean(ObjectMapper.class) // 仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean
    public ObjectMapper getObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.build();
    }

    @Bean
    public ObjectMapper ObjectMapper(){
        return new ObjectMapper();
    }

}