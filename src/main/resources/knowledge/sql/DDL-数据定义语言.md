# 数据定义语言 Data Definition Language

---
## 参考网站
1. [Oracle、MySQL、SQL Server创建表和给表和字段加注释](https://www.cnblogs.com/zt528/p/5386516.html)
2. [SQL Server 添加表注释、字段注释](https://blog.csdn.net/stupidbird003/article/details/64562683)
---
## 创建表
```
CREATE TEMPORARY TABLE emp(
  empno INT,
  ename VARCHAR(10),
  job VARCHAR(9),
  mgr INT,
  hiredate DATE,
  sal DECIMAL(7, 2),
  comm DECIMAL(7, 2),
  deptno TINYINT
);
  
CREATE TEMPORARY TABLE dept(
  deptno TINYINT,
  dname VARCHAR(14),
  loc VARCHAR(13)
);

CREATE TEMPORARY TABLE sales(
  year_id INT NOT NULL,
  month_id INT NOT NULL,
  day_id INT NOT NULL,
  sales_value DECIMAL(10, 2) NOT NULL
);
```
>### MySQL
>```
>CREATE TABLE score (
>	id INT AUTO_INCREMENT PRIMARY KEY,                  -- 自增：AUTO_INCREMENT
>	name VARCHAR(20) COMMENT '姓名',
>	course VARCHAR(10) COMMENT '课程',
>	score INT COMMENT '成绩'
>) COMMENT '成绩表';
>```
>### SQL Server
>```
>CREATE TABLE score (
>	id INT IDENTITY(1,1) PRIMARY KEY,                   -- 自增：IDENTITY(1,1)
>	name VARCHAR(20),
>	course VARCHAR(10),
>	score INT
>);
>```
>### Oracle
>```
>CREATE TABLE emp(
>  empno NUMBER(4, 0),
>  ename VARCHAR2(10),
>  job VARCHAR2(9),
>  mgr NUMBER(4, 0),
>  hiredate DATE,
>  sal NUMBER(7, 2),
>  comm NUMBER(7, 2),
>  deptno NUMBER(2, 0) 
>);
>  
>CREATE TABLE dept(
>  deptno NUMBER(2, 0),
>  dname VARCHAR2(14),
>  loc VARCHAR2(13)
>);
>
>CREATE TABLE sales(
>  year_id NUMBER NOT NULL,
>  month_id NUMBER NOT NULL,
>  day_id NUMBER NOT NULL,
>  sales_value NUMBER(10, 2) NOT NULL
>);
> ------------------------------
>
>CREATE TABLE score (
>	id NUMBER(10) PRIMARY KEY,                          -- 自增：使用 CREATE SEQUENCE 实现
>	name VARCHAR2(20),
>	course VARCHAR2(10),
>	score NUMBER(3)
>);
>```
---
## [创建临时表](https://www.iteye.com/blog/sosuny-891437)
>### MySQL
>```
>CREATE TEMPORARY TABLE tmp (
>    num INT PRIMARY KEY,
>    pname VARCHAR(20)
>)
>```
>### SQL Server  
>1. 局部临时表：(#开头) 仅当前连接可见，断开连接自动删除  
>2. 全局临时表：(##开头) 对其它连接可见，当前连接和其他访问过它的连接都断开时自动删除
>```
>CREATE TABLE #temp(
>  num INT PRIMARY KEY,
>  pname VARCHAR(20)
>)
>```
---
## 添加备注
>### MySQL
>```
>ALTER TABLE score COMMENT '成绩表';           
>ALTER TABLE score MODIFY score INT COMMENT '成绩';
>```
>### SQL Server
>```
>格式： execute sp_addextendedproperty 'MS_Description','字段备注信息','user','dbo','table','字段所属的表名','column','添加注释的字段名';
>       EXEC sys.sp_addextendedproperty 'MS_Description', '姓名', 'user', 'dbo', 'table', 'score', 'column', 'name';
>       EXEC sys.sp_addextendedproperty 'MS_Description', '成绩表', 'user', 'dbo', 'table', 'score', null, null;
>```
>### Oracle
>```
>COMMENT ON TABLE score IS '成绩表';
>COMMENT ON COLUMN score.name IS '姓名';
>```
---
## 创建索引
```
CREATE INDEX idx_course ON score(course);
```
---
## 删除索引
>### MySQL
>```
>ALTER TABLE score DROP INDEX idx_course;
>DROP INDEX idx_course ON score;
>```
>### SQL Server
>```
>DROP INDEX score.idx_course;
>```
>### Oracle
>```
>DROP INDEX idx_course;
>```
---
## 修改字段
```
ALTER TABLE score ADD grade VARCHAR(1);                 -- 增加字段
ALTER TABLE score MODIFY grade VARCHAR(2);              -- 修改字段
ALTER TABLE score DROP COLUMN grade;                    -- 删除字段
```
>### 修改字段名
>>### MySQL
>>```
>>ALTER TABLE score CHANGE course subject VARCHAR(10) COMMENT '课程';
>>```
---
## 删除
```
DROP DATBASE test;                                      -- 删除数据库
DROP TABLE score;                                       -- 删除表
TRUNCATE TABLE score;                                   -- 删除表数据
```
---