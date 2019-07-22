CREATE TABLE emp(
  empno NUMBER(4,0),
  ename VARCHAR2(10),
  job VARCHAR2(9),
  mgr NUMBER(4,0),
  hiredate DATE,
  sal NUMBER(7,2),
  comm NUMBER(7,2),
  deptno NUMBER(2,0) 
);
  
CREATE TABLE dept(
  deptno NUMBER(2,0),
  dname VARCHAR2(14 BYTE),
  loc VARCHAR2(13 BYTE)
);

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


