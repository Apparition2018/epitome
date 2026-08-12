package org.ljh.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.data.jpa.autoconfigure.DataJpaRepositoriesAutoConfiguration;

/**
 * JPA（Jakarta Persistence）演示启动类
 * <p>本模块的数据访问统一使用 JPA 规范标准 API（{@code jakarta.persistence.*} +
 * {@code EntityManager} / {@code EntityManagerFactory}），通过 {@code EntityManager}
 * 完成增删改查，而不引入 Spring Data JPA 的 {@code JpaRepository} / 派生查询 /
 * {@code @Query} 注解——这里显式关闭 {@code DataJpaRepositoriesAutoConfiguration}，
 * 确保整个应用不创建任何 Repository 代理，数据访问仅依赖标准 {@code EntityManager}。</p>
 *
 * @author ljh
 * @since 2026/8/11
 */
@SpringBootApplication(exclude = DataJpaRepositoriesAutoConfiguration.class)
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
