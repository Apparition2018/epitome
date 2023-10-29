关联查询的外连接写法：
左外连接：
SELECT e.ename,e.sal,d.dname,d.loc
FROM emp e,dept d
WHERE e.deptno=d.deptno(+);

右外连接：
SELECT e.ename,e.sal,d.dname,d.loc
FROM emp e,dept d
WHERE e.deptno(+)=d.deptno;
