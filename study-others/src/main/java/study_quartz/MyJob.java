package study_quartz;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 一个类,表示一个真正的任务
* @Description 
* @author xiaoding
* @date 2018年4月24日 上午9:06:04
 */
public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("执行了!");
	}
	
}