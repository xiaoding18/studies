package study_exception;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class StudyException {

	/*
	 * 学习使用异常
	 * */
	public static void main(String[] str){
		int i=0;
		
		/*try {
			i = MethodsThrowException(new int[] { 1, 2, 3, 4 });
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		/*getAvg(new int[]{1,2,3,-5});
		System.out.println(i);*/
		NeedTry(1);
	}
	//新建一个方法，使这个方法抛出异常
	public static int MethodsThrowException (int[] arrs){
		if(arrs==null){
			throw new NullPointerException("数组为空");
		}
		if(arrs.length<5){
			throw new NullPointerException("数组索引越界");
		}
		return arrs[4];
	}
	
	public static void Methods(int[] arr){
		if(arr==null){
			throw new NullPointerException();
		}
	}
	
	public static double getAvg(int...arr){
		int sum=0;
		for(int i:arr){
			if(i<0)
				throw new FuShuException("负数异常:"+i);
			sum+=i;
		}
		return sum/(double)arr.length;
	}
	
	//在JDK1.7之后可以使用特殊的try-catch-resource语句块来关闭资源，下面使用这种方式来关闭资源
	public static void NeedTry(int num) {
		//创建一个文件对象来存储数据
		File file = new File("test.txt");
		//使用try语句来创建对象并且写入数据
		try(BufferedWriter bw  = new BufferedWriter(new FileWriter(file));){
			bw.write("这是一个字符串");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//下面创建一个文本对象来读取数据
		try(BufferedReader br = new BufferedReader(new FileReader(file));){
			String str = null;
			while((str=br.readLine())!=null) {
				System.out.println(str);
			}
		} catch (IOException|RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file.delete();
		
	}
	
}
/**
 * 自定义的异常类,当学生的成绩为负数的时候抛出异常
 * */
class FuShuException extends RuntimeException{
	public FuShuException(){
	}
	public FuShuException(String str){
		super(str);
	}
}
