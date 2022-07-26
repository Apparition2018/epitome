字段的别名
当一个SELECT字句中含有函数或者表达式时，查询出来的结果集中对应该字段的名字就是这个函数或表达式，
不易理解，可读性差，为此我们可以为这样的字段单独制定别名。
SELECT ename,sal*12 sal FROM emp;

SELECT ename,sal*12 "s al" FROM emp;

SELECT ename,sal,job FROM emp WHERE sal > 1000 AND job = 'CLERK';
SELECT ename,sal,job FROM emp WHERE sal > 1000 OR job = 'CLERK';

AND,OR用来连续多个条件
OR的优先级低于AND，所以若希望提高OR的优先级可以使用小括号
SELECT ename,sal,job FROM emp WHERE sal>1000 AND (job='SALESMAN' OR job='CLERK');
SELECT ename,sal,job FROM emp WHERE sal>1000 AND job='CLERK' OR job='SALESMAN';

LIKE用于模糊匹配字符串
支持两个通配符：
_:任意一个字符
%：任意个字符（0—多个）

查看名字第二个字母是A的员工？
SELECT ename,job FROM emp WHERE ename LIKE '_A%';
SELECT ename,job FROM emp WHERE ename LIKE '_A%D';

IN(list), NOT IN(list)
判断在列表中或不在列表中
IN常用在子查询中

SELECT ename,job FROM emp WHERE job IN('MANAGER', 'CLERK');
SELECT ename,job FROM emp WHERE deptno NOT IN(10, 20);

SELECT ename,job,sal,job,deptno FROM emp WHERE deptno <> 10 AND sal > 1000 AND job IN('CLERK', 'SALESMAN') AND ename LIKE '%A%';

BETWEEN...AND...
判断在一个范围内

查看工资在1500到3000之间的员工信息？
SELECT ename,sal,deptno FROM emp WHERE sal BETWEEN 1500 AND 3000;

ANY(list), ALL(list)
配合>,>=,<,<=一个列表使用
>ANY(list):大于列表其一（大于最小即可）
>ALL(list):大于列表所有（大于最大）
<ANY(list):小于列表其一（小于最大即可）
<ALL(list):小于列表所有（小于最小）
ANY,ALL常用在子查询中
SELECT empno,ename,job,sal,deptno FROM emp WHERE sal>ANY (3500,4000,4500);

SELECT ename,sal,job FROM emp WHERE ename = UPPER('scott');
SELECT ename,sal,job FROM emp WHERE sal*12 > 50000;

DISTINCT关键字
用来将结果集中指定字段值重复的记录去除

查看公司有哪些职位？
SELECT DISTINCT job FROM emp;

多字段去重时，是这些字段值组合一样的记录不会重复出现
SELECT DISTINCT job, deptno FROM emp;

排序結果集
ORDER BY子句可以对结果集进行升序或降序排列
升序：ASC 不写默认也是升序
降序：DESC
ORDER BY只能写在DQL的最后一个子句上

查看公司中工资的排名？
SELECT ename,sal,deptno FROM emp ORDER BY sal DESC;

多字段排序时，按照ORDER BY后面第一个字段先排序，当第一个字段有重复时，
才按照第二个字段的值排序这些记录，依次类推，所以多字段排序时有优先级的
SELECT ename,sal,deptno FROM emp ORDER BY sal DESC, deptno;

排序含有NULL值的字段时，NULL被认作为最大值
SELECT ename,comm FROM emp ORDER BY comm DESC;

查看公司的所有职位，并按照职位名称排序
SELECT DISTINCT job FROM emp ORDER BY job;

聚合函数
聚合函数是用来统计的，可以将结果集按照指定字段值进行统计，然后得到一个结果，
其中有四个针对值本身统计的函数
求最大值，最小值，平均值和总和
MAX,MIN,AVG,SUM

还有一个对结果集记录数的统计函数：
COUNT

求公司工资的最大、最小、平均、总和
SELECT MAX(sal),MIN(sal),AVG(sal),SUM(sal) FROM emp;

查看公司一共多少人？
SELECT COUNT(ename) FROM emp;

聚合函数忽略NULL值
查看平均绩效？
SELECT AVG(comm),SUM(comm) FROM emp;
SELECT AVG(NVL(comm,0)) FROM emp;
SELECT SUM(comm)/COUNT(*) FROM emp;

查看20号部门的平均工资？
SELECT AVG(sal) FROM emp WHERE deptno=20;

以下SQL执行会报错！
SELECT ename,MAX(sal) FROM emp;

