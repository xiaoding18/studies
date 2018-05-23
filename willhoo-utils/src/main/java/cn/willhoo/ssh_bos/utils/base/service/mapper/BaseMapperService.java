package cn.willhoo.ssh_bos.utils.base.service.mapper;

import java.util.List;

import cn.willhoo.ssh_bos.utils.base.service.BaseService;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月20日 下午9:09:55   
*/
public interface BaseMapperService<T> extends BaseService<T>{

	/**
	 * 条件查询
	 */
	List<T> findAllByCondition(T t);
	
	
}
