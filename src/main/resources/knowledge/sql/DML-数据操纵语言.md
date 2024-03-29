# 数据操纵语言 Data Manipulation Language

---
## 参考网站
1. [Data Manipulation Statements](https://dev.mysql.com/doc/refman/8.2/en/sql-data-manipulation-statements.html)
---
## INSERT
```sql
INSERT INTO score (name, course, score) VALUES ('王五', '语文', 81);
INSERT INTO score (name, course, score) VALUES ('王五', '数学', 100);
INSERT INTO score (name, course, score) VALUES
('王五', '英语', 90),
('张三', '语文', 81),
('张三', '数学', 75),
('张三', '英语', null),
('李四', '语文', 76),
('李四', '数学', 90);
```
1. MySQL
- `INSERT IGNORE INTO`：如果插入数据主键重复，则忽略插入数据
- `INSERT INTO ... ON DUPLICATE KEY UPDATE`：如果插入数据主键重复，则更新数据
```mysql
INSERT INTO score (id, name, course, score) VALUES (1, '王五', '语文', 81)
ON DUPLICATE KEY UPDATE id = 1, name = '王五', course = '语文', score = 81;
```
---
## UPDATE
```mysql
UPDATE score SET name = '王五', course = '语文', score = 81 WHERE id = 1;
```
---
## DELETE
```mysql
DELETE FROM score WHERE id = 1;
```
---