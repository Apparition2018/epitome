package org.ljh.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 定时任务配置：{@code @EnableScheduling} 开启注解式定时任务。
 *
 * <p>默认情况下 {@code @Scheduled} 使用单线程执行，所有任务串行；
 * 这里自定义 {@code ThreadPoolTaskScheduler} 让多个定时任务可以并行执行。
 *
 * <p>等价写法：不定义 Bean，依赖 Spring Boot 自动配置的
 * {@code spring.task.scheduling.pool.size}（默认 1）。
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        // 线程名便于日志排查
        scheduler.setPoolSize(4);
        scheduler.setThreadNamePrefix("scheduling-");
        scheduler.initialize();
        return scheduler;
    }
}
