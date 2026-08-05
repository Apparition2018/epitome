# Quartz 定时任务调度使用指南

## 概述

[Quartz](https://www.quartz-scheduler.org/) 是 Java 生态最流行的**作业调度框架**，核心解决"某个任务（Job）在什么时间、按什么周期执行"。Spring Boot 通过 `spring-boot-starter-quartz` 集成，自动装配 `Scheduler`，兼容 Quartz 原生对象，开箱即用。

## 1. 依赖

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
```

## 2. 四大核心概念

| 概念         | 作用                                       | 类比    |
|------------|------------------------------------------|-------|
| **Job**    | 要执行的业务逻辑，实现 `org.quartz.Job` 的 `execute()` | 要做的事 |
| **JobDetail** | Job 的描述，绑定实现类、设置名称/分组、携带参数          | 任务单  |
| **Trigger** | 定义 Job 何时、多频繁执行                          | 闹钟   |
| **Scheduler** | 总管：注册 Job+Trigger、控制调度生命周期              | 调度中心 |

## 3. 触发方式总览

Quartz 提供两大类触发途径：

- **自动触发（核心）**：靠 Trigger 的时间计划到点自动执行，无需任何手动代码
- **手动触发（辅助）**：`scheduler.triggerJob(key)` 立即执行一次，多用于测试、即时补跑

> 自动触发是生产环境的主线，也是本项目（`QuartzConfig`）采用的方式。

---

## 4. 自动触发（声明式，启动即生效）

**核心：用 `@Bean` 声明 JobDetail + Trigger，Spring Boot 启动时自动注册进 Scheduler，到点自动执行。整个过程你只需要声明，不用写任何触发代码。**

### 4.1 定义 Job 实现类

实现 `Job` 接口，写业务逻辑；每次触发都会执行 `execute()`：

```java
@Component
public class ReportJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String name = context.getJobDetail().getKey().getName();
        String task = context.getMergedJobDataMap().getString("task");
        log.info("==> 执行 Quartz 任务 [{}], 业务参数 task={}", name, task);
    }
}
```

### 4.2 声明 JobDetail + Trigger（@Configuration）

```java
@Configuration
public class QuartzConfig {

    /** 步骤 1：定义任务。 */
    @Bean
    public JobDetailFactoryBean reportJobDetail() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(ReportJob.class);            // 绑定 Job 实现类
        factory.setName("reportJob");                    // 任务名
        factory.setGroup("report");                      // 分组
        factory.getJobDataMap().put("task", "generate-daily-report"); // 传参
        factory.setDurability(true);                     // 无 Trigger 时也保留
        return factory;
    }

    /** 步骤 2：定义触发规则（Cron），并注入 JobDetail 完成绑定。 */
    @Bean
    public CronTriggerFactoryBean reportCronTrigger(JobDetail reportJob) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(reportJob);
        factory.setName("reportCronTrigger");
        factory.setCronExpression("0/10 * * * * ?");     // 每 10 秒触发一次
        return factory;
    }
}
```

### 4.3 QuartzApplication 启动即生效

关键在于启动类 `QuartzApplication`：

```java
@SpringBootApplication
public class QuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
```

`@SpringBootApplication` 会**自动扫描** `QuartzConfig`（`@Configuration`）和 `ReportJob`（`@Component`）。因此：

- 运行 `QuartzApplication.main(...)`，**应用一启动即生效**；
- Spring Boot 启动过程中自动创建 QuartzConfig 里的 JobDetail + Trigger 两个 Bean，并注册进 Scheduler；
- 随后 Scheduler 按 Cron `0/10 * * * * ?` 每 10 秒**自动调用** `ReportJob.execute()`，全程无需任何手动触发代码。

> 验证：启动后观察日志 `==> 执行 Quartz 任务 [reportJob]...`，即证明自动触发已生效。

---

## 5. 手动触发（编程式 + 辅助手段）

对需要**运行时动态注册、或即时执行一次**的场景，注入 `Scheduler` 编程操作：

```java
@Autowired
private Scheduler scheduler;

