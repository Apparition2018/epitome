-- 触发器：https://www.cnblogs.com/wangprince2017/p/7827091.html
DROP TABLE executor
GO

CREATE TABLE executor(
	id	BIGINT IDENTITY(1,1) PRIMARY KEY,
	task_id BIGINT,
	name NVARCHAR(20)
)
GO

INSERT INTO executor VALUES(1, 'e1')
INSERT INTO executor VALUES(1, 'e2')
INSERT INTO executor VALUES(2, 'e3')
INSERT INTO executor VALUES(3, 'e4')
INSERT INTO executor VALUES(4, 'e5')
GO

SELECT * FROM task
SELECT * FROM executor
GO

-- 删除触发器
DROP TRIGGER trig_task_delete
GO

-- 创建触发器
CREATE TRIGGER trig_task_delete
	ON task
	AFTER DELETE
AS
	DECLARE @deleteCount INT
IF @@NESTLEVEL > 10
	RETURN
BEGIN
	SELECT @deleteCount = COUNT(1) FROM deleted
	IF @deleteCount = 0
		RETURN
	DELETE FROM task WHERE parent_id IN (SELECT id FROM deleted)
	DELETE FROM executor WHERE task_id IN (SELECT id FROM deleted)
END
GO

DELETE FROM task WHERE id = 2
GO