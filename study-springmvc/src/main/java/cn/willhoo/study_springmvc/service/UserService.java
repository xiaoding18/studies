package cn.willhoo.study_springmvc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.willhoo.study_springmvc.entity.User;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月5日 下午4:20:33   
* 
*/
@Transactional
public interface UserService {

	
	/**
	 * 获取所有的用户
	 */
	List<User> findAllUser();
	
	/**
	 * 根据ID查找一个用户
	 */
	User findUserById(int id);
	
	/**
	 * 查出和请求过来的用户相同地址的所有用户
	 */
	List<User> findUserListByAddress(String address);
	
	/**
	 * 保存一个文件名至数据库中
	 */
	void saveFile(String fileName);
	
	
	/**
	 * 删除指定的所有的用户
	 */
	void deleteAll(Long[] ids);
		
}
