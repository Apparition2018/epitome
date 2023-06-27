# 数据查询语言 Data Query Language

---
## [查询语句执行顺序](https://www.cnblogs.com/wyq178/p/11576065.html)
```
FROM
ON
JOIN
WHERE                       不能使用别名，因为还没执行 SELECT
GROUP BY                    只保留了分组字段和聚合函数的结果，因此 SELECT、ORDER BY 只能使用这些字段 
AGGREGATE
HAVING                      能用 WHERE 过滤就不要用 HAVING
SELECT
DISTINCT
UNION|INTERSECT|EXCEPT
ORDER BY
LIMIT|TOP|OFFSET|FETCH
```
---
## 基础查询
- DISTINCT
```sql
SELECT DISTINCT deptno, job FROM emp ORDER BY deptno, job DESC;
```
- LIKE：_ 一个字符，% 任意个字符
```sql
SELECT ename FROM emp WHERE ename LIKE '_A%';
```
- BETWEEN ... AND ...
```sql
SELECT ename, job, sal FROM emp WHERE sal BETWEEN 1500 AND 3000;
```
- IN / NOT IN
```sql
SELECT ename, job, deptno FROM emp WHERE deptno NOT IN(10, 20, 40);
```
---
## 分组查询 (GROUP BY / HAVING)
```sql
SELECT deptno, MAX(sal) max_sal FROM emp GROUP BY deptno HAVING MAX(sal) > 2000;
```
### 高级分组查询
1. MySQL：`GROUP_CONCAT`
```mysql
SELECT d.loc, GROUP_CONCAT(e.ename ORDER BY e.ename)
FROM emp e
JOIN dept d ON e.deptno = d.deptno
GROUP BY d.loc
```
2. Oracle：`ROLLUP`，`CUBE`，`GROUPING SETS`
---
## 关联查询
```oracle
SELECT e.ename, d.loc FROM emp e, dept d;                                           -- 笛卡儿积
SELECT e.ename, d.loc FROM emp e, dept d WHERE e.deptno = d.deptno;                 -- WHERE
SELECT e.ename, d.loc FROM emp e INNER JOIN dept d ON e.deptno = d.deptno;          -- 内关联
SELECT e.ename, d.loc FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno;           -- 左关联
SELECT e.ename, d.loc FROM emp e RIGHT JOIN dept d ON e.deptno = d.deptno;          -- 右关联
SELECT e.ename, d.loc FROM emp e FULL JOIN dept d ON e.deptno = d.deptno;           -- 全关联 (MySQL 不支持)
SELECT e.ename, m.ename, e.sal, m.sal FROM emp e, emp m WHERE e.mgr = m.empno;      -- 自关联
```
---
## 子查询
```
1. 子查询必须写在括号内
2. 子查询必须包括 SELECT 子句和 FROM 子句
3. 子查询可以使用 WHERE，GROUP BY 和 HAVING 子句
4. 子查询不能使用 COMPUTE 或 FOR BROWSE 子句
5. 只有在使用了 TOP 子句时，才能使用 ORDER BY 子句  ???
6. 可以嵌套最多 32 个级别的子查询
```
- 多行比较操作符：IN / ANY / ALL
```sql
SELECT empno, ename FROM emp WHERE deptno IN(SELECT deptno FROM emp WHERE job = 'SALESMAN');

SELECT ename, sal FROM emp WHERE sal > ALL(SELECT sal FROM emp WHERE job = 'CLERK');
```
- EXISTS
```sql
SELECT d.deptno, d.dname, d.loc FROM dept d WHERE EXISTS (SELECT 1 FROM emp e WHERE e.deptno = d.deptno);
```
- 行内视图 / 匿名视图
```sql
SELECT e.ename, e.deptno FROM emp e, (SELECT deptno, AVG(sal) avg_sal FROM emp GROUP BY deptno) t WHERE e.deptno = t.deptno AND sal > t.avg_sal;
```
- SELECT 子句 (可认为是外连接的另一种表现形式)
```sql
SELECT e.ename, e.deptno, (SELECT d.loc FROM dept d WHERE d.deptno = e.deptno) deptno FROM emp e;
```
---
## 分页查询 
1. MySQL
```mysql
SELECT ename, sal, deptno 
FROM emp ORDER BY sal DESC LIMIT 5, 5
```
- WITH ROLLUP
```sql
SELECT deptno, count(*) cnt
FROM emp GROUP BY deptno WITH ROLLUP
```
2. Oracle
```oracle
SELECT * FROM (
   SELECT ROWNUM rn, t.* FROM (
       SELECT ename, sal, deptno FROM emp ORDER BY sal DESC
   ) t
WHERE ROWNUM <= 10) WHERE rn > 5;
-- ROWNUM <= (page * pageSize)
-- rn > ((page - 1) * pageSize)
```
---
## 排名查询
1. [MySQL](https://www.jb51.net/article/194925.htm) / Oracle
- ROW_NUMBER: 连续且唯一
```mysql
SELECT ename, deptno, sal, ROW_NUMBER() OVER(PARTITION BY deptno ORDER BY sal DESC) sal_rank FROM emp;
```
- RANK: 不连续不唯一
```mysql
SELECT ename, deptno, sal, RANK() OVER(PARTITION BY deptno ORDER BY sal DESC) sal_rank FROM emp;
```
- DENSE_RANK: 连续不唯一
```mysql
SELECT ename, deptno, sal, DENSE_RANK() OVER(PARTITION BY deptno ORDER BY sal DESC) sal_rank FROM emp;
```
- null 值排最后
```mysql
SELECT ename, sal, RANK() OVER(ORDER BY -sal ASC) sal_rank FROM emp;
```
---
## 集合查询
```
UNION                                                   并集，去掉重复，第一列升序 
UNION ALL                                               并集，包括重复，不排序
INTERSECT                                               交集，第一列升序
MINUS / EXCEPT                                          差集
```
---
