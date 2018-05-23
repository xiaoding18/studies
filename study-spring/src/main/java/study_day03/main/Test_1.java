package study_day03.main;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study_day03.service.CustomerService;

/**   
* @Description 
* @author xiaoding
* @date 2018年3月23日 上午10:39:58   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:study_day03/applicationContext.xml")
public class Test_1 {

	@Autowired
	private CustomerService service;
	
	
	@Test
	public void Test1() {
		System.out.println(service.getClass());
		service.saveCustomer();
		System.out.println(service.getClass().getName());
	}
}
