-- https://sosuny.iteye.com/blog/891437

/********* SQL Server **********/
-- 局部临时表
--（#开头）仅当前连接可见，断开连接自动删除
CREATE TABLE #temp(
	num INT PRIMARY KEY,
	pname VARCHAR (20)
)
SELECT * FROM #temp

-- 全局临时表
--（##开头）对其它连接可见，当前连接和其他访问过它的连接都断开时自动删除
CREATE TABLE ##temp(
	num INT PRIMARY KEY,
	pname VARCHAR (20)
)
SELECT * FROM ##temp
GO

/********* Oracle **********/
GO

/********* MySQL **********/
GO