-- ==================== JPA 演示独立表 ====================
-- 说明：CRUD、一对多、复合主键、枚举转换、各类查询等演示均复用 commons 的 world 三表
-- （country / city / countrylanguage）。以下仅为 world 三表无法表达的映射特性所需的最小独立表：
-- ① 继承映射 + @Embeddable + @Version 乐观锁：person；
-- ② 多对多：teacher / student / teacher_student；
-- ③ 一对一：author / passport。

-- ① 继承映射 SINGLE_TABLE + @Embeddable + 乐观锁：person 表
-- Staff / Manager 子类专属列（department / team_size）及 Staff 的可嵌入地址三列均放本表；
-- version 列供 @Version 乐观锁使用
DROP TABLE IF EXISTS person;

CREATE TABLE person (
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    DISC_TYPE    VARCHAR(20)  NOT NULL,
    name         VARCHAR(50)  NOT NULL,
    department   VARCHAR(50),            -- 仅 Staff
    team_size    INT,                    -- 仅 Manager
    street       VARCHAR(100),           -- Address.street（仅 Staff 嵌入）
    address_city VARCHAR(50),            -- Address.city（仅 Staff 嵌入）
    zip_code     VARCHAR(20),            -- Address.zipCode（仅 Staff 嵌入）
    version      BIGINT       NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

-- ② 多对多：teacher / student / teacher_student 中间表
DROP TABLE IF EXISTS teacher_student;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;

CREATE TABLE teacher (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE student (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE teacher_student (
    teacher_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    PRIMARY KEY (teacher_id, student_id),
    CONSTRAINT fk_ts_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (id),
    CONSTRAINT fk_ts_student  FOREIGN KEY (student_id)  REFERENCES student (id)
);

-- ③ 一对一：author / passport（外键在 author 表）
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS passport;

CREATE TABLE passport (
    id     BIGINT      NOT NULL AUTO_INCREMENT,
    number VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE author (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    passport_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_author_passport FOREIGN KEY (passport_id) REFERENCES passport (id)
);

-- ④ 主键生成策略 TABLE + @TableGenerator：customer 表
-- 字段同时承载序号 2 的字段映射演示（@Basic / @Enumerated STRING+ORDINAL / @Temporal DATE+TIMESTAMP）
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS id_gen_table;

-- @TableGenerator 使用的主键生成表：id_gen_name 记录生成器名，id_gen_value 记录当前值
CREATE TABLE id_gen_table (
    id_gen_name  VARCHAR(50) NOT NULL,
    id_gen_value BIGINT      NOT NULL,
    PRIMARY KEY (id_gen_name)
);

CREATE TABLE customer (
    id             BIGINT       NOT NULL,
    name           VARCHAR(50),
    status         VARCHAR(20),    -- @Enumerated(STRING)：存枚举名 ACTIVE/INACTIVE
    status_ordinal INT,            -- @Enumerated(ORDINAL)：存下标 0/1
    birth_date     DATE,           -- @Temporal(DATE)
    created_at     TIMESTAMP,      -- @Temporal(TIMESTAMP)
    note           VARCHAR(255),   -- @Basic(fetch=LAZY)
    PRIMARY KEY (id)
);

-- ⑤ 主键生成策略 SEQUENCE + @SequenceGenerator：orders 表（多对一，外键引用 customer）
CREATE SEQUENCE order_seq START WITH 1 INCREMENT BY 1;

-- Customer2 / Customer3 使用 GenerationType.AUTO，H2 方言下 Hibernate 按「实体名_SEQ」找序列；
-- 未显式声明 @SequenceGenerator 时 Hibernate 默认 allocationSize=50，故序列步长须为 50，
-- 否则启动会因 increment size 不匹配而报 MappingException
CREATE SEQUENCE customer2_SEQ START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE customer3_SEQ START WITH 1 INCREMENT BY 50;

CREATE TABLE orders (
    id          BIGINT        NOT NULL,
    total       DECIMAL(10,2),
    customer_id BIGINT        NOT NULL,   -- 多对一外键列：NOT NULL，删除被引用端触发外键约束异常
    PRIMARY KEY (id),
    CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

-- ⑥ 单向一对多：customer2 一对多 order2
-- 外键列 customer_id 建在「多」方表 order2，但由「一」方 Customer2 维护（@JoinColumn）
DROP TABLE IF EXISTS order2;
DROP TABLE IF EXISTS customer2;

CREATE TABLE customer2 (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE order2 (
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50),
    customer_id BIGINT,             -- 单向一对多：外键列在 n 方，由 1 方维护
    PRIMARY KEY (id),
    CONSTRAINT fk_order2_customer2 FOREIGN KEY (customer_id) REFERENCES customer2 (id)
);

-- ⑦ 实体二级缓存（规范层 @Cacheable）：customer3 表
DROP TABLE IF EXISTS customer3;

CREATE TABLE customer3 (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY (id)
);
