package cn.willhoo.ssh_bos.utils.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月15日 上午9:18:05   
*/
public class Test1 {

	private String username;
	
	public String getUserName() {
		return username;
	}
	
	/**
	 * 测试POI的使用
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void Test_1() throws FileNotFoundException, IOException {
		String path = "C:\\Users\\xiaoding\\Desktop";
		File excel = new File(path,"区域测试数据.xls");
		Workbook workbook = new HSSFWorkbook(new FileInputStream(excel));
		Sheet sheet = workbook.getSheet("area");
		for (Row row : sheet) {
			System.out.println(row.getCell(2));
		}
	}
	
	
	@Test
	public void Test_2() {
		String path = "";
		File excel = new File(path,"");
		Workbook workbook = new XSSFWorkbook();
		
		Sheet sheet = workbook.createSheet();
		
		Row row = sheet.createRow(1);
		Cell cell = row.createCell(1);
		cell.setCellValue("单元格的值");
	}
}
