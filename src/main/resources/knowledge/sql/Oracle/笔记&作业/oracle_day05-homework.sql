--1:创建一个视图，包含20号部门的员工信息，字段:empno,ename,sal,job,deptno
CREATE VIEW v_emp_20 AS SELECT empno,ename,sal,job,deptno FROM emp WHERE deptno = 20;

--2:创建一个序列seq_emp_no,从10开始，步进为10
CREATE SEQUENCE seq_emp_no START WITH 10 INCREMENT BY 10;

--3:编写SQL语句查看seq_emp_no序列的下一个数字
SELECT seq_emp_no.NEXTVAL FROM dual;

--4:编写SQL语句查看seq_emp_no序列的当前数字
SELECT seq_emp_no.CURRVAL FROM dual;

--5:为emp表的ename字段添加索引:idx_emp_ename
CREATE INDEX idx_emp_ename ON emp(ename);

--6:为emp表的LOWER(ename)字段添加索引:idx_emp_lower_ename
CREATE INDEX idx_emp_lower_eanme ON emp(LOWER(ename));

--7:为emp表的sal,comm添加多列索引
CREATE INDEX idx_emp_sal_comm ON emp(sal,comm);

--创建myemployee表，字段:
  --id NUMBER(4) ,                     id作为主键
  --name VARCHAR2(20),           name要求不能为空
  --birthday DATE,
  --telephone VARCHAR2(11)    telephone需要唯一
  --score NUMBER(9,2)               score值必须>=0
CREATE TABLE myemployee (
  id NUMBER(4) PRIMARY KEY,
  name VARCHAR2(20) NOT NULL,
  birthday DATE,
  telephone VARCHAR2(11),
  CONSTRAINT myemployee_telephone_uk UNIQUE(telephone),
  score NUMBER(9,2),
  CONSTRAINT myemployee_score_check CHECK (score >=0)
);
