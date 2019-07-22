1:查看与CLARK相同职位的员工
SELECT ename,job FROM emp WHERE job=(SELECT job FROM emp WHERE ename='CLARK');

2:查看低于公司平均工资的员工
SELECT ename,sal FROM emp WHERE sal<(SELECT AVG(sal) FROM emp);

3:查看与ALLEN同部门的员工
SELECT ename,deptno FROM emp WHERE deptno=(SELECT deptno FROM emp WHERE ename='ALLEN');

4:查看平均工资低于20号部门平均工资的部门平均工资
SELECT AVG(sal),deptno FROM emp GROUP BY deptno HAVING AVG(sal)<(SELECT AVG(sal) FROM emp WHERE deptno=20 GROUP BY deptno);

5:查看低于自己所在部门平均工资的员工
SELECT e.ename,e.sal,e.deptno FROM emp e,(SELECT deptno,AVG(sal) avg_sal FROM emp GROUP BY deptno) t WHERE e.deptno=t.deptno AND e.sal<t.avg_sal;

6:查看公司工资排名的第1-5名
SELECT * FROM (SElECT ROWNUM rn,ename,sal FROM(SELECT * FROM emp ORDER BY sal DESC)) WHERE rn BETWEEN 1 AND 5;

7:查看CLERK职位的人数和其他职位的总人数各多少?
SELECT COUNT(1),DECODE(job,'CLERK','CLERK','OTHER') n_job FROM emp GROUP BY DECODE(job,'CLERK','CLERK','OTHER');

8:查看每个职位的工资排名
SELECT ename,sal,job,ROW_NUMBER() OVER(PARTITION BY job ORDER BY sal DESC) rank FROM emp;

9:查看每个职位的工资排名，若工资一致，排名一致
SELECT ename,sal,job,RANK() OVER(PARTITION BY job ORDER BY sal DESC) rank FROM emp;

10:查看每个职位的工资排名，若工资一致，排名一致，不跳名次。
SELECT ename,sal,job,DENSE_RANK() OVER(PARTITION BY job ORDER BY sal DESC) rank FROM emp;

11:分别查看:同部门同职位，同职位，以及所有员工的工资总和
SELECT job,deptno,SUM(sal) FROM emp GROUP BY ROLLUP(job,deptno) ORDER BY job,deptno;

12:分别查看:同部门同职位，同职位，同部门以及所有员工的工资总和
SELECT job,deptno,SUM(sal) FROM emp GROUP BY CUBE(job,deptno) ORDER BY job,deptno;

13:分别查看同部门同职位和同职位的员工的工资总和
SELECT job,deptno,SUM(sal) FROM emp GROUP BY GROUPING SETS((job,deptno),job) ORDER BY job,deptno;

14:查看公司最高工资的员工的名字以及所在部门名称
SELECT e.ename,e.sal,d.deptno FROM emp e JOIN dept d ON e.deptno=d.deptno WHERE e.sal=(SELECT MAX(sal) FROM emp);

15:查看每个部门的最高工资的员工名字
SELECT e.ename,e.sal,e.deptno FROM emp e,(SELECT deptno,MAX(sal) max_sal FROM emp GROUP BY deptno) t WHERE e.deptno=t.deptno AND e.sal=t.max_sal;

16:查看有下属的员工信息
SELECT m.ename,m.job,m.sal FROM emp m WHERE EXISTS(SELECT e.ename FROM emp e WHERE e.mgr=m.empno);
SELECT * FROM emp;


