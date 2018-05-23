package study_day02.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import study_day02.entity.Order;
import study_day02.entity.User;
import study_day02.mapper.OrderMapper;
import study_day02.mapper.UserMapper;

/**   
* @Description 练习使用动态sql
* @author xiaoding
* @date 2018年4月3日 上午11:14:21   
*/
public class Test_2 {

	private SqlSession sqlSession;

	public Test_2() throws IOException {

		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		sqlSession = factory.openSession(true);
	}
	
	/**
	 * 使用动态sql语句,if标签和where标签
	 */
	@Test
	public void Test1() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		//设置查询的条件
		User user = new User();
		user.setUsername("%小%");
		user.setSex("1");
		List<User> users = mapper.findUserByDynamicSQL(user);
		users.forEach(System.out::println);
	}
	
	/**
	 * 使用动态sql语句,set标签
	 */
	@Test
	public void Test2() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		//设置条件,将所有的名字中含有小的人的性别改成2
		User user = new User();
		user.setUsername("小");
		user.setId(10);
		user.setSex("2");
		mapper.updateUserByDynamicSQL(user);
	}
	
	/**
	 * 使用动态sql语句
	 * sql:定义一条sql语句片段,可以在以后使用include标签来引用
	 * include:引用一条sql标签定义的sql片段
	 */
	@Test
	public void Test3() {
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> orders = mapper.findAllOrderByDynamic();
		orders.forEach(System.out::println);
	}
	
	/**
	 * 使用动态sql语句,随机生成用户然后执行批量插入
	 */
	@Test
	public void Test4() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		
		List<User> users = new ArrayList<>();
		//生成用户数据
		for(int i=0;i<100;i++) {
			User user = new User();
			user.setId(0);
			user.setAddress("地址"+i);
			user.setSex(i%2 == 0? "1":"0");
			user.setUsername("用户"+i);
			user.setBirthday(new Date(System.currentTimeMillis()));
			users.add(user);
		}
		
		mapper.insertUserBatch(users);
	}
	
	/**
	 * 测试一对一的查询
	 */
	@Test
	public void Test5() {
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> list = mapper.oneToOneSelect();
		list.forEach(System.out::println);
	}
	
	/**
	 * 测试一对多的查询
	 */
	@Test
	public void Test6() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> users = mapper.findUserAndOrders(1);
		users.forEach(System.out::println);
	}
	
	/**
	 * 测试查询所有的用户
	 */
	@Test
	public void Test7() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> users = mapper.findAllUser();
		users.forEach(System.out::println);
	}
	
}