分组
GROUP BY子句
GROUP BY子句可以将结果集按照指定的字段值一样的记录看做同一组，
配合聚合函数使用可以对每组的数据进行统计并的出结果

当SELECT子句中使用了聚合函数时，那么凡不在聚合函数中的单独字段都必须出现在GROUP BY子句中

查看每个部门的平均工资？
SELECT AVG(sal),deptno FROM emp GROUP BY deptno;

查看每个职位的最高工资和最低工资？
SELECT MAX(sal),MIN(sal),job FROM emp GROUP BY job;

多字段分组
GROUP BY使用多个字段分组时，是将这些字段值的组合一样的记录看做一组

查看每个部门每种职位各多少人？
SELECT COUNT(*),deptno,job FROM emp GROUP BY deptno,job;

查看每个部门的平均工资，但前提是该部门的平均工资要高于2000
SELECT AVG(sal),deptno FROM emp WHERE AVG(sal)>2000 GROUP BY deptno;
上面的SQL语句会报错，WHERE中不能使用聚合函数作为过滤条件，原因在于过滤的时机不对

WHERE是在第一次检索表时，逐行过滤数据以产生查询结果集时使用的。
而聚合函数是用来统计，那么前提是有数据，所以要将所有数据查询出来才能统计，
之后才可以进行过滤，这实际是在WHERE之后进行的。
HAVING必须跟在GROUP BY子句之后，作用是添加过滤条件将不满足的分组去除。
而HAVING中允许使用聚合函数作为过滤条件。

SELECT AVG(sal),deptno FROM emp GROUP BY deptno HAVING AVG(sal)>2000;

查看部门平均工资高于2000的这些部门的最高工资是多少？
SELECT MAX(sal),deptno FROM emp GROUP BY deptno HAVING AVG(sal)>2000;

关键查询
联合多张表查询数据即关联查询。
查询的结果集中的字段来自多张表。
关联查询的重点是连接条件，数据库是根据连接条件对表中的数据做关联，然后查询的。

查看每个员工的名字以及其所在部门的名字？
;
对于查询的某个字段在多张表上同时存在,
但在查询时出现的未明确定义列的问题，我们可以使用表名或表别名来指定该字段来自哪张表
SELECT e.ename,e.deptno,d.dname
FROM emp e,dept d
WHERE e.deptno=d.deptno;

查看在NEW YORK工作的员工？
SELECT e.ename,e.deptno,d.dname,d.loc
FROM emp e,dept d
WHERE e.deptno=d.deptno
AND d.loc='NEW YORK';

关联查询通常要加连接条件，不写连接条件会出现"笛卡尔"
笛卡尔积通常是一个无意义的结果集
笛卡尔积是将关联查询表中的数据意义连接一遍而产生的结果集，数据量为关联查询表数据量的乘积
SELECT e.ename,e.deptno,d.dname,d.loc
FROM emp e,dept d;

内连接
内连接也是用来实现关联查询的
SELECT e.ename,d.dname FROM emp e JOIN dept d ON e.deptno=d.deptno;

SELECT e.ename,d.dname,d.dname,d.loc
FROM emp e JOIN dept d
ON e.deptno=d.deptno
WHERE d.loc='NEW YORK';

关联查询忽略不满足连接条件的记录

外连接
外连接在关联查询中出了可以将满足连接条件的记录查询出来之外，
还可以将不满足连接条件的记录也查询出来
外连接分为：
左外连接：以JOIN左侧表作为驱动表（所有数据显示出来），那么该表某条记录不满足连接条件时，
        来自右侧表中的字段值全部为空
SELECT e.ename,e.sal,d.dname,d.loc
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno=d.deptno;

右外连接：
SELECT e.ename,e.sal,d.dname,d.loc
FROM emp e RIGHT OUTER JOIN dept d
ON e.deptno=d.deptno;

全外连接:
SELECT e.ename,e.sal,d.dname,d.loc
FROM emp e FULl OUTER JOIN dept d
ON e.deptno=d.deptno;

关联查询的外连接写法：
左外连接：
SELECT e.ename,e.sal,d.dname,d.loc
FROM emp e,dept d
WHERE e.deptno=d.deptno(+);

右外连接：
SELECT e.ename,e.sal,d.dname,d.loc
FROM emp e,dept d
WHERE e.deptno(+)=d.deptno;

自连接
当前表的记录与当前表的多条记录有对应关系
自连接解决属性相同，但是又存在上下级关系的树状结构数据使用

查看每个员工的名字以及其上司的名字？
SELECT e.ename,m.ename superior
FROM emp e, emp m
WHERE e.mgr=m.empno
