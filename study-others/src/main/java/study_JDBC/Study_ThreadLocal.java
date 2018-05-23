package study_JDBC;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Study_ThreadLocal {

	//学习ThreadLocal类在数据库中的应用
	/*
	 * ThreadLocal的方法
	 * 	set(Object obj);将一个对象存入ThreadLocal中
	 * 	get();根据当前线程的名字获取到存入的数据
	 * 主要的使用场景
	 * 	当运行多线程程序时，需要有一个静态的ThreadLocal类用来保存数据。这时可以选择使用ThreadLocal来保存数据。
	 * 	会自动获取到当前线程的名字，从而获取到这个线程的保存的数据
	 * */
	
	//创建一个使用线程绑定的DBUtils对象
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();//集合对象
	
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();//数据源
	
	public static Connection getConnection() {
		Connection con = local.get();
		if(con==null) {//如果获取到的连接为null，则将这个连接赋值，并且存入local中
			try {
				con = dataSource.getConnection();
				local.set(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		//保证获取到的con不为空
		return con;
	}
}
