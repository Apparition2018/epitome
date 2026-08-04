package org.ljh.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Quartz Job：实际执行的任务逻辑。
 * 每次触发都会执行 execute()，可从 JobExecutionContext 读取 JobDataMap 中的业务参数。
 */
@Component
public class ReportJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(ReportJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String name = context.getJobDetail().getKey().getName();
        String task = context.getMergedJobDataMap().getString("task");
        log.info("==> 执行 Quartz 任务 [{}], 业务参数 task={}", name, task);
    }
}
