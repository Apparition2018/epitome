package jar.quartz;

import l.demo.Demo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
@Slf4j
public class MyJob extends Demo implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info(jobExecutionContext.getJobDetail().getKey() + ": " + DATE_TIME_SDF.format(new Date()));
    }
}
