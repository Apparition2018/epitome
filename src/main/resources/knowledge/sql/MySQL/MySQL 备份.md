# MySQL 备份

---
## Reference
1. [Backup and Recovery](https://dev.mysql.com/doc/refman/8.0/en/backup-and-recovery.html)
2. [InnoDB Backup](https://dev.mysql.com/doc/refman/8.0/en/innodb-backup.html)
---
## 分类
1. 数据库是否运行
    - 冷备份（静态备份）：将数据库正常关闭，在停止状态下，将数据库文件全部备份（复制）下来
    - 热备份（动态备份）：利用备份软件，在数据库正常运行的状态下，将数据库中的数据文件备份出来
2. 备份量
    - 完全备份：备份所有数据
    - 差量备份：仅备份上一次完全备份之后变化的数据
    - 增量备份：备份上一次备份之后变化的数据
---
## 手动复制
- `SHOW GLOBAL VARIABLES LIKE '%datadir%';`
---
## [mysqldump](https://www.bilibili.com/video/BV1nm4y1z7p4/)
- 备份所有数据库：`mysqldump -uroot -pCesc123! --all-databases > backup.sql`
- 备份一部分数据库：`mysqldump -uroot -pCesc123! --databases epitome ry > backup.sql`
- 备份某个数据库中的某些表：`mysqldump -uroot -pCesc123! epitome dept emp > backup.sql`
- 恢复数据：登录 mysql → `source backup.sql`
---
## [crontab](https://www.bilibili.com/video/BV1ei4y1S7za/)
1. 创建 shell 脚本：`vim /etc/mysql-backup.sh`
    ```shell
    today=`/bin/date + "%Y-%m-%d"`
    /usr/bin/mysqldump --login-path=bkpuse_local --all-databases --events --routines --triggers --lock-all-tables --flush-logs --flush-privileges --add-drop-database --master-data=2 > /mysqlbackup/mysql-all-$today.sql
    ```
2. 使用 chmod 命令赋予 /etc/mysql-backup.sh 脚本执行权限：`chmod 755 /etc/mysql-backup.sh`
3. 使用 crontab -e 命令添加周期备份任务：`crontab -e 0 23 * * * /bin/bash /etc/mysql-backup.sh`
4. 使用 crontab -l 命令查看周期备份任务：`crontabl -l`
---
## [backup-x](https://github.com/jeessy2/backup-x)
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
## GUI
- DBeaver：数据库 → 工具 → 转储数据库 (Dump database)
- Navicat
    - 自动运行 → 新建批处理作业 → 备份 → 选择数据库 → 双击可用的工作 → 保存
    - 设置任务计划 → 触发器 → 新建…
---