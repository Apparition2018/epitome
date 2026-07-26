# Flyway 数据库版本迁移

## 概述

[Flyway](https://flywaydb.org/) 是一款开源的数据库版本管理工具，通过版本化 SQL 脚本自动管理数据库 Schema 变更。Spring Boot 通过 `spring-boot-starter-flyway`
提供开箱即用支持。

### 核心思想

- 每个数据库变更 = 一个版本化的 SQL 脚本
- 脚本按版本号顺序执行，已执行的脚本不会重复执行
- 执行记录存储在 `flyway_schema_history` 表中

## 1. 依赖

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-flyway</artifactId>
</dependency>
```

## 2. 配置

Flyway 相关配置项（完整配置见 [`application.properties`](src/test/resources/application.properties)）：

## 3. SQL 迁移脚本

### 命名规范

```
<Prefix><Version>__<Description>.sql
```

| 部分          | 格式                                                                        | 示例                    |
|-------------|---------------------------------------------------------------------------|-----------------------|
| Prefix      | `V` — 版本化迁移（Versioned）<br>`R` — 可重复迁移（Repeatable）<br>`U` — 撤销迁移（Undo，收费版） | `V`                   |
| Version     | 数字 + 可选的点和下划线                                                             | `1`、`1_1`、`20260720`  |
| Separator   | **两个下划线**                                                                 | `__`                  |
| Description | 单词用下划线或空格分隔                                                               | `create_person_table` |

### 版本化迁移示例

**V1__create_person_table.sql**

```sql
CREATE TABLE person
(
    id   BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age  INT         NOT NULL
);
```

**V2__insert_sample_persons.sql**

```sql
INSERT INTO person (name, age)
VALUES ('Alice', 28);
INSERT INTO person (name, age)
VALUES ('Bob', 35);
INSERT INTO person (name, age)
VALUES ('Carol', 42);
```

**V1_1__add_email_column.sql**

```sql
ALTER TABLE person
    ADD COLUMN email VARCHAR(100);
```

### 可重复迁移示例

以 `R` 开头，每次 checksum 变化都会重新执行：

**R__create_views.sql**

```sql
CREATE OR REPLACE VIEW adult_person AS
SELECT id, name, age
FROM person
WHERE age >= 18;
```

### 撤销迁移示例（收费版）

**U1__revert_create_person_table.sql**

```sql
DROP TABLE person IF EXISTS;
```

### 占位符替换

Flyway 支持在 SQL 脚本中使用 `${placeholder}` 占位符，执行时替换为实际值，适用于多环境差异或数据库方言兼容。

```properties
spring.flyway.placeholder-replacement=true   # 默认 true
spring.flyway.placeholder-prefix=${          # 默认 ${
spring.flyway.placeholder-suffix=}           # 默认 }
spring.flyway.placeholders.table_prefix=prod_
```

示例：定义 `${table_prefix}`，实际执行时自动替换为 `prod_`。

> **注意**：SQL 中本身包含 `${}` 语法（如 MyBatis）时，需调整前缀后缀避免冲突。

## 4. 验证迁移结果

### 4.1 通过日志

启动日志会输出迁移信息：

```
INFO  o.f.c.i.database.H2DatabaseType     : Database: jdbc:h2:mem:test (H2 2.x)
INFO  o.f.core.internal.command.DbMigrate : Successfully applied 2 migrations to schema "PUBLIC", now at version v2
```

### 4.2 通过 H2 Console 手动验证

应用启动后访问 `http://localhost:8080/h2-console`，连接 `jdbc:h2:mem:test`，可查询 `flyway_schema_history` 表查看迁移记录。

> 本模块的 [`FlywayApplicationTests.java`](src/test/java/org/ljh/flyway/FlywayApplicationTests.java) 使用 `CountDownLatch` 阻塞进程保持应用运行，方便手动验证。

## 5. 常用操作

### 5.1 修复/重置迁移

当迁移脚本 checksum 变更或手动修改数据库导致校验失败时：

```sql
-- 删除 flyway_schema_history 中指定版本的记录
DELETE
FROM flyway_schema_history
WHERE version = '1_1';

-- 或重置整个 Flyway 状态（慎用，仅开发环境）
DELETE
FROM flyway_schema_history;
-- 然后设置 spring.flyway.baseline-on-migrate=true
```

### 5.2 跳过已有数据的基线化

对已有数据的数据库首次引入 Flyway：

```properties
spring.flyway.baseline-on-migrate=true
spring.flyway.baseline-version=1.0
```

这会创建 `flyway_schema_history` 表，插入一条 version=1.0 的基线记录，此后只会执行版本号 > 1.0 的脚本，V1 会被跳过。

### 5.3 通过 Maven 插件执行迁移

```bash
mvn flyway:migrate -Dflyway.locations=filesystem:db/migration
```

## 6. 与 MyBatis / Spring Data JPA 配合

Flyway 先执行迁移创建表结构，MyBatis/JPA 再操作表数据，两者互补。使用 JPA 时应设置 `spring.jpa.hibernate.ddl-auto=validate` 或 `none`，避免与 Flyway 冲突。

## 7. 目录结构推荐

```
src/
└── main/
    └── resources/
        └── db/
            └── migration/
                ├── V1__create_person_table.sql
                ├── V2__insert_sample_persons.sql
                ├── V1_1__add_email_column.sql
                ├── V2_1__add_index.sql
                └── R__create_views.sql
```

## 8. 模块示例

本模块 `spring-boot-flyway` 提供了完整可运行的示例，文件均位于 [`src/test/`](src/test/) 下：

| 文件                                                                                               | 说明                        |
|--------------------------------------------------------------------------------------------------|---------------------------|
| [`FlywayTestApplication.java`](src/test/java/org/ljh/flyway/FlywayTestApplication.java)          | Spring Boot 启动类，自动触发迁移    |
| [`FlywayApplicationTests.java`](src/test/java/org/ljh/flyway/FlywayApplicationTests.java)        | 测试类，阻塞进程供 H2 Console 手动验证 |
| [`V1__create_person_table.sql`](src/test/resources/db/migration/V1__create_person_table.sql)     | 版本 1：创建 person 表          |
| [`V2__insert_sample_persons.sql`](src/test/resources/db/migration/V2__insert_sample_persons.sql) | 版本 2：插入示例数据               |
| [`application.properties`](src/test/resources/application.properties)                            | 数据源 + Flyway 配置           |

## 9. 注意事项

1. **生产环境** 已执行的脚本**禁止修改**内容或重命名，否则 checksum 校验失败
2. **事务** Flyway 将整个迁移脚本包裹在一个事务中执行，所有 SQL 要么全部成功要么全部回滚（PostgreSQL 完美支持 DDL 事务；MySQL 的 DDL 会隐式提交，不完全支持回滚；H2 默认每条语句自动提交，无事务保护）
3. **多数据源** 可通过 `@FlywayDataSource` 或 `spring.flyway.datasource.*` 配置多个 Flyway 实例
