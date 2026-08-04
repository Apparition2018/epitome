package org.ljh.quartz.config;

import org.ljh.quartz.job.ReportJob;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * Quartz 配置：声明式注册定时任务。
 * 1. reportJobDetail()：定义任务，Bean 名 = setName("reportJob")；
 * 2. reportCronTrigger()：定义触发规则，每 10 秒执行一次（0/10 * * * * ?）；
 * 3. 方法参数 JobDetail reportJob 注入步骤 1 的 JobDetail，完成绑定；
 * 4. Spring Boot 自动注册进 Scheduler 并定时触发。
 */
@Configuration
public class QuartzConfig {

    /** 步骤 1：定义任务。 */
    @Bean
    public JobDetailFactoryBean reportJobDetail() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(ReportJob.class);
        factory.setName("reportJob");
        factory.setGroup("report");
        factory.getJobDataMap().put("task", "generate-daily-report");
        factory.setDurability(true);
        return factory;
    }

    /** 步骤 2 + 3：定义触发规则，并注入 JobDetail 完成绑定。 */
    @Bean
    public CronTriggerFactoryBean reportCronTrigger(JobDetail reportJob) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(reportJob);
        factory.setName("reportCronTrigger");
        factory.setCronExpression("0/10 * * * * ?");
        return factory;
    }
}
