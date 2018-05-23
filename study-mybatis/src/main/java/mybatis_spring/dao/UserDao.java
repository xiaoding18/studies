package mybatis_spring.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mybatis_spring.entity.User;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月4日 下午3:51:01   
*/
@Component
public class UserDao {

	
	@Autowired
	private SqlSessionFactory factory;
	
	
	/**
	 * 保存一个用户到数据库中
	 * @param user
	 */
	public void save(User user) {
		SqlSession session = factory.openSession();
		session.insert("insertUser",user);
	}
}
