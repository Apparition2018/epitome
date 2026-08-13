package org.ljh.druid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Druid 连接池演示启动类
 * <p>演示 Druid 作为 Spring Boot 数据源连接池的接入、监控与慢 SQL 统计。</p>
 *
 * @author ljh
 * @since 2026/8/13
 */
@SpringBootApplication
public class DruidApplication {

    public static void main(String[] args) {
        SpringApplication.run(DruidApplication.class, args);
    }
}
