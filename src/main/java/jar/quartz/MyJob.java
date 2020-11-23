package jar.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(sdf.format(new Date()));
	}
}
