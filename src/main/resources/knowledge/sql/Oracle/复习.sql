create table student (
  sno varchar2(10) primary key,
  sname varchar2(20),
  sage number(2),
  ssex varchar2(5)
);

create table teacher (
  tno varchar2(10) primary key,
  tname varchar2(20)
);

create table course (
  cno varchar2(10),
  cname varchar2(20),
  tno varchar2(20),
  constraint pk_course primary key(cno, tno)
);

create table sc(
  sno varchar2(10),
  cno varchar2(10),
  score number(4, 2),
  constraint pk_sc primary key(sno, cno)
);

/*******初始化学生表的数据******/
insert into student values ('s001','张三',23,'男');
insert into student values ('s002','李四',23,'男');
insert into student values ('s003','吴鹏',25,'男');
insert into student values ('s004','琴沁',20,'女');
insert into student values ('s005','王丽',20,'女');
insert into student values ('s006','李波',21,'男');
insert into student values ('s007','刘玉',21,'男');
insert into student values ('s008','萧蓉',21,'女');
insert into student values ('s009','陈萧晓',23,'女');
insert into student values ('s010','陈美',22,'女');
/******************初始化教师表***********************/
insert into teacher values ('t001', '刘阳');
insert into teacher values ('t002', '谌燕');
insert into teacher values ('t003', '胡明星');
insert into teacher values ('t004', '刘翔');
/***************初始化课程表****************************/
insert into course values ('c001','J2SE','t002');
insert into course values ('c002','Java Web','t002');
insert into course values ('c003','SSH','t001');
insert into course values ('c004','Oracle','t001');
insert into course values ('c005','SQL SERVER 2005','t003');
insert into course values ('c006','C#','t003');
insert into course values ('c007','JavaScript','t002');
insert into course values ('c008','DIV+CSS','t001');
insert into course values ('c009','PHP','t003');
insert into course values ('c010','EJB3.0','t002');
/***************初始化成绩表***********************/
insert into sc values ('s001','c001',78.9);
insert into sc values ('s002','c001',80.9);
insert into sc values ('s003','c001',81.9);
insert into sc values ('s004','c001',60.9);
insert into sc values ('s001','c002',82.9);
insert into sc values ('s002','c002',72.9);
insert into sc values ('s003','c002',81.9);
insert into sc values ('s001','c003','59');


-- 1、查询“001”课程比“002”课程成绩高的所有学生的学号；
select a.sno, a.score c001_score, b.score c002_score
from (select sno, score from sc where cno = 'c001') a,
     (select sno, score from sc where cno = 'c002') b
where a.sno = b.sno and a.score > b.score;
     
-- 2、查询平均成绩大于60分的同学的学号和平均成绩；
select sno, avg(score) from sc group by sno having avg(score) > 60;

-- 3、查询所有同学的学号、姓名、选课数、总成绩；
select s.sno, s.sname, count(sc.cno), sum(score)
from student s left outer join sc on s.sno = sc.sno group by s.sno, s.sname order by sno;

-- 4、查询姓“刘”的老师的个数；
select count(tname) from teacher where tname like '刘%';

-- 5、查询没学过“谌燕”老师课的同学的学号、姓名；
select s.sno, s.sname from student s
where s.sno not in
(select distinct(sc.sno) from sc, course c, teacher t 
 where sc.cno = c.cno and c.tno = t.tno and t.tname = '谌燕');

-- 6、查询学过“001”并且也学过编号“002”课程的同学的学号、姓名； ???
select s.sno, s.sname from student s, sc where s.sno = sc.sno and sc.cno = 'c001' and exists
(select * from sc sc2 where sc.sno = sc2.sno and sc2.cno = 'c002');

(select s.sno, s.sname from student s, sc where s.sno = sc.sno and sc.cno = 'c001')
intersect
(select s.sno, s.sname from student s, sc where s.sno = sc.sno and sc.cno = 'c002');

