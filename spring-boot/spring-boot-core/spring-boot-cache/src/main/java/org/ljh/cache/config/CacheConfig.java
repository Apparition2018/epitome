package org.ljh.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * 缓存配置：{@code @EnableCaching} 开启注解式缓存，{@code CacheManager} 使用 Caffeine 本地缓存。
 *
 * <p>等价写法：不定义 Bean，改在 {@code application.properties} 配置：
 * <pre>
 * spring.cache.type=caffeine
 * spring.cache.caffeine.spec=maximumSize=500,expireAfterWrite=5m
 * </pre>
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("users");
        cacheManager.setCaffeine(Caffeine.newBuilder()
            // 缓存最多 500 条
            .maximumSize(500)
            // 写入后 5 分钟过期
            .expireAfterWrite(Duration.ofMinutes(5)));
        return cacheManager;
    }
}
