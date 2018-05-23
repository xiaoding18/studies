package cn.willhoo.ssh_bos.utils.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月20日 下午9:38:22   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test3 {

	@Autowired
	private ApplicationContext context;
	
	
	/**
	 * 测试是否能在运行的时候获取到spring的容器
	 */
	@Test
	public void TestContext() {
		System.out.println(context);
	}
}
