package jar.quartz;

import cn.hutool.core.date.DatePattern;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        log.info("{}: {}", jobExecutionContext.getJobDetail().getKey(),
            DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN).format(LocalDateTime.now()));
    }
}
