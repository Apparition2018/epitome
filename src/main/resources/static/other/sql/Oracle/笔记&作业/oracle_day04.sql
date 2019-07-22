子查询
子查询是嵌套在其他SQL语句当中，母的是为嵌套的SQL提供数据，以便其执行

SELECT ename,sal FROM emp WHERE sal>(SELECT sal FROM emp WHERE ename='CLARK');

查看与FORD同部门的员工？
SELECT ename,deptno FROM emp WHERE deptno=(SELECT deptno FROM emp WHERE ename='FORD');

查看工资高于公司平均工资的员工？
SELECT ename,sal FROM emp WHERE sal>(SELECT AVG(sal) FROM emp);

查看公司最高工资是谁？
SELECT ename,sal FROM emp WHERE sal=(SELECT MAX(sal) FROM emp);

查看部门的平均工资，前提是该部门的平均工资要高于30号部门的平均工资
SELECT deptno,AVG(sal) FROM emp GROUP BY deptno HAVING AVG(SAL)>(SELECT AVG(SAL) FROM emp WHERE deptno=30 GROUP BY deptno);


在DDL与DML中也可以使用子查询
1：在DDL中使用子查询，可以将一个子查询的结果集当做表快速创建

创建一张表，含有empno,ename,sal,job,deptno,daname,loc并且数据就是现有表emp，dept的数据？
CREATE TABLE emp2 AS SELECT e.empno id,e.ename,e.sal,e.job,d.deptno,d.dname,d.loc FROM emp e,dept d WHERE e.deptno=d.deptno(+);

DML中使用子查询
将SMITH所在部门所有员工工资上浮10%
UPDATE emp SET sal=sal*1.1 WHERE deptno=(SELECT deptno FROM emp WHERE ename='SMITH');

子查询根据查询结果不同，分为：
单行单列子查询，多行单列子查询和多行多列子查询
其中单列子查询常用在过滤条件中，而多列子查询通常当做表看待

对于多行单列子查询作为过滤条件进行判断时，要配合IN,ANY,ALL使用

查看与SALESMAN同部门的其它职位员工？
SELECT ename,job,deptno FROM emp WHERE deptno IN(SELECT deptno FROM emp WHERE job='SALESMAN') AND job<>'SALESMAN';

查看比职位是SALESMAN和CLERK工资都高的员工？
SELECT ename,sal,job FROM emp WHERE sal>ALL(SELECT sal FROM emp WHERE job IN('SALESMAN','CLERK'));

EXISTS关键字
EXISTS关键字用在过滤条件中，其后需要跟一个子查询，当该子查询可以查询出至少一条记录时，即判定为满足条件

查看有员工的部门？
SELECT d.deptno,d.dname,d.loc FROM dept d WHERE EXISTS(SELECT * FROM emp e WHERE e.deptno=d.deptno);

查看没有员工的部门？
SELECT d.deptno,d.dname,d.loc FROM dept d WHERE NOT EXISTS(SELECT * FROM emp e WHERE e.deptno=d.deptno);

查看有下属的员工？
SELECT m.empno,m.ename,m.job FROM emp m WHERE EXISTS(SELECT * FROM emp e WHERE e.mgr=m.empno);

FROM子句中使用子查询
当一个子查询为多列子查询时，通常是当做一张表使用出现在FROM子句中

查看谁的工资高于自己所在部门平均工资？
SELECT e.ename,e.sal,e.deptno FROM emp e,(SELECT deptno,AVG(sal) avg_sal FROM emp GROUP BY deptno) t WHERE e.deptno=t.deptno AND e.sal>t.avg_sal;

查看每个部门最高工资的员工？
SELECT e.ename,e.sal,e.deptno FROM emp e,(SELECT deptno,MAX(sal) max_sal FROM emp GROUP BY deptno) t WHERE e.deptno=t.deptno AND e.sal=t.max_sal;

SELECT e.ename,e.sal,(SELECT d.dname FROM dept d WHERE d.deptno=e.deptno) dname FROM emp e;

分页查询
当一个查询语句插叙的数据量非常大时，需要分批分段将数据查询出来，这样做可以减少系统资源开销，提高数据库响应速度

分页由于在标准SQL中没有定义，所以不同的数据库有不同的用法，即：方言
ROWNUM:伪列
ORACLE提供了伪列：ROWRUN来配合完成分页查询
ROWRUN不存在于任何表中，但是所有的表都可以查询该字段，该字段的值在结果集中就是每条记录的行号。
ROWRN的值是动态生成的，是伴随查询的过程中生成的。即：每当可以从表中查询出一条记录时，ROWRUN会为该记录生成行号，从1开始逐1递增。
SELECT ROWNUM,ename,sal,job,deptno FROM emp;

在使用ROWNUM对结果集编号的过程中不要使用ROWRUN做>1以上数字的判断，否则查询不出任何结果
SELECT ROWNUM,ename,sal,job,deptno FROM emp WHERE ROWNUM>1;

SELECT * FROM (SELECT ROWNUM rn,ename,sal,job,deptno FROM emp) WHERE rn BETWEEN 6 AND 10;

