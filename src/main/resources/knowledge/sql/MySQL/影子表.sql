DROP TABLE IF EXISTS user_new, user_old;
CREATE TABLE user_new LIKE user;
RENAME TABLE user TO user_old, user_new TO user;