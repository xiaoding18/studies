package study_day01.dao.impl;

import study_day01.dao.CustomerDAO;
import study_day01.entity.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	
	/**
	 * 保存一个联系人到数据库中
	 */
	@Override
	public void saveCustomer(Customer customer) {
		System.out.println("将"+customer+"保存至数据库");
		
	}

}
