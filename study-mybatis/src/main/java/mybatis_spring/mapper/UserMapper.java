package mybatis_spring.mapper;

import java.util.List;

import mybatis_spring.entity.User;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月4日 下午3:23:08   
*/
public interface UserMapper {
	
	/**
	 * 查找到所有的用户
	 */
	List<User> findAllUser();
}