-- 7、查询学过“谌燕”老师所教的所有课的同学的学号、姓名；
select s.sno, s.sname from student s where s.sno in
(select sc.sno from sc, course c, teacher t where sc.cno = c.cno and c.tno = t.tno and t.tname = '谌燕' group by sc.sno having count(sc.cno) = 
(select count(c.cno) from course c, teacher t where t.tno = c.tno and t.tname = '谌燕'));

-- 8、查询课程编号“002”的成绩比课程编号“001”课程低的所有同学的学号、姓名；
select t.sno, t.sname 
from (select s.sno, s.sname, sc.score, cno, (select score from sc sc2 where s.sno = sc2.sno and sc2.cno = 'c002') score2 
      from student s, sc where s.sno = sc.sno and sc.cno = 'c001') t
where score2 < score;

select s.sno, s.sname from student s 
where s.sno in (select a.sno from (select sno, score from sc where cno = 'c001') a,
                                  (select sno, score from sc where cno = 'c002') b 
                                  where a.sno = b.sno and a.score > b.score);

-- 9、查询所有课程成绩高于80分的同学的学号、姓名；
select sno, sname from student 
where sno not in (select s.sno from student s, sc where s.sno = sc.sno and sc.score < 80) 
and sno in (select sno from sc);
 
-- 10、查询没有学全所有课的同学的学号、姓名；
select s.sno, s.sname from student s, sc where s.sno = sc.sno group by s.sno, s.sname having count(sc.cno) < (select count(cno) from course);

-- 11、查询至少有一门课与学号为“1001”的同学所学相同的同学的学号和姓名；


-- 12、查询至少学过学号为“001”同学所有一门课的其他同学学号和姓名；


-- 13、把“SC”表中“叶平”老师教的课的成绩都更改为此课程的平均成绩；


-- 14、查询和“1002”号的同学学习的课程完全相同的其他同学学号和姓名；


-- 15、删除学习“叶平”老师课的SC表记录；


-- 16、向SC表中插入一些记录，这些记录要求符合以下条件：没有上过编号“003”课程的同学学号、2、号课的平均成绩；


-- 17、按平均成绩从高到低显示所有学生的“数据库”、“企业管理”、“英语”三门的课程成绩，按如下形式显示： 学生ID,,数据库,企业管理,英语,有效课程数,有效平均分


-- 18、查询各科成绩最高和最低的分：以如下形式显示：课程ID，最高分，最低分


-- 19、按各科平均成绩从低到高和及格率的百分数从高到低顺序


-- 20、查询如下课程平均成绩和及格率的百分数(用"1行"显示): 企业管理（001），马克思（002），OO&UML （003），数据库（004）


