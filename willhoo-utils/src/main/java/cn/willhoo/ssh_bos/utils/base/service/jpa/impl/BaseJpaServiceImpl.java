package cn.willhoo.ssh_bos.utils.base.service.jpa.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import cn.willhoo.ssh_bos.utils.base.dao.BaseDao;
import cn.willhoo.ssh_bos.utils.base.service.jpa.BaseJpaService;


/**   
* @Description 
* @author xiaoding
* @date 2018年4月8日 下午5:09:59   
*/

public class BaseJpaServiceImpl<T> implements BaseJpaService<T> {
	
	@Autowired
	private BaseDao<T> baseDao;

	public void saveOrUpdate(T standard) {
		baseDao.save(standard);
	}

	public List<T> findAll() {
		List<T> list = baseDao.findAll();
		return list;
	}

	@Override
	public Page<T> pagingBy(Pageable able,Specification<T> sp) {
		//返回分页查询和条件查询的结果
		return baseDao.findAll(sp,able);
	}
	
	public T findById(Long id) {
		return baseDao.findOne(id);
	}
	
	@Override
	public void delete(String ids) {
		String[] split = ids.split(",");
		Arrays.stream(split).map(Long::new).forEach(baseDao::delete);
	}
	

	@Override
	public List<T> findAll(Specification<T> sp) {
		return baseDao.findAll(sp);
	}

	@Override
	public void delete(Long id) {
		
	}
	

}
