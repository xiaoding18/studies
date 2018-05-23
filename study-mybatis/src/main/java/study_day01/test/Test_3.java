package study_day01.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import study_day01.entity.User;
import study_day01.mapper.UserMapper;

/**   
* @Description 测试mybatis使用mapper方式来开发
* @author xiaoding
* @date 2018年4月2日 下午7:54:57   
*/
public class Test_3 {

	
	private SqlSession session;
	private SqlSessionFactory factory;
	
	
	/**
	 * 我曹这也太强了吧...
	 * 如果这个执行不需要像hibernate一样消耗巨大的性能的话,那么这个框架统治持久层是必然的...
	 */
	@Test
	public void Test1() {
		//第一步,先获得mapper对象
		UserMapper mapper = session.getMapper(UserMapper.class);
		//执行方法
		User user = mapper.findUserById(10);
		System.out.println(user);
	}
	
	/**
	 * 测试向数据库中插入一条数据
	 */
	@Test
	public void Test2() {
		//先得到对象
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User();
		user.setAddress("长沙");
		user.setBirthday(new Date(System.currentTimeMillis()));
		user.setSex("1");
		user.setUsername("明月");
		
		//保存这个对象
		mapper.insertUser(user);
	}
	
	/**
	 * 测试mybatis的缓存机制
	 * 测试一级缓存
	 * 可以看到mybatis自己就有一级缓存,我们不用担心
	 * 那么问题来了,这个一级缓存是怎么实现的?
	 */
	@Test
	public void Test3() {
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user1 = mapper.findUserById(10);
		System.out.println("第一次输出:"+user1);
		
		System.out.println("------------------------------");
		//再次查找一次
		User user2 = mapper.findUserById(10);
		System.out.println("第二次输出"+user2);
	}
	
	/**
	 * 测试mybatis的二级缓存
	 * 需要注意的是,如果要测试mybatis的二级缓存,那么需要将实体类实现序列化接口
	 */
	@Test
	public void Test4() {
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user1 = mapper.findUserById(10);
		System.out.println("第一次输出:"+user1);
		
		System.out.println("------------------------------");
		//再次查找一次
		User user2 = mapper.findUserById(10);
		System.out.println("第二次输出"+user2);
		
		//将session关闭
		session.close();
		
		UserMapper mapper2 = session.getMapper(UserMapper.class);
		User user3 = mapper2.findUserById(10);
		
		System.out.println(user3);
	}
	
	
	
	
	
	@Before
	public void before() {
		InputStream in;
		try {
			in = Resources.getResourceAsStream("sqlMapConfig.xml");
			// 创建工厂对象
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			factory = builder.build(in);
			// 对于本类的session对象赋值
			session = factory.openSession(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
