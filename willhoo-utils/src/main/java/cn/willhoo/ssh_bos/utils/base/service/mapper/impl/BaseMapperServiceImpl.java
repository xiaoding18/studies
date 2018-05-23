package cn.willhoo.ssh_bos.utils.base.service.mapper.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import cn.willhoo.ssh_bos.utils.base.dao.BaseMapper;
import cn.willhoo.ssh_bos.utils.base.service.mapper.BaseMapperService;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月20日 下午8:44:08   
*/
public abstract class BaseMapperServiceImpl<T> implements BaseMapperService<T>{

	protected BaseMapper<T> mapper;
	
	@Override
	public void saveOrUpdate(T t) {
		//如果传递过来的model没有id,则表示进行的操作是保存,否则就是修改
		//先得到id属性
		try {
			Method method = t.getClass().getMethod("getId");
			Object id = method.invoke(t);
			if(id == null) {
				//执行保存
				mapper.save(t);
			}else {
				mapper.update(t);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<T> findAll() {
		return mapper.findAll();
	}


	@Override
	public T findById(Long id) {
		return mapper.findById(id);
	}

	@Override
	public void delete(String ids) {
		mapper.deleteBatch(ids.split(","));
	}


	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}
	

	@Override
	public List<T> findAllByCondition(T t) {
		return mapper.findAllByCondition(t);
	}

	
}
