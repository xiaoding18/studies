package cn.willhoo.crm.mapper;

import cn.willhoo.crm.domain.BaseDict;

import java.util.List;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月13日 下午4:29:48   
*/
public interface BaseDictMapper {

	/**
	 * 根据类型从字典表中获取到所有的指定数据
	 * @param type
	 * @return
	 */
	List<BaseDict> findAllBaseDict(Long type);

}
