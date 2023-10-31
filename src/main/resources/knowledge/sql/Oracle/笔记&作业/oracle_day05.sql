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

检查约束
ALTER TABLE employees2 ADD CONSTRAINT employess2_salary_check CHECK (salary > 2000);
