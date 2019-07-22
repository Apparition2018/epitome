-- IF...ELSE
DECLARE @site_value INT;
SET @site_value =15;
IF @site_value < 25
	PRINT 'TechOnTheNet.com';
ELSE
BEGIN
	IF @site_value < 50
		PRINT 'CheckYourMath.com';
	ELSE
		PRINT 'BigActivities.com';
END;
GO

-- WHILE LOOP
DECLARE @site_value INT;
SET @site_value = 0;
WHILE @site_value <= 10
BEGIN
	PRINT 'Inside WHILE LOOP on TechOnTheNet.com';
	SET @site_value = @site_value + 1;
END;
PRINT 'Done WHILE LOOP on TechOnTheNet.com';
GO

-- FOR LOOP
DECLARE @cnt INT = 0;
WHILE @cnt < 10
BEGIN
	PRINT 'Inside simulated FOR LOOP on TechOnTheNet.com';
	SET @cnt = @cnt + 1;
END;
PRINT 'Done WHILE LOOP on TechOnTheNet.com';
GO

-- BREAK
DECLARE @site_value INT;
SET @site_value = 0;
WHILE @site_value <= 10
BEGIN
	IF @site_value = 2
		BREAK;
	ELSE
		PRINT 'Inside WHILE LOOP on TechOnTheNet.com';
	SET @site_value = @site_value + 1;
END;
PRINT 'Done WHILE LOOP on TechOnTheNet.com';
GO

-- CONTINUE
DECLARE @site_value INT;
SET @site_value = 0;
WHILE @site_value <= 10
BEGIN
	IF @site_value = 2
		BREAK;
	ELSE
	BEGIN
		SET @site_value = @site_value + 1;
		PRINT 'Inside WHILE LOOP on TechOnTheNet.com';
		CONTINUE;
	END;
END;
PRINT 'Done WHILE LOOP on TechOnTheNet.com';
GO

-- GOTO
DECLARE @site_value INT;
SET @Site_value = 0;
WHILE @site_value <= 10
BEGIN
	IF @site_value = 2
		GOTO TechOnTheNet;
	SET @site_value = @site_value + 1;
END;
TechOnTheNet:
	PRINT 'TechOnTheNet.com';
--GO