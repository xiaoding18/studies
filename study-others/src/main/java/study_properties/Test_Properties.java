package study_properties;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

public class Test_Properties {

	//使用流文件向文件中写入Properties数据
	@Test
	public void Question_1() throws IOException{

		//先创建一个文件写入对象
		FileWriter fw = new FileWriter("test.properties");
		Properties pro = new Properties();
		//向Propertis集合里面写入数据
		pro.setProperty("this is key1", "this is value1");
		pro.setProperty("this is key2", "this is value2");
		pro.setProperty("this is key3", "this is value3");
		//打印一下这个集合
		System.out.println(pro);
		//迭代这个集合,由于该集合继承了Hashtable类所以可以使用map集合迭代的类似的方式迭代
		//先获得所有的key
		Set<String> stringPropertyNames = pro.stringPropertyNames();
		//迭代Set集合
		for(String str:stringPropertyNames){
			System.out.println(str+"..."+pro.getProperty(str));
		}
		pro.store(fw, "修改");
		fw.close();
		Question_2();
	}
	
	//使用已经加载好的键值对
	public void Question_2() throws IOException{
		FileReader fr = new FileReader("test.properties");
		Properties p = new Properties();
		p.load(fr);
		fr.close();
		System.out.println(p);
	}
	
	
	
}
