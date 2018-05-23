package study_JDBC;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

public class NeedBasicDataSource {

	private static QueryRunner qr;
	//使用DBCP连接池获得DataSource对象
	//将会使用配置文件的方式
	public static void main(String[] args) throws Exception {
		Properties pro = new Properties();
		//加载Properties
		pro.load(NeedBasicDataSource.class.getClassLoader().getResourceAsStream("database.properties"));
		DataSource dataSource = BasicDataSourceFactory.createDataSource(pro);
		qr = new QueryRunner(dataSource);
		String sql = "insert into user values(?,?,?)";
		qr.update(sql,2,"xiaoding","123456");
		System.out.println("执行成功");
	}
	

}
