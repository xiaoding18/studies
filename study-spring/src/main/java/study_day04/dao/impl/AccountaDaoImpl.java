package study_day04.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import study_day04.dao.AccountDao;
import study_day04.entity.Account;

/**   
* @Description 
* @author xiaoding
* @date 2018年3月24日 下午12:32:57   
*/
@Repository
public class AccountaDaoImpl implements AccountDao {

	@Autowired
	private JdbcTemplate jt;
	
	@Override
	public void saveAccount(Account account) {
		String sql = "insert into account values(?,?,?)";
		Object[] param = {account.getId(),account.getName(),account.getMoney()};
		jt.update(sql,param);
	}

	@Override
	public void sub(int id, double money) {
		String sql = "update account set money = money - ? where id = ?";
		Object[] param = {money,id};
		int update = jt.update(sql, param);
		System.out.println(update);
		int i = 1/0;
	}

	@Override
	public void add(int id, double money) {
		String sql = "update account set money = money + ? where id = ?";
		Object[] param = {money,id};
		jt.update(sql, param);
	}

	@Override
	public List<Account> findAll() {
		String sql = "select * from account";
		List<Account> result = jt.query(sql,new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account account = new Account();
				account.setId(rs.getInt("id"));
				account.setName(rs.getString("name"));
				account.setMoney(rs.getDouble("money"));
				return account;
			}
		});
		
		
		List<Account> query = jt.query(sql, new RowMapper<Account>() {

			@Override
			public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
				Account account = new Account();
				account.setId(rs.getInt("id"));
				account.setName(rs.getString("name"));
				account.setMoney(rs.getDouble("money"));
				return account;
			}
			
		});
		return result;
	}

}
