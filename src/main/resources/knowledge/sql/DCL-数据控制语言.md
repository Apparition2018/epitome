# 数据控制语言 Data Control Language

---
## GRANT
```
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
## REVOKE
```
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