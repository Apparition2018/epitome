# Oracle

---
## 常用语句
1. [创建表空间](https://www.techonthenet.com/oracle/tablespaces/index.php)
```oracle
CREATE TABLESPACE tablespace_name
-- 数据存放位置
DATAFILE 'filename'
-- 初始空间
SIZE 32m
-- 每次扩大空间
AUTOEXTEND ON NEXT 32m
-- 最大空间·
MAXSIZE 34359721984
-- 本地管理
EXTENT MANAGEMENT LOCAL;
```
2. 删除表空间
```
DROP TABLESPACE tablespace_name [INCLUDING CONTENTS and DATAFILES];
```
3. [创建用户](https://www.techonthenet.com/oracle/users/index.php)
```oracle
CREATE USER user_name IDENTIFIED BY password
-- 指定默认表空间
DEFAULT TABLESPACE tablespace_name;
```
4. 授权：`GRANT DBA TO user_name`
---
## 导入数据
```
imp user_name/password@127.0.0.1:1521/siniorcl file=D:\xxx.dmp show=n buffer=20480 ignore=n commit=y grants=y full=y log=imp.log
```
---
