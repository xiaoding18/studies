package study_IO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class InputStreamAndOutput {

	//使用输入流与输出流
	
	public void TestInput() throws IOException{
		FileInputStream fis = new FileInputStream("test.txt");
		StringBuilder sb = new StringBuilder();		
		byte[] b = new byte[1024];
		int lenght = 0;
		while((lenght=fis.read(b))!=-1){
			sb.append(new String(b,0,lenght));
		}
		fis.close();
		System.out.println(sb.toString());
	}
	
	//使用高效的输入流
	
	public void TestBufferedInput() throws IOException{
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("test.txt"));
		StringBuilder sb = new StringBuilder();		
		byte[] b = new byte[1024];
		int lenght = 0;
		while((lenght=bis.read(b))!=-1){
			sb.append(new String(b,0,lenght));
		}
		System.out.println(sb.toString());
		bis.close();
	}
	@Test
	//使用inputStreamReader读取文件中的信息，并且使用OutputStreamWriter对另一个文件执行写入
	public void TestChangeBufferedInput() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt"),"utf-8"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("text.txt"),"utf-8"));
		char[] chars = new char[1024];
		int lenght = 0;
		//读取文件信息并写入
		while((lenght=br.read(chars))!=-1){
			bw.write(chars,0,lenght);
		}
		//读取完毕之后执行写入
		br.close();
		bw.close();
		
	}
	
	//当不使用平台默认的字符集的时候
	public void TestCharset() throws Exception {
		//先创建对象
		FileWriter fw = new FileWriter("text.txt");
		
	}
}
