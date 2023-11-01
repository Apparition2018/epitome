# 数据定义语言 Data Definition Language

---
## Reference
1. [Oracle、MySQL、SQL Server创建表和给表和字段加注释](https://www.cnblogs.com/zt528/p/5386516.html)
2. [SQL Server 添加表注释、字段注释](https://blog.csdn.net/stupidbird003/article/details/64562683)
3. [Data Definition Statements](https://dev.mysql.com/doc/refman/8.2/en/sql-data-definition-statements.html)
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
    -- 自增：使用 SEQUENCE 实现
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
### 重命名表
| DB     | SQL                          |
|--------|------------------------------|
| MySQL  | RENAME TABLE score TO score; |
| Oracle | RENAME score TO score;       |
### 删除表
```sql
DROP TABLE score;
TRUNCATE TABLE score;
```
### 根据现有表创建表并添加数据
1. MySQL / Oracle
```mysql
CREATE TABLE emp2 AS
SELECT e.empno id, e.ename, d.dname FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno;
```
2. SQL Server
```tsql
SELECT e.empno id, e.ename, d.deptno INTO emp2 FROM LEFT JOIN dept d ON e.deptno = d.deptno;
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
## 约束
| Constraints | Abbreviation |
|-------------|:------------:|
| Primary Key |      pk      |
| Unique      |      uk      |
| Check       |     chk      |
| Foreign Key |      fk      |
| Not Null    |              |
| Default     |              |
### 添加约束
```mysql
-- 检查约束
CREATE TABLE emp(
    empno int(0) PRIMARY KEY,
    sal decimal(7, 2),
    CONSTRAINT chk_emp_sal CHECK (sal > 0)
);

-- 外键约束
ALTER TABLE emp
ADD CONSTRAINT fk_emp_deptno FOREIGN KEY (deptno) REFERENCES dept (deptno);
```
### 删除约束
```sql
ALTER TABLE emp  
DROP CONSTRAINT chk_emp_sal;
```
---
## 视图
- 目的：①简化操作；②控制访问权限
- 简单视图：只包含基本列的 SELECT 语句
- 复制视图：包含多张表、计算字段、聚合函数、分组和子查询等
- 只能对简单视图进行 DML 操作：???
    - 可以进行 insert、update 操作，会影响底层表
    - MySQL 不能进行 delete 操作
    - Oracle 进行 delete 操作 不会影响底层表
- 选项：
    - WITH CHECK OPTION：对视图数据进行 DML 操作后，如果视图对这些数据不可见，则不通过该操作
    - WITH READ ONLY：只允许 SELECT 操作

|       | Statement                                                                                                  |
|-------|------------------------------------------------------------------------------------------------------------|
| 创建    | CREATE VIEW v_emp AS SELECT empno, ename, sal*12 sal FROM emp WHERE mgr IS NOT NULL;                       |
| 删除    | DROP VIEW v_emp;                                                                                           |
| 创建或替换 | CREATE OR REPLACE VIEW v_emp AS SELECT empno, ename, deptno FROM emp WHERE deptno <> 50 WITH CHECK OPTION; |
| 插入行   | INSERT INTO v_emp (empno, ename, deptno) VALUES (9999, 'JACK', 10);                                        |
| 更新行   | UPDATE v_emp SET deptno = 50 WHERE empno = 9999;                                                           |
| 删除行   | DELETE v_emp WHERE empno = 9999;                                                                           |
---
## 字段
```sql
-- 增加字段，Oracle/MySQL server 不支持 AFTER
ALTER TABLE score ADD grade varchar(1) AFTER name;
-- 修改字段
ALTER TABLE score MODIFY grade varchar(2);
-- 修改字段 (SQL Server)
ALTER TABLE score ALTER COLUMN grade varchar(2);
-- 删除字段
ALTER TABLE score DROP COLUMN grade;
```
### 修改字段名
| DB     | SQL                                                  |
|--------|------------------------------------------------------|
| MySQL  | ALTER TABLE score CHANGE course subject varchar(10); |
| Oracle | ALTER TABLE score RENAME COLUMN course To subject;   |
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
CREATE INDEX idx_emp_ename ON emp(ename);
```
1. MySQL：`ALTER TABLE emp ADD INDEX idx_emp_ename(ename);`
### 删除索引
1. MySQL
```mysql
DROP INDEX idx_emp_ename ON emp;
ALTER TABLE emp DROP INDEX idx_emp_ename;
```
2. Oracle：`DROP INDEX idx_emp_ename;`
3. SQL Server：`DROP INDEX emp.idx_emp_ename;`
### 重建索引
1. MySQL：先删除索引，后重新创建索引
2. Oracle
```oracle
ALTER INDEX idx_emp_ename REBUILD;
```
---
## 序列
### 创建序列
```oracle
CREATE SEQUENCE seq_emp_id START WITH 1000 INCREMENT BY 1 NOCACHE;
```
### 将序列插入到表的主键
```oracle
-- NEXTVAL：生成序列的下一个值
-- CURRVAL：获取序列的当前值
CREATE OR REPLACE TRIGGER tri_emp_before_insert
BEFORE INSERT ON emp FOR EACH ROW
BEGIN
    SELECT seq_emp_id.NEXTVAL INTO :NEW.empno FROM DUAL;
END;
```
### 删除序列
```oracle
DROP SEQUENCE sep_emp_id;
```
---