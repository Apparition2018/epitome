数据查询语言（DQL） ：Data Query Language

  高级查询
    子查询
      --返回多行，使用多行比较操作符：IN、ALL、ANY
      SELECT empno,ename,sal,deptno FROM emp WHERE deptno IN(SELECT deptno FROM emp WHERE job='SALESMAN') AND job<>'SALESMAN';
      --EXISTS
      SELECT d.deptno,d.dname,d.loc FROM dept d WHERE EXISTS (SELECT e.ename FROM emp e WHERE e.deptno=d.deptno); ***
      --行内视图、匿名试图
      SELECT e.ename, e.sal, e.deptno FROM emp e,((SELECT deptno,AVG(sal) avg_sal FROM emp GROUP BY deptno) t) WHERE e.deptno = t.deptno AND sal>t.avg_sal;
      --在SELECT子句部分，可以认为是外连接的另一种表现形式
      SELECT e.ename, e.sal, e.deptno, (SELECT d.loc FROM dept d WHERE d.deptno = e.deptno) deptno FROM emp e;

    分页查询
      SELECT * FROM (SELECT ROWNUM rn,t.* FROM ((SELECT ename,sal,deptno FROM emp ORDER BY sal DESC) t) WHERE ROWNUM <= 10) WHERE rn > 5;

    DECODE函数
      --在分组查询中的应用
      SELECT DECODE(job,'PRESIDENT','BOSS','MANAGER','VIP','ANALYS','VIP','OPERATION') job, COUNT(1) FROM emp GROUP BY DECODE(job,'PRESIDENT','BOSS','MANAGER','VIP','ANALYS','VIP','OPERATION');
      --按字段内容排序
      SELECT deptno, dname, loc FROM dept ORDER BY DECODE(dname, 'RESEARCH',1,'OPERATIONS',2,'SALES',3), loc;
      --CASE语句，功能和DECODE函数功能相似，实现类似于if-else的操作
      SELECT ename, job, sal, (CASE job WHEN 'MANAGER' THEN sal * 1.2 WHEN 'ANALYST' THEN sal * 1.1 WHEN 'SALESMAN' THEN sal * 1.05 ELSE sal END) bonus FROM emp;

    排序函数
      --ROW_NUMBER,连续且唯一
      SELECT ename,deptno,sal,ROW_NUMBER() OVER(PARTITION BY deptno ORDER BY sal DESC) sal_rank FROM emp;
      --RANK,不连续、不唯一
      SELECT ename,deptno,sal,RANK() OVER(PARTITION BY deptno ORDER BY sal DESC) sal_rank FROM emp;
      --DENSE_RANK,连续、不唯一
      SELECT ename,deptno,sal,DENSE_RANK() OVER(PARTITION BY deptno ORDER BY sal DESC) sal_rank FROM emp;

    高级分组函数
      --ROLLUP
      SELECT year_id,month_id,day_id,SUM(sales_value) sum FROM sales GROUP BY ROLLUP(year_id,month_id,day_id) ORDER BY year_id,month_id,day_id;
      --CUBE
      SELECT year_id,month_id,day_id,SUM(sales_value) sum FROM sales GROUP BY CUBE(year_id,month_id,day_id) ORDER BY year_id,month_id,day_id;
      --GROUPING SETS
      SELECT year_id,month_id,day_id,SUM(sales_value) sum FROM sales GROUP BY GROUPING SETS((year_id,month_id,day_id),(year_id,month_id)) ORDER BY year_id,month_id,day_id;


    集合
      --UNION，去掉重复，升序 (第一列)
      SELECT ename, job, sal FROM emp WHERE job = 'MANAGER'
      UNION
      SELECT ename, job, sal FROM emp WHERE sal> 2500;
      --UNION ALL，包括重复，不排序
      --INTERSECT，交集，升序 (第一列)
      --MINUS/EXCEPT，差集，结果集一 减 结果集二
