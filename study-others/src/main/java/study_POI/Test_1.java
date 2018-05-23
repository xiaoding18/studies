package study_POI;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月13日 下午11:36:17   
*/
public class Test_1 {

	private static String filePath = "";
	
	
	
	@Test
	public void Test1() throws IOException {
		//先获取文件的流对象
		InputStream is = this.getClass().getResourceAsStream("区域测试数据.xls");
		
		//创建工作簿
		/*
		 * 工作簿与其对应的版本信息如下
		 * HSSFWorkbook:老版本
		 * XSSFWorkbook:新版本
		 */
		Workbook workbook = new HSSFWorkbook(is); 
		
		//获取到工作表
		Sheet sheet = workbook.getSheet("area");
		
		//遍历工作表的每一行,然后将结果封装成一个结果集
		List<Area> list = new ArrayList<>();
		int i=0;
		for (Row row : sheet) {
			if(i==0) {
				i++;
				continue;
			}
			Area area = new Area();
			Long id = Long.valueOf(row.getCell(0).toString());
			String areacode = row.getCell(1).getStringCellValue();
			String province = row.getCell(2).getStringCellValue();
			String city = row.getCell(3).getStringCellValue();
			String district = row.getCell(4).getStringCellValue();
			
			area.setId(id);
			area.setAreacode(areacode);
			area.setProvince(province);
			area.setCity(city);
			area.setDistrict(district);
			
			list.add(area);
		}
		
		list.forEach(System.out::println);
		
		//如果使用Jdk1.8来完成这个操作
//		workbook.close();
	}
	
	
}
