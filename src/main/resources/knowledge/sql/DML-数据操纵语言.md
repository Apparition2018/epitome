# 数据操纵语言 Data Manipulation Language
---
## INSERT
```
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
>### MySQL
>```
>INSERT IGNORE INTO                         如果插入数据主键重复，则忽略插入数据
>```
---
## UPDATE
```
UPDATE score SET name = '王五', course = '语文', score = 81 WHERE id = 1;
```
---
## DELETE
```
DELETE FROM score WHERE id = 1;
```