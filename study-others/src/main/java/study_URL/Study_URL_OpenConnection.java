package study_URL;

import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

/**
 * 这个类用来学习URL的内容，主要是学习了其openConnect()与getStream()方法
 * @author xiaoding
 *
 */
public class Study_URL_OpenConnection {

	/**
	 * 先使用简单的openStream()方法
	 * @throws Exception 
	 */
	@Test
	public void Question_1() throws Exception {
		//根据地址创建一个URL
		URL url = new URL("https://www.baidu.com");
		System.out.println(url.getFile());
		
		//对这个URL使用OpenStream方法以获取其数据
		InputStream openStream = url.openStream();
		//读取输入流的内容
		byte[] b = new byte[1024];
		int lenght;
		StringBuilder sb = new StringBuilder();
		while((lenght=openStream.read(b))!=-1) {
			sb.append(new String(b,0,lenght));
		}
		//打印这个网页的内容
		System.out.println(sb.toString());
	}
}
