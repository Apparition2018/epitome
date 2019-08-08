select * from score

/*
1）	charindex: 返回字符串中指定表达式的起始位置
	patindex: 同上，但可以使用通配符。
*/
select * from score order by charindex(substring(name, 2, 1), '三四五'), score desc
select * from score order by patindex('%' + name + '%', '张三李四王五'), score desc


/*
2）	case when then else end
*/
select * from score order by (case name when '张三' then 0 when '李四' then 1 when '王五' then 2 else '' end), case when score is null then 1 else 0 end, score desc

/*
3)	decode (Oracle)
*/
