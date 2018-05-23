package study_BeanUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Study_BeanUtils {

	//练习BeanUtils的使用
	
	//先练习一下setProperty方法  设置对应JavaBean中的对应属性的值
	//static void setProperty(要被赋值的对象，要被赋值的属性，这个属性的值);
	@Test
	public void testSetProperty() throws Exception, InvocationTargetException {
		User user = new User();
		//通过setProperty方法设置这个对象的username属性
		BeanUtils.setProperty(user, "usernamne", "xiaoding");
		//由于最后一个是Object类型，所以可以直接使用10赋值
		BeanUtils.setProperty(user, "age", 10);
		BeanUtils.setProperty(user,"username","xiaoding2");
		//打印一下user对象
		System.out.println(user);
	}
	
	//使用一下getProperty方法  拿到这个JavaBean的对应的属性
	//static getProperty(需要被取得值的对象，需要被取得的属性);
	@Test
	public void testGetProperty() throws Exception, InvocationTargetException, NoSuchMethodException {
		User user = new User();
		user.setPassword("pass");
		user.setUsername("user");
		System.out.println(user);
		//使用getProperty方法取得值并且打印
		
		System.out.println(BeanUtils.getProperty(user, "username"));
		System.out.println(BeanUtils.getProperty(user, "password"));
	}



	//使用copyProperty方法   将一个javaBean中的数据复制到另一个JavaBean
	//static void copyProperty(需要被复制之后的对象，复制的对象)
	@Test
	public void testCopyProperty() throws Exception{
		User srcUser = new User();
		srcUser.setPassword("password");
		srcUser.setUsername("username");
		//输出一下原来的值
		System.out.println(srcUser);
		User destUser = new User();

		BeanUtils.copyProperties(destUser, srcUser);
		//输出一下更改之后的值
		System.out.println(destUser);
	}


	
	//使用BeanUtils.populate(bean, properties);将一个map集合中的数据封装到JavaBean中
	//static void populate(需要被封装至的对象，充当数据源的map集合);
	@Test

	public void testPopulate() throws Exception {
		Map<String,Object> map = new HashMap<>();
		User user = new User();
		map.put("username", "username");
		map.put("password", "pass");
		map.put("age", 100);
		map.put("school", "雅礼中学");
		//当赋值完毕时,先打印一下map集合中的值
		System.out.println(map);
		//开始赋值
		BeanUtils.populate(user, map);
		//输出一下当前user对象的值
		System.out.println(user);
	}
	
	//使用describe方法,将一个JavaBean中的数据封装到map集合中
	//static Map
	@Test
	public void testDescribe() throws Exception {
		User user = new User();
		user.setPassword("password");
		user.setUsername("username");
		user.setAge(100);
		user.setSchool("雅礼中学");
		//输出一下这个对象
		System.out.println(user);
		//使用方法将其数据封装到map集合中
		Map describe = BeanUtils.describe(user);
		//输出这个map集合中的数据
		System.out.println(describe);
	}
	
	
	@Test
	public void TestDouble() throws IllegalAccessException, InvocationTargetException {
		
		
		
		
		User user = new User();
		BeanUtils.setProperty(user, "age", "12");
		System.out.println(user);
	}
}