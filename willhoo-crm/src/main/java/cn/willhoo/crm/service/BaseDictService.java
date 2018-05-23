package cn.willhoo.crm.service;

import cn.willhoo.crm.domain.BaseDict;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月13日 下午8:55:35   
*/
@Transactional
public interface BaseDictService {

	List<BaseDict> findAllBaseDict(String type);
}
