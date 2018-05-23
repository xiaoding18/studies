package study_day01.service.impl;

import study_day01.dao.CustomerDAO;
import study_day01.entity.Customer;
import study_day01.service.CustomerService;



public class CustomerServiceImpl implements CustomerService{

	private CustomerDAO dao;
	
	/**
	 * 将一个客户保存到数据库中
	 */
	@Override
	public void saveCustomer(Customer customer) {
		dao.saveCustomer(customer);
	}

	
	/*
	 * 设置一个局部变量dao的set方法,方便spring框架的注入
	 */
	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}
	
	
}
