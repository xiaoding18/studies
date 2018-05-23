package cn.willhoo.ssh_bos.utils.init;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

/**   
* @Description 一个基本的初始化器,可以初始化SSM框架的service,mapper,controller
* @author xiaoding
* @date 2018年4月20日 下午9:29:39   
*/
public final class BaseInitializer implements BeanPostProcessor {

	/**
	 * 在创建bean的时候获取到spring容器,对于其中的某些bean进行手动控制
	 */
	@Autowired
	private ApplicationContext context;
	
	public static final String[] types = {
		"CONTROLLER",
		"SERVICE",
		"MAPPER"
	};

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		//1.判断这个类是否是我们自己写的
		
		//2.判断这个类中是否包含types中的任意类型
		
		//3.如果包含types中的任意类型
		
		return null;
	}
	
	/**
	 * 获取一个类的大写简单类名
	 * @param clazz
	 * @return
	 */
	public String getUpperBeanName(Class<?> clazz) {
		return clazz.getSimpleName().toUpperCase();
	}
	
	
	/**
	 * 判断一个bean是否符合被本注入器注入的条件,如果符合则返回将会被注入的类型
	 */
	public String typeOfInject(Class<?> clazz) {
		for(int i=0;i<types.length-1;i++) {
			//如果在types中包含这个可以被注入的bean
			if(getUpperBeanName(clazz).contains(types[i])) {
				return types[i+1];
			}
		}
		
		return null;
	}
}
