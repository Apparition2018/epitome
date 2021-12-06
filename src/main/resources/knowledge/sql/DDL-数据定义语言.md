# 数据定义语言 Data Definition Language

---
## 参考网站
1. [Oracle、MySQL、SQL Server创建表和给表和字段加注释](https://www.cnblogs.com/zt528/p/5386516.html)
2. [SQL Server 添加表注释、字段注释](https://blog.csdn.net/stupidbird003/article/details/64562683)
3. [Data Definition Statements](https://dev.mysql.com/doc/refman/8.0/en/sql-data-definition-statements.html)
---
## 表
>## 创建表
>>### MySQL
>>```sql
>>CREATE TABLE `score` (
>>  `id` INT AUTO_INCREMENT PRIMARY KEY,                  -- 自增：AUTO_INCREMENT
>>  `name` VARCHAR(20) COMMENT '姓名',
>>  `course` VARCHAR(10) COMMENT '课程',
>>  `score` INT COMMENT '成绩'
>>) COMMENT '成绩表';
>>```
>>### SQL Server
>>```sql
>>CREATE TABLE `score` (
>>  `id` INT IDENTITY(1,1) PRIMARY KEY,                   -- 自增：IDENTITY(1,1)
>>  `name` VARCHAR(20),
>>  `course` VARCHAR(10),
>>  `score` INT
>>);
>>```
>>### Oracle
>>```sql
>>CREATE TABLE `score` (
>>  `id` NUMBER(10) PRIMARY KEY,                          -- 自增：使用 CREATE SEQUENCE 实现
>>  `name` VARCHAR2(20),
>>  `course` VARCHAR2(10),
>>  `score` NUMBER(3)
>>);
>>```
>## 删除表
>```sql
>DROP TABLE `score`;
>TRUNCATE TABLE `score`;
>```
>## [创建临时表](https://www.iteye.com/blog/sosuny-891437)
>>### MySQL
>>```sql
>>CREATE TEMPORARY TABLE `tmp` (
>>    `num` INT PRIMARY KEY,
>>    `pname` VARCHAR(20)
>>)
>>```
>>### SQL Server  
>>1. 局部临时表：(#开头) 仅当前连接可见，断开连接自动删除  
>>2. 全局临时表：(##开头) 对其它连接可见，当前连接和其他访问过它的连接都断开时自动删除
>>```sql
>>CREATE TABLE #temp (
>>  `num` INT PRIMARY KEY,
>>  `pname` VARCHAR(20)
>>)
>>```
---
## 备注
>### MySQL
>```sql
>ALTER TABLE `score` COMMENT '成绩表';           
>ALTER TABLE `score` MODIFY `score` INT COMMENT '成绩';
>```
>### SQL Server
>```sql
>execute sp_addextendedproperty 'MS_Description','字段备注信息','user','dbo','table','字段所属的表名','column','添加注释的字段名';
>EXEC sys.sp_addextendedproperty 'MS_Description', '姓名', 'user', 'dbo', 'table', 'score', 'column', 'name';
>EXEC sys.sp_addextendedproperty 'MS_Description', '成绩表', 'user', 'dbo', 'table', 'score', null, null;
>```
>### Oracle
>```sql
>COMMENT ON TABLE `score` IS '成绩表';
>COMMENT ON COLUMN `score.name` IS '姓名';
>```
---
## 索引
>## 创建索引
>```sql
>CREATE INDEX `idx_course` ON `score`(course);
>```
>---
>## 删除索引
>>### MySQL
>>```sql
>>ALTER TABLE `score` DROP INDEX `idx_course`;
>>DROP INDEX `idx_course` ON `score`;
>>```
>>### SQL Server
>>```sql
>>DROP INDEX `score`.idx_course;
>>```
>>### Oracle
>>```sql
>>DROP INDEX `idx_course`;
>>```
---
## 字段
```sql
ALTER TABLE `score` ADD `grade` VARCHAR(1) AFTER `name`;    -- 增加字段
ALTER TABLE `score` MODIFY `grade` VARCHAR(2);              -- 修改字段
ALTER TABLE `score` DROP COLUMN `grade`;                    -- 删除字段
```
>### 修改字段名
>>### MySQL
>>```sql
>>ALTER TABLE `score` CHANGE `course` subject VARCHAR(10) COMMENT '课程';
>>```
---
## 存储过程
1. [MySQL: Procedures](https://www.techonthenet.com/mysql/procedures.php)
2. [MySQL中的IN、OUT、INOUT类型](https://www.cnblogs.com/super-yu/p/9018512.html)
---