package study_reflet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Study_Reflet {
	//学习java的反射的使用
	public static void main(String[] args) throws Exception {
		/*//先使用普通的方法创建对象,使用全餐构造方法
		Person p = new Person("肖鼎",20);
		p.eat();
		//然后使用反射来创建对象
		Class<Person> c = Person.class;
		//使用空参创建对象
		Object person = c.newInstance();
		//运行这个类的普通方法
		System.out.println(person.toString());
		//运行这个类的特有方法
		Person p1 = (Person)person;
		p1.eat();
		//使用这个类的全参构造方法
		Object person2 = c.newInstance();*/
		//运行方法
//		needConstractor();
//		needConstractorByPrivate();
//		needFields();
//		needMethods();
		changeArrayList();
		
	}
	//使用反射获取对象的构造方法然后创建一个新的对象
	public static void needConstractor() throws Exception, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//创建一个Person的对象
		Class<Person> c = Person.class;
		//获取这个对象的构造方法
		Constructor[] cons = c.getConstructors();
		//打印构造方法
		for(Constructor c2:cons) {
//			System.out.println(c2);
		}
		//使用构造方法创建对象
		//获取一个指定参数列表的构造方法
		Constructor<Person> constructor = c.getConstructor(String.class,int.class);
		Person newInstance = constructor.newInstance("肖鼎",20);
		newInstance.eat();
	}
	
	//获取到方法的私有构造方法,并且new出对象
	public static void needConstractorByPrivate() throws Exception, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<Person> c = Person.class;
		Constructor[] cons = c.getDeclaredConstructors();
		//打印出这个对象的所有的构造方法
		for(Constructor<Person> con:cons) {
			System.out.println(con);
		}
		try {
			//尝试直接使用私有的构造方法创建对象
			Person instance = (Person) cons[0].newInstance();
		}catch (Exception e) {
			System.out.println("直接调用私有的构造方法"+cons[0]+"失败!");
		}
		//现在先将私有化的构造方法转换掉,具体的实现方法是调用其父类的方法setAccessible()
		cons[0].setAccessible(true);
		//忽略私有化之后使用构造方法创建对象
		Object instance = cons[0].newInstance();
		System.out.println(instance);
	}
	
	/**
	 * 使用反射获取对象的Class对象并且修改其变量的值
	 * @throws Exception 
	 * @throws Exception 
	 * @throws InstantiationException 
	 * */
	public static void needFields() throws Exception {
		Class<Person> c = Person.class;
		//拿到对象
		Person person = c.newInstance();
		System.out.println(person);
		//拿到对象的参数
		Field field = c.getDeclaredField("username");
		try {
			
			//修改指定对象的这个字段得到值
			field.set(person, "xiaoding");
			System.out.println(person);
		} catch (Exception e) {
			System.out.println("直接拿是不能拿到private字段的");
		}
		System.out.println("现在开始忽略它的权限");
		field.setAccessible(true);
		//继续执行
		field.set(person, "李凯");
		System.out.println(person);
	}
	
	/**
	 * 获取到类的方法并且执行
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * */
	public static void needMethods() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<Person> c = Person.class;
		
		//拿到对象
		Person person = c.newInstance();
		person.setUsername("肖鼎");
		System.out.println(person);
		//拿到对象的参数
		Method method = null;
		try {
			method = c.getDeclaredMethod("privateEat");
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//直接使用这个方法
			System.out.println(person);
		} catch (Exception e) {
			System.out.println("直接拿是不能拿到private方法的");
		}
		System.out.println("现在开始忽略它的权限");
		method.setAccessible(true);
		method.invoke(person);
		System.out.println(person);
	}
	
	/**
	 * 创建一个ArrayList的，带有泛型的对象,然后使用反射添加一个Integer的对象,然后遍历这个List
	 * @throws Exception 
	 * */
	public static void changeArrayList() throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		list.add("好的");
		//得到ArrayList的class对象
		Class c = list.getClass();
		//获取到它的方法
		Method method = c.getMethod("add", Object.class);
		//执行这个方法
		method.invoke(list, 1);
		//先输出一下这个集合
		System.out.println(list);
		/*//下面开始迭代这个集合,由于添加了Integer类型所以会报错
		for(String str:list)
			System.out.println(str);*/
		//现在使用Object类型来进行迭代
		for(Object obj:list)
			System.out.println(obj);
		
		
	}
	
	
}
