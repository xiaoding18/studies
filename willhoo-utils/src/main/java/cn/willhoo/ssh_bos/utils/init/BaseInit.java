package cn.willhoo.ssh_bos.utils.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**   
* @Description 编写一个Bean处理器,当一个Action被注入完成时,对其里面的baseService或者是baseDao进行注入
* @author xiaoding
* @date 2018年4月18日 下午7:46:08   
*/

public class BaseInit{

	
	/**
	 * 支持的模糊搜索类型
	 */
	private static String[] types = {
			
			"Action",
			"Service",
			"Dao"
	};
	
	/**
	 * 在所有的bean初始化之前执行
	 */
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * 在所有的bean初始化之后执行
	 */
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		//过滤掉其他的包
		if(bean.getClass().getName().startsWith("cn.willhoo")) {
			BaseUtils.init(bean);
			System.out.println("已经注入:"+beanName);
		}
		return bean;
	}

	
	
}
