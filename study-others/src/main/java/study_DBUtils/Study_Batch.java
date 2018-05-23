package study_DBUtils;

import java.sql.PreparedStatement;

import org.junit.Test;


import study_JDBC.JDBCUtils;

//主要学习批处理
public class Study_Batch {

	//批处理默认mysql也是关闭的，如果需要打开那么需要在连接参数添加参数： 
	//MySQL的批处理也需要通过参数来打开：rewriteBatchedStatements=true
	
	/*
	 * 当没有开启mysql数据库的批处理的时间结果为:
	 * 	不使用批处理的时间为:5427
	 * 	使用批处理的时间为:4879
	 * 当开启mysql数据库的批处理的时间结果为:
	 * 	不使用批处理的时间为:5282
	 *	使用批处理的时间为:62
	 *
	 * */

	@Test
	public void Question_1() throws Throwable {
		long notTime = notUseBatch();
		long isTime = isUseBatch();
		System.out.println("不使用批处理的时间为:"+notTime);
		System.out.println("使用批处理的时间为:"+isTime);
	}
	//使用批处理向数据库中插入1000条数据，并且统计时间
	//注:这次不会在连接参数中开启mysql的批处理
	public long isUseBatch() throws Throwable {
		long startTime = System.currentTimeMillis();
		//以下为执行代码
		String sql = "insert into testing values(?,?)";
		PreparedStatement pst = JDBCUtils.getConnection().prepareStatement(sql);
		for(int i=0;i<1000;i++) {
			pst.setInt(1, i);
			pst.setString(2, "小明"+i+"号");
			pst.addBatch();
		}
		//执行批处理
		pst.executeBatch();
		pst.clearBatch();
		long endTime = System.currentTimeMillis();
		return endTime-startTime;
	}
	
	//不使用批处理向数据库中插入数据，并且统计时间
	public long notUseBatch() throws Throwable {
		long startTime = System.currentTimeMillis();
		//以下为执行代码
		String sql = "insert into testing values(?,?);";
		PreparedStatement pst = JDBCUtils.getConnection().prepareStatement(sql);
		for(int i=0;i<1000;i++) {
			pst.setInt(1, i);
			pst.setString(2, "小明"+i+"号");
			//直接就执行
			pst.execute();
		}
		long endTime = System.currentTimeMillis();
		return endTime-startTime;
	}
}
