package jar.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

import static l.demo.Demo.DATE_TIME_FORMAT;

/**
 * MyJob
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
@Slf4j
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info(jobExecutionContext.getJobDetail().getKey() + ": " + DATE_TIME_FORMAT.get().format(new Date()));
    }
}
