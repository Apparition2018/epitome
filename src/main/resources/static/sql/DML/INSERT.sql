INSERT INTO score (name, course, score) VALUES ('王五', '语文', 81)
INSERT INTO score (name, course, score) VALUES ('王五', '数学', 100)
INSERT INTO score VALUES ('王五', '英语', 90)
INSERT INTO score VALUES ('张三', '语文', 81)
INSERT INTO score VALUES ('张三', '数学', 75)
INSERT INTO score VALUES ('张三', '英语', null)
INSERT INTO score VALUES ('李四', '语文', 76)
INSERT INTO score VALUES ('李四', '数学', 90)

/********* MySQL **********/
-- insert ignore into 表示如果插入数据主键重复，则忽略插入数据
INSERT IGNORE INTO score VALUES ('王五', '英语', 90)