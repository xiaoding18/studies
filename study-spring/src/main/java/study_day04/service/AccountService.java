package study_day04.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import study_day04.entity.Account;

/**   
* @Description 
* @author xiaoding
* @date 2018年3月24日 下午3:24:59   
*/
/*
 * 在这里,我们可以配置这个接口的默认值,通常我们只需要配置以下参数即可
 * read-only:
 * 	用来配置是否只读,很明显除了读取操作其他的都是false,默认值是true
 * propagation:
 * 	配置事务的传播方式,默认值是required,即将会主动传播,读取操作的话我们配置support(支持)就好了
 */
@Transactional//使用这个标签可以将这个接口声明成为一个包含事务的接口
public interface AccountService {

	/**
	 * 获取到所有的用户信息
	 * 因为这个是读取操作,并且这个接口中只有一个读取的方法,所以我们选择配置读取操作
	 * 配置读取操作为只读,支持事务
	 */
	@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
	List<Account> findAll();
	
	/**
	 * 添加一个账号
	 */
	void saveAccount(Account account);
	
	/**
	 * 进行一次转账
	 * @param srcAccount   转账发起人
	 * @param destAccount  被转账人
	 * @param money        转账金额
	 */
	void transfer(int srcAccount, int destAccount, double money);
}
