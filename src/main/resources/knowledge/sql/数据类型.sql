-- SQL 常用数据类型：https://blog.csdn.net/lz199719/article/details/79803161
-- SQL Server 常用数据类型：https://www.cnblogs.com/mcgrady/p/3840812.html
-- SQL Server 与 Oracle 之前数据类型转换：https://www.cnblogs.com/JoeyWong/p/8660217.html
-- Oracle 中的 decimal 与 Number 区别：https://blog.csdn.net/freshlover/article/details/8809529

/********** Common **********/
-- CHAR(n)		定长，空间大，效率高
-- NCHAR(n)		定长，空间大，效率高
-- VARCHAR(n)	变长，空间小，效率低
-- NVARCHAR(n)	变长，空间小，效率低

/********** SQL Server **********/
-- SQL Server 中 char, varchar, nchar, nvarchar 的区别：https://www.cnblogs.com/limeiky/p/5313312.html
-- VARCHAR(n)	存储ANSI字符，1个英文1个字节，1个汉字2个字节；n表示字节长度，如VARCHAR(10)可存储10个英文或5个汉字，总字节长度不能超过10字节
-- NVARCHAR(n)	存储Unicode字符，所有字符都是2个字节；n表示字符数，如VARCHAR(10)可存储5个英文或5个汉字，总字节长度是存储字符数的2倍

GO

/********* Oracle **********/


GO


/********* MySQL **********/
GO