-- 21、查询不同老师所教不同课程平均分从高到低显示
  SELECT max(Z.T#) AS 教师ID,MAX(Z.Tname) AS 教师姓名,C.C# AS 课程ＩＤ,MAX(C.Cname) AS 课程名称,AVG(Score) AS 平均成绩
    FROM SC AS T,Course AS C ,Teacher AS Z 
    where T.C#=C.C# and C.T#=Z.T# 
  GROUP BY C.C#
  ORDER BY AVG(Score) DESC
-- 22、查询如下课程成绩第 3 名到第 6 名的学生成绩单：企业管理（001），马克思（002），UML （003），数据库（004）
    [学生ID],[学生姓名],企业管理,马克思,UML,数据库,平均成绩 
    SELECT  DISTINCT top 3 
      SC.S# As 学生学号, 
        Student.Sname AS 学生姓名 , 
      T1.score AS 企业管理, 
      T2.score AS 马克思, 
      T3.score AS UML, 
      T4.score AS 数据库, 
      ISNULL(T1.score,0) + ISNULL(T2.score,0) + ISNULL(T3.score,0) + ISNULL(T4.score,0) as 总分 
      FROM Student,SC  LEFT JOIN SC AS T1 
                      ON SC.S# = T1.S# AND T1.C# = '001' 
            LEFT JOIN SC AS T2 
                      ON SC.S# = T2.S# AND T2.C# = '002' 
            LEFT JOIN SC AS T3 
                      ON SC.S# = T3.S# AND T3.C# = '003' 
            LEFT JOIN SC AS T4 
                      ON SC.S# = T4.S# AND T4.C# = '004' 
      WHERE student.S#=SC.S# and 
      ISNULL(T1.score,0) + ISNULL(T2.score,0) + ISNULL(T3.score,0) + ISNULL(T4.score,0) 
      NOT IN 
      (SELECT 
            DISTINCT 
            TOP 15 WITH TIES 
            ISNULL(T1.score,0) + ISNULL(T2.score,0) + ISNULL(T3.score,0) + ISNULL(T4.score,0) 
      FROM sc 
            LEFT JOIN sc AS T1 
                      ON sc.S# = T1.S# AND T1.C# = 'k1' 
            LEFT JOIN sc AS T2 
                      ON sc.S# = T2.S# AND T2.C# = 'k2' 
            LEFT JOIN sc AS T3 
                      ON sc.S# = T3.S# AND T3.C# = 'k3' 
            LEFT JOIN sc AS T4 
                      ON sc.S# = T4.S# AND T4.C# = 'k4' 
      ORDER BY ISNULL(T1.score,0) + ISNULL(T2.score,0) + ISNULL(T3.score,0) + ISNULL(T4.score,0) DESC); 

-- 23、统计列印各科成绩,各分数段人数:课程ID,课程名称,[100-85],[85-70],[70-60],[ <60]
    SELECT SC.C# as 课程ID, Cname as 课程名称 
        ,SUM(CASE WHEN score BETWEEN 85 AND 100 THEN 1 ELSE 0 END) AS [100 - 85] 
        ,SUM(CASE WHEN score BETWEEN 70 AND 85 THEN 1 ELSE 0 END) AS [85 - 70] 
        ,SUM(CASE WHEN score BETWEEN 60 AND 70 THEN 1 ELSE 0 END) AS [70 - 60] 
        ,SUM(CASE WHEN score < 60 THEN 1 ELSE 0 END) AS [60 -] 
    FROM SC,Course 
    where SC.C#=Course.C# 
    GROUP BY SC.C#,Cname; 

-- 24、查询学生平均成绩及其名次
      SELECT 1+(SELECT COUNT( distinct 平均成绩) 
              FROM (SELECT S#,AVG(score) AS 平均成绩 
                      FROM SC 
                  GROUP BY S# 
                  ) AS T1 
            WHERE 平均成绩 > T2.平均成绩) as 名次, 
      S# as 学生学号,平均成绩 
    FROM (SELECT S#,AVG(score) 平均成绩 
            FROM SC 
        GROUP BY S# 
        ) AS T2 
    ORDER BY 平均成绩 desc; 
  
-- 25、查询各科成绩前三名的记录:(不考虑成绩并列情况)
      SELECT t1.S# as 学生ID,t1.C# as 课程ID,Score as 分数 
      FROM SC t1 
      WHERE score IN (SELECT TOP 3 score 
              FROM SC 
              WHERE t1.C#= C# 
            ORDER BY score DESC 
              ) 
      ORDER BY t1.C#; 
-- 26、查询每门课程被选修的学生数
  select c#,count(S#) from sc group by C#; 
-- 27、查询出只选修了一门课程的全部学生的学号和姓名
  select SC.S#,Student.Sname,count(C#) AS 选课数 
  from SC ,Student 
  where SC.S#=Student.S# group by SC.S# ,Student.Sname having count(C#)=1; 
-- 28、查询男生、女生人数
    Select count(Ssex) as 男生人数 from Student group by Ssex having Ssex='男'; 
    Select count(Ssex) as 女生人数 from Student group by Ssex having Ssex='女'； 
-- 29、查询姓“张”的学生名单
    SELECT Sname FROM Student WHERE Sname like '张%'; 
-- 30、查询同名同性学生名单，并统计同名人数
  select Sname,count(*) from Student group by Sname having  count(*)>1;; 
-- 31、1981年出生的学生名单(注：Student表中Sage列的类型是datetime)
    select Sname,  CONVERT(char (11),DATEPART(year,Sage)) as age 
    from student 
    where  CONVERT(char(11),DATEPART(year,Sage))='1981'; 
-- 32、查询每门课程的平均成绩，结果按平均成绩升序排列，平均成绩相同时，按课程号降序排列
    Select C#,Avg(score) from SC group by C# order by Avg(score),C# DESC ; 
-- 33、查询平均成绩大于85的所有学生的学号、姓名和平均成绩
    select Sname,SC.S# ,avg(score) 
    from Student,SC 
    where Student.S#=SC.S# group by SC.S#,Sname having    avg(score)>85; 
-- 34、查询课程名称为“数据库”，且分数低于60的学生姓名和分数
    Select Sname,isnull(score,0) 
    from Student,SC,Course 
    where SC.S#=Student.S# and SC.C#=Course.C# and  Course.Cname='数据库'and score <60; 
-- 35、查询所有学生的选课情况；
    SELECT SC.S#,SC.C#,Sname,Cname 
    FROM SC,Student,Course 
    where SC.S#=Student.S# and SC.C#=Course.C# ; 
-- 36、查询任何一门课程成绩在70分以上的姓名、课程名称和分数；
    SELECT  distinct student.S#,student.Sname,SC.C#,SC.score 
    FROM student,Sc 
    WHERE SC.score>=70 AND SC.S#=student.S#; 
-- 37、查询不及格的课程，并按课程号从大到小排列
    select c# from sc where scor e <60 order by C# ; 
-- 38、查询课程编号为003且课程成绩在80分以上的学生的学号和姓名；
    select SC.S#,Student.Sname from SC,Student where SC.S#=Student.S# and Score>80 and C#='003'; 
-- 39、求选了课程的学生人数
    select count(*) from sc; 
-- 40、查询选修“叶平”老师所授课程的学生中，成绩最高的学生姓名及其成绩
    select Student.Sname,score 
    from Student,SC,Course C,Teacher 
    where Student.S#=SC.S# and SC.C#=C.C# and C.T#=Teacher.T# and Teacher.Tname='叶平' and SC.score=(select max(score)from SC where C#=C.C# ); 
-- 41、查询各个课程及相应的选修人数
    select count(*) from sc group by C#; 
-- 42、查询不同课程成绩相同的学生的学号、课程号、学生成绩
  select distinct  A.S#,B.score from SC A  ,SC B where A.Score=B.Score and A.C# <>B.C# ;
-- 43、查询每门功成绩最好的前两名
    SELECT t1.S# as 学生ID,t1.C# as 课程ID,Score as 分数 
      FROM SC t1 
      WHERE score IN (SELECT TOP 2 score 
              FROM SC 
              WHERE t1.C#= C# 
            ORDER BY score DESC 
              ) 
      ORDER BY t1.C#; 
-- 44、统计每门课程的学生选修人数（超过10人的课程才统计）。要求输出课程号和选修人数，查询结果按人数降序排列，查询结果按人数降序排列，若人数相同，按课程号升序排列
    select  C# as 课程号,count(*) as 人数 
    from  sc  
    group  by  C# 
    order  by  count(*) desc,c#  
-- 45、检索至少选修两门课程的学生学号
    select  S#  
    from  sc  
    group  by  s# 
    having  count(*)  >  =  2 
-- 46、查询全部学生都选修的课程的课程号和课程名
    select  C#,Cname  
    from  Course  
    where  C#  in  (select  c#  from  sc group  by  c#)  
-- 47、查询没学过“叶平”老师讲授的任一门课程的学生姓名
    select Sname from Student where S# not in (select S# from Course,Teacher,SC where Course.T#=Teacher.T# and SC.C#=course.C# and Tname='叶平'); 
-- 48、查询两门以上不及格课程的同学的学号及其平均成绩
    select S#,avg(isnull(score,0)) from SC where S# in (select S# from SC where score <60 group by S# having count(*)>2)group by S#; 
-- 49、检索“004”课程分数小于60，按分数降序排列的同学学号
    select S# from SC where C#='004'and score <60 order by score desc; 
-- 50、删除“002”同学的“001”课程的成绩
delete from Sc where S#='002'and C#='001';




