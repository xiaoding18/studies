package study_day02.mapper;

import java.util.List;

import study_day01.entity.QueryVo;
import study_day02.entity.User;

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
	
	/**
	 * 使用动态sql语句来查询用户
	 */
	List<User> findUserByDynamicSQL(User user);
	
	/**
	 * 使用动态sql语句修改用户,使用set标签
	 */
	void updateUserByDynamicSQL(User user);
	
	/**
	 * 执行批量插入用户
	 */
	void insertUserBatch(List<User> users);
	
	/**
	 * 查询某个用户,并且得到其所有的订单信息
	 */
	List<User> findUserAndOrders(int id);
	
	/**
	 * 查询所有的用户
	 */
	List<User> findAllUser();
}
