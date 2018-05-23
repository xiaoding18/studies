package study_time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class StudyTimes {

	
	public void Testing_1() throws ParseException{
		//创建日期时间对象
		Date date = new Date();
		//使用日期格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日，HH时mm分ss秒");
		//将时间格式化
		String times = sdf.format(date);
		System.out.println(sdf.parse(times));
		System.nanoTime();
		
	}
	@Test
	public void Question_1(){
		Student stu1 = new Student("小明","1234");
		Student stu2 = new Student("小明","1234");
		System.out.println(stu1 == stu2);
		System.out.println(stu1.equals(stu2));
		
		List<Student> listStu = new ArrayList<Student>();
		
		listStu.add(stu1);
		System.out.println(listStu.contains(stu2));
	}
}
