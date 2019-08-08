/*********
1) FROM			  右 → 左，数据量较少的放后面
2) WHERE		  右 → 左，将过滤多的放后面
3) GROUP BY		左 → 右
4) 聚合函数    MAX, MIN, SUM, AVG, COUNT
5) HAVING		  消耗资源，避免使用
6) SELECT		  避免使用*号
7) DISTINCT		
8) UNION		
9) ORDER BY		左 → 右，消耗资源
10）LIMIT|TOP
**********/