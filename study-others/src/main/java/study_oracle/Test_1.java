package study_oracle;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.OutParameter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import oracle.jdbc.driver.OracleResultSet;
import oracle.jdbc.driver.OracleTypes;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月1日 上午10:21:21   
*/

public class Test_1 {
	
	private QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
	
	/**
	 * 测试oracle数据库连接
	 * @throws Throwable
	 */
	//@Test
	public void Test1() throws Throwable {
		String sql = "select * from emp";
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
		list.forEach(System.out::println);
	}
	
	/**
	 * 测试使用jdbc执行存储过程
	 */
//	@Test
	public void Test_2() throws Exception{
		String sql = "{call pro_up_sal(?,?,?)}";
		Connection conn = qr.getDataSource().getConnection();
		//获取到可以执行存储过程的对象
		CallableStatement call = conn.prepareCall(sql);
		
		call.setInt(1, 7369);
		call.setInt(2, 100);
		
		//将第三个参数声明成为一个输出型参数
		call.registerOutParameter(3, OracleTypes.NUMBER);
		
		//执行这个存储过程
		call.execute();
		
		//获取到这个存储过程的输出值
		Object result = call.getObject(3);
		System.out.println(result);
	}
	
	/**
	 * 测试使用dbutils执行存储过程(有输出参数的)
	 * @throws Exception 
	 */
	
	public void Test3() throws Exception {
		String sql = "{call pro_up_sal(?,?,?)}";
		OutParameter<BigDecimal> out = new OutParameter<>(OracleTypes.NUMBER,BigDecimal.class);
		Object[] param = {7369,100,out};
		int execute = qr.execute(sql,param);
		System.out.println(execute);
		//得到输出的结果
		System.out.println("现有的工资:"+out.getValue());
		
	}
	
	/**
	 * 使用jdbc操作输出参数为游标的存储过程
	 * @throws Exception 
	 */
	@Test
	public void Test_4() throws Exception {
		
		String sql = "{call pro_find_all_by_dept(?,?)}";
		Connection conn = qr.getDataSource().getConnection();
		CallableStatement call = conn.prepareCall(sql);
		//注册第一个普通输入类型的参数
		call.setInt(1, 10);
		
		//注册第二个输出,游标类型的参数
		call.registerOutParameter(2, OracleTypes.CURSOR);
		//执行sql
		call.execute();
		
		//获取到执行结果
		
		//获取真正得到的结果
		OracleResultSet result = (OracleResultSet) call.getObject(2);
		while(result.next()) {
			System.out.println(result.getString("ename"));
		}
		System.gc();
	}
	/**
	 * 使用dbutils来指向带有参数,并且返回值为游标的存储过程
	 * @throws Throwable
	 */
	
	public void Test_5() throws Throwable {
		String sql = "";
		OutParameter<ResultSet> outParameter = new OutParameter<>(OracleTypes.CURSOR,ResultSet.class);
		Object[] params = {10,outParameter};
		qr.execute(sql, params);
		
		//遍历结果集
		ResultSet result = outParameter.getValue();
		while(result.next()) {
			System.out.println(result.getInt(1));
		}
	}
}
