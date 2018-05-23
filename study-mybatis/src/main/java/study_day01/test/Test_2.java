package study_day01.test;

import java.sql.Date;

import org.junit.Test;

import study_day01.dao.UserDao;
import study_day01.dao.impl.UserDaoImpl;
import study_day01.entity.User;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月2日 下午7:11:43   
*/
public class Test_2 {

	private UserDao userDao = new UserDaoImpl();
	
	/**
	 * 查找一条数据并且输出
	 */
	@Test
	public void Test1() {
		User user = userDao.findUserById(10);
		System.out.println(user);
	}
	
	/**
	 * 插入一条数据
	 */
	@Test
	public void Test2() {
		User user = new User();
		user.setAddress("湖南");
		user.setBirthday(new Date(System.currentTimeMillis()));
		user.setSex("1");
		user.setUsername("胡歌");
		
		//执行插入操作
		userDao.insertUser(user);
	}
}
