数据查询语言（DQL） ：Data Query Language

  基础查询
    SELECT empno AS id,ename "Name",sal*12 "Annual Salary" FROM emp;                                  -- 别名
    SELECT ename FROM emp WHERE ename LIKE '_A%';                                                     -- 模糊查询; %：0到多个, _：单个
    SELECT ename,job,sal,deptno FROM emp WHERE deptno NOT IN(10,20,40) AND sal BETWEEN 1500 AND 3000; -- NOT IN / BETWEEN…AND…
    SELECT ename,sal FROM emp WHERE sal>ALL(SELECT sal FROM emp WHERE job='CLERK');                   -- ALL / ANY
    SELECT LOWER(ename) name,job,sal FROM emp WHERE ename=UPPER('smith');
    SELECT DISTINCT deptno,job FROM emp ORDER BY deptno ASC,job DESC;                                 -- DISTINCT / ASC，DESC NULL视作最大
    SELECT MAX(sal),MIN(sal),SUM(sal),AVG(sal),COUNT(comm) FROM emp;                                  -- 聚合函数，忽略NULL值
    SELECT deptno,MAX(sal) max_sal FROM emp GROUP BY deptno HAVING MAX(sal)>2000;                     -- GROUY BY / HAVING

    1.FROM:     执行顺序为从后往前、从右到左。数据量较少的表尽量放在后面。
    2.WHERE:    执行顺序为自下而上、从右到左。将能过滤掉最大数量记录的条件写在 WHERE 的最右。
    3.GROUP BY: 执行顺序从左往右分组，最好在 GROUP BY 前使用 WHERE 将不需要的记录在 GROUP BY 之前过滤掉。
    4.HAVING:   会在检索出所有记录之后才对结果集进行过滤,消耗资源,尽量避免使用。
    5.SELECT:   少用*号，尽量取字段名称。
    6.ORDER BY: 执行顺序为从左到右排序，消耗资源。

  关联查询
    SELECT e.ename,d.loc FROM emp e,dept d WHERE e.deptno=d.deptno;               -- WHERE
    SELECT e.ename,d.loc FROM emp e JOIN dept d ON e.deptno=d.deptno;             -- JOIN...ON...
    SELECT e.ename,d.loc FROM emp e,dept d;                                       -- 笛卡尔积
    SELECT e.ename,d.loc FROM emp e LEFT OUTER JOIN dept d ON e.deptno=d.deptno;  -- 左外连接:LEFT OUTER JOIN...ON...
    SELECT e.ename,d.loc FROM emp e FULL OUTER JOIN dept d ON e.deptno=d.deptno;  -- 全连接:FULL OUTER JOIN...ON...
    SELECT e.ename,m.ename,e.sal,m.sal FROM emp e,emp m WHERE e.mgr=m.empno;      -- 自连接
