package study_quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月24日 上午10:26:25   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:study_quartz/applicationContext.xml")
public class SpringQuartzTest {
	
	@Test
	public void Test_1() throws InterruptedException {
		Thread.sleep(100000);
	}
}
