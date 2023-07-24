# XXL-JOB

---
## Reference
1. [分布式任务调度平台XXL-JOB](https://www.xuxueli.com/xxl-job/#%E3%80%8A%E5%88%86%E5%B8%83%E5%BC%8F%E4%BB%BB%E5%8A%A1%E8%B0%83%E5%BA%A6%E5%B9%B3%E5%8F%B0XXL-JOB%E3%80%8B)
2. [XXL-JOB分布式调度平台，90分钟带你上手加实战](https://www.bilibili.com/video/BV1QM411n75R/)
---
## [快速入门](https://www.xuxueli.com/xxl-job/#%E4%BA%8C%E3%80%81%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8)
1. 下载[项目源码](https://github.com/xuxueli/xxl-job)
2. 初始化”调度数据库“：/xxl-job/doc/db/tables_xxl_job.sql
3. IDEA 打开项目
    1. xxl-job-admin（调度中心）
        - application.properties：修改数据源 url 和 密码
        - 运行 XxlJobAdminApplication：http://localhost:8080/xxl-job-admin with admin/123456
    2. xxl-job-executor-samples（执行器示例）
        - 运行 XXlJobExecutorApplication 或 FramelessApplication
4. 执行器管理 → 新增执行器
    - AppName 对应 xxl.job.executor.appname 的值
5. 任务管理 → 新增任务
    - JobHandler 对应 @XxlJob() 的 value
---