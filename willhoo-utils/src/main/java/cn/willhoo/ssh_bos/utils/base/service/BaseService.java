package cn.willhoo.ssh_bos.utils.base.service;

import java.util.List;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月20日 下午8:51:37   
*/
public interface BaseService<T> {
	
	/**
	 * 保存一个standard到数据库中
	 */
	void saveOrUpdate(T t);
	
	/**
	 * 查询所有的standard
	 */
	List<T> findAll();
	
	
	
	/**
	 * @return
	 */
	T findById(Long id);

	/**
	 * @param ids
	 */
	void delete(String ids);
	
	/**
	 * 删除一个对象
	 */
	void delete(Long id);

}
