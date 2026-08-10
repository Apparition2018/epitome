-- ==================== 演示乐观锁与 @DynamicUpdate 的独立表（world 库不含该表，故单独建） ====================
DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id      BIGINT NOT NULL AUTO_INCREMENT,
    name    VARCHAR(50)  NOT NULL,
    price   DECIMAL(10, 2),
    stock   INT,
    version BIGINT NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
);

-- ==================== 继承映射 SINGLE_TABLE + @Embeddable：person 表 ====================
-- Staff / Manager 子类的专属列（department / team_size）及 Staff 的可嵌入地址三列也放本表，列取所有子类并集
DROP TABLE IF EXISTS person;

CREATE TABLE person (
    id           BIGINT NOT NULL AUTO_INCREMENT,
    DISC_TYPE    VARCHAR(20) NOT NULL,
    name         VARCHAR(50) NOT NULL,
    department   VARCHAR(50),   -- 仅 Staff 使用
    team_size    INT,           -- 仅 Manager 使用
    street       VARCHAR(100),  -- Address.street（仅 Staff 嵌入）
    address_city VARCHAR(50),   -- Address.city（仅 Staff 嵌入）
    zip_code     VARCHAR(20),   -- Address.zipCode（仅 Staff 嵌入）
    PRIMARY KEY (id)
);

-- ==================== 多对多：teacher / student / teacher_student 中间表 ====================
DROP TABLE IF EXISTS teacher_student;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;

CREATE TABLE teacher (
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE student (
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE teacher_student (
    teacher_id BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    PRIMARY KEY (teacher_id, student_id),
    CONSTRAINT fk_ts_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (id),
    CONSTRAINT fk_ts_student FOREIGN KEY (student_id) REFERENCES student (id)
);

-- ==================== 一对一：author / passport（外键在 author 表） ====================
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS passport;

CREATE TABLE passport (
    id     BIGINT NOT NULL AUTO_INCREMENT,
    number VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE author (
    id          BIGINT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    passport_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_author_passport FOREIGN KEY (passport_id) REFERENCES passport (id)
);
