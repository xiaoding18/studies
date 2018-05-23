package study_day01.dao.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import study_day01.dao.UserDao;
import study_day01.entity.User;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月2日 下午6:55:46   
*/

public class UserDaoImpl implements UserDao{

	
	
	
	private SqlSession session;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public UserDaoImpl(){
		//先加载配置文件
		InputStream in;
		try {
			in = Resources.getResourceAsStream("sqlMapConfig.xml");
			// 创建工厂对象
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(in);
			// 对于本类的session对象赋值
			session = factory.openSession(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		

		
	}
	
	@Override
	public User findUserById(int id) {
		Object select = session.selectOne("queryUserById",id);
		return (User) select;
	}

	
	
	@Override
	public void insertUser(User user) {
		session.insert("insertUser",user);
	}

	

	

	

	




	
}
