DQL:数据查询语句
SELECT语句中必须包含至少两个子包，分别是SELECT子句与FROM子句。
SELECT子句用来指定要查询的字段，FROM子句用来指定数据来源的表。

SELECT中除了可以指定表中具体字段外，还可以指定一个函数或表达式。

查看每个员工的年薪
SELECT empno,ename,job,sal*12
FROM emp

SELECT语句中添加WHERE子句
查看工资高于2000的员工？
SELECT ename，sal，deptno
FROM emp
WHERE sal > 2000

查看10号部门员工？
SELECT ename，sal，deptno
FROM emp
WHERE deptno = 10

字符串函数
1：CONCAT(char1,char2)
连接字符串

SELECT CONCAT(ename，sal)
FROM emp

SELECT CONCAT(CONCAT(ename,':'),sal)
FROM emp

使用"||"可以方便的连接多个字符串
SELECT ename || ':' || sal
FROM emp

SMITH的工资是800所在部门20
SELECT ename||'的工资是'||sal||'所在部门'||deptno
FROM emp
WHERE ename = 'SMITH'

2:LENGTH(char)
获取指定字符串的长度

查看每个员工名字的字符个数？
SELECT ename,LENGTH(ename)
FROM emp

3:LOWER,UPPER,INITCAP
将字符串转换为全小写，全大写，首字母大写

dual：伪表
当查询的数据与任何表没有关系时，可以查询伪表，这样只会查询出一条记录

SELECT UPPER('helloworld'),
       LOWER('HELLOWORLD'),
       INITCAP('helloworld')
FROM dual

4:TRIM,LTRIM,RTRIM
去除字符串两端的指定内容

SELECT
  TRIM('e' FROM 'eseeliteee')
FROM
  dual

SELECT
  LTRIM('esfeaesfeesf','esf')
From
  dual

5:LPAD(char1,n,char2),RPAD
补位函数，将char1显示n位，若char1不足n位时左(右)补充若干个char2字符串达到位数

SELECT ename，sal，RPAD(sal,5,'$')
FROM emp

6:SUBSTR(char,m[,n])
截取char字符串，从m处开始，连续截取n个字符。
需要注意，数据库中下标都是从1开始的！！
n若不指定或指定的数字超过可以截取的实际字符长度时都是截取到当前字符串末尾。

SElECT
  SUBSTR('thinking in java' ,-7,2)
FROM
  dual

7:INSTR(char1, char2[,n[,m]])
查看char2在char1的位置
n和m不指定默认都是1
n表示从第几个字符开始查找
m表示查看第几次出现
SElECT
  INSTR('thinking in java' ,'in',4,3)
FROM
  dual

数字函数
1：ROUND(n,[,m])
保留n小数点后m位
m不写默认为0，即：保留到整数位
m若为负数，则是保留10位以上的数字
SELECT ROUND(45.678,2) FROM dual;
SELECT ROUND(45.678,0) FROM dual;
SELECT ROUND(45.678,-1) FROM dual;
SELECT ROUND(45.678,-2) FROM dual

2：TRUNC(n,[,m])
截取数字，参数意义与ROUND一致
SELECT TRUNC(45.678,2) FROM dual;
SELECT TRUNC(45.678,0) FROM dual;
SELECT TRUNC(45.678,-1) FROM dual

3:MOD(m,n)
m除以n求余数，n若为0则直接返回m
SELECT ename，sal，MOD（sal，1000）
FROM emp

4：CEIL,FLOOR
向上取整与向下取整
SELECT CEIL(45.678) FROM dual;
SELECT FLOOR(45.678) FROM dual;

日期类型相关函数：

日期相关关键字：
1：SYSDATE:返回一个表示当前系统时间的DATE值
2：SYSTIMESTAMP：返回当前时间的时间戳类型值

SELECT SYSDATE FROM dual;
SELECT SYSTIMESTAMP FROM dual

INSERT INTO emp
(ename,hiredate)
VALUES
('jack',SYSDATE)

日期转换函数
1：TO_DATE()
将给定字符串按照给定的日期格式解析为一个DATE值
日期格式字符串中不是字母或符号的其他字符串都需要使用双引号括起来

