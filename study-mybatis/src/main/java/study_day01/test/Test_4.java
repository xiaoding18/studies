package study_day01.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import study_day01.entity.QueryVo;
import study_day01.entity.User;
import study_day01.mapper.UserMapper;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月3日 上午9:14:54   
*/

public class Test_4 {

	
	private SqlSession sqlSession;
	
	public Test_4() throws IOException {
		// 先获取到配置文件对象
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		//获得工厂对象
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//创建sqlSession对象
		sqlSession = factory.openSession();
	}
	
	

		

	
	@Test
	public void Test1() {
		//先获取映射信息
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.findUserById(10);
		System.out.println(user);
	}
	
	
	/**
	 * 模糊查询名称中包含某些字符串的用户
	 */
	@Test
	public void Test2() {
		User user = new User();
		user.setUsername("小");
		QueryVo vo = new QueryVo();
		vo.setUser(user);
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.findUserByName(vo);
		list.forEach(System.out::println);
	}
	
	/**
	 * 查询当前一共有多少用户
	 */
	@Test
	public void Test3() {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		Integer count = mapper.queryCountUser();
		System.out.println("当前一共有"+count+"用户");
	}
	
	
	/**
	 * 如果不使用resultMap的话,查询字段和数据库中的字段不一样时,将会无法注入这个值
	 */
}
