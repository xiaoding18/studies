package study_deleteFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**   
* @Description 我只是想要删除一个MyEclipse而已,顺便写了这个
* @author xiaoding
* @date 2018年3月21日 下午9:17:10   
*/
public class DeleteFiles {
	
	public DeleteFiles() {
		files = new ArrayList<>(10000);
	}

	//指定需要删除的目录
	private String directory = "D:\\MyEclipse";
	
	private List<File> files;
	/**
	 * 思路1:
	 * 	这种思路类似于操作系统,先找到所有的文件,将其存储在一个List集合中,然后一个个删除
	 */
	
	public void DeleteFiles() {
		
	}
	
	/**
	 * 找到所有的File对象
	 */
	public void getAllFiles() {
		File dirFile = new File(directory);
		getFile(dirFile);
	}
	
	@Test
	public void Test_1() {
		//先尝试获取到所有file
		getAllFiles();
		
		System.out.println("获取到所有的文件目录"+files.size());
		//删除所有的文件
		files.forEach(File::delete);
	}
	
	/**
	 * 递归方法
	 */
	public void getFile(File file) {
		File[] listFiles = file.listFiles();
		for(int i=0;i<listFiles.length;i++) {
			//如果得到的file对象还是文件目录
			if(listFiles[i].isDirectory()) {
				if(listFiles[i].listFiles().length == 0) {
					files.add(listFiles[i]);
				}
				getFile(listFiles[i]);
			}else {//否则就添加
				files.add(listFiles[i]);
				
			}
		}
		//试试清除内存?
		System.gc();
	}
	
	
	
}
