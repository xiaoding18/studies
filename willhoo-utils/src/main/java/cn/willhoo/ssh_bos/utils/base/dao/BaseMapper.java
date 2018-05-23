package cn.willhoo.ssh_bos.utils.base.dao;

import java.util.List;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月20日 下午8:45:05   
*/
public interface BaseMapper<T> {

	/**
	 * 保存一个standard到数据库中
	 */
	void save(T t);
	
	/**
	 * 查询所有的standard
	 */
	List<T> findAll();
	
	List<T> findAllByCondition(T t);
	
	/**
	 * @return
	 */
	T findById(Long id);

	/**
	 * @param ids
	 */
	void deleteBatch(String[] ids);
	
	/**
	 * 删除一个对象
	 */
	void delete(Long id);
	
	/**
	 * 修改一个对象
	 */
	void update(T t);
	
}
