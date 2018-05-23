package cn.willhoo.ssh_bos.utils.init;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import cn.willhoo.ssh_bos.utils.test.Test2;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月15日 上午8:47:47   
*/
public class BaseUtils {
	
	
	/**
	 * 获取父类的泛型
	 * 
	 * 获取所有这个泛型类型的变量
	 * 
	 * 对于每一个变量都new一下,然后和getModel方法中的地址比较一下
	 * 
	 * 如果地址相同就认为这个就是模型
	 * 如果地址不同就把这个对象再次赋值为null
	 */
	
	/**
	 * 获取一个class中所有type类型的变量
	 * @param clazz 作为主体的Class对象
	 * @param type 需要获取的变量的类型
	 * @return
	 */
	public static List<Field> getFieldByType(Class clazz, Class type) {
		Field[] fields = clazz.getDeclaredFields();
		List<Field> list = Arrays.stream(fields)
							.filter(x -> type == x.getType())
							.collect(Collectors.toList());
		return list;
	}
	
	/**
	 * 对于每一个对象都new一下,然后将其和getModel方法中获取到的参数进行比较
	 * 如果其地址等于getModel方法中获取到的地址,则保留退出这个循环
	 * 这个方法不需要返回值
	 * @param obj 对应的action对象
	 * @param list 现有的参数列表
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static void FieldHandler(Object obj,List<Field> list) throws NoSuchMethodException, SecurityException {
		
		//得到getModel方法
		Method method = obj.getClass().getMethod("getModel");
		
		boolean b = list.stream()
					 	.peek(x -> x.setAccessible(true))//设置每个元素都能被访问
					 	.anyMatch(x -> invokeMethod(obj, method, x));//如果找到了任何一个能匹配的,就退出
		if(!b) {
			throw new RuntimeException("注入失败!");
		}
	}
	
	/**
	 * 执行方法进行比较
	 * @param obj
	 * @param method
	 * @param field
	 * @return
	 */
	private static boolean invokeMethod(Object obj,Method method,Field field) {
		try {
			//得到实例对象
			Object instance = field.getType().newInstance();
			//为这个参数设置值
			field.set(obj, instance);
			//调用方法进行比较,如果能匹配就返回true,如果不能匹配就将对象设置为null
			Object invoke = method.invoke(obj);
			if(method.invoke(obj) == instance) {
				return true;
			};
			field.set(obj,null);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	/**
	 * 支持的模糊搜索类型
	 */
	private static String[] types = {
			
			"Action",
			"Service",
			"Dao"
	};
	
	/**
	 * 提供一种更加方便的方式,即自动检测当前类的类名的后缀名
	 */
	public static void init(Object obj) {
		
		
		//1.获取当前类的简单类名
		String name = obj.getClass().getSimpleName();
		//2.查看当前类的类名中包含types中的哪个字符串
		String type = null;
		for(int i=0;i<types.length;i++) {
			if(name.contains(types[i])) {
				type = types[i+1];
			}
		}
		//如果还是没有找到所需要的类型,则抛出异常
		if(type == null) {
			return ;
		}
		//3.将那个字符串当做init方法的参数
		//如果是action则需要注入额外的参数,需要将model注入
		//在这里模型驱动的值只能是model....我不知道怎么才能获取到这个Model的值
		init(obj,type);
	}
	
	/**
	 * 步骤如下:
	 * 先查询具体类(如AreaService)中是否含有%service%的变量
	 * 如果有这个变量
	 * 查找父类的baseService
	 * 为父类的baseService赋值
	 * @param obj
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public static Object init(Object obj,String name){
		Class clazz = obj.getClass();
		try {
			
			//1.查询具体类中是否含有%service%的变量
			String fieldName = findNameByClass(clazz, name);
			Field field = findFieldByClass(clazz,fieldName);
			System.out.println(obj.getClass());
			//如果没有这个变量就更换搜索方式
			if(field == null) {
				field=findLikeFieldByClass(clazz, name);
				//如果还是查询不到,则抛出异常
				if(field == null) {
					if(clazz.getSimpleName().contains("Action")|| clazz.getSimpleName().contains("action")) {
						return actionOptions(clazz);
					}
				}
			}
			//2.查找父类中的baseService,父类中只允许存在于一个baseService
			Field superField = findLikeFieldByClass(clazz.getSuperclass(),name);
			if(superField == null) {
				return null;
			}
			
			//3.为父类中的baseService赋值
			superField.set(obj, field.get(obj));
		}catch (Exception e) {
			e.printStackTrace(System.out);
			throw new RuntimeException(e.getMessage());
			
		}
		return null;
	}
		
	
	private static Object actionOptions(Class clazz) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		Class superClass = clazz.getSuperclass();
		//得到model的参数
		Field model = superClass.getDeclaredField("model");
		model.setAccessible(true);
		Class<?> type = model.getType();
		Class modelType = getSuperGenericByClass(clazz);
		System.out.println(modelType);
		return modelType.newInstance();
	}
	
	/**
	 * 根据名字查询一个class中是否含有这个name所表示的Field,如果含有则会返回这个Field
	 * @param clazz
	 * @param name
	 * @return
	 */
	private static Field findFieldByClass(Class clazz,String name) {
		String daoName = findNameByClass(clazz,name);
		for(Field field:clazz.getDeclaredFields()) {
			//如果确实有这个变量
			if(field.getName().equals(daoName)) {
				field.setAccessible(true);
				return field;
			}
		}
		return null;
	}

	//根据class查找具体类中应该含有的这个变量
	private static String findNameByClass(Class superClass,String name){
		//获取父类上的泛型
		Class clazz = getSuperGenericByClass(superClass);
		// 得到简单类名
		String simpleName = clazz.getSimpleName();
		
		//首字母大写
		name = StringUtils.capitalize(name);
		
		return StringUtils.uncapitalize(simpleName + name);
	}
	
	//获取这个类父类上的泛型,如果父类上有多个泛型,将只会返回第一个泛型
	@SuppressWarnings("rawtypes")
	private static Class getSuperGenericByClass(Class superClass) {
		// 先获取类名所代表的dao
		Type superType = superClass.getGenericSuperclass();
		// 得到泛型
		ParameterizedType pt = (ParameterizedType) superType;

		Class clazz = (Class) pt.getActualTypeArguments()[0];
		return clazz;
	}
	
	
	/**
	 * 根据名字和参数列表查找一个field
	 * 如果没有这个field则会返回null
	 * @param fields
	 * @param name
	 * @return
	 */
	private static Field findLikeFieldByClass(Class clazz,String name) {
		Field[] fields = clazz.getDeclaredFields();
		for(Field field:fields) {
			if(field.getName().matches(getRegexByName(name))) {
				field.setAccessible(true);
				return field;
			}
		}
		return null;
	}
	
	//根据Name获取根据这个name所生成的正则
	private static String getRegexByName(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(".*[");
		char c = name.charAt(0);
		sb.append(Character.toUpperCase(c)+""+Character.toLowerCase(c));
		sb.append("]");
		//如果字符串的长度正好为1(级只有一个字符)则只会拼接一个空字符串
		sb.append(name.substring(1));
		return sb.toString();
	}
	
//	@Test
	public void Test1() {
		String name = "1";
		System.out.println(name.substring(1).length());
	}
	
	@Test
	public void Test2() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Test2 t2 = new Test2();
		Field field2 = t2.getClass().getSuperclass().getDeclaredField("username");
		Field field = t2.getClass().getDeclaredField("password");
		
		
		field.setAccessible(true);
		field2.setAccessible(true);
		
		field2.set(t2, "这个是username");
		field.set(t2, "这个是password");
		
		System.out.println(t2);
	}
	
	private String test;
	
	@Test
	public void Test3() {
		List<Field> fieldByType = getFieldByType(this.getClass(), String.class);
		System.out.println(fieldByType);
		
		List<TestC> list = new ArrayList<>();
		list.add(new TestC());
		list.add(new TestC());
		list.add(new TestC());
		list.add(new TestC());
		list.stream().peek(TestC::out).forEach(System.out::println);;
	}
	
	private class TestC {
		public void out() {
			System.out.println(1);
		}
	}
}
