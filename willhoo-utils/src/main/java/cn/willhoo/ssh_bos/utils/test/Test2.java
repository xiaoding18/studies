package cn.willhoo.ssh_bos.utils.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import net.sourceforge.pinyin4j.PinyinHelper;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月15日 上午9:18:11   
*/
public class Test2 extends Test1{

	private String password;
	
	@Override
	public String toString() {
		return super.getUserName()+"========"+password;
	}
	
	//测试pinyin4j
	@Test
	public void Test_1() {
		String dest = "我是龙傲天我怕谁哈哈哈";
		char c = '好';
		String[] array = PinyinHelper.toHanyuPinyinStringArray(c);
		System.out.println(Arrays.toString(array));
		char[] chars = dest.toCharArray();
		for (char d : chars) {
			String[] strs = PinyinHelper.toHanyuPinyinStringArray(d);
			System.out.println(Arrays.toString(strs));
		}
	}
}
