package cn.willhoo.ssh_bos.utils.base.controller.controller;

import java.util.List;
import java.util.Map;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月20日 下午8:22:37   
*/
public interface BaseController<T> {

	/**
	 * 查询所有
	 */
	List<T> findAll();
	
	/**
	 * 按照条件查询并且分页
	 */
	Map<String, Object> listByPage(
            Integer page,
            Integer rows,
            T t);
	
	/**
	 * 保存或者是修改
	 * @return 
	 */
	Object saveOrUpdate(T t);
	
	
	/**
	 * 根据Id查找一个指定的对象
	 */
	T findById(Long id);
	
	/**
	 * 
	 */
	List<T> list();
	
	
	/**
	 * 删除指定的对象,这些对象可以是数组
	 * @return 
	 */
	Object delete(String ids);
	
	
	
}
