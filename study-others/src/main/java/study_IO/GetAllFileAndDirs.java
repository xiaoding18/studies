package study_IO;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

public class GetAllFileAndDirs {
	private static int count=0;
	

	/*
	 * 对一个文件目录下的所有的文件目录进行遍历
	 * 1.先获取该目录下的所有的文件对象
	 * 2.判断这个文件对象是不是文件夹，如果是则再次迭代
	 * */
	
	public void getAllDirsAndFiles(){
		String fileDir = "d:\\java 基础";
		File fileDirs = new File(fileDir);
		
		if(fileDirs.exists()){//如果这个目录存在
			if(fileDirs.isDirectory()){//如果这个目录是一个目录
				OutsFiles(fileDirs);
			}
		}
		
	}
	public void OutsFiles(File files){
		File[] listFiles = files.listFiles();
		for (File file : listFiles) {
			if(file.isDirectory()){
				System.out.println(file);
				OutsFiles(file);
			}
			System.out.println(file);
			
		}
		
	}
	
	
	/**
	 * 计算100以内的数的和
	 * */
	public int getSum100(int n){
		if(n==1){
			return 1;
		}
		return n = getSum100(n-1)+n;
	}
	
	
	//使用文件过滤器搜索一个文件目录里的所有符合要求的文件
	public void getAllFilesFilter(){
		File file = new File("D:\\java 基础\\day22");
		getAllFile(file);

	}
	@Test
	public void getAllFile(File file){
		if(file.isDirectory()){
			//先获取这个目录下的所有的文件目录
			File[] fileDir = file.listFiles(new MyFileFilter());
			for(File files:fileDir){
				
				if (files.isDirectory()) {
					getAllFile(files);
				}
				//输出这个文件的名称
				if(files.isFile())
				System.out.println(files.getName());
			}
		}
	}
}
/**
 * 写一个自定义的文件过滤器
 * 只取得文件后缀名为.java的文件
 * */
class MyFileFilter implements FileFilter{

	@Override
	public boolean accept(File pathname) {
		//如果这个File对象是一个目录
		if(pathname.isDirectory())
			return true;
		return pathname.getName().toLowerCase().endsWith(".java");
	}
	
}
