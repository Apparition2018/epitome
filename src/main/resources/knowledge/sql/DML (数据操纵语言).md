# 数据操纵语言 Data Manipulation Language
---
## INSERT
```
INSERT INTO table_name 
VALUES (value1, value2, value3, ...);

INSERT INTO table_name (column1, column2, column3, ...) 
VALUES (value1, value2, value3, ...);
```
>### MySQL
>```
>INSERT IGNORE INTO             如果插入数据主键重复，则忽略插入数据
>```
---
## UPDATE
```
UPDATE score SET name = '王五', course = '语文', score = 81 WHERE id = 1
```
---
## DELETE
```
DELETE FROM score
```
