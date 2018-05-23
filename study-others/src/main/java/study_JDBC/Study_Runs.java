package study_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Study_Runs {

	public static void main(String[] args) throws SQLException {
		//先获取数据库的连接
		Connection connection = DBUtilsConfig.getConnection();
		//拿到连接之后尝试取得数据
		System.out.println(connection);
		/*String sql = "select * from test where id=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setObject(1, 10);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("id")+"   "+rs.getString("name")+"   "+rs.getString("age")+"   "+rs.getString("score"));
		}
		//关闭数据库连接
		DBUtilsConfig.close(rs, connection, pst);*/
	}
}
