package cn.willhoo.crm.service.impl;

import cn.willhoo.crm.domain.BaseDict;
import cn.willhoo.crm.mapper.BaseDictMapper;
import cn.willhoo.crm.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月13日 下午8:55:54   
*/
@Service
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictMapper dao;
	
	@Override
	public List<BaseDict> findAllBaseDict(String type) {
		return dao.findAllBaseDict(Long.parseLong(type));
	}

}
