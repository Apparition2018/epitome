-- ACID
/*
原子性 - Atomicity，确保工作单元内的所有操作成功完成; 否则，事务在故障点处中止，并且先前的操作被回滚到它们的原先状态。
一致性 - Consistency，确保数据库在成功提交的事务后正确更改状态。
隔离性 - Isolation，事务之间是独立运行互不相关的。
持久性 - Durability，事务一旦被执行,即使系统故障,其结果依然有效。
*/
GO

/********** SQL Server **********/
-- 开启事务
BEGIN TRAN
SELECT * FROM score

INSERT INTO score VALUES ('李四', '英语', '85')
SELECT * FROM score

-- 保存事务
SAVE TRAN sp1
INSERT INTO score VALUES ('李四', '物理', '59')
SELECT * FROM score

-- 回滚事务
ROLLBACK TRAN sp1
SELECT * FROM score
ROLLBACK TRAN
SELECT * FROM score

-- 提交事务
COMMIT TRAN
GO