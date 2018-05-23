package study_quartz;

import org.quartz.Job;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**   
* @Description 设置一个自动装配的工厂对象,由于默认的装配方式是使用Class.newInstance的方式来进行注入,我们在这里修改原有的注入方式,
* 将原有的注入方式换成使用spring中的bean来进行注入
* @author xiaoding
* @date 2018年4月24日 下午5:23:04   
*/
@Component
public class SingleJobFactory extends SpringBeanJobFactory{
	
	@Autowired	
	private ApplicationContext context;
	
	/**
	 * 获取一个单例的job
	 */
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) {
		Class<? extends Job> jobClass = bundle.getJobDetail().getJobClass();
		return context.getBean(jobClass);
	}

}
