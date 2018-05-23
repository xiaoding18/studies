package cn.willhoo.crm.mapper;

import cn.willhoo.crm.domain.Customer;
import cn.willhoo.crm.domain.QueryVo;

import java.util.List;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月13日 下午4:28:34   
*/
public interface CustomerMapper {

	
	/**
	 * 根据条件查询所有符合条件的customer
	 * @param qv
	 * @return
	 */
	List<Customer> findAllByCondition(QueryVo qv);
	
	Long findCountByCondition(QueryVo qv);

	/**
	 * @param id
	 * @return
	 */
	Customer findCustomerById(String id);

	/**
	 * @param customer
	 */
	void updateCustomer(Customer customer);
}
