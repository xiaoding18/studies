package study_day02.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import study_day02.dao.CustomerDAO;
import study_day02.entity.Customer;
import study_day02.service.CustomerService;

/**
 * 
* @Description  对这个类使用service注解,表示这个类是一个service层的类
* @author xiaoding
* @date 2018年3月20日 下午9:20:49
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	
	@Resource
	private CustomerDAO dao;
	
	/**
	 * 将一个客户保存到数据库中
	 */
	@Override
	public void saveCustomer(Customer customer) {
		dao.saveCustomer(customer);
	}

	
}
