# MySQL 备份

---
## Reference
1. [Backup and Recovery](https://dev.mysql.com/doc/refman/8.2/en/backup-and-recovery.html)
2. [InnoDB Backup](https://dev.mysql.com/doc/refman/8.2/en/innodb-backup.html)
---
## 分类
1. 数据库是否运行
    - 冷备份（静态备份/物理备份）：将数据库正常关闭，将数据库文件全部备份（复制）下来
    - 热备份（动态备份/逻辑备份）：利用备份软件，在数据库正常运行的状态下，将数据库中的数据文件备份出来
2. 备份量
    - 全量备份：备份所有数据
    - 差量备份：仅备份上一次完全备份之后变化的数据
    - 增量备份：备份上一次备份之后变化的数据
---
## 手动复制
- `SHOW GLOBAL VARIABLES LIKE '%datadir%';`
---
## [crontab](https://www.bilibili.com/video/BV1ei4y1S7za/) + mysqldump
- @see MySQL 程序.md#mysqldump
1. 创建 shell 脚本：`vim /etc/mysql-backup.sh`
    ```shell
    today=`/bin/date +%Y-%m-%d`
    /usr/bin/mysqldump --login-path=bak_local --all-databases --routines --triggers --events --single-transaction --master-data=2 --flush-logs --flush-privileges --add-drop-database > /home/mysql-all-$today.sql
    ```
2. 使用 chmod 命令赋予 /etc/mysql-backup.sh 脚本执行权限：`chmod 755 /etc/mysql-backup.sh`
3. 添加周期备份任务：`crontab -e` → `0 23 * * * /bin/bash /etc/mysql-backup.sh`
4. 查看周期备份任务：`crontab -l`
---
## [增量备份恢复](https://www.bilibili.com/video/BV14L41157yi/)
1. 开启 binlog
2. 全量备份：`mysqldump -uroot -p --all-databases --routines --triggers --events --single-transaction --master-data=2 --flush-logs > full_bak_2023-08-04.sql`
3. 任何 DML 操作
4. 刷新日志：`mysqladmin -uroot -p flush-logs`。这里假设生成了 binlog.000003 日志文件，那么 第3步的 DML 操作即记录在 binlog.000002 
    ```mysql
    -- 二进制日志文件相关命令
    SHOW BINARY LOGS;   -- 列出服务器二进制日志文件
    SHOW MASTER STATUS; -- 提供有关源服务器的二进制日志文件的状态信息
    ```
5. 全量备份恢复：`mysql -uroot -p < full_bak_2023-08-04.sql`
6. 增量备份恢复：`mysqlbinlog ${mysql_home}/data/binlog.000002 | mysql -uroot -p`
---
## [基于日志位置恢复](https://www.bilibili.com/video/BV1Ta41167zy/)
1. 查看某时间段的日志：`mysqlbinlog --start-datetime="2023-08-04 10:15" --stop-datetime="2023-8-04 10:18" --verbose binlog.000004`
    - 记下某操作的 pos 点：2035990
2. 基于日志位置恢复：`mysqLbilog --stop-position=2035990 binlog.000004 | mysql -uroot -p`
---
## 其它工具
- DBeaver：数据库 → 工具 → 转储数据库 (Dump database)
- Navicat
    - 自动运行 → 新建批处理作业 → 备份 → 选择数据库 → 双击可用的工作 → 保存
    - 设置任务计划 → 触发器 → 新建…
- [backup-x](https://github.com/jeessy2/backup-x)
    ```bash
    mkdir -p /home/lighthouse/docker_data/backup-x
    cd /home/lighthouse/docker_data/backup-x
    ```
    ```bash
    docker run -d --name backup-x -p 9977:9977 --restart=always \
    -v $PWD:/app/backup-x-files \
    jeessy/backup-x
    ```
---