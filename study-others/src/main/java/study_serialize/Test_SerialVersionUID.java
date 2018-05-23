package study_serialize;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import org.junit.Test;

public class Test_SerialVersionUID {

	//使用ObjectOutputStream写对象
	/*如果没有实现接口，则运行的时候会报错*/
	
	public void Question_1() throws IOException, ClassNotFoundException{
		writeObject();
		readObject();
	}
	
	//readObject
	public void readObject() throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"));
		Students stu = (Students) ois.readObject();
		System.out.println(stu);
		ois.close();
	}
	public void writeObject() throws FileNotFoundException, IOException{
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("object.txt"));
		Students stu = new Students("肖鼎",20);
		os.writeObject(stu);
		os.close();
	}
	
	//使用打印字符流进行打印
	
	public void Question_2() throws IOException{
		//先使用一个流读取文件
		BufferedReader br = new BufferedReader(new FileReader("test.properties"));
		//创建PrintWriter对象
		PrintWriter pw = new PrintWriter(new FileWriter("PrintWriter.txt"),true);
		String str = null;
		while((str=br.readLine())!=null){
			pw.println(str);
		}
		pw.close();
		br.close();
		
	}
	//使用打印字节流进行打印
	@Test
	public void Question_3()throws Exception{
		// 先使用一个流读取文件
		BufferedReader br = new BufferedReader(new FileReader("test.properties"));
		// 创建PrintWriter对象
		PrintStream pw = new PrintStream(new FileOutputStream("PrintWriter.txt"),true);
		String str = null;
		while ((str = br.readLine()) != null) {
			pw.println(str);
		}
		pw.close();
		br.close();
	}
}
