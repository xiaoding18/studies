package cn.willhoo.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.willhoo.crm.domain.Customer;
import cn.willhoo.crm.domain.QueryVo;
import cn.willhoo.crm.mapper.CustomerMapper;
import cn.willhoo.crm.service.CustomerService;
import cn.willhoo.crm.utils.Page;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月13日 下午8:56:07   
*/
@Service
public class CustomerSerivceImpl implements CustomerService{

	@Autowired
	private CustomerMapper mapper;
	
	@Override
	public Page<Customer> findAllByCondition(QueryVo qv) {
		Integer start = (qv.getPage()-1)*qv.getRows();
		qv.setStart(start);
		Page<Customer> page = new Page<>();
		page.setRows(mapper.findAllByCondition(qv));
		
		page.setTotal(mapper.findCountByCondition(qv).intValue());
		return page;
	}

	@Override
	public Customer findCustomerById(String id) {
		return mapper.findCustomerById(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		mapper.updateCustomer(customer);
	}

}
