package study_quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月24日 上午10:25:30   
*/
public class SpringQuartzJob implements Job{

	//执行次数
	private int i = 0;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("spring整合quartz的job"+i);
		i++;
		System.out.println(i);
	}

}
