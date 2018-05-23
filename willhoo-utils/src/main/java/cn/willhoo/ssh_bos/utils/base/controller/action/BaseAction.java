package cn.willhoo.ssh_bos.utils.base.controller.action;

import cn.willhoo.ssh_bos.utils.base.service.jpa.BaseJpaService;
import cn.willhoo.ssh_bos.utils.init.BaseUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**   
* @Description 
* @author xiaoding
* @author xiaoding
* @date 2018年4月11日 上午9:41:05   
*/
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	private static final long serialVersionUID = 2774933157636860346L;
	
	/*
	 * fastJson默认有去除重复引用的对象,即在多对一的时候如果有两个对象引用了同一个对象,
	 * 则将只会保留第一个对象
	 * 解决办法:
	 * JSON.toJSONString(target,SerializerFeature.DisableCircularReferenceDetect);
	 * 但是在继续维护之后,又会出现另一个bug
	 * 即fastJSON将会一直递归的转换,
	 * 这个bug的解决方式就是在其中一的一方的上面加一个注解
	 * @JSONField
	 */

	public BaseAction() {
		model = (T) BaseUtils.init(this,"action");
	}
	
	
 	protected T model;
	
	private Long uuid;
	
	private String ids;
	
	protected Map<String,Object> messager = new HashMap<>();
	
	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public Long getUuid() {
		return uuid;
	}


	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}


	protected int page = 1;
	protected int rows = 10;
	
	
	@Autowired(required=false)
	private BaseJpaService<T> baseService;
	
	
	/**
	 * 当页面发送这个请求的时候,将会查找到所有的standard,然后将这些数据添加到页面中,返回的数据格式是json
	 * @throws IOException
	 */
	@Action("findAllStandard")
	public void findAllStandard() {
		List<T> list = baseService.findAll();
		//将json数据添加到响应中
		writeToJson(list);
	}
	
	
	protected void writeToJson(Object object)  {
		object = JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
		System.err.println(object);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().append(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void writeToJson() {
		writeToJson(messager);
	}
	
	protected void writeJsonException(Exception e) {
		e.printStackTrace();
		messager.put("state", false);
		messager.put("message", e.getMessage());
		writeToJson(messager);
		
	}
	
	protected void writeJsonSuccess(Object key,Object obj) {
		messager.put("state", true);
		messager.put(key.toString(), obj);
		writeToJson(messager);
	}
	protected void writeJsonSuccess() {
		messager.put("status", true);
		writeToJson(messager);
	}
	protected void writeJsonError(Exception e) {
		e.printStackTrace();
		messager.put("state", false);
		messager.put("message", e.getMessage());
		writeToJson(messager);
	}
	protected void writeJsonError() {
		messager.put("state", false);
		writeToJson(messager);
	}
	protected void writeJsonError(String key,Object value) {
		messager.put(key, value);
		writeJsonError();
	}
	protected void writeJsonError(String message) {
		writeJsonError("message",message);
	}
	
	/**
	 * 分页查询数据,并且可以进行条件查询
	 * @return
	 * @throws IOException 
	 */
	@Action("listByPage")
	public void listByPage(){
		
		Pageable pageable = new PageRequest(page-1,rows);
		
		Page<T> page = baseService.pagingBy(pageable,buildSpecification());
		
		messager.put("rows", page.getContent());
		messager.put("total", page.getTotalElements());
		
		writeToJson(messager);
	}
	
	/**
	 * 保存或者是修改一条记录
	 * @throws IOException 
	 */
	@Action("saveOrUpdate")
	public void saveOrUpdate() {
		Map<String,Object> message = new HashMap<>();
		try {
			System.out.println(model);
			baseService.saveOrUpdate(model);
			message.put("state", true);
		}catch (Exception e) {
			message.put("state", false);
			message.put("message", e.getMessage());
			e.printStackTrace();
		}finally {
			writeToJson(message);
		}
	}
	
	/**
	 * 在修改之前先查询这条记录
	 * @return
	 * @throws IOException 
	 */
	@Action("findById")
	public void findById(){
		T s = baseService.findById(uuid);
		writeToJson(s);
	}
	
	@Action("list")
	public void list() {
		List<T> list = baseService.findAll();
		writeToJson(list);
	}
	
	/**
	 * 删除一个或者多条记录
	 * @return
	 * @throws IOException 
	 */
	@Action("delStandard")
	public void delStandard(){
		Map<String,Object> map = new HashMap<>();
		try {
			baseService.delete(ids);
			map.put("state", true);
		} catch (Exception e) {
			map.put("state",false);
			map.put("message", e.getMessage());
		}
		writeToJson(map);
		
	}
	
	protected abstract Specification<T> buildSpecification();
	
	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	@Override
	public T getModel() {
		return model;
	}
}
