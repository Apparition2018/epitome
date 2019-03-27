select stuff('abcdef', 2, 3, 'ijklmn')

select * from score

select distinct name, score = stuff((select ',' + convert(nvarchar, score) from score s2 where s2.name = s1.name for xml path('')), 1, 1, '')
from score s1