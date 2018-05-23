package study_day04.main;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import study_day04.entity.Account;
import study_day04.service.AccountService;


/**   
* @Description 
* @author xiaoding
* @date 2018年3月24日 上午9:33:40   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:study_day04/applicationContext.xml")
public class Test_1 {

	@Autowired
	private AccountService service;
	
	/*@Test
	public void Test1() {
		
		//查询到所有的账户信息并且显示
		List<Account> list = service.findAll();
		list.forEach(System.out::println);
		
		//尝试保存一个账号
		Account account = new Account();
		account.setName("小天");
		account.setMoney(500D);
//		service.saveAccount(account);
		
		
		//尝试一次转账
		service.transfer(3, 2, 300);
		
		
	}*/
	
	@Test
	public void Test2() throws SQLException, PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("root");
		dataSource.setJdbcUrl("jdbc:mysql:///message");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		Connection conn = dataSource.getConnection();
		System.out.println(conn.isClosed());
		
	}
}
