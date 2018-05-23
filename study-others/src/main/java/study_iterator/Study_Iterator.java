package study_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class Study_Iterator {

	//学习使用迭代器
	@Test
	public void Question_1(){
		List<String> list = new ArrayList<String>();
		list.add("草拟吗");
		list.add("你麻痹");
		list.add("shit");
		
		Iterator it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
