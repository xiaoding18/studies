package study_day03.service.impl;

import org.springframework.stereotype.Service;

import study_day03.service.CustomerService;

/**   
* @Description 这是一个
* @author xiaoding
* @date 2018年3月23日 上午10:35:23   
*/
@Service
public class CustomerServiceImpl implements CustomerService{

	@Override
	public void saveCustomer() {
		System.out.println(this.getClass());
		System.out.println("保存成功");
	}

	@Override
	public void findCustomer() {
		
	}

}
