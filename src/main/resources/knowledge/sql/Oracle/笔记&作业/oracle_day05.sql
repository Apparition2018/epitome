视图
视图是数据库对象之一
视图在SQL语句中体现的角色与表一致，
但并非真实存在的表，它是对应一个SELECT语句的查询结果集
使用视图的主要目的：
1：重用子查询，简化SQL复杂度
2：限制数据访问

--创建视图
CREATE VIEW v_emp_10 AS SELECT empno, ename, sal, deptno FROM emp WHERE deptno = 10;

--删除视图
DROP VIEW v_emp_10;

--创建视图对应的子查询若含有函数或表达式，那么该函数或者表达式必须给别名
CREATE VIEW v_emp_10 AS SELECT ename,sal*12 sal,job,deptno FROM emp WHERE deptno=10;

--修改视图就是替换视图中对应的子查询
CREATE OR REPLACE VIEW v_emp_10 AS SELECT empno,ename,sal,job,deptno FROM emp WHERE deptno=10;

对视图进行DML操作：
对视图进行DML操作就是对视图数据来源的基础表进行的操作
只能对简单视图进行DML操作，复杂视图不允许执行DML操作，并且对简单视图进行DML操作不能违反视图数据来源的基础表的约束条件

INSERT INTO v_emp_10 (empno,ename,sal,job,deptno) VALUES (1001,'JACK',3000,'CLERK',10);

UPDATE v_emp_10 SET sal=5000 WHERE empno=1001;

DELETE v_emp_10 WHERE empno=1001;

对视图的不当DML操作会污染基础表数据
所谓污染，指的是对视图进行DML操作后，视图对基础表做相应DML操作，但是操作后视图对这些数据不可见。
增加、修改操作不当会有，删除不会有。
INSERT INTO v_emp_10 (empno,ename,sal,job,deptno) VALUES (1001,'JACK',5000,'MANAGER',20);
UPDATE v_emp_10 SET deptno=20;

为视图添加检查选项，这样做可以避免对视图进行DML操作后对基表数据污染，
因为检查选项的意义在于视图对进行DML操作后的数据要检查是否可以见，不可见则不允许相关操作。
CREATE OR REPLACE VIEW v_emp_10 AS
SELECT empno,ename,sal,job,deptno FROM emp WHERE deptno=10 WITH CHECK OPTION;

为视图添加只读选项后，该视图不允许进行DML操作。
CREATE OR REPLACE VIEW v_emp_10 AS
SELECT empno,ename,sal,job,deptno FROM emp WHERE deptno=10 WITH READ ONLY;

数据字典
数据字典也是一堆表，这些表示由数据库自行维护，作为“清单”使用，可以查看如：创建过的表，视图等信息

--USER_OBJECTS:记录用户创建的所有数据库对象
SELECT object_name FROM user_objects;
SELECT object_name,object_type FROM user_objects;
SELECT object_name,object_type FROM user_objects WHERE object_type='VIEW';

--USER_VIEWS:记录用户创建的所有视图
SELECT view_name,text FROM user_views;
SELECT * FROM user_views;

--USER_TAVBLES:记录用户创建的所有表
SELECT table_name FROM user_tables;
SELECT * FROM user_tables;

复杂视图：
视图对应的子查询中含有函数，表达式，分组，去重，关联查询等操作时，该视图为复杂视图
复杂视图不允许进行DML操作
--创建一个部门工资情况的视图，包含信息：部门编号，部门名称，平均、最高、最低工资和工资总和
CREATE VIEW v_emp_salinfo AS 
SELECT d.deptno,d.dname,AVG(e.sal) avg_sal,MAX(e.sal) max_sal,MIN(e.sal) min_sal,SUM(e.sal) sum_sal 
FROM emp e,dept d WHERE e.deptno=d.deptno GROUP BY d.deptno,d.dname;

--查看比自己所在部门平均工资高的员工？
SELECT e.ename,e.deptno,e.sal FROM emp e,v_emp_salinfo v WHERE e.deptno=v.deptno AND e.sal>v.avg_sal;

序列
序列也是数据库对象之一
序列是用来根据指定的规律生成一系列数字使用的。
通常使用序列为表的主键字段生成值。

CREATE SEQUENCE seq_emp_id 
START WITH 1000
INCREMENT BY 1;

序列提供了两个伪列：
NEXTVAL:获取序列下一个数字（序列自后生成的数字加步长得到）
CURRVAL:获取序列最后生成的数字
需要注意，新创建的序列至少使用NEXTVAL生成一个数字以后才可以开始使用CURRVAL，
NEXTVAL会导致序列发生步进，且序列是不能会退的，不受事务影响。

SELECT seq_emp_id.NEXTVAL FROM dual;
SELECT seq_emp_id.CURRVAL FROM dual;

INSERT INTO emp (empno,ename,sal,job,deptno) VALUES (seq_emp_id.NEXTVAL,'JACK',3000,'CLERK',10);

DELETE 序列
DROP SEQUENCE sep_emp_id;

索引
索引是数据库对象之一
索引的意义在于加快查询效率
索引的实际创建与使用是数据库自行完成的，我们的操作在于是否对表创建索引
CREATE INDEX idx_emp_ename ON emp(ename);
SELECT * FROM emp WHERE ename='XXXX' ORDER BY ename;

经常出现在 WHERE, ORDER BY 中或 DISTINCT 中的字段可以建立索引，
需要注意的是，对于字符串类型字段，在 WHERE 中若是 LIKE 判断是不会使用到索引的。

CREATE INDEX idx_emp_job_sal ON emp(job,sal);
SELECT empno,ename,sal,job FROM emp ORDER BY job,sal;
CREATE INDEX emp_ename_upper_idx ON emp(UPPER(ename));
SELECT * FROM emp WHERE UPPER(ename) = 'KING';

--重建索引
ALTER INDEX idx_emp_ename REBUILD;

--删除索引
DROP INDEX idx_emp_ename;

--建表时添加非空约束
CREATE TABLE employees (
  eid NUMBER(6),
  name VARCHAR2(30) NOT NULL,
  salary NUMBER(7,2),
  hiredate DATE CONSTRAINT employees_hiredate_nn NOT NULL
);

--修改表时添加非空约束/取消非空约束
ALTER TABLE employees MODIFY (eid NUMBER(6) NOT NULL);
ALTER TABLE employees MODIFY (eid NUMBER(6) NULL);

唯一性约束
当表中某个字段使用了唯一性约束后，该字段的值不允许有重复的，NULL除外
CREATE TABLE employees1(
  eid NUMBER(6) UNIQUE,
  name VARCHAR2(30),
  email VARCHAR2(50),
  salary NUMBER(7,2),
  hiredate DATE,
  CONSTRAINT employees1_email_uk UNIQUE(email)
);

INSERT INTO employees1 (eid,name,email) VALUES (1,'JACK','JACK@TEDU.CN');

--在建表之后增加唯一性约束条件
ALTER TABLE employees1 ADD CONSTRAINT empoyeees1_name_uk UNIQUE(name);

主键约束
主键约束条件从功能上看相当于非空且唯一的组合

--在建表时添加主键约束条件
CREATE TABLE employees2 (
  eid NUMBER(6) PRIMARY KEY,
  name VARCHAR2(30),
  email VARCHAR2(50),
  salary  NUMBER(7,2),
  hiredate DATE
);

INSERT INTO employees2 (eid,name) VALUES (NULL,'JACK');

检查约束
ALTER TABLE employees2 ADD CONSTRAINT employess2_salary_check CHECK (salary > 2000);

