package cn.willhoo.ssh_bos.utils.base.service.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.willhoo.ssh_bos.utils.base.service.BaseService;


/**   
* @Description 
* @author xiaoding
* @date 2018年4月8日 下午5:10:15   
*/
public interface BaseJpaService<T> extends BaseService<T>{

	
	
	/**
	 * 分页查询对象
	 */
	Page<T> pagingBy(Pageable able, Specification<T> sp);

	
	/**
	 * 根据条件查询所有的结果
	 */
	List<T> findAll(Specification<T> sp);
	
}
