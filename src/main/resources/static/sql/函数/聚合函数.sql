-- GROUP BY: 和合计函数一起使用
-- HAVING: WHERE 无法与合计函数一起使用

-- COUNT
SELECT COUNT(DISTINCT name) FROM score

-- AVG
SELECT course, AVG(score) FROM score GROUP BY course

-- MAX
SELECT course, MAX(score) FROM score GROUP BY course

-- MIN
SELECT course, MIN(score) FROM score GROUP BY course

-- SUM
SELECT name, SUM(score) FROM score GROUP BY name