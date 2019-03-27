/********** ANSI_NULLS **********/
-- 设置 null 值比较
-- 如设置 ON，只有 is null 能判断 null
-- 如设置 OFF，= null 也可以判断 null
SET ANSI_NULLS ON
SELECT * FROM score WHERE score is NULL
SELECT * FROM score WHERE score = NULL
GO

/********** QUOTED_IDENTIFIER **********/
-- 设置双引号 "" 是否起标识符作用
-- https://blog.csdn.net/u012209148/article/details/78978226
SET QUOTED_IDENTIFIER ON
CREATE TABLE "distinct" (
  id varchar(200) primary key not null,
  sort int,
  name varchar(200),
)
DROP TABLE "distinct"
GO

/********** ANSI_PANDDING **********/
-- 设置列如何存储长度比列的已定义大小短的值
-- https://blog.csdn.net/u012209148/article/details/78978323
SET ANSI_PADDING ON
GO