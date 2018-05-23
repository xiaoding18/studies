package study_day01.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import study_day01.entity.User;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月2日 下午2:08:09   
*/
public class Test_1 {

	
	private SqlSession session;
	
	@Before
	public void initMethod() throws IOException {
		
		 
		
		//先加载配置文件
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("sqlMapConfig.xml");
		
		//创建工厂对象
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(in);
		
		//对于本类的session对象赋值
		session = factory.openSession(true);
	}
	
	/**
	 * 配置第一次使用Mybatis
	 * @throws IOException
	 */
	@Test
	public void Test() throws IOException {
		
		//加载主配置文件内容
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		
		//读取配置文件内容
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		//sqlSessionFactory对象和hibernate中的sessionFactory对象一样,都是核心对象,是线程安全的
		//通常在一个项目中只需要一个sqlSessionFactory对象(单例模式)
		SqlSessionFactory factory = builder.build(in);
		
		
		//使用sqlSessionFactory对象创建sqlSession对象
		/*
		 * SqlSession对象相当于是hibernate中的session对象,每一个执行的方法都需要创建一个sqlSession对象
		 */
		SqlSession session = factory.openSession();
		Object user = session.selectOne("testSpace.queryAllUserById", 10);
		System.out.println(user);
		//最后释放对象
		session.close();

	}
	
	
	/**
	 * 第二次使用mybatis,我们需要关心其执行的sql语句
	 * 占位符的使用原则:
	 * 	如果是java中的基础类型,并且使用字符串的拼接,则必须要使用${value},否则就是报错(类似于spring中的注入?)
	 * 	如果不使用字符串拼接,则随便使用什么占位符都可以,但是不能没有占位符,比如这种格式#{}
	 * 
	 * 
	 * @throws IOException
	 */
	//根据用户名称模糊查询用户
//	@Test
	public void Test2() throws IOException {
		//加载主配置文件内容
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//读取主配置文件内容
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(in);
		
		//创建sqlSession对象
		SqlSession session = factory.openSession();
		
		//使用session对象调用sql语句来执行
//		List<Object> list = session.selectList("testSpace.queryUserByName","%小%");
		/*
		 * 我们想要的是传递参数的时候不用加上%%,这个时候可以使用字符串拼接符${}来实现(这个需要写在sql语句中)
		 */
		List<Object> list = session.selectList("testSpace.queryUserByName","小");
		
		list.forEach(System.out::println);
		session.close();
	}
	
	
	
	
	/**
	 * 新增一个用户
	 * 这种方式使用的是手动提交事务
	 */
//	@Test
	public void Test3() {
		
		User user = new User();
		user.setId(3);
		user.setUsername("古天乐");
		user.setBirthday(new Date(System.currentTimeMillis()));
		user.setSex("2");
		user.setAddress("现代人");
		session.insert("testSpace.insertUser",user);
		
		//使用手动提交事务的方式
		session.commit();
		
		//当修改了session创建时的方式时,即创建这个事务的时候就指定这个事务自动提交
		/*
		 * 使用自动提交需要注意
		 * 一旦sql语句执行完了,那么就会提交事务,也就是说我们不能将多条sql语句放在一个事务中执行
		 */
		//记得关闭资源
		session.close();
	}
	
	//还是新增一个用户
	/**
	 * 我们的数据库中可以使用自增的字段,然后我们有这种需求:
	 * 	在插入一条sql记录之后,我们需要获取到刚才插入的这条sql记录的主键的值
	 * 下面将会是在插入一条记录之后获取到这条刚才插入的数据的主键的值
	 */
	@Test
	public void Test4() {
		User user = new User();
		user.setUsername("刘诗诗");
		user.setBirthday(new Date(System.currentTimeMillis()));
		user.setSex("2");
		user.setAddress("湖南省");
		//在插入前输出一下这个user对象
		System.out.println(user);
		session.insert("testSpace.insertUser",user);
		//在插入之后再输出一下这个user的id
		System.out.println(user);
	}
	
	/**
	 * 修改一个用户
	 * 将古天乐从现代人改成古代人,性别改成1
	 */
//	@Test
	public void Test5() {
		User user = new User();
		user.setId(3);
		user.setSex("1");
		user.setAddress("古代人");
		
		//执行sql语句
		session.update("updateUserById",user);
		
	}
	
	/**
	 * 根据id删除用户
	 */
//	@Test
	public void Test6() {
		User user = new User();
		user.setId(3);
		session.delete("deleteById",user);
	}
}
