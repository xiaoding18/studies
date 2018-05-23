package temp;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import study_day02.entity.User;
import study_day02.mapper.UserMapper;

import java.io.IOException;
import java.util.List;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月12日 下午3:52:25   
*/
public class Test_1 {

	private SqlSession sqlSession;
	
	@Before
	public void before() throws IOException {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(Resources.getResourceAsStream("sqlMapConfig.xml"));
		sqlSession = factory.openSession();
	}
	
	
	@Test
	public void Test1() {
		//使用sqlSession获取到Mapper接口的代理类
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = mapper.findAllUser();
		list.forEach(System.out::println);
	}
}
