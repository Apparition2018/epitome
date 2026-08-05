数据定义语言（DDL） ：Data Definition Language
  创建表
    CREATE TABLE [schema.]table_name (
        column_name datatype[DEFAULT expr][,…]
    );
    DESC emp;                     -- DESC
    gender CHAR(1) DEFAULT 'm',   -- DEFAULT

  修改表
    RENAME TABLE old_name TO new_name;
    ALTER TABLE table_name ADD(column datatype [DEFAULT expr] [, column datatype…]);    -- 增加列
    ALTER TABLE table_name DROP(column);                                                -- 删除列
    ALTER TABLE table_name MODIFY(column datatype [DEFAULT expr] [, column datatype…]); -- 修改列

  删除表
    DROP TABLE table_name;

数据操纵语言（DML） ：Data Manipulation Language
  INSERT 语句
    INSERT INTO table_name[(column[, column…])] VALUES(value[, value…]);

  UPDATE 语句
    UPDATE table_name SET column = value [, column = value]…[WHERE condition];

  DELETE 语句
    DELETE [FROM] table_name [WHERE condition];

  字符串操作
    字符串类型
      CHAR(10)：     定长，最大2000字节，不指定长度默认为1字节
      VARCHAR2(10)： 变长，最大4000字节，必须指定长度
      LONG:          VARCHAR2加长版，最大2G，
      CLOB:          定长或变长，最大4G

    字符串函数
      SELECT CONCAT(CONCAT(ename,':'),sal) sal FROM emp;                                  -- CONCAT(char1, char2)
      SELECT ename||':'||sal sal FROM emp;                                                -- ||
      SELECT ename,LENGTH(ename) FROM emp;                                                -- LENGTH
      SELECT UPPER('hello world'),LOWER('HELLO WORLD'),INITCAP('HELLO WORLD') FROM dual;  -- UPPER(charstream) / LOWER(charstream) / INITCAP(charstream)
      SELECT TRIM('e' from 'eslitse') AS t1,                                              -- TRIM(c2 FROM c1)
             LTRIM('eslitse','es') AS t2,                                                 -- LTRIM(c1[, c2])
             RTRIM('eslitse','es') AS t3 FROM dual;                                       -- RTRIM(c1[, c2])
      SELECT RPAD(ename,10,' ') ename FROM emp;                                           -- RPAD(char1, n, char2)
      SELECT LPAD(ename,10,' ') ename FROM emp;                                           -- LPAD(char1, n, char2)
      SELECT SUBSTR('thinking in java',-3,2) word FROM dual;                              -- SUBSTR(charstream, [m[, n]])
      SELECT INSTR('thinking in java','in',4,2) charat FROM dual;                         -- INSTR(char1, char2[, n [, m]])

  数值操作
    数值类型
      NUMBER(p,s) DECIMAL(p,s)  DEC(p,s)  INTEGER   INT
      SMALLINT    FLOAT(b)      DOUBLE PRECISION    REAL

    数值函数
      SELECT ROUND(45.678,2) round FROM dual;   -- ROUND
      SELECT ROUND(45.678,-1) round FROM dual;
      SELECT CEIL(45.678) ceil FROM dual;       -- CEIL
      SELECT FLOOR(45.678) floor FROM dual;     -- FLOOR
      SELECT TRUNC(45.678,2) TRUNC FROM dual;   -- TRUNC
      SELECT TRUNC(45.678,-1) TRUNC FROM dual;
      SELECT ename,sal,MOD(sal,1000) FROM emp;  -- MOD

    日期操作
      日期类型
        DATE：     7个字节；     世界、年、月、天、时、分、秒
        TIMESTAMP：7或11个字节； 世界、年、月、天、时、分、秒、纳秒；可指定0-9位，默认6位

      日期关键字
        SELECT SYSDATE FROM dual;                                       -- SYSDATE，DD-MON-RR
        SELECT TO_CHAR(SYSDATE,'yyyy-mm-dd day hh24:mi:ss') FROM dual;
        SELECT TO_CHAR(SYSTIMESTAMP,'ss.ff') FROM dual;                 -- ss.ff

      日期转换函数
        SELECT GREATEST(hiredate,TO_DATE('1982年01月01号','YYYY"年"MM"月"DD"号"')) FROM emp;   -- TO_DATE(charstream[, fmt[, nlsparams]])
        SELECT GREATEST(TO_CHAR(hiredate,'YYYY"年"MM"月"DD"号"'),'1982年01月01号') FROM emp;   -- TO_CHAR(date[, fmt[, nlsparams]])

      日期常用函数
        SELECT LAST_DAY(SYSDATE) FROM dual;                                           -- LAST_DAY(date)
        SELECT ADD_MONTHS(SYSDATE,1) FROM dual;                                       -- ADD_MONTHS(date, i)
        SELECT MONTHS_BETWEEN(SYSDATE,TO_DATE('1990-10-16','YYYY-MM-DD')) FROM dual;  -- MONTHS_BETWEEN(date1, date2)
        SELECT NEXT_DAY(SYSDATE,3) FROM dual;                                         -- NEXT_DAY(date, charstream)
        SELECT GREATEST(SYSDATE,hiredate) FROM emp;                                   -- GREATEST(expr1[, expr2[, expr3]]…)
        SELECT LEAST(TO_DATE('1982-01-01','YYYY-MM-DD'),hiredate) FROM emp;           -- LEAST(expr1[, expr2[, expr3]]…)
        SELECT EXTRACT(YEAR FROM SYSDATE) FROM dual;                                  -- EXTRACT(date FROM datetime)
        SELECT EXTRACT(HOUR FROM TIMESTAMP '2008-10-10 10:10:10') FROM dual;

    空值操作
      NULL与字符串连接等于什么也没干，NULL与数字运算，结果还是NULL
      SELECT * FROM emp WHERE comm IS NULL;                           -- IS NULL
      UPDATE * FROM student SET gender = NULL;                        -- == NULL
      gender CHAR(1) NOT NULL;                                        -- NOT NULL
      SELECT ename,sal,comm,sal+NVL(comm,0) salary FROM emp;          -- NVL(expr1, expr2)
      SELECT ename,sal,comm,NVL2(comm,sal+comm,sal) salary FROM emp;  -- NVL2(expr1, expr2, expr3)

事务控制语言（TCL） ：Transaction Control Language
  COMMIT
  ROLLBACK
  SAVEPOINT

数据控制语言（DCL） ：Data Control Language
  GRANT
  REVOKE
