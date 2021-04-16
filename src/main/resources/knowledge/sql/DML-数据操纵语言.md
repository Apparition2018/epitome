# 数据操纵语言 Data Manipulation Language

---
## INSERT
```
INSERT INTO score (name, course, score) VALUES ('王五', '语文', 81);
INSERT INTO score (name, course, score) VALUES ('王五', '数学', 100);
INSERT INTO score (name, course, score) VALUES 
('王五', '英语', 90),
('张三', '语文', 81),
('张三', '数学', 75),
('张三', '英语', null),
('李四', '语文', 76),
('李四', '数学', 90);
```
>### MySQL
>```
>INSERT IGNORE INTO                         如果插入数据主键重复，则忽略插入数据
>```
---
## UPDATE
```
UPDATE score SET name = '王五', course = '语文', score = 81 WHERE id = 1;
```
---
## DELETE
```
DELETE FROM score WHERE id = 1;
```
---
## emp | dept
### MySQL | SQL Server
```
INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7369,'SMITH','CLERK',7902,'1980/12/17',800.00,NULL,20);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7499,'ALLEN','SALESMAN',7698,'1981/2/20',1600.00,300.00,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7521,'WARD','SALESMAN',7698,'1981/2/22',1250.00,500.00,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7566,'JONES','MANAGER',7839,'1981/4/2',2975.00,NULL,20);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7654,'MARTIN','SALESMAN',7698,'1981/9/28',1250.00,1400.00,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7698,'BLAKE','MANAGER',7839,'1981/5/1',2850.00,NULL,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7782,'CLARK','MANAGER',7839,'1981/6/9',2450.00,NUll,10);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7788,'SCOTT','ANALYST',7566,'1987/4/19',3000.00,NUll,50);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7839,'KING','PRESIDENT',NUll,'1981/11/17',5000.00,NUll,10);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7844,'TURNER','SALESMAN',7698,'1981/9/8',1500.00,0.00,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7876,'ADAMS','CLERK',7788,'1987/5/23',1100.00,NUll,20);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7900,'JAMES','CLERK',7698,'1981/12/3',950.00,NUll,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7902,'FORD','ANALYST',7566,'1981/12/3',3000.00,NULL,20);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7934,'MILLER','CLERK',7782,'1982/1/23',1300.00,NUll,10);

INSERT INTO dept
(deptno,dname,loc)
VALUES
(10,'ACCOUNTING','NEW YORK');

INSERT INTO dept
(deptno,dname,loc)
VALUES
(20,'RESEARCH','DALLAS');

INSERT INTO dept
(deptno,dname,loc)
VALUES
(30,'SALES','CHICAGO');

INSERT INTO dept
(deptno,dname,loc)
VALUES
(40,'OPERATIONS','BOSTON');
```
---
### Oracle
```
INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7369,'SMITH','CLERK',7902,TO_DATE('1980/12/17','YYYY-MM-DD'),800.00,NULL,20);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7499,'ALLEN','SALESMAN',7698,TO_DATE('1981/2/20','YYYY-MM-DD'),1600.00,300.00,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7521,'WARD','SALESMAN',7698,TO_DATE('1981/2/22','YYYY-MM-DD'),1250.00,500.00,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7566,'JONES','MANAGER',7839,TO_DATE('1981/4/2','YYYY-MM-DD'),2975.00,NULL,20);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7654,'MARTIN','SALESMAN',7698,TO_DATE('1981/9/28','YYYY-MM-DD'),1250.00,1400.00,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7698,'BLAKE','MANAGER',7839,TO_DATE('1981/5/1','YYYY-MM-DD'),2850.00,NULL,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7782,'CLARK','MANAGER',7839,TO_DATE('1981/6/9','YYYY-MM-DD'),2450.00,NUll,10);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7788,'SCOTT','ANALYST',7566,TO_DATE('1987/4/19','YYYY-MM-DD'),3000.00,NUll,50);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7839,'KING','PRESIDENT',NUll,TO_DATE('1981/11/17','YYYY-MM-DD'),5000.00,NUll,10);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7844,'TURNER','SALESMAN',7698,TO_DATE('1981/9/8','YYYY-MM-DD'),1500.00,0.00,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7876,'ADAMS','CLERK',7788,TO_DATE('1987/5/23','YYYY-MM-DD'),1100.00,NUll,20);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7900,'JAMES','CLERK',7698,TO_DATE('1981/12/3','YYYY-MM-DD'),950.00,NUll,30);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7902,'FORD','ANALYST',7566,TO_DATE('1981/12/3','YYYY-MM-DD'),3000.00,NULL,20);

INSERT INTO emp
(empno,ename,job,mgr,hiredate,sal,comm,deptno)
VALUES
(7934,'MILLER','CLERK',7782,TO_DATE('1982/1/23','YYYY-MM-DD'),1300.00,NUll,10);

INSERT INTO dept
(deptno,dname,loc)
VALUES
(10,'ACCOUNTING','NEW YORK');

INSERT INTO dept
(deptno,dname,loc)
VALUES
(20,'RESEARCH','DALLAS');

INSERT INTO dept
(deptno,dname,loc)
VALUES
(30,'SALES','CHICAGO');

INSERT INTO dept
(deptno,dname,loc)
VALUES
(40,'OPERATIONS','BOSTON');

INSERT INTO sales
SELECT TRUNC(DBMS_RANDOM.value(2010, 2012)) AS year_id,
       TRUNC(DBMS_RANDOM.value(1, 13)) AS month_id,
       TRUNC(DBMS_RANDOM.value(1, 32)) AS day_id,
       ROUND(DBMS_RANDOM.value(1, 100), 2) AS sales_value
FROM dual
CONNECT BY level <= 1000;
```