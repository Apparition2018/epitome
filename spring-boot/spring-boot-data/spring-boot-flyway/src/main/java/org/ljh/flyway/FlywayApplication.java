package org.ljh.flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FlywayApplication
 * <p>
 * 基于 H2 内存库的 Flyway 数据库版本迁移样例。
 * 应用启动时，Flyway 会自动执行 classpath:db/migration 下的版本化脚本（V1、V2）。
 *
 * @author ljh
 * @since 2026/7/18 17:50
 */
@SpringBootApplication
public class FlywayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayApplication.class, args);
    }
}
