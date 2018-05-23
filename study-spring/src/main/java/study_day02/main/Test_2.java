package study_day02.main;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study_day02.action.CustomerAction;
import study_day02.entity.Customer;
import study_day02.utils.SpringConfiguration;

/**   
* @Description 测试注解整合junit框架的方式
* @author xiaoding
* @date 2018年3月20日 下午9:26:03   
*/

@ContextConfiguration(classes=SpringConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test_2 {

	@Autowired
	private CustomerAction action;
	
	
	
//	@Test
	public void Test() throws SQLException {
		Customer customer = new Customer(0,"肖鼎","男");
		action.saveCustomer(customer);
		byte b = 1;
	}
	
//	@Resource
	@Autowired
	private List<String> myList2;
	
	@Test
	public void Test_1() {
		System.out.println(myList2.getClass());
	}
	
	
	
	
}