@Test
void scheduleAndTriggerJob() throws Exception {
    JobDetail job = JobBuilder.newJob(DemoJob.class)
            .withIdentity("demoJob")
            .usingJobData("message", "hello-quartz")
            .build();

    Trigger trigger = TriggerBuilder.newTrigger()
            .forJob(job)
            .withIdentity("demoJobTrigger")
            .startNow()
            .build();

    scheduler.scheduleJob(job, trigger);   // 注册
    scheduler.triggerJob(job.getKey());    // 立即手动触发一次
}
```

> 本项目 [`QuartzTest.java`](src/test/java/org/ljh/quartz/QuartzTest.java) 即用 `triggerJob` 在测试中即时触发并断言任务执行。它是自动触发之外的辅助手段。

## 6. Trigger 类型详解

| 类型                   | 工厂类                                  | 适用场景                 |
|----------------------|--------------------------------------|----------------------|
| CronTrigger         | `CronTriggerFactoryBean`             | 按 Cron 表达式（最常用）       |
| SimpleTrigger       | `SimpleTriggerFactoryBean`           | 固定间隔 + 固定次数           |
| CalendarInterval    | `CalendarIntervalTriggerFactoryBean` | 按日历单位间隔（每月/每季度）     |
| DailyTimeInterval   | `DailyTimeIntervalTriggerFactoryBean` | 每日某时段内按间隔触发          |

### Cron 表达式格式

```
秒 分 时 日 月 星期 [年]
```

| 表达式              | 含义                |
|-------------------|-------------------|
| `0 0 12 * * ?`    | 每天中午 12 点        |
| `0/10 * * * * ?`  | 每 10 秒            |
| `0 0 9 * * MON-FRI` | 工作日早上 9 点       |
| `0 0 2 1 * ?`     | 每月 1 日凌晨 2 点      |

> `*` 任意，`?` 只在日/星期字段使用（两者互斥），`0/10` 表示从 0 开始每 10 秒。

## 7. 向 Job 传参（JobDataMap）

```java
// 注册时传参
JobDetail job = JobBuilder.newJob(DemoJob.class)
        .usingJobData("message", "with-param")   // 方式一
        .build();
// 或 factory.getJobDataMap().put("key", val)    // 方式二

// Job 内读取（合并 JobDetail 与 Trigger 的数据）
JobDataMap data = context.getMergedJobDataMap();
String msg = data.getString("message");
```

## 8. 常用注解

- `@DisallowConcurrentExecution` —— 禁止同一 Job 并发执行（前一次未结束不触发下一次）
- `@PersistJobDataAfterExecution` —— 执行后保留 JobDataMap 的修改
- 二者通常**成对使用**（持久化参数且不并发时语义正确）

## 9. 配置项

```properties
# 是否自动启动 scheduler，默认 true
spring.quartz.auto-startup=true
# 存储类型：memory（内存）或 jdbc（持久化），默认 memory
spring.quartz.job-store-type=memory
# 同名 Job 是否覆盖已有，默认 false
spring.quartz.overwrite-existing-jobs=false
# 关闭时是否等待任务执行完，默认 false
spring.quartz.wait-for-jobs-to-complete-on-shutdown=false
```

## 10. 持久化与集群

- 默认 `memory` 内存存储，**重启后任务丢失**
- 生产用 `spring.quartz.job-store-type=jdbc`，数据落库（需创建 Quartz 的 11 张表），支持**集群**调度、故障转移

## 11. Misfire（错过补偿）

任务因停机/忙碌错过触发时间时，按配置策略处理：
- `MISFIRE_INSTRUCTION_FIRE_ONCE_NOW` —— 立即补执行一次
- `MISFIRE_INSTRUCTION_DO_NOTHING` —— 忽略错过

## 12. 与 Spring `@Scheduled` 对比

| 维度    | `@Scheduled`        | Quartz           |
|-------|---------------------|------------------|
| 复杂度  | 简单轻量               | 强大灵活             |
| 持久化  | 无                   | JDBC 支持           |
| 集群   | 不支持                | 支持               |
| 触发器  | 固定 cron / 固定间隔      | 多类型 + 动态注册       |
| 适用场景 | 简单定时任务              | 复杂调度 / 分布式       |

> 简单需求用 `@Scheduled`（`@EnableScheduling` + `@Scheduled(cron=...)`）；复杂、需持久化/集群用 Quartz。

## 13. 模块示例

| 文件                                                                                       | 说明                           |
|------------------------------------------------------------------------------------------|------------------------------|
| [`QuartzApplication.java`](src/main/java/org/ljh/quartz/QuartzApplication.java)          | 启动类，启动即加载 Quartz 配置并自动触发     |
| [`QuartzConfig.java`](src/main/java/org/ljh/quartz/config/QuartzConfig.java)             | 声明式注册任务，每 10 秒自动触发           |
| [`ReportJob.java`](src/main/java/org/ljh/quartz/job/ReportJob.java)                      | Job 实现类，实际业务逻辑                |
| [`QuartzTest.java`](src/test/java/org/ljh/quartz/QuartzTest.java)                        | 测试：自动装配、动态注册 + 手动触发、传参      |
| [`application.properties`](src/main/resources/application.properties)                    | Quartz 配置                      |

## 14. 注意事项

1. **并发**：默认允许并发，需串行时加 `@DisallowConcurrentExecution`
2. **持久化**：默认内存存储，重启丢任务；生产建议 `jdbc`
3. **异常**：`execute()` 抛异常仅本次触发失败，调度器继续按计划执行
4. **时区**：Cron 默认服务器时区，可用 `factory.setTimeZone()` 指定
