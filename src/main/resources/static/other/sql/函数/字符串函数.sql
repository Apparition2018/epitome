-- ASCII
SELECT ASCII('a') -- 97

-- CONCAT
SELECT CONCAT('water', 'melon')

-- LOWER/UPPER
SELECT LOWER('A') -- a
SELECT UPPER('a') -- A

-- LTRIM/RTRIM
-- SQL Server 只能有一个参数，Oracle 和 MySQL 可以有2个参数
SELECT LTRIM(' a ') -- a

-- REPLACE
SELECT REPLACE('123tech123', '123', '') -- tech
GO


/********** SQL Server **********/
-- CHAR: 将整数 ASCII 代码转换为字符
SELECT CHAR(97) -- a

-- CHARINDEX: 返回字符串中指定表达式的开始位置
SELECT CHARINDEX('a', 'abc') -- 1
SELECT CHARINDEX('b', 'abc') -- 2
SELECT CHARINDEX('c', 'abc') -- 3

-- PATINDEX: 返回指定表达式中模式第一次出现的开始位置
SELECT PATINDEX('%a%', 'abc') -- 1
SELECT PATINDEX('%b%', 'abc') -- 2
SELECT PATINDEX('%c%', 'abc') -- 3

-- LEFT/RIGHT
SELECT LEFT('watermelon', 5)  -- water
SELECT RIGHT('watermelon', 5) -- melon

-- LEN
SELECT LEN('watermelon') -- 10

-- NCHAR，返回具有给定的整数代码的 unicode 字符
SELECT NCHAR('97') -- a

-- REVERSE
SELECT REVERSE('123')

-- SPACE，返回由重复空格组成的字符串
SELECT SPACE(7) -- '       '

-- STR，返回从数值表达式转换而来的字符表达式
SELECT STR(123.456, 7, 2) -- 123.46
SELECT STR(123.456, 6, 2) -- 123.46
SELECT STR(123.456, 5, 2) -- 123.5
SELECT STR(123.456, 4, 2) --  123
SELECT STR(123.456, 3, 2) -- 123

-- STUFF，删除指定长度的字符，并在指定的起点处插入另一组字符
SELECT STUFF('This is a Dog', 11, 3, 'Cat') -- This is a Cat

-- SUBSTRING
SELECT SUBSTRING('This is a Dog', 11, 3) -- Dog
GO

/********** Oracle **********/
/*
-- TO_CHAR
SELECT TO_CHAR(97) -- a

-- ||
SELECT ('water' || 'melon') -- watermelon

-- INITCAP
SELECT INITCAP('water') -- Water

-- INSTR
SELECT INSTR('This is a Dog', 'i', 3, 2) -- 6

-- LENGTH
SELECT LENGTH('watermelon') -- 10

-- LPAD/RPAD
SELECT RPAD('760' ,4, '0') -- 0760

-- TRIM
SELECT TRIM(LEADING '0' FROM '000Tech')		-- Tech
SELECT TRIM(TRAILING '1' FROM 'Tech1')		-- Tech
SELECT TRIM(BOTH '123' FROM '123Tech123')	-- Tech

-- SUBSTR
SELECT SUBSTR('This is a Dog', 11, 3) -- Dog
*/
GO


/********** MYSQL **********/
/*
-- CONCAT_WS
SELECT CONCAT_WS(',', 1, 2, 3, 4) -- 1,2,3,4

-- INSTR
SELECT INSTR('This is a Dog', 'i', 3, 2) -- 6

-- LEFT/RIGHT
SELECT LEFT('watermelon', 5)  -- water
SELECT RIGHT('watermelon', 5) -- melon

-- LENGTH
SELECT LENGTH('watermelon') -- 10

-- LPAD/RPAD
SELECT RPAD('760' ,4, '0') -- 0760

-- TRIM
SELECT TRIM(LEADING '0' FROM '000Tech')		-- Tech
SELECT TRIM(TRAILING '1' FROM 'Tech1')		-- Tech
SELECT TRIM(BOTH '123' FROM '123Tech123')	-- Tech

-- REVERSE
SELECT REVERSE('123')

-- SPACE
SELECT SPACE(7) -- '       '

-- SUBSTR/SUBSTRING
SELECT SUBSTR('This is a Dog', 11, 3) -- Dog
*/
GO