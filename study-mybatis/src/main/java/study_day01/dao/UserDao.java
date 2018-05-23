package study_day01.dao;

import study_day01.entity.User;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月2日 下午6:55:27   
*/
public interface UserDao {

	
	/**
	 * 根据用户ID查找这个对象
	 * @param id
	 * @return
	 */
	User findUserById(int id);
	
	/**
	 * 添加一个用户对象
	 */
	void insertUser(User user);
	
}
