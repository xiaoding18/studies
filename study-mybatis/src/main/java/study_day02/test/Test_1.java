package study_day02.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import study_day02.entity.Order;
import study_day02.mapper.OrderMapper;

/**   
* @Description 主要是演示了resultMap属性的使用
* @author xiaoding
* @date 2018年4月3日 上午10:36:11   
*/
public class Test_1 {
	
	private SqlSession sqlSession;
	

	public Test_1() throws IOException {
		
		InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		sqlSession = factory.openSession();
	}
	
	@Test
	public void Test3() {
	}
	
	
	/**
	 * 不使用resultMapper,用来测试
	 * 可以看到我们输出的userId是null,为了解决这种方案,可以使用resultMap的方式
	 */
	@Test
	public void Test1() {
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> orders = mapper.findAllOrders();
		orders.forEach(System.out::println);
		sqlSession.getMapper(OrderMapper.class);
	}
	
	/**
	 * 使用resultMap解决上述问题
	 */
	@Test
	public void Test2() {
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> orders = mapper.findAllOrdersByResultMap();
		orders.forEach(System.out::println);
	}
	
	
}
