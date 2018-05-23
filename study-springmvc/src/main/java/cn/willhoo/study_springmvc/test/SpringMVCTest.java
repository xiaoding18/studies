package cn.willhoo.study_springmvc.test;

import cn.willhoo.study_springmvc.entity.User;
import cn.willhoo.study_springmvc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**   
* @Description 用来测试其他的环境是否配置好了
* @author xiaoding
* @date 2018年4月5日 下午4:21:03   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringMVCTest {

	@Autowired
	private UserService service;
	
	
	/**
	 * 测试Mybatis和spring是否整合成功
	 */
	@Test
	public void SpringMybatisTest() {
		List<User> users = service.findAllUser();
		users.forEach(System.out::println);
	}
	
	
}
