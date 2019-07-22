SELECT name, AVG(score) avg_score FROM score GROUP BY name HAVING AVG(score) < 80
GO

SELECT * FROM score