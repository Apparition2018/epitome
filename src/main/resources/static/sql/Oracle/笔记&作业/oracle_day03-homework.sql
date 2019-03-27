1:查看工资高于2000的员工
SELECT ename,sal FROM emp WHERE sal>2000;

2:查看不是"CLERK"职位的员工
SELECT ename,job FROM emp WHERE job<>'CLERK';

3:查看工资在1000-2500之间的员工
SELECT ename,sal FROM emp WHERE sal BETWEEN 1000 AND 2500;

4:查看名字是以K结尾的员工
SELECT ename FROM emp WHERE ename LIKE '%K';

5:查看20,30号部门的员工
SELECT ename,deptno FROM emp WHERE deptno IN(20,30);

6:查看奖金为NULL的员工
SELECT ename,comm FROM emp WHERE comm IS NULL;

7:查看年薪高于20000的员工
SELECT ename,sal,sal*12 FROM emp WHERE sal*12>20000;

8:查看公司共有多少种职位
SELECT DISTINCT job FROM emp;

9:按部门号从小到大排列查看员工
SELECT ename,deptno FROM emp ORDER BY deptno;

10:查看每个部门的最高，最低，平均工资，和工资总和
SELECT deptno,MAX(sal),MIN(sal),AVG(sal),SUM(sal) FROM emp GROUP BY deptno;

11:查看平均工资高于2000的部门的最低薪水
SELECT deptno,MIN(sal),AVG(sal) FROM emp GROUP BY deptno HAVING AVG(sal)>2000;

12:查看在NEWYORK工作的员工
SELECT e.ename,d.loc FROM emp e,dept d WHERE e.deptno=d.deptno AND d.loc='NEW YORK';

13:查看所有员工及所在部门信息，若该员工没有部门，则部门信息以NULL显示
SELECT e.ename,d.dname,d.loc FROM emp e JOIN dept d ON e.deptno=d.deptno(+);

14:查看ALLEN的上司是谁
SELECT e.ename,m.ename superior FROM emp e,emp m WHERE e.mgr=m.empno AND e.ename='ALLEN';

15:查看SMITH上司在哪个城市工作
SELECT m.ename superior,d.loc FROM emp e,emp m,dept d WHERE e.mgr=m.empno AND m.deptno=d.deptno AND e.ename='SMITH';

16:查看平均工资高于2000的那些部门名字以及所在城市
SELECT AVG(e.sal),d.dname,d.loc FROM dept d,emp e WHERE e.deptno=d.deptno GROUP BY d.deptno,d.dname,d.loc HAVING AVG(e.sal)>2000;

17:在NEW YORK工作的员工有多少人
SELECT COUNT(e.ename) FROM emp e,dept d WHERE e.deptno=d.deptno AND d.loc='NEW YORK';

18:在DALLAS工作的员工的平均工资是多少
SELECT AVG(e.sal),d.loc FROM emp e,dept d WHERE e.deptno=d.deptno GROUP BY d.loc HAVING d.loc='DALLAS';




