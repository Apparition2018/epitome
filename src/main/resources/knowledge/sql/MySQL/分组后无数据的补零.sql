-- 1) UNION ALL
SELECT post_name, sum(cnt) cnt
FROM (SELECT post_name, count(*) cnt
	FROM sys_user u, sys_user_post up, sys_post p
	WHERE u.user_id = up.user_id AND up.post_id = p.post_id
	GROUP BY post_name
    UNION ALL
	SELECT post_name, 0 AS cnt
	FROM sys_post) AS temp
GROUP BY post_name;

-- 2) LEFT JOIN
SELECT p.post_name, ifnull(temp.cnt, 0)
FROM sys_post p
    LEFT JOIN (SELECT post_name, count(*) cnt
            FROM sys_user u, sys_user_post up, sys_post p
	        WHERE u.user_id = up.user_id  AND up.post_id = p.post_id
	        GROUP BY post_name) AS temp ON p.post_name = temp.post_name;

-- 3)
SELECT
	count( CASE WHEN p.post_name = '董事长' THEN 1 END ) AS '董事长',
	count( CASE WHEN p.post_name = '项目经理' THEN 1 END ) AS '项目经理',
	count( CASE WHEN p.post_name = '人力资源' THEN 1 END ) AS '人力资源',
	count( CASE WHEN p.post_name = '普通员工' THEN 1 END ) AS '普通员工' 
FROM sys_user u, sys_user_post up, sys_post p
WHERE u.user_id = up.user_id AND up.post_id = p.post_id;