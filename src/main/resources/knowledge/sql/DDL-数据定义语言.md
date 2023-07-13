# 数据定义语言 Data Definition Language

---
## Reference
1. [Oracle、MySQL、SQL Server创建表和给表和字段加注释](https://www.cnblogs.com/zt528/p/5386516.html)
2. [SQL Server 添加表注释、字段注释](https://blog.csdn.net/stupidbird003/article/details/64562683)
3. [Data Definition Statements](https://dev.mysql.com/doc/refman/8.0/en/sql-data-definition-statements.html)
---
## 存储过程
1. [MySQL: Procedures](https://www.techonthenet.com/mysql/procedures.php)
2. [MySQL中的IN、OUT、INOUT类型](https://www.cnblogs.com/super-yu/p/9018512.html)
---
## 表
### 创建表
1. MySQL
```mysql
CREATE TABLE `score` (
    -- 自增：AUTO_INCREMENT
    `id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20) COMMENT '姓名',
    `course` varchar(10) COMMENT '课程',
    `score` int COMMENT '成绩'
) ENGINE = InnoDB COMMENT = '成绩表';
```
2. Oracle
```oracle
CREATE TABLE score (
    -- 自增：使用 CREATE SEQUENCE 实现
    id number(10) PRIMARY KEY,
    name varchar2(20),
    course varchar2(10),
    score number(3)
);
```
3. SQL Server
```tsql
CREATE TABLE score (
    -- 自增：IDENTITY(1,1)
    id int IDENTITY(1,1) PRIMARY KEY,
    name varchar(20),
    course varchar(10),
    score int
);
```
### 删除表
```sql
DROP TABLE score;
TRUNCATE TABLE score;
```
### [创建临时表](https://www.iteye.com/blog/sosuny-891437)
1. MySQL
```mysql
CREATE TEMPORARY TABLE `tmp` (
    `id` int PRIMARY KEY,
    `name` varchar(20)
);
```
2. Oracle
```oracle
CREATE GLOBAL TEMPORARY TABLE demo (
    id number PRIMARY KEY,
    name varchar2(20)
);
```
3. SQL Server  
    1. 局部临时表：(#开头) 仅当前连接可见，断开连接自动删除  
    2. 全局临时表：(##开头) 对其它连接可见，当前连接和其他访问过它的连接都断开时自动删除
```tsql
CREATE TABLE #temp (
    id int PRIMARY KEY,
    name varchar(20)
);
```
### 复制表结构
1. MySQL / Oracle：`CREATE TABLE score_bak AS SELECT * FROM score WHERE 1=2;`
2. MySQL：`CREATE TABLE score_bak LIKE score;`
3. SQL server：`SELECT * INTO score_bak FROM score WHERE 1=2;`
---
## 备注
1. MySQL
```mysql
ALTER TABLE `score` COMMENT '成绩表';           
ALTER TABLE `score` MODIFY `score` int COMMENT '成绩';
```
2. Oracle
```oracle
COMMENT ON TABLE score IS '成绩表';
COMMENT ON COLUMN score.name IS '姓名';
```
3. SQL Server
```tsql
-- execute sp_addextendedproperty 'MS_Description','字段备注信息','user','dbo','table','字段所属的表名','column','添加注释的字段名';
EXEC sys.sp_addextendedproperty 'MS_Description', '姓名', 'user', 'dbo', 'table', 'score', 'column', 'name';
EXEC sys.sp_addextendedproperty 'MS_Description', '成绩表', 'user', 'dbo', 'table', 'score', null, null;
```
---
## 索引
### 创建索引
```sql
CREATE INDEX idx_course ON score(course);
```
### 删除索引
1. MySQL
```mysql
ALTER TABLE `score` DROP INDEX `idx_course`;
DROP INDEX `idx_course` ON `score`;
```
2. Oracle：`DROP INDEX idx_course;`
3. SQL Server：`DROP INDEX score.idx_course;`
---
## 字段
```sql
-- 增加字段，Oracle/SQL server 不支持 AFTER
ALTER TABLE score ADD grade varchar(1) AFTER name;
-- 修改字段
ALTER TABLE score MODIFY grade varchar(2);
-- 修改字段 (SQL Server)
ALTER TABLE score ALTER COLUMN grade varchar(2);
-- 删除字段
ALTER TABLE score DROP COLUMN grade;
```
### 修改字段名
| DB     | SQL                                                               |
|--------|-------------------------------------------------------------------|
| MySQL  | ALTER TABLE score CHANGE course subject varchar(10) COMMENT '课程'; |
| Oracle | ALTER TABLE score RENAME COLUMN course To subject                 |
---