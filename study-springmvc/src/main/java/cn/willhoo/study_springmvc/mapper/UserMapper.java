package cn.willhoo.study_springmvc.mapper;

import java.util.List;

import cn.willhoo.study_springmvc.entity.User;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月5日 下午4:19:45   
*/
public interface UserMapper {
	
	

	/**
	 * 查询所有的用户,并且显示到页面
	 * @return
	 */
	List<User> findAllUser();
	
	/**
	 * 根据ID查询一个用户
	 */
	User findUserById(int id);
	
	/**
	 * 查询和这个用户所在地址所有的用户
	 */
	List<User> findUserListByAddress(String address);
	
	/**
	 * 
	 */
	void saveFile(String fileName);
	
	
	void deleteAll(Long[] ids);
	
}
