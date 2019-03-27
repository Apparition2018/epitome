视图/虚表(VIEW) 
  简单视图：基于单表建立，不包含任何函数运算、表达式或分组函数;           可以通过DML操作影响到基表数据
  复杂视图：基于单表建立，包含了单行函数、表达式、分组函数或GROUP BY子句; 不允许DML操作
  连接试图：基于多表建立 
  -- suquery代表SELECT查询语句
    CREATE [OR REPLACE] VIEW view_name[(alias[, alias…])] AS subquery;
  -- CHECK OPTION约束:通过视图所做的修改，必须在视图的可见范围内
    CREATE VIEW v_emp_20 AS SELECT empno id,ename,sal,deptno FROM emp WHERE deptno=20 WITH CHECK OPTION;
  -- READ ONLY约束:不能被修改
    CREATE VIEW v_emp_20 AS SELECT empno id,ename,sal,deptno FROM emp WHERE deptno=20 WITH READ ONLY;
  -- 数据字典 USER_OBJECTS:查询所有视图名称
    SELECT object_name FROM user_objects WHERE object_type='VIEW';
  -- 数据字典 USER_VIEWS:查询指定视图的SELECT查询语句
    SELECT text FROM user_views WHERE view_name = 'V_EMP_20'; -- 必须大写
  -- 数据字典 USER_UPDATABLE_COLUMNS：查询视图
    SELECT column_name, insertable, updatable, deletable FROM user_updatable_columns WHERE table_name = 'V_EMP_10'; ???


序列(SEQUENCE)
  是独立的数据库对象，不依赖与表；为一个或多个表提供主键值
  
  CREATE SEQUENCE [schema.]sequence_name
    [ START WITH i ] [ INCREMENT BY j ]       -- 默认第一个值1，步进1
    [ MAXVALUE m | NOMAXVALUE ]               
    [ MINVALUE n | NOMINVALUE ]
    [ CYCLE | NOCYCLE ][ CACHE p | NOCACHE ]; -- CACHE：提高序列生成效率，默认20
  
  CREATE SEQUENCE seq_student_id START WITH 1003 INCREMENT BY 1;
  INSERT INTO student (id,name,gender) VALUES (seq_student_id.NEXTVAL,'變態輝','m'); -- NEXTVAL
  SELECT seq_student_id.CURRVAL FROM dual;                                          -- CURRVAL,必须先执行一次NEXTVAL


索引
  为了提高查询效率，是独立于表的对象。
  CREATE [UNIQUE] INDEX index_name ON table(column[, column…]);  
  CREATE INDEX idx_year_month_day ON sales(year_id,month_id,day_id);  -- 经常排序，可建复合索引 / 多列索引
  CREATE INDEX idx_emp_ename_upper ON emp(UPPER(ename));              -- 大小写无关搜索,可建基于UPPER函数的索引
  ALTER INDEX idx_year_month_day REBUILD;                             -- 经常执行DML操作，可重建索引
  创建原则： 
    1）WHERE 子句
    2）ORDER BY、DISTINCT，字段顺序一致
    3）表的连接条件
    4）不要在经常做DML操作的表建索引
    5）不要在小表上建立索引
    6）索引不是越多越好
    7）删除很少被使用的、不合理的索引
    
    
约束(CONSTRAINT)
  非空约束(Not Null)，简称NN
    CREATE TABLE employees (
    eid NUMBER(6),
    name VARCHAR2(30) NOT NULL,
    salary NUMBER(7, 2),
    hiredate DATE CONSTRAINT employees_hiredate_nn NOT NULL
    );
  唯一性约束(Unique)，简称UK
    CREATE TABLE employees (
    eid NUMBER(6) UNIQUE,
    name VARCHAR2(30),
    email VARCHAR2(50),
    salary NUMBER(7, 2),
    hiredate DATE,
    CONSTRAINT employees_email_uk UNIQUE(email)
    );
    ALTER TABLE employees ADD CONSTRAINT employees_name_uk UNIQUE(name);      -- 建表后增加

  主键约束(Primary Key)，简称PK,相当于非空且唯一
  主键字段可以是单字段或多字段组合;
  从表上定义的外键的列值，必须从主表被参照的列值中选取，或者为NULL；
  当主表参照列的值被从表参照时，主表的该行记录不允许被删除;
    选取原则：
      主键应是对系统无意义的数据
      永远也不要更新主键，让主键除了唯一标识一行之外，再无其他的用途
      主键不应包含动态变化的数据，如时间戳
      主键应自动生成，不要人为干预，以免使它带有除了唯一标识一行以外的意义
      主键尽量建立在单列上

      CREATE TABLE employees (
      eid NUMBER(6) PRIMARY KEY,
      name VARCHAR2(30),
      email VARCHAR2(50),
      salary NUMBER(7, 2),
      hiredate DATE
      );
      ALTER TABLE employees ADD CONSTRAINT employees_eid_pk PRIMARY KEY (eid);  -- 建表后增加
  
  外键约束(Foreign Key)，简称FK
    ALTER TABLE employees ADD CONSTRAINT employees_deptno_fk FOREIGN KEY (deptno) REFERENCES dept(deptno); -- 只能建表后增加
    
  检查约束(Check)，简称CK
    ALTER TABLE employees ADD CONSTRAINT employees_salary_check CHECK (salary > 2000);
  
  
  
 
  
  
  

  