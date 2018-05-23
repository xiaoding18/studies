package cn.willhoo.ssh_bos.utils.quartz;

import org.quartz.Job;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**   
* @Description 一个单例的job工厂,将会获取已经创建的job然后返回这个job
* @author xiaoding
* @date 2018年4月24日 下午7:35:03   
*/
@Component
public class SingletonJobFactory extends AdaptableJobFactory{

	@Autowired
	private ApplicationContext context;
	
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Class<? extends Job> jobClass = bundle.getJobDetail().getJobClass();
		return context.getBean(jobClass);
	}
	
	
}
