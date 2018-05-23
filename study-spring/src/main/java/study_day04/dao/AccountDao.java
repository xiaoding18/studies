package study_day04.dao;

import java.util.List;

import study_day04.entity.Account;

/**   
* @Description 
* @author xiaoding
* @date 2018年3月24日 下午12:32:41   
*/
public interface AccountDao {

	/**
	 * @param account
	 */
	void saveAccount(Account account);
	
	
	/**
	 * 从一个人的money中扣除指定数值
	 */
	void sub(int id, double money);
	
	
	/**
	 * 为一个人的money增加指定的值
	 */
	void add(int id, double money);
	
	/**
	 * 从数据库中查询所有人的信息
	 */
	List<Account> findAll();
}
