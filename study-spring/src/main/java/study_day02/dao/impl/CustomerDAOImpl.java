package study_day02.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import study_day02.dao.CustomerDAO;
import study_day02.entity.Customer;

/**
 * 对这个类使用repository注解,表示这个类是持久层的一个bean
* @Description:将客户信息保存至数据库的DAO
* @author xiaoding
* @date 2018年3月20日 下午9:20:05
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private QueryRunner qr;
	
	/**
	 * 保存一个联系人到数据库中
	 */
	@Override
	public void saveCustomer(Customer customer) {
		
//		String sql = "insert into customer_test values(?,?,'男')";
//		Object[] param = {customer.getId(),customer.getName()};
		String sql = "insert into customer_test values(?,?,?)";
		Object[] param = {customer.getId(),customer.getName(),'男'};
		System.out.println(customer.getSex());
		try {
			qr.execute(sql, param);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		System.out.println("将"+customer+"保存至数据库");
	}

}
