# 数据控制语言 Data Control Language

---
## 参考网站
1. [Database Administration Statements](https://dev.mysql.com/doc/refman/8.0/en/sql-server-administration-statements.html)
---
## [GRANT](https://dev.mysql.com/doc/refman/8.0/en/grant.html)
```sql
GRANT 
    SELECT, 
    INSERT, 
    UPDATE, 
    DELETE,
    REFERENCES,
    ALTER,
    ALL
ON 
    employees 
TO 
    smithj;
```
---
## [REVOKE](https://dev.mysql.com/doc/refman/8.0/en/revoke.html)
```sql
REVOKE
    SELECT, 
    INSERT, 
    UPDATE, 
    DELETE,
    REFERENCES,
    ALTER,
    ALL
FROM 
    employees 
TO 
    smithj;
```