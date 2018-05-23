package mybatis_spring.test;

import java.util.Date;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mybatis_spring.dao.UserDao;
import mybatis_spring.entity.User;
import mybatis_spring.mapper.UserMapper;

/**   
* @Description 测试mybatis整合spring
* @author xiaoding
* @date 2018年4月4日 下午2:37:29   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mybatis_spring/applicationContext.xml")
public class Test_1 {

	@Autowired
	private UserDao dao;
	
	
	/**
	 * 使用普通方式开发
	 */
	@Test
	public void Test1() {
		
		User user = new User();
		user.setSex("1");
		user.setBirthday(new Date());
		user.setUsername("刘鑫明");
		user.setAddress("广州");
		dao.save(user);
	}
	
	
	
	/**
	 * 使用mapper接口配合配置文件开发
	 */
	@Test
	public void Test2() {
		List<User> list = mapper.findAllUser();
		list.forEach(System.out::println);
	}
	
	/**
	 * 使用mapper接口扫描开发
	 */
	
	
	/**
	 * 测试@PostConstruct注解和Autowired注解那个先执行
	 * 结果是@postConstruct注解要后执行
	 */
	public void init() {
		List<User> list = mapper.findAllUser();
		System.out.println(list);
	}
	
	@Autowired
	private UserMapper mapper;
	
	@Test
	public void Test4() {
		System.out.println("正常执行");
	}
	
}
