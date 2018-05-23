package study_IO;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestWriterAndReader {

	/*public static void main(String[] str) throws IOException{
		FileReader fr= new FileReader("test.txt");
		FileWriter fw = new FileWriter("test.txt");
		char[] cbuf = new char[10];
		int n = 0;
		while((n=fr.read(cbuf))!=-1){
			fw.write(new String(cbuf,0,n)+"1");
			fw.flush();
		}
		fr.close();
		fw.close();
	}*/
	@Test
	public void Question_1(){
		String[] str  = {"1","2","3","4","5"};
		List<String> list = Arrays.asList(str);
		list.add("s");
		System.out.println(list);
	}
}