SELECT
  TO_DATE('1988年05月02日 21时15分22秒',
          'YYYY"年"MM"月"DD"日" HH24"时"MI"分"SS"秒"')
FROM
  dual

2:TO_CHAR函数
SELECT
  TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS')
FROM
  dual

RR与YY都是两位数字表示年，但是当使用TO_DATE函数将两位数字解析为实际日期时，
RR会自行判断实际，而YY不会。

SELECT
  TO_CHAR(
    TO_DATE('49-08-02','RR-MM-DD'),
            'YYYY-MM-DD')
FROM
  dual

日期可以进行计算
1：对一个日期加减一个数字，等同于计算加减天数
2：两个日期相减，差为相差的天数
日期之间比大小，越晚的越大。

查看100天以后是哪天？
SELECT SYSDATE+100 FROM dual

查看每个员工入职到今天为止共多少天
SELECT ename,SYSDATE-hiredate FROM emp

SELECT
  SYSDATE-TO_DATE('1990-10-16','YYYY-MM-DD')
FROM
  dual

日期函数：
1：LAST_DAY(date)
返回给定日期所在月的月底日期

查看当月月底？
SELECT LAST_DAY(SYSDATE)
FROM dual

2:ADD_MONTHS(date,i)
对指定日期加上指定月，若i为负数则是减去

查看每个员工的转正日期？
SELECT ename,hiredate,ADD_MONTHS(hiredate,3)
FROM emp

3:MONTHS_BETWEEN(date1,date2)
计算两个日期之间相差多少个月，计算方式使用date1-date2得到的
查看每个员工入职到今天为止共多少个月？
SELECT
  ename,MONTHS_BETWEEN(SYSDATE,hiredate)
FROM emp

SELECT
  MONTHS_BETWEEN(SYSDATE,TO_DATE('1990-10-16','YYYY-MM-DD'))
FROM dual

4:NEXT_DAY(date,i)
返回给定日期第二天开始算一周之内指定周几对应的日期

SELECT NEXT_DAY(SYSDATE,5)
FROM dual

5:LEAST、GREATEST
最小值与最大值，凡是可以比较大小的数据类型都可以使用这两个函数
对于日期当中，最大值即是最晚的日期，最小值即最早的日期
SELECT
  LEAST(SYSDATE,
    TO_DATE('2008-08-08',
            'YYYY-MM-DD'))
FROM dual

6:EXTRACT
提取一个日期中指定时间分量的值
SELECT
  EXTRACT(YEAR FROM SYSDATE)
FROM dual

查看1980年入职的员工？
SELECT ename,sal,hiredate
FROM emp
WHERE EXTRACT(YEAR FROM hiredate)=1980

空值操作：
1：插入NULL值
CREATE TABLE student
(id NUMBER(4), name CHAR(20), gender CHAR(1));
INSERT INTO student VALUES (1000, '李莫愁', 'F');
INSERT INTO student VALUES (1001, '林平子', NULL);
INSERT INTO student (id, name) VALUES (1002, '张无忌');

2：更新NULL值
UPDATE student
SET gender=NULL
WHERE id=1000

作为条件判断NULL值：
判断要使用IS NULL和IS NOT NUll，不能使用"="判断NULL。
DELETE FROM student
WHERE gender IS NOT NULL

NULL的运算
NULL与字符串连接等于什么也没干，NULL与数字运算，结果还是NULL

SELECT ename||NULL FROM emp

查看每个员工的收入（工资加绩效）
SELECT ename,sal,comm,sal+comm
FROM emp

空值函数
1：NVL(a1,a2)
当a1为NULL时，函数返回a2，否则返回a1自身，所以函数作用是将NULL值替换为指定值

SELECT ename,sal,comm,sal+NVL(comm,0)
FROM emp

2:NVL2(a1,a2,a3)
当a1不为NULL时，函数返回a2
当a1为NULL时，函数返回a3
查看每个员工的绩效，有绩效的则显示位“有绩效”，为NULL的则显示为“没有绩效”
SELECT ename,comm,NVL2(comm,'有绩效','没有绩效')
FROM emp

SELECT ename,sal,comm,sal+NVL2(comm,comm,0)
FROM emp

SELECT * FROM student
