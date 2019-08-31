-- 查看注释
SELECT st.name AS table_name, sc.name AS column_name, sep.value AS column_description
FROM sys.tables st
	INNER JOIN sys.columns sc ON st.object_id = sc.object_id
	LEFT JOIN sys.extended_properties sep ON sc.object_id = sep.major_id AND sc.column_id = sep.minor_id
WHERE st.name = 'score'
GO

SELECT * FROM sys.extended_properties WHERE major_id = object_id('score')
GO