-- 复制表，目标表不存在，拷贝表的数据以及表的结构
-- 目标表的pk，Extra(auto_increment)等属性，需要自己alter添加
-- SQL Server
SELECT * INTO score_bak FROM score
-- Oracle
-- CREATE TABLE score_bak AS SELECT * FROM score
GO

-- 复制表，目标表不存在，拷贝表的结构，不拷贝表的数据
-- SQL Server
SELECT * INTO score_bak FROM score WHERE 1=0
-- Oracle
-- CREATE TABLE score_bak AS SELECT * FROM score WHERE 1=0
GO

-- 复制表，目标表存在，只拷贝表的数据，不拷贝表的结构(两个表结构一样)
-- SQL Server / Oracle
INSERT INTO score_bak SELECT * FROM score
GO

-- 复制表，目标表存在，只拷贝表的数据，不拷贝表的结构(两个表结构不一样)
-- SQL Server / Oracle
INSERT INTO score_bak (course, score) SELECT course, score FROM score
GO

SELECT * FROM score
SELECT * FROM score_bak
-- DROP TABLE score_bak
-- DELETE FROM score_bak