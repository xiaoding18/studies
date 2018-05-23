package study_day01.mapper;

import java.util.List;

import study_day01.entity.QueryVo;
import study_day01.entity.User;

/**   
* @Description 表示用户开发所需要的接口
* @author xiaoding
* @date 2018年4月2日 下午7:42:26   
*/
public interface UserMapper {

	/**
	 * 保存一个用户对象
	 */
	void insertUser(User user);
	
	/**
	 * 查找一个用户对象
	 */
	User findUserById(int id);
	
	/**
	 * 根据名称模糊查询一个用户对象
	 */
	List<User> findUserByName(QueryVo vo);
	
	/**
	 * 查询当前一共有多少用户
	 */
	Integer queryCountUser();
	
}
