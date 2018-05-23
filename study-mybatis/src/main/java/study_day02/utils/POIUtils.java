package study_day02.utils;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月4日 上午8:10:44   
* 
* readme:
* 	这个类运行的整体流程,在第一次被调用时,将会找到指定的配置文件的路径,然后将其读取到内存中,配置文件中的信息类似于
* 		xxx包.实体类名1={实体类属性1=excel文件对应属性1,实体类属性2=excel文件对应属性2....}
* 		xxx包.实体类名2={实体类属性1=excel文件对应属性1,实体类属性2=excel文件对应属性2....}
* 	即这个类可以对于多个实体类进行封装
* 	需要将properties文件中的value值解析成json对象,然后再创建这个实体类解析专用的Entry对象,Entry对象封装了注入这个实体类的方法
* 	最后再将所有的实体类的Class对象和其对应的Entry解析对象封装成Map集合,这样在使用的时候就不需要读取properties文件了,加快程序执行的速度
* 	
* 	使用:
* 		需要传入一个能表示这个excel文档对象的流,File对象或者是这个Excel文件的path,以及需要封装成的实体类的class对象,即可进行封装
* 	依赖:
* 		使用这个类需要依赖BeanUtils包,如果不能使用BeanUtils需要手动进行注入(我本来是写的手动注入的..忘了BeanUtils了,但是后来删掉了)
* 		如果不是使用jdk1.8,需要重写Entry中的convertor方法
* 	
*/
public final class POIUtils {

	//工具类
	public POIUtils() {}
	
	
	/**
	 * Class属性:
	 * 	表示需要注入的所有的实体类的Class对象
	 * Entry属性:
	 * 	这个实体类具体的注入方式
	 */
	private static Map<Class<?>, Entry> map; 
	
	/**
	 * 配置文件所在的路径
	 */
	private static String configPath = "";
	
	
	/**
	 * 对外只提供这个方法
	 * 提供需要获得的class对象和excel的文件对象,得到注入的结果
	 */
	public <T> T getResource(File file,Class<T> entityClass) {
		return null;
	}
	/**
	 * 对外只提供这个方法
	 * 提供需要获得的class对象和excel的IO流对象,得到注入的结果
	 */
	public <T> T getResource(InputStream in,Class<T> entityClass) {
		return null;
	}
	/**
	 * 对外只提供这个方法
	 * 提供需要获得的class对象和excel的路径对象,得到注入的结果
	 */
	public <T> T getResource(String path,Class<T> entityClass) {
		return null;
	}
	
	
	/**
	 * 对外只提供这个方法
	 * 提供需要获得的class对象和excel的路径对象,得到注入的结果
	 */
	public <T> T getResource(Map<String,String> paramMap,Class<T> entityClass) {
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	static {
		//使用静态代码块对map赋值,只需要加载一次,不使用properties类是因为线程安全,map需要设置为不可让外界访问
		
	}
	
	
	public static void init() {
		try {
			
		}catch (Exception e) {
			System.err.println("找不到XX文件:"+e.getMessage());
		}
	}
	
	/**
	 * 对于注入的属性的完全描述
	* @Description 
	* @author xiaoding
	* @date 2018年4月4日 上午8:22:25
	 */
	private static class Entry{
		/**
		 * 直接从excel中读取到的数据
		 * key属性:
		 * 	excel中的列名(比如用户名)
		 * value属性:
		 * 	这个列名所对应的值(比如"文亮")
		 */
		private Map<String,String> entryMap;
		
		/**
		 * 从properties文件中读取到的原始数据,经过简单处理,变成
		 * key:
		 * 	excel中的列名(比如用户名)
		 * value:
		 * 	实体类的属性名,比如"username"
		 */
		private static Map<String,String> propertiesMap;
		
		/**
		 * 需要注入的对象的引用
		 */
		private Object obj;
		
		
		
		/**
		 * 转换器
		 * 将properties中的属性名和实体类的property建立联系
		 * 即原来在properties文件中是这样的对应关系
		 * 	用户名:username,
		 * 而beanUtils只能识别这样的
		 * 	username:value  --value即username的值
		 * 这就需要一个转换器
		 */
		private void convertor() {
			//获取map中所有的key,并且将其值注入到属性中,是用的jdk1.8吧?(要不是这个该死的异常只需要一行)
			entryMap.forEach((x,y) -> {
				try {
					BeanUtils.setProperty(obj, x, propertiesMap.get(y));
				} catch (Exception e) {e.printStackTrace();}
			});
			
		}
		
		//给定一个构造方法
		public Entry(Map<String,String> map) {
			this.entryMap = map;
		}
		
		
		
		
	}
	
	
	/**
	 * 如果能使用使用框架的destroy方法,并且可以使用xml作为数据的载体的话,则可以将数据写入到xml文件中,这个功能没时间开发
	 */
	public static void destroy() {
		
	}
	
	
	
	
	
	
	
	
	
	
}
