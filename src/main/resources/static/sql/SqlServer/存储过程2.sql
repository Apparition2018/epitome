DROP TABLE task
GO

CREATE TABLE [dbo].[task](
	[id] [bigint] IDENTITY(1,1) PRIMARY KEY,
	[parent_id] [bigint],
	[grand_id] [bigint],
	[task_name] [nvarchar](50),
	[level_] [int]
)
GO

INSERT INTO task VALUES('', 0, '任务1', 1)
INSERT INTO task VALUES(1, 0, '任务2', 2)
INSERT INTO task VALUES(2, 0, '任务3', 3)
INSERT INTO task VALUES(3, 0, '任务4', 4)
GO

SELECT * FROM task
GO

-- 删除过程
DROP PROC proc_TaskByGrandIdUpdate
GO

-- 创建过程
CREATE PROC proc_TaskByGrandIdUpdate
AS
	BEGIN
		DECLARE @temp TABLE(
			id BIGINT,
			grand_id BIGINT
		)
		INSERT INTO @temp(id, grand_id) SELECT id, grand_id FROM task
		DECLARE @id BIGINT, @grand_id BIGINT
		WHILE EXISTS(SELECT id FROM @temp)
			BEGIN
				SET ROWCOUNT 1
				SELECT @id = id FROM @temp;
				WITH cte AS(
					SELECT id, parent_id, grand_id, level_ FROM task WHERE id = @id
					UNION ALL
					SELECT t.id, t.parent_id, t.grand_id, t.level_ FROM task t INNER JOIN cte c ON c.parent_id = t.id
				) SELECT @grand_id = id FROM cte WHERE level_ = 1
				UPDATE task SET grand_id = @grand_id WHERE id = @id
				SET ROWCOUNT 0

				DELETE FROM @temp WHERE id = @id
			END
	END
GO

-- 执行过程
EXEC proc_TaskByGrandIdUpdate
GO