package study_day01.temp;

import java.io.IOException;
import java.util.Properties;

public class BeanFactory {

	/**
	 * 编写工厂类的四个基本步骤
	 * 1. 声明一个现有的工厂对象引用
	 * 2. 将构造方法私有化
	 * 3. 使用静态代码块实例化
	 * 4. 提供一个静态的共有方法,获取工厂实例引用
	 */
	
	//声明一个私有的工厂对象
	private static BeanFactory factory;
	
	//版本二使用的方式
	private static Properties prop;
	
	//构造方法私有化
	private BeanFactory() {}
	
	//使用静态代码块实例化
	static {
		factory = new BeanFactory();
		prop = new Properties();
		try {
			prop.load(ClassLoader.getSystemResourceAsStream("bean.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//提供一个共有的静态方法,获取工厂实例
	public Object getInstance(String instanceName) {
		return null;
	}
}
