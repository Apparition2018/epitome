-- V1：创建 person 表（DDL）
CREATE TABLE person
(
    id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50)  NOT NULL,
    age  INT          NOT NULL
);
