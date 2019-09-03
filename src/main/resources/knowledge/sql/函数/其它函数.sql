-- CASE
SELECT name, course, CASE WHEN score >= 60 THEN '及格' WHEN score < 60 THEN '不及格' END FROM score
GO


/********** SQL Server **********/
-- ISNULL
SELECT name, course, ISNULL(score, 0) FROM score

ISDATE
ISNUMERIC
GO


/********** Oracle **********/
/*
-- MD5
SELECT MD5('abc') -- '900150983cd24fb0d6963f7d28e17f72'

-- NVL
NVL( string1, replace_with )

-- NVL2
NVL2( string1, value_if_not_null, value_if_null )
*/
GO

/********** MySQL **********/
/*
-- ISNULL
SELECT ISNULL('')	-- 0
SELECT ISNULL(NULL)	-- 1
SELECT ISNULL(28)	-- 0
*/
GO