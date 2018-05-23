package study_day02.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import study_day02.entity.Customer;
import study_day02.service.CustomerService;

/**
 * 
* @Description 配置一个基于注解的类,使用controller注解,表示这个类是控制层
* 如果不给Controller注解value属性,则ID默认为这个类名的开头小写
* @author xiaoding
* @date 2018年3月20日 下午9:16:19
 */
@Controller
public class CustomerAction {
	
	
	/**
	 * 使用Autowired注解可以自动注入一个基于这个类型的值,将会先根据Class属性来寻找配对的bean的信息,如果有多个则根据变量名来注入
	 */
	@Autowired
	private CustomerService service;
	
	
	public void saveCustomer(Customer customer) {
		service.saveCustomer(customer);
	}
}
