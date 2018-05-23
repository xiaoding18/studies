package study_day01.main;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import study_day01.entity.Customer;

/**   
* @Description:测试spring的复合类型注入
* @author xiaoding
* @date 2018年3月20日 下午3:56:09   
*/
public class Test_2 {

	//需要做的就是向这个List中注入三个Customer对象
	private List<Customer> customerList;
	
	
	//将三个Customer对象注入map中
	private Map<String,Customer> map;
	@Test
	public void Test1() {
		
		//先创建本例对象
		ApplicationContext context = new ClassPathXmlApplicationContext("study_day01/applicationContext.xml");
		Test_2 test_2 = context.getBean(this.getClass());
		
		//分别输出List和map中的内容,查看是否符合要求
		test_2.customerList.forEach(System.out::println);
		test_2.map.forEach((x,y) -> System.out.println(x+""+y));
	}
	
	
	
	//提供一下setter
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	public void setMap(Map<String, Customer> map) {
		this.map = map;
	}
}
