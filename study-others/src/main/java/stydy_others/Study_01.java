package stydy_others;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Study_01 {

	/**
	 * 用来测试关于Integer对于常量池中的取值范围
	 * */
	public void Question_1(){
		
		//比较对于新建对象的应用
		Integer i1 = new Integer(12);
		Integer i2 = new Integer(12);
		System.out.println(i1 == i2);                 //false 证明直接创建对象的地址不同
		System.out.println(i1.equals(i2));            //true  说明Integer中重写了equals方法
		
		Integer i3 = 127;
		Integer i4 = 127;
		System.out.println(i3 == i4);                 //true  证明两个地址值相同
		
		
		Integer i5 = 128;
		Integer i6 = 128;
		System.out.println(i5 == i6);                  //false 证明当值超过byte范围时将会创建一个新的对象(-128~127)
		
		/*
		 * 附Integer底层实现代码：
		 *     public static Integer valueOf(int i) {
	              if (i >= IntegerCache.low && i <= IntegerCache.high)//当i的值是在-128~127时，并不会创建新的对象
	              return IntegerCache.cache[i + (-IntegerCache.low)];//将会返回一个固定数组里的值
	              return new Integer(i);                             //否则就是创建一个新的对象
               }
		 * 
		 * */
	}
	
	/**
	 * 学习对于正则表达式的使用
	 * 1.对于分组的使用:在正则表达式中可以使用分组功能
	 * 		(1)可以使用"()"将一段字符串括起来，形成分组.比如:String regex = "(.)";表示这个一个任意的字符
	 * 		(2)可以使用"\\+一个数字"表示该组再次出现一次,比如:String regex = "(.)\\1";这个表示任意字符出现两次
	 * 		(3)可以使用"$"获取到前面的正则表达式中的内容,比如:String regex = "(.)\\1";String.replace(regex,"$1");
	 * 		   表示用匹配叠词，如"好好","干干",然后使用一个字替代里面的叠词,结果为"好","干"
	 * */
	
	public void Question_2(){
		//举例:将"我....我我...喜喜喜欢.....欢欢....你"替换成"我喜欢你"
		String regex = "(.)\\1+";
		String str = "我....我我...喜喜喜欢.....欢欢....你";
		str = str.replaceAll("\\.", "");
		System.out.println(str.replaceAll(regex, "$1"));
	}
	/**
	 * 学会使用Patten类和Match类的使用
	 * */
	
	public void Question_3(){
		String str = "我的手机号码是18711724620,曾经用过13875372976,还用过13298727240";//需要被替换的字符串
		String regex = "1[3578]\\d{9}";//正则表达式
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while(m.find()){//当Matcher还能从里面找到符合正则表达式的字符串时
			System.out.println(m.group());
		}
	}
	/**
	 * 使用Math类
	 * */
	
	public void Question_4(){
		List<String> lisr = new ArrayList<String>();
		
		//求绝对值
		System.out.println(Math.abs(-2));
		//向上取整
		System.out.println(Math.ceil(-1.5));
		//向下取整
		System.out.println(Math.floor(-2.9));
		//乘方运算
		System.out.println(Math.pow(2, 3));
		//四舍五入
		System.out.println(Math.round(2.5));
		System.out.println(Math.round(2.4));
		//开平方运算
		System.out.println(Math.sqrt(4));
	}
	
	public void Question_5(){
		long start = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
			System.out.println("*");
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		BigDecimal d= new BigDecimal("");
	}
	/**
	 * 使用Calendar对象表示时间
	 * */
	public void Question_6(){
		
	}
}
