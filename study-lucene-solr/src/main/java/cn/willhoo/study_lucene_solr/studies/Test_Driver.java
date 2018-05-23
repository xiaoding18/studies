package cn.willhoo.study_lucene_solr.studies;

import java.io.IOException;
import java.io.InputStream;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月8日 下午11:13:56   
*/
public class Test_Driver {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		Runtime run = Runtime.getRuntime();
		
		Process process = run.exec("ping localhost");
		
		InputStream in = process.getInputStream();
		
		int length ;
		byte[] b = new byte[1024];
		while((length=in.read(b)) != -1) {
			System.out.print(new String(b,0,length,"gbk"));
		}
		
	}
}
