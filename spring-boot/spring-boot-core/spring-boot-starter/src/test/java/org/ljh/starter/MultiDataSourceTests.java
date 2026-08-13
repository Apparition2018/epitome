package org.ljh.starter;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * HikariCP + H2 最简多数据源演示测试：
 * primary / secondary 两个独立的内存库，各自建表、读写互不干扰。
 *
 * <p>运行方式：直接执行本测试（需先完成 pom.xml 依赖添加）。
 */
@SpringBootTest
class MultiDataSourceTests {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;

    @Test
    @DisplayName("两个数据源是两个独立的 Hikari 连接池")
    void twoDistinctHikariPools() {
        assertThat(primaryDataSource).isInstanceOf(HikariDataSource.class);
        assertThat(secondaryDataSource).isInstanceOf(HikariDataSource.class);
        assertThat(primaryDataSource).isNotSameAs(secondaryDataSource);
        // 连接池参数从 application.properties 绑定生效
        assertThat(((HikariDataSource) primaryDataSource).getMaximumPoolSize()).isEqualTo(5);
    }

    @Test
    @DisplayName("primary 库可独立建表读写")
    void primaryWritesOwnDatabase() {
        primaryJdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS t_user (
                    id   INT PRIMARY KEY,
                    name VARCHAR(50)
                )
                """);
        primaryJdbcTemplate.update("INSERT INTO t_user (id, name) VALUES (?, ?)", 1, "primary-user");

        Integer count = primaryJdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_user", Integer.class);
        assertThat(count).isEqualTo(1);
        String name = primaryJdbcTemplate.queryForObject("SELECT name FROM t_user WHERE id = 1", String.class);
        assertThat(name).isEqualTo("primary-user");
    }

    @Test
    @DisplayName("secondary 库可独立建表读写")
    void secondaryWritesOwnDatabase() {
        secondaryJdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS t_order (
                    id     INT PRIMARY KEY,
                    amount DECIMAL(10, 2)
                )
                """);
        secondaryJdbcTemplate.update("INSERT INTO t_order (id, amount) VALUES (?, ?)", 100, 99.50);

        Integer count = secondaryJdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_order", Integer.class);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("两库完全隔离：primary 查不到 secondary 的表")
    void databasesAreIsolated() {
        // 先在 secondary 库建表，保证本测试自包含、不依赖执行顺序
        secondaryJdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS t_order (
                    id     INT PRIMARY KEY,
                    amount DECIMAL(10, 2)
                )
                """);
        // t_order 只存在于 secondary 库，primary 库查询必然失败（表不存在）
        assertThatThrownBy(() -> primaryJdbcTemplate
                .queryForObject("SELECT COUNT(*) FROM t_order", Integer.class))
                .isInstanceOf(BadSqlGrammarException.class);
    }
}
