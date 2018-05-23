package study_quartz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月24日 上午8:59:47   
*/
public class QuartzTest {

	
	
	public static void main(String[] args) throws SchedulerException {
		//创建一个任务
		JobDetail job = JobBuilder.newJob(MyJob.class).build();
		
		//创建一个trigger,即触发器
		SimpleTrigger trigger = TriggerBuilder
								.newTrigger()
								.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1))
								.build();
		//定义一个日历类型的任务,从0秒开始,每5秒执行一次
		/**
		 * 日历类型的表达式
		 * 一共有六个或者是7个数字,依次分别代表
		 * 秒,分,时,日,月,星期,年
		 * 其中日和星期不能同时使用
		 * 
		 */
		CronTrigger trigger2 = TriggerBuilder
								.newTrigger()
								.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
								.build();
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		scheduler.scheduleJob(job,trigger2);
		scheduler.scheduleJob(job, trigger2);
		
		
		//启动任务
		scheduler.start();
		
	}
	
}
