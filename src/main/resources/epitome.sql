CREATE TABLE score
(
    id     INT AUTO_INCREMENT PRIMARY KEY, -- 自增：AUTO_INCREMENT
    name   VARCHAR(20) COMMENT '姓名',
    course VARCHAR(10) COMMENT '课程',
    score  INT COMMENT '成绩'
) COMMENT '成绩表';

INSERT INTO score (name, course, score)
VALUES ('王五', '语文', 81);
INSERT INTO score (name, course, score)
VALUES ('王五', '数学', 100);
INSERT INTO score (name, course, score)
VALUES ('王五', '英语', 90),
       ('张三', '语文', 81),
       ('张三', '数学', 75),
       ('张三', '英语', null),
       ('李四', '语文', 76),
       ('李四', '数学', 90);



CREATE TABLE emp
(
    empno    INT,
    ename    VARCHAR(10),
    job      VARCHAR(9),
    mgr      INT,
    hiredate DATE,
    sal      DECIMAL(7, 2),
    comm     DECIMAL(7, 2),
    deptno   TINYINT
);

CREATE TABLE dept
(
    deptno TINYINT,
    dname  VARCHAR(14),
    loc    VARCHAR(13)
);

CREATE TABLE sales
(
    year_id     INT            NOT NULL,
    month_id    INT            NOT NULL,
    day_id      INT            NOT NULL,
    sales_value DECIMAL(10, 2) NOT NULL
);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7369, 'SMITH', 'CLERK', 7902, '1980/12/17', 800.00, NULL, 20);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981/2/20', 1600.00, 300.00, 30);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981/2/22', 1250.00, 500.00, 30);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7566, 'JONES', 'MANAGER', 7839, '1981/4/2', 2975.00, NULL, 20);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981/9/28', 1250.00, 1400.00, 30);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981/5/1', 2850.00, NULL, 30);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981/6/9', 2450.00, NUll, 10);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1987/4/19', 3000.00, NUll, 50);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7839, 'KING', 'PRESIDENT', NUll, '1981/11/17', 5000.00, NUll, 10);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981/9/8', 1500.00, 0.00, 30);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7876, 'ADAMS', 'CLERK', 7788, '1987/5/23', 1100.00, NUll, 20);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7900, 'JAMES', 'CLERK', 7698, '1981/12/3', 950.00, NUll, 30);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7902, 'FORD', 'ANALYST', 7566, '1981/12/3', 3000.00, NULL, 20);

INSERT INTO emp
    (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (7934, 'MILLER', 'CLERK', 7782, '1982/1/23', 1300.00, NUll, 10);

INSERT INTO dept
    (deptno, dname, loc)
VALUES (10, 'ACCOUNTING', 'NEW YORK');

INSERT INTO dept
    (deptno, dname, loc)
VALUES (20, 'RESEARCH', 'DALLAS');

INSERT INTO dept
    (deptno, dname, loc)
VALUES (30, 'SALES', 'CHICAGO');

INSERT INTO dept
    (deptno, dname, loc)
VALUES (40, 'OPERATIONS', 'BOSTON');