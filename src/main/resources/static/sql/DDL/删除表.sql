SELECT * FROM score

/********** Common **********/
-- 删除表
DROP TABLE score

-- 删除数据库
DROP DATABASE app

-- TRUNCATE
TRUNCATE TABLE score
GO

/********** SQL Server **********/
-- 删除索引
DROP INDEX score.idx_id
GO

/********* Oracle **********/
-- 删除索引
-- DROP INDEX idx_id
GO

/********* MySQL **********/
-- 删除索引
-- ALTER TABLE score DROP INDEX idx_id
GO