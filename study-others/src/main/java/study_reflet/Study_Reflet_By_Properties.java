package study_reflet;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 使用配置文件的方式来运行程序,使用配置文件的方式可以完美的控制程序中需要被执行的方法
 * 下面是步骤
 * 1.创建一个配置文件表示这个方法中需要被运行的方法,以及参数的值
 * 2.主程序中获取到配置文件中的属性
 * 3.为成员变量赋值，然后执行方法
 * */
public class Study_Reflet_By_Properties {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception, IOException {
		//先获取到properties文件中的键值对
		Properties pro = new Properties();
		//加载properties集合
		pro.load(new FileInputStream("bin/study_reflex/reflex_by_properties.properties"));
		//拿到需要被执行的类的Class
		Class c = Class.forName(pro.getProperty("className"));
		//创建这个类的对象
		Object object = c.newInstance();
		//获取到需要被赋值的所有的参数
		String[] fields = pro.get("fields").toString().split(",");
		for(int i=0;i<fields.length;i++) {
			//拿到这个参数
			Field field = c.getField(fields[i]);
			//为这个参数赋值
			//先拿到这个参数对应的set方法
			String methodName = "set"+getMethodByGet(fields[i]);
			//获取这个方法并且运行
			try {
				String s = "field"+getMethodByGet(fields[i]);
				c.getMethod(methodName, String.class).invoke(object, pro.get(s));
			}catch (Exception e) {
				System.err.println("NoSuchMethodException"+pro.get("field"+getMethodByGet(fields[i])));
			}
		}
		//参数赋值完毕之后开始运行方法
		//获取到所有的方法
		String[] methods = pro.get("methods").toString().split(",");
		//开始执行每一个方法
		for(String method:methods) {
			//先获取到这个方法的所有的形参
			fields = pro.get("method"+method).toString().split(",");
			//生成参数数组
			Class[] classArray = new Class[fields.length];
			//为classArray赋值
			for(int i=0;i<fields.length;i++) {
				classArray[i] = Class.forName(fields[i]);
			}
			//得到所有的实参
			fields = pro.get("method"+method+"Fields").toString().split(",");
			//获取到方法
			String s=c.getMethod(method, classArray).getName();
			c.getMethod(method, classArray).invoke(object, fields);
		}
	}
	/*
	 * 创建一个方法获取这个参数的get方法
	 * */
	public static String getMethodByGet(String field) {
		if(field.length()==1)
			return field.toUpperCase();
		if(field.charAt(0)<'z' && field.charAt(0)>'a') {
			return (char)(field.charAt(0)-32)+field.substring(1);
		}
		return field;
	}
}
