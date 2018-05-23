package cn.willhoo.crm.service;

import org.springframework.transaction.annotation.Transactional;

import cn.willhoo.crm.domain.Customer;
import cn.willhoo.crm.domain.QueryVo;
import cn.willhoo.crm.utils.Page;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月13日 下午4:29:00   
*/
@Transactional
public interface CustomerService {

	/**
	 * 条件查询
	 */
	Page<Customer> findAllByCondition(QueryVo qv);
	
	/**
	 * 根据Id获取一个用户的信息
	 */
	Customer findCustomerById(String id);
	
	/**
	 * 修改,保存一个用户的信息
	 */
	void updateCustomer(Customer customer);
}
