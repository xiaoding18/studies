package study_DBUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import study_JDBC.DBUtilsConfig;

/**
 * 用来测试DBUtils
 * */
public class Study_DBUtils {

	/*
	 * 如何使用QueryRunner进行查询
	 * 使用QueryRunner进行查询需要有参数,其方法为QueryRunner(Connection con,);
	 * 
	 * */
	private static Connection conn = DBUtilsConfig.getConnection();
	/*public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into test values(?,?,?,?,?)";
		Object[] obj = {100,123,100,10000,5};
		int update = qr.update(conn,sql,obj);
		//关闭连接
		DbUtils.closeQuietly(conn);
		System.out.println(update);
	}*/
	
	/*
	 * 使用QueryRunner进行查询操作
	 * */
	@Test
	public void needQueryRunner() {
		QueryRunner qr = new QueryRunner();
		String sql = "select * from test";
		//使用ArrayHandler的处理方式
//		needArrayHandler(qr, sql);
//		needArrayListHandler(qr, sql);
//		needBeanHandler(qr, sql);
//		needBeanListHandler(sql, qr);
//		needBeanHandler(qr, sql);
//		needColumnListHandler(sql, qr);
//		needMapHandler(sql, qr);
		needMapListHandler(sql,qr);
	}
	
	//使用ArrayHandler的处理方式
	/*
	 * 结果将会是查询到的结果的第一行，并且将会是从第一列一直到最后一列
	 * 需要注意的是如果查询不到结果数组将不是null而是其长度为0
	 * */
	public void needArrayHandler(QueryRunner qr,String sql) {
		try {
			Object[] query = qr.query(conn, sql, new ArrayHandler());
			for(Object o:query) {
				System.out.println(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//使用ArrayListHandler的查询方式
	/*
	 * 结果将会是所有的结果并且以对象数组的方式存在于List集合中
	 * 需要注意的我是如果查询没有结果List集合将不会为空，而是size=0
	 * */
	public void needArrayListHandler(QueryRunner qr,String sql) {
		try {
			List<Object[]> query = qr.query(conn, sql, new ArrayListHandler());
			for(Object[] objs:query) {
				for(Object obj:objs) {
					System.out.print(obj+"  ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//使用BeanHandler的处理方式
	/*
	 * 结果将会是一个封装好的javabean对象,这个对象将会是第一条被查询到的记录，同ArrayHandler的处理方式
	 * 需要注意的是如果没有查询结果，返回的对象将会是null，因为没有数据可以用来封装
	 * 使用BeanHandler处理方式的javabean对象必须要有空参构造方法，否则在查询不到结果的时候将会抛出异常
	 * */
	public void needBeanHandler(QueryRunner qr, String sql) {
		Scanner scan = new Scanner(System.in);
		double d ;
		while(scan.hasNextDouble()) {
			d = scan.nextDouble();
			System.out.println(d);
		}
		
//		try {
//			Person query = qr.query(conn, sql, new BeanHandler<Person>(Person.class));
//			System.out.println(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
	
	//使用BeanListHandler的处理方式
	/*
	 * 将会返回一个封装好的所有的对象的List集合，同ArrayListHandler的处理方式
	 * */
	public void needBeanListHandler(String sql, QueryRunner qr) {
		try {
			List<Person> query = qr.query(conn, sql, new BeanListHandler<Person>(Person.class));
			for(Person p:query) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//使用ColumnHandler的处理方式
	/*
	 * 将会返回一个指定列的Object数组
	 * */
	public void needColumnListHandler(String sql, QueryRunner qr) {
		try {
			List<Integer> query = qr.query(conn, sql, new ColumnListHandler<Integer>("id"));
			for(Integer i:query) {
				System.out.println(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//使用ScalarHandler的处理方式
	/*
	 * ScalarHandler的处理方式特用来处理只需要返回一个结果的sql语句，比如 select count(*) form 表名 这种只需要一个结果
	 * 但是如果查询出来的结果有多个将只会取第一条记录，所以也可以用来取得某个查询结果的第一条
	 * */
	public void needScalarHandler(String sql, QueryRunner qr) {
		try {
			Integer query = qr.query(conn, sql, new ScalarHandler<Integer>("id"));
			System.out.println(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//使用MapHandler的处理方式
	/*
	 * 使用MapHandler处理方式将会封装取得的第一条记录并且其会是一个map集合键值对的形式
	 * */
	public void needMapHandler(String sql, QueryRunner qr) {
		try {
			Map<String, Object> query = qr.query(conn, sql, new MapHandler());
			System.out.println(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//使用MapListHandler的处理方式
	/*
	 * 将会返回一个存储Map集合的List集合
	 * */
	
	public void needMapListHandler(String sql, QueryRunner qr) {
		try {
			List<Map<String, Object>> query = qr.query(conn, sql, new MapListHandler());
			for(Map<String ,Object> map:query) {
				System.out.println(map.getClass());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
