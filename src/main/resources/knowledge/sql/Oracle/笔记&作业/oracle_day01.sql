
SQL:结构化查询语言
SQL是与数据库沟通的语言,用于操作
数据库.SQL得益于标准,所有的数据库
管理系统都支持该标准.

DDL语句
数据定义语言,用于维护数据库对象
数据库对象包含:表,视图,索引,序列
 
查看表的结构  
DESC employee

删除表
DROP TABLE employee

SQL语句本身是不区分大小写的，但是字符串的值是区分大小写的。
字符串的字面量在数据库中是使用单引号括起来的，数据库中所有数据类型的默认值都是NULL,可以使用DEFAULT关键字为字段单独指定默认值。


创建表：CREATE
CREATE TABLE employee(
  id NUMBER(4),
  name VARCHAR2(20) NOT NULL,
  gender CHAR(1) DEFAULT 'M',
  birth DATE,
  salary NUMBER(6,2) DEFAULT 3000,
  job VARCHAR2(30),
  deptno NUMBER(2)  
) 
  
NOT NULL 约束
当一个字段使用NOT NULL约束后，该字段的值任何情况下不允许为空

修改表
1：修改表名
  RENAME TABLE old_name TO new_name
  RENAME TABLE employee TO myemp
2：修改表结构
  2.1：添加新字段
    向myemp表中添加入职时间字段
    ALTER TABLE myemp
    ADD(hiredate DATE)
  2.2：修改现有字段
  可以修改字段的类型，长度，添加默认值或非空约束。
  修改字段最好在表中没有数据的时候进行，
  否则尽量不修改类型，长度只增不减，否则可能导致修改失败。
  将JOB字段的长度改为40
  ALTER TABLE myemp
  MODIFY(JOB VARCHAR2(40))
  2.3：删除现有字段
  ALTER TABLE myemp
  DROP（hiredate）
  
DML：数据操作语言
DML是用在增删改表中数据的。

1：插入数据
INSERT语句
INSERT INTO myemp
(id,name,job,deptno)
VALUES
(1,'jack','clerk',10)
插入时不指定字段则是全列插入（不建议使用）
INSERT INTO myemp
VALUES
(2,'rose','F',SYSDATE,3000,'clerk',10)
插入日期数据时，使用TO_DATE函数
INSERT INTO myemp
（ID,name,birth,deptno)
VALUES
(3,'jerry',TO_DATE('1993-05-21','YYYY-MM-DD'),10)

2：修改数据
UPDATE语句
修改的时候通常要添加WHERE，这样只会去将表中满足WHERE条件的记录进行修改，否则是全表数据修改。
UPDATE myemp
SET salary = 5000, deptno = 20
WHERE name = 'rose'

3：删除数据
DELETE语句
不添加WHERE字句是清空表操作！
DELETE FROM myemp
WHERE gender = 'M'


COMMIT

SELECT * FROM myemp