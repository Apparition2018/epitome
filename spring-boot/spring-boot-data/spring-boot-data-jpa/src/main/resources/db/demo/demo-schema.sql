-- 演示表：乐观锁 + 审计（不属 world 库，单独建表）
-- version: 乐观锁版本号，每次 update 自动 +1
-- created_by / created_at / updated_by / updated_at: 审计字段
CREATE TABLE IF NOT EXISTS product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    created_by VARCHAR(50),
    created_at TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP,
    PRIMARY KEY (id)
);
