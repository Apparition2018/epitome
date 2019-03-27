-- 过程：https://www.techonthenet.com/sql_server/procedures.php
-- 命名规范：https://www.cnblogs.com/taoys/archive/2010/11/18/1880822.html
DROP PROC FindSite
GO

CREATE PROC FindSite
		@site_name VARCHAR(50) OUT
AS
	BEGIN
		DECLARE @site_id INT;
		SET @site_id = 8;
		IF @site_id < 10
			SET @site_name = 'TechOnTheNet.com';
		ELSE
			SET @site_name = 'CheckYourMath.com';
	END;
GO

DECLARE @site_name varchar(50);
EXEC FindSite @site_name OUT;
PRINT @site_name;
GO