package study_Set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class Study_Set {
	/*用来迭代Set集合*/
	
	public void Question_1(){
		/*使用第一种方式迭代*/
		Set<String> set = new HashSet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("4");
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		/*使用第二种方式迭代*/
		for(String str:set){
			System.out.println(str);
		}
		
	}
	
	/*用来迭代LinkedList*/
	public void Question_2(){
		
		LinkedList<String> ll = new LinkedList<String>();
		ll.add("1");
		ll.add("2");
		ll.add("3");
		ll.add("4");
		/*使用迭代器迭代*/
		Iterator<String> iterator = ll.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		/*使用增强for循环*/
		for(String str:ll){
			System.out.println(str);
		}
		/*使用普通for循环*/
		for(int i=0;i<ll.size();i++){
			System.out.println(ll.get(i));
		}
	}
	@Test
	/*用来迭代Map集合*/
	public void Question_3(){
		Map<String, Integer> map = new HashMap<String,Integer>();
		map.put("StringKey1", 1);
		map.put("StringKey2", 2);
		map.put("StringKey3", 3);
		map.put("StringKey4", 4);
		/*使用迭代器迭代map集合*/
		//先获取Map.Entry
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		//然后遍历Set集合
		for(Entry<String,Integer> entry:entrySet){
			System.out.println(entry.getKey()+"  "+entry.getValue());
		}
		
		/*另一种方式*/
		//通过迭代Set集合的方式取出数据
		Set<String> keySet = map.keySet();
		//获取到Key的Set集合之后就可以由Key得到Value的值
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()){
			System.out.println(map.get(iterator.next()));
		}
		
	}
	
}
