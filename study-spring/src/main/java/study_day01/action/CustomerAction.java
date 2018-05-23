package study_day01.action;

import study_day01.entity.Customer;
import study_day01.service.CustomerService;

public class CustomerAction {
	
	
	/**
	 * 顺便注入一下这个action吧
	 */
	private CustomerService service;

	public CustomerAction(CustomerService service) {
		this.service = service;
	}
	
	
	public void saveCustomer(Customer customer) {
		service.saveCustomer(customer);
	}
}
