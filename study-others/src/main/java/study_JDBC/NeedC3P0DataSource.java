package study_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

public class NeedC3P0DataSource {

	
	private static QueryRunner qr;
	//使用c3p0的连接池
	/*public static void main(String[] args) throws Throwable {
		//先得到数据源，由于使用了配置文件的方式，所以直接就不需要在做其他的操作了
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		qr = new QueryRunner(dataSource);
		String sql = "delete from user where id=?";
		qr.update(sql,123);
		System.out.println("删除成功");
	}*/
	
	public static void main(String[] str) {
		//测试在相同的线程中获取到的Connection是否是同一个Connection对象
		Connection con = Study_ThreadLocal.getConnection();
		boolean testCon = testCon(con);
		System.out.println(testCon);//true说明获取到的连接对象还是这个连接对象
	}
	
	//还是在这个线程中，测试获取到的线程是否还是这个连接对象
	public static boolean testCon(Connection con) {
		return con.equals(Study_ThreadLocal.getConnection());
	}
	
	//测试使用事务进行操作
	public static void testTransaction() throws SQLException {
		//先获取到一个连接对象
		Connection con = JDBCUtils.getConnection();
		try {
			//开启一个事务
			con.setAutoCommit(false);
			//下面开始执行SQL语句
			PreparedStatement pst = con.prepareStatement("");
			pst.executeUpdate();
			//提交事务
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			//当程序出现异常的时候回滚事务
			con.rollback();
		}
	}
	
	//对于使用DBUtils来使用事务的方法
	public static void needDBUtilsForTransaction() throws SQLException {
		//不能使用连接池来创建QueryRunner对象
		//需要直接获取到QueryRunner对象
		QueryRunner qr = new QueryRunner();
		Connection con = JDBCUtils.getConnection();
		try {
			qr.update(con,"");//在这里做对于数据库的操作
			DbUtils.commitAndClose(con);
		} catch (SQLException e) {
			e.printStackTrace();
			//当出现异常的时候将会回滚事务
			DbUtils.rollback(con);
		}
	}
}
