# 数据查询语言 Data QueryLanguage
---
## 查询语句执行顺序
```
1   FROM                右 → 左                 数据量较少的放后面
2   WHERE               右 → 左                 将过滤多的放后面
3   GROUP BY            左 → 右
4   AGGREGATE                                   MAX, MIN, SUM, AVG, COUNT
5   HAVING                                      消耗资源
6   SELECT                                      避免使用 * 号
7   DISTINCT
8   UNION
9   ORDER BY            左 → 右                 消耗资源
10  LIMIT|TOP
```
---
## 基础查询
>### DISTINCT
>```
>SELECT DISTINCT deptno, job FROM emp ORDER BY deptno, job DESC;
>```
>### LIKE，_ 一个字符，% 任意个字符
>```
>SELECT ename FROM emp WHERE ename LIKE '_A%';
>```
>### BETWEEN ... AND ...
>```
>SELECT ename, job, sal FROM emp WHERE sal BETWEEN 1500 AND 3000;
>```
>### IN / NOT IN
>```
>SELECT ename, job, deptno FROM emp WHERE deptno NOT IN(10, 20, 40);
>```
>### ALL / ANY
>```
>SELECT ename, sal FROM emp WHERE sal > ALL(SELECT sal FROM emp WHERE job = 'CLERK');
>```
---
## 分组查询 (GROUY BY / HAVING)
```
SELECT deptno, MAX(sal) max_sal FROM emp GROUP BY deptno HAVING MAX(sal) > 2000;
```
---
## 关联查询
```
SELECT e.ename, d.loc FROM emp e, dept d;                                               笛卡儿积
SELECT e.ename, d.loc FROM emp e, dept d WHERE e.deptno = d.deptno;                     WHERE
SELECT e.ename, d.loc FROM emp e JOIN dept d ON e.deptno = d.deptno;                    JOIN ... ON ...
SELECT e.ename, d.loc FROM emp e INNER JOIN dept d ON e.deptno = d.deptno;              INNER JOIN ... ON ...
SELECT e.ename, d.loc FROM emp e LEFT JOIN dept d ON e.deptno = d.deptno;               LEFT JOIN ... ON ...
SELECT e.ename, d.loc FROM emp e RIGHT JOIN dept d ON e.deptno = d.deptno;              RIGHT JOIN ... ON ...
SELECT e.ename, d.loc FROM emp e FULL JOIN dept d ON e.deptno = d.deptno;               FULL JOIN ... ON ...
SELECT e.ename, m.ename, e.sal, m.sal FROM emp e, emp m WHERE e.mgr = m.empno;          自关联
```
---
## 子查询
1. 子查询必须写在括号内
2. 子查询必须包括 SELECT 子句和 FROM 子句
3. 子查询可以使用 WHERE，GROUP BY 和 HAVING 子句
4. 子查询不能使用 COMPUTE 或 FOR BROWSE 子句
5. 只有在使用了 TOP 子句时，才能使用 ORDER BY 子句  ???
6. 可以嵌套最多 32 个级别的子查询
---