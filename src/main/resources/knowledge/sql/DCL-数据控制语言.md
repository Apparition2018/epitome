# 数据控制语言 Data Control Language

---
## Reference
1. [Database Administration Statements](https://dev.mysql.com/doc/refman/8.2/en/sql-server-administration-statements.html)
--- 
## [GRANT](https://dev.mysql.com/doc/refman/8.2/en/grant.html)
```sql
GRANT
    SELECT, INSERT, UPDATE, DELETE, REFERENCES, ALTER
ON epitome.score
TO ljh@localhost;
```
---
## [REVOKE](https://dev.mysql.com/doc/refman/8.2/en/revoke.html)
```sql
REVOKE
    SELECT, INSERT, UPDATE, DELETE, REFERENCES, ALTER
ON epitome.score
FROM ljh@localhost;
```