package study_day02.main;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import study_day02.action.CustomerAction;
import study_day02.entity.Customer;

/**   
* @Description:测试spring注解加xml文件的方式
* @author xiaoding
* @date 2018年3月20日 下午9:12:25   
*/
public class Test_1 {

	@Test
	public void Test1() {
		//还是先获取ApplicationContext对象
		ApplicationContext context = new ClassPathXmlApplicationContext("study_day02/applicationContext.xml");
		CustomerAction action = context.getBean(CustomerAction.class);
		
		//执行保存操作
		Customer customer = new Customer(0,"肖鼎","男");
		action.saveCustomer(customer);
	}
}
