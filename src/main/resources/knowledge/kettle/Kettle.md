# Kettle
- ETL：抽取(extract)、转换(transform)、加载(load)
---
## Reference
1. [Download Below Pentaho Community Edition](https://www.hitachivantara.com/en-us/products/pentaho-platform/data-integration-analytics/pentaho-community-edition.html)
2. [可视化 ETL 工具之 Kettle](https://www.bilibili.com/video/BV1u84y1p7qW/?p=85)
---
## 数据库
1. 数据库驱动：把 mysql 5.x 和 8.x 驱动复制到 D:\data-integration\lib
2. 主对象树 → 转换 → DB 连接
---
## 案例
### 案例一
1. 输入 → 文本文件输入
    1. 文件 → 文件或目录：[user.txt](user.txt)
    2. 内容
        - 文件类型：CSV
        - 分隔符：,
        - 编码方式：UTF-8
    3. 字段 → 获取字段信息
2. 输出 → Excel输出
    1. 文件 → 文件名：xxx\user
    2. 字段 → 获取字段 → 最小宽度
3. 保存：xxx\user → 启动
### 案例二
1. 输入 → Excel输入
    1. 文件
        - 引擎：Excel 2007 XLSX (Apache POI)
        - 文件或目录：[user.xls](user.xls)
    2. 工作表 → 获取工作表名称
    3. 字段 → 获取来自头部数据的字段… 
2. 输出 → 表输出
    1. 数据库连接
    2. 目标表：kettle → SQL → 执行
3. 保存：xxx\user-2 → 启动
### 案例三
1. 输入 → 表输入
    1. 数据库连接
    2. 获取数据库查询语句：kettle
2. 输出 → 表输出
    1. 数据库连接
    2. 目标表：kettle_2 → SQL → 执行
3. 保存：xxx\user-3 → 启动
---