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
## 子查询
1. 子查询必须写在括号内
2. 子查询必须包括 SELECT 子句和 FROM 子句
3. 子查询可以使用 WHERE，GROUP BY 和 HAVING 子句
4. 子查询不能使用 COMPUTE 或 FOR BROWSE 子句
5. 只有在使用了 TOP 子句时，才能使用 ORDER BY 子句  ???
6. 可以嵌套最多 32 个级别的子查询
---
## DISTINCT
```
SELECT DISTINCT course FROM score
```
---
## LIKE
```
%	            表示 0 | 1 | n 个字符  
_	            表示 1 个字符
```
---
## GROUP BY
```
SELECT name, AVG(score) avg_score FROM score GROUP BY name HAVING AVG(score) < 80
```
---