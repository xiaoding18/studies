package study_day04.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import study_day04.dao.AccountDao;
import study_day04.entity.Account;
import study_day04.service.AccountService;

/**   
* @Description 
* @author xiaoding
* @date 2018年3月24日 下午3:24:39   
*/
@Service
public class AccountServiceImpl implements AccountService{

	
	@Autowired
	private AccountDao dao;
	
	/**
	 * Transactional注解能打在类上,那么这个类父类的方法(就是toString,equals这种)能不能触发事务?如果有一个类B继承了有Transactional注解的类A,
	 * 那么在类B中调用类A的方法会不会触发事务?
	 * 调用类B本身的方法又会不会触发事务?
	 */
	
	
	/**
	 * 保存一个账号
	 * @param account
	 */
	@Override
	public void saveAccount(Account account) {
		dao.saveAccount(account);
	}


	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}


	@Override
	public void transfer(int srcAccount, int destAccount, double money) {
		//先减少转账人的money
		dao.sub(srcAccount, money);
		
		//然后增加被转账人的钱
		dao.add(destAccount, money);
	}
	
	
	
}
