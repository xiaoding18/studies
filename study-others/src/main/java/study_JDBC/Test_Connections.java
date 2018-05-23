package study_JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test_Connections {

	private static Connection con;
	static {
		con = JDBCUtils.getConnection();
	}
	
	//下面开始暴力插入数据
	public static void main(String[] args) throws Exception {
		
		for(int i=0;i<10;i++) {
			String sql = "";
			PreparedStatement pst = con.prepareStatement("");
			pst.executeUpdate();
		}
	}
}
