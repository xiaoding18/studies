package study_day01.main;



import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import study_day01.action.CustomerAction;
import study_day01.entity.Customer;


/**
 * 
* @Description 
* @author xiaoding
* @date 2018年3月24日 下午8:08:52
 */
public class Test_1 {


	@Test
	public void Test_2() {
		
		
		//先拿到本例对象
		ApplicationContext context = new FileSystemXmlApplicationContext("study_day01/applicationContext.xml");
		//尝试使用Class的方式拿到对象(使用这种方式不需要强转?)
		CustomerAction action = context.getBean(CustomerAction.class);
		
		Customer customer = new Customer(0,"肖鼎",'男');
		
		action.saveCustomer(customer);
		
//		List<Customer> customerList = new ArrayList<Customer>();
		
		//尝试使用set方法注入
		
		
	}
}
