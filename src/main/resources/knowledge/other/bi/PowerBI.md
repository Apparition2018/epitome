# [PowerBI](https://learn.microsoft.com/zh-cn/training/browse/?products=power-bi)
- [PowerBI入门到实战教程](https://www.bilibili.com/video/BV11ezsBCE58/)
---
1. POWER Query：M 语言
    - 透视与逆透视
2. POWER Pivot：DAX (Data Analysis Expression) 语言
3. POWER View
---
## DAX
### 语法规范
1. 表名：'表'
2. 字段：[列]
3. 度量值：[值]
- 始终使用表名引用字段，以区分开度量值
### 函数
| 函数                                          | 作用                              |
|---------------------------------------------|---------------------------------|
| COUNTROWS(表)                                | 记录数                             |
| DISTINCTCOUNT(表[姓名])                        | 去除重复计数                          |
| COUNTA(表[列])                                | 计算列中单元格不为空的数目                   |
| COUNTBLANK(表[列])                            | 计算列中单元格为空白的数目                   |
| PRODUCT(表[列])                               | 计算列中单元格乘积                       |
| SUMX('表', 算术表达式)                            | 求和                              |
| AVERAGEX('表', 算术表达式)                        | 算术平均值                           |
| MAXX('表', 算术表达式)                            | 最大值                             |
| MINX('表', 算术表达式)                            | 最小值                             |
| COUNTX('表', 算术表达式)                          | 记录数                             |
| COUNTAX('表', 算术表达式)                         | 计算列中单元格不为空的数目                   |
| PRODUCTX('表', 算术表达式)                        | 计算列中单元格乘积                       |
| CALCULATE(表达式, 筛选条件1, 筛选条件2...)             | 在修改后的筛选上下文中计算表达式                |
| FILTER('表', 筛选条件)                           | 返回满足筛选条件的所有行组成的表                |
| RELATED('表'[列])                             | 从关联表的“一”端返回相关行的值（多 → 一）         |
| ELATEDTABLE('表')                            | 从关联表的“多”端返回所有相关行组成的表（一 → 多）     |
| SUMMARIZE('表', 分组列1, 分组列2..., "新列名", 聚合表达式) | 按指定列分组，并返回分组后的汇总表               |
| EARLIER(列, 层数)                              | 返回当前行在更早外部上下文中的列值（用于行上下文嵌套计算）   |
| EDATE(日期, 月数)                               | 返回指定日期前后若干个月的日期                 |
| EOMONTH(日期, 月数)                             | 返回指定日期前后若干个月的最后一天               |
| DATEDIFF(起始日期, 结束日期, 时间单位)                  | 返回两个日期之间的时间差                    |
| DATESMTD(日期列)                               | 返回当前月份至今（Month-To-Date）的日期范围表   |
| DATESQTD(日期列)                               | 返回当前季度至今（Quarter-To-Date）的日期范围表 |
| DATESYTD(日期列)                               | 返回当前年份至今（Year-To-Date）的日期范围表    |
| TOTALMTD(表达式, 日期列)                          | 计算当前月份至今（Month-To-Date）的累计值     |
| TOTALQTD(表达式, 日期列)                          | 计算当前季度至今（Quarter-To-Date）的累计值   |
| TOTALWTD(表达式, 日期列)                          | 计算当前周至今（Week-To-Date）的累计值       |
| TOTALYTD(表达式, 日期列)                          | 计算当前年份至今（Year-To-Date）的累计值      |
---
