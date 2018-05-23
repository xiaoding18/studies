package study_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 用来实现获取到数据库的工具
 * */
public class JDBCUtils {

	private static final String username= "root";
	private static final String password="root";
	private static final String url = "jdbc:mysql://localhost:3306/xiaoding?rewriteBatchedStatements=true";
	private static final String driverName="com.mysql.jdbc.Driver";
	//私有化构造方法
	private JDBCUtils() {}
	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库的连接
	 * */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	/**
	 * 关闭数据库所使用的资源，释放资源
	 * */
	public static void close(ResultSet rs,Connection conn,Statement stat) {
		try {
			if(rs!=null) {
				rs.close();
			}
		}catch (Exception e) {
		}finally {
			try {
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
			}finally {
				try {
					if(stat!=null) {
						stat.close();
					}
				}catch (Exception e) {
				}
			}
		}
	}
}
