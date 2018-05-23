package study_download;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月7日 下午7:24:37   
*/
public class Study_Download {
	
	private static final String url = "http://download.oracle.com/otn/linux/oracle12c/122010/linuxx64_12201_database.zip";
	private static final String saveDir = "d:/oracle/";
	private static final String fileName = "oracle.zip";
	
	public static void main(String[] args) {
		downloadByNIO2(url, saveDir, fileName);
	}
	

	 public static void downloadByNIO2(String url, String saveDir, String fileName) {
	        try (InputStream ins = new URL(url).openStream()) {
	            Path target = Paths.get(saveDir, fileName);
	            Files.createDirectories(target.getParent());
	            Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        
	    }
}
