-- https://www.cnblogs.com/zt528/p/5386516.html
-- https://blog.csdn.net/stupidbird003/article/details/64562683

/********** SQL Server **********/
DROP TABLE score
-- 创建表
CREATE TABLE [dbo].[score](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](20) NULL,
	[course] [varchar](10) NULL,
	[score] [int] NULL,
 CONSTRAINT [PK_score] PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

DROP TABLE score
-- 创建表
-- IDENTITY(1,1) 从1开始每次增加1
CREATE TABLE score (
	id INT IDENTITY(1,1) PRIMARY KEY,
	name VARCHAR(20),
	course VARCHAR(10),
	score INT
)

-- 添加加注释，通过增加扩展属性来实现
-- 格式：EXEC sys.sp_addextendedproperty '扩展属性名称', '注释', 'user', 'dbo', 'table' , '表名' , 'column', '字段名'
-- sp_addextendedproperty		添加注释
-- sp_updateextendedproperty	修改注释
-- sp_dropextendedproperty		删除注释
EXEC sys.sp_addextendedproperty 'MS_Description', '姓名', 'user', 'dbo', 'table', 'score', 'column', 'name'
EXEC sys.sp_addextendedproperty 'MS_Description', '课程', 'user', 'dbo', 'table', 'score', 'column', 'course'
EXEC sys.sp_addextendedproperty 'MS_Description', '成绩', 'user', 'dbo', 'table', 'score', 'column', 'score'
EXEC sys.sp_addextendedproperty 'MS_Description', '成绩表', 'user', 'dbo', 'table', 'score', null, null

-- 查看注释
SELECT st.name AS table_name, sc.name AS column_name, sep.value AS column_description
FROM sys.tables st
	INNER JOIN sys.columns sc ON st.object_id = sc.object_id
	LEFT JOIN sys.extended_properties sep ON sc.object_id = sep.major_id AND sc.column_id = sep.minor_id
WHERE st.name = 'score'
GO

SELECT * FROM sys.extended_properties WHERE major_id = object_id('score')
GO

/********** Oracle **********/
-- Oracle 没有实现自增，请用 SEQUENCE 实现
/*
-- 创建表
CREATE TABLE score (
	id NUMBER(10) PRIMARY KEY,
	name VARCHAR2(20),
	course VARCHAR2(10),
	score NUMBER(3)
)

-- 添加注释
comment on column score.name is '姓名';
comment on table score is '成绩表'
*/
GO

--/********** MySQL **********/
/*
-- 创建表
CREATE TABLE score (
	id int auto_increment,
	name VARCHAR(20) comment '姓名',
	course VARCHAR(10) comment '课程',
	score int comment '成绩'
	PRIMARY KEY (id)
)

-- 添加表注释
alert table score comment = '成绩表'
*/
GO

SELECT * FROM score