SELECT * FROM score

-- 增加字段
ALTER TABLE score ADD course2 NVARCHAR(20)
GO

-- 删除字段
ALTER TABLE score DROP COLUMN course2
GO

/********** SQL Server **********/
-- 重命名表
EXEC sp_rename score, score

-- 修改字段类型
ALTER TABLE score ALTER COLUMN score INT
GO

/********* Oracle **********/
-- 重命名表
-- RENAME score TO score
GO

/********* MySQL **********/
GO