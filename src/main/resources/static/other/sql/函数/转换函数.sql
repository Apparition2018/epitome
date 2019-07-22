--CAST
SELECT CAST('2018-08-15' AS DATETIME)	-- 2018-08-15 00:00:00.000
SELECT CAST('1' AS VARCHAR)				-- 1
GO


/********** SQL Server **********/
-- CONVERT: https://www.techonthenet.com/sql_server/functions/convert.php
SELECT CONVERT(VARCHAR(10), GETDATE(), 120) -- 2018-12-10
SELECT CONVERT(INT, '12')					-- 12
SELECT CONVERT(VARCHAR(3), 12)				-- 12
GO


/********** Oracle **********/
/*
-- https://www.techonthenet.com/oracle/functions/index.php
-- TO_CHAR
-- TO_DATE
-- TO_NUMBER
...
*/
GO


/********** MySQL **********/
/*
-- CONVERT: https://www.techonthenet.com/mysql/functions/convert.php
SELECT CONVERT(SYSDATE(), DATE) -- 2018-08-15
*/
GO