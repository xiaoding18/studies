package cn.willhoo.ssh_bos.utils.base.controller.controller.impl;

import cn.willhoo.ssh_bos.utils.base.controller.controller.BaseController;
import cn.willhoo.ssh_bos.utils.base.service.mapper.BaseMapperService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月20日 下午8:19:05   
*/
public class BaseControllerImpl<T>  implements BaseController<T> {

	protected static final String SUCCESS = "{'status':true}";
	protected static final String ERROR = "{'status':false}";
	
	
	protected BaseMapperService<T> service;
	
	@Override
	@ResponseBody
	@RequestMapping("findAll")
	public List<T> findAll() {
		return service.findAll();
	}

	@Override
	@RequestMapping("listByPage")
	@ResponseBody
	public Map<String,Object> listByPage(
			@RequestParam(value="page",defaultValue="1") Integer page, 
			@RequestParam(value="rows",defaultValue="10") Integer rows, 
			T model) {
		//开始分页
		PageHelper.startPage(page, rows);
		List<T> list = service.findAllByCondition(model);
		//返回分页之后的数据
		PageInfo<T> info = new PageInfo<T>(list);
		Map<String,Object> messager = new HashMap<>();
		messager.put("total",info.getTotal() );
		messager.put("rows", info.getList());
		return messager;
	}

	
	@Override
	@ResponseBody
	@RequestMapping("saveOrUpdate")
	public Object saveOrUpdate(T model) {
		try {
			System.out.println(model);
			service.saveOrUpdate(model);
			return writeSuccess();
		}catch (Exception e) {//如果操作失败了
			return writeError(e);
		}
		
	}

	@Override
	@ResponseBody
	@RequestMapping("findById")
	public T findById(@RequestParam("uuid") Long id) {
		return service.findById(id);
	}

	@Override
	@ResponseBody
	@RequestMapping("list")
	public List<T> list() {
		return service.findAll();
	}

	@Override
	@ResponseBody
	@RequestMapping("deleteBatch")
	public Object delete(@RequestParam("ids") String ids) {
		//将会删除ids中所有的数据
		try {
			service.delete(ids);
			return writeSuccess();
		}catch (Exception e) {
			e.printStackTrace();
			return writeError(e);
		}
		
		//可以添加删除成功或者失败的提示
		
	}

	
	/**
	 * 操作成功
	 * @return
	 */
	protected Object writeSuccess(String key,Object value) {
		HashMap<String, Object> messager = new HashMap<>();
		messager.put(key, value);
		return writeMessage(messager,true);
	}
	
	
	/**
	 * 操作失败
	 * @param key
	 * @param value
	 * @return
	 */
	protected Object writeError(String key,Object value) {
		HashMap<String, Object> messager = new HashMap<>();
		messager.put(key, value);
		return writeMessage(messager,false);
	}
	
	/**
	 * 操作失败
	 * @return
	 */
	protected Object writeError() {
		return writeMessage(new HashMap<String,Object>(),false);
	}
	protected Object writeError(Object message) {
		return writeError("message",message.toString());
	}
	
	protected Object writeSuccess() {
		return writeMessage(new HashMap<String,Object>(), true);
	}
	
	protected Object writeMessage(Map<String,Object> messager,boolean flag) {
		messager.put("state", flag);
		return messager;
	}
	
}
