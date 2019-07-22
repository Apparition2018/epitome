WITH cte AS
(
SELECT * FROM task WHERE id = '3'
UNION ALL
SELECT t2.* FROM task t2 INNER JOIN cte c ON c.parent_id = t2.id
)
SELECT * FROM cte ORDER BY id
go

select * from task
select * from executor