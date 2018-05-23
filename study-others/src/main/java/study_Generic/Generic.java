package study_Generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class Generic {

	/**
	 * 主要学习泛型的使用
	 * 先写一个主类学生,然后分别写学习委员以及班长还有普通学生继承该类
	 * 然后分别创建三个类的对象,写一个方法将他们进行迭代
	 * 方法详细:参数需要使用泛型，该泛型必须继承这个类
	 * */
	@Test
	public void Question_1(){
		//先构建对象
		Banzhang ban1 = new Banzhang("xiaoding","xiaoding",20,"肖鼎");
		Banzhang ban2 = new Banzhang("xiaoding","xiaoding",20,"肖鼎");
		Tongxue tx1 = new Tongxue("likai","likai",20,"李凯");
		Tongxue tx2 = new Tongxue("likai","likai",20,"李凯");
		Xuexiweiyuan xw1 = new Xuexiweiyuan("guoquan","guoquan",20,"郭泉");
		Xuexiweiyuan xw2 = new Xuexiweiyuan("guoquan","guoquan",20,"郭泉");
		
		//创建集合包含这些对象
		List<Banzhang> list1 = new ArrayList<Banzhang>();
		list1.add(ban1);
		list1.add(ban2);
		
		List<Tongxue> list2 = new ArrayList<Tongxue>();
		list2.add(tx1);
		list2.add(tx2);
		
		List<Xuexiweiyuan> list3 = new ArrayList<Xuexiweiyuan>();
		list3.add(xw1);
		list3.add(xw2);
		//调用方法
		Outs(list1);
		Outs(list2);
		Outs(list3);
		
	}
	
	/**
	 * 建立方法迭代数据
	 * */
	public void Outs(Collection<? extends Student> stus){
		for(Student stu:stus){
			System.out.println(stu);
		}
	}
}