查询工资在6到10名的员工？
SELECT * FROM (SELECT ROWNUM rn,ename,sal FROM (SELECT * FROM emp ORDER BY sal DESC)) WHERE rn BETWEEN 6 AND 10;

pageSize:每页显示的条目数
page：页数

pageSize:5 page:2
start:(page-1)*pageSize+1
end:pageSize*page

DECODE函数
可以实现简单的
SELECT ename,job,sal,DECODE(job,'MANAGER',sal*1.2,'ANALYST',sal*1.1,'SALESMAN',sal*1.05,sal) bonus FROM emp;

SELECT ename,job,sal,CASE job WHEN 'MANAGER' THEN sal*1.2 WHEN 'ANALYST' THEN sal*1.1 WHEN 'SALESMAN' THEN sal*1.05 ELSE sal END bonus FROM emp;

在分组中使用DECODE函数可以将字段值不同的记录划分为一组

将MANAGER和ANALYST看做一组，其他职位的看作另一组，分别统计各组人数？
SELECT COUNT(1),DECODE(job,'MANAGER','VIP','ANALYST','VIP','OTHER') new_job FROM emp GROUP BY DECODE(job,'MANAGER','VIP','ANALYST','VIP','OTHER');

SELECT deptno,dname,loc FROM dept ORDER BY DECODE(dname,'OPERATIONS',1,'ACCOUNTING',2,'SALES',3);

排序函数
排序函数可以对结果集按照指定的字段分组，然后在组内按照指定的字段排序，然后生成组内的行号

查看每个部门的工资排名：
ROW_NUMBER:生成组内连续且唯一的数字
SELECT ename,sal,deptno,ROW_NUMBER() OVER(PARTITION BY deptno ORDER BY sal DESC) rank FROM emp;

RANK:生成组内不连续也不唯一的数字
SELECT ename,sal,deptno,RANK() OVER(PARTITION BY deptno ORDER BY sal DESC) rank FROM emp;

DENSE_RANK:生成组内连续但不唯一的数字
SELECT ename,sal,deptno,DENSE_RANK() OVER(PARTITION BY deptno ORDER BY sal DESC) rank FROM emp;

SELECT ename,job,sal FROM emp WHERE job = 'MANAGER' UNION SELECT ename,job,sal FROM emp WHERE sal > 2500;

SELECT ename,job,sal FROM emp WHERE job = 'MANAGER' UNION ALL SELECT ename,job,sal FROM emp WHERE sal > 2500;

SELECT ename,job,sal FROM emp WHERE job = 'MANAGER' INTERSECT SELECT ename,job,sal FROM emp WHERE sal > 2500;

SELECT ename,job,sal FROM emp WHERE job = 'MANAGER' MINUS SELECT ename,job,sal FROM emp WHERE sal > 2500;

CREATE TABLE sales(
  year_id NUMBER NOT NULL,
  month_id NUMBER NOT NULL,
  day_id NUMBER NOT NULL,
  sales_value NUMBER(10,2) NOT NULL
);
INSERT INTO sales
SELECT TRUNC(DBMS_RANDOM.value(2010,2012)) AS year_id,
       TRUNC(DBMS_RANDOM.value(1,13)) AS month_id,
       TRUNC(DBMS_RANDOM.value(1,32)) AS day_id,
       ROUND(DBMS_RANDOM.value(1,100),2) AS sales_value
FROM dual
CONNECT BY level <=1000;

SELECT year_id,month_id,day_id,sales_value FROM sales ORDER BY year_id,month_id,day_id;

查看每天营业额？
SELECT year_id,month_id,day_id,SUM(sales_value) FROM sales GROUP BY year_id,month_id,day_id ORDER BY year_id,month_id,day_id;

查看每月营业额？
SELECT year_id,month_id,SUM(sales_value) FROM sales GROUP BY year_id,month_id ORDER BY year_id,month_id;

查看每年营业额？
SELECT year_id,SUM(sales_value) FROM sales GROUP BY year_id ORDER BY year_id;

总共的营业额？

ROLLUP()函数
GROUP BY ROLLUP(a,b,c)
等同于
GROUP BY a,b,c
UNION ALL
GROUP BY a,b
UNION ALL 
GROUP BY a
UNION ALL
全表

SELECT year_id,month_id,day_id,SUM(sales_value) FROM sales GROUP BY ROLLUP(year_id,month_id,day_id) ORDER BY year_id,month_id,day_id;

CUBE()函数
CUBE()函数会将所有参数的每种组合多作为一次分组方式进行统计，然后将结果并在一起，所以分组次数为2的参数个数次方

GROUP BY CUBE(a,b,c)
abc
ab
ac
bc
a
b
c
全表

SELECT year_id,month_id,day_id,SUM(sales_value) FROM sales GROUP BY CUBE(year_id,month_id,day_id) ORDER BY year_id,month_id,day_id;

GROUPING SETS()
每一个参数是一种分组方式，然后将这些分组统计的结果并在一个结果集显示.

查看每天与每月的营业额？
SELECT year_id,month_id,day_id,SUM(sales_value) FROM sales GROUP BY GROUPING SETS((year_id,month_id,day_id),(year_id,month_id)) ORDER BY year_id,month_id,day_id;