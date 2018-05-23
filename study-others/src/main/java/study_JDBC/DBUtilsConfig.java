package study_JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


/**
 * 通过配置文件设置的工具类
 * */
public class DBUtilsConfig {

	private DBUtilsConfig() {
	}
	private static final String username;
	private static final String password;
	private static final String driverClass;
	private static final String url;
	private static final String hostName;
	static {
		//加载文件的内容
		InputStream in = DBUtilsConfig.class.getClassLoader().getResourceAsStream("database.properties");
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new RuntimeException("数据配置文件不存在!");
		}
		username = pro.getProperty("username");
		password = pro.getProperty("password");
		driverClass = pro.getProperty("driverClass");
		url = pro.getProperty("url");
		hostName = pro.getProperty("hostName");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("加载驱动失败!");
		}
	}
	/**
	 * 获取一个数据库的链接
	 * */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取数据库连接失败!");
		}
		return connection;
	}
	/**
	 * 关闭数据库所使用的资源，释放资源
	 * */
	public static void close(ResultSet rs,Connection conn,PreparedStatement stat) {
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
	public static void main(String[] args) {
		Fu fu = new Zi();
		fu.outs();
		
		Zi zi = new Zi();
		zi.outs();
		
		new Zi().outs();
	}
}


class Fu {
	public void outs() {
		System.out.println("我是爸爸");
	}
}
class Zi extends Fu{
	@Override
	public void outs() {
		System.out.println("我是儿子");
	}
	
}
