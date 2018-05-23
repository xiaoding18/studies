package cn.willhoo.ssh_bos.utils.poi;

import cn.willhoo.ssh_bos.utils.pinyin4j.PinYin4jUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @author xiaoding
 * @date 2018年4月14日 下午3:02:12
 */
public class POIUtils {

	
	
	/**
	 * 
	 * @param is 需要加载的excel流对象
	 * @param flag 是否是老版本的excel,如果是老版本的就为true
	 * @return
	 * @throws IOException
	 */
	public static <T> List<T> parseExcelByClass(InputStream is, boolean flag,Class<T> clazz) {

		// 创建一个工作簿
		Workbook workbook = null;
		try {
			workbook = flag ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("创建完成");
		Sheet sheet = workbook.getSheet("area");
		// 遍历这个sheet,然后获取每个单元格的数据,并且将数据写入到对象中
		List<T> areaList = new ArrayList<>();
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			
			Row row = sheet.getRow(i);
			T instance = null;
			try {
				instance = writeArea(clazz, row);
			} catch (Exception e) {
				e.printStackTrace();
			}
			areaList.add(instance);
		}
		return areaList;
	}
	
	/**
	 * 默认的处理excel文件的方式,用于默认处理新版本的excel文件
	 */
	
	public static <T> Workbook parseToWorkbook(List<T> list) {
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("area");
		
		writeFirstRow(sheet);
		
		for(int i=1;i<list.size();i++) {
			writeAreaRow(list.get(i),sheet,i-1);
		}
		//返回执行完毕的对象
		return workbook;
	}
	
	/**
	 * 写一行数据
	 * @throws SecurityException 
	 * @throws Exception 
	 */
	public static void writeAreaRow(Object obj,Sheet sheet,int rowNum){
		try {
			Row row = sheet.createRow(rowNum);
			Class<?> clazz = obj.getClass();
			row.createCell(0).setCellValue(clazz.getMethod("getId").invoke(obj).toString());
			row.createCell(1).setCellValue(clazz.getMethod("getProvince").invoke(obj).toString());
			row.createCell(2).setCellValue(clazz.getMethod("getCity").invoke(obj).toString());
			row.createCell(3).setCellValue(clazz.getMethod("getDistrict").invoke(obj).toString());
			row.createCell(4).setCellValue(clazz.getMethod("getPostcode").invoke(obj).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写第一行
	 * 以后可以变成可以写任意对象的一行
	 */
	public static void writeFirstRow(Sheet sheet) {
		Row firstRow = sheet.createRow(0);
		firstRow.createCell(0).setCellValue("区域编码");;
		firstRow.createCell(1).setCellValue("省份");;
		firstRow.createCell(2).setCellValue("城市");;
		firstRow.createCell(3).setCellValue("区域");;
		firstRow.createCell(4).setCellValue("邮编");;
	}
	
	public static <T> T writeArea(Class<T> clazz,Row row) throws Exception{
		T instance = clazz.newInstance();
		String province = row.getCell(0).getStringCellValue();
		String city = row.getCell(1).getStringCellValue();
		String district = row.getCell(2).getStringCellValue();
		//调用set方法进行注入
		clazz.getMethod("setAreacode", String.class).invoke(instance,province);
		clazz.getMethod("setProvince", String.class).invoke(instance, city);
		clazz.getMethod("setCity", String.class).invoke(instance, district);
		clazz.getMethod("setDistrict", String.class).invoke(instance, row.getCell(3).getStringCellValue());
		clazz.getMethod("setPostcode", String.class).invoke(instance, row.getCell(4).getStringCellValue());
		
		//为了将数据封装到区域简码和城市编码,需要去除最后一个字符(原本是广州市,但是我们只需要广州)
		province = province.substring(0,province.length()-1);
		city = city.substring(0,city.length()-1);
		district = district.substring(0,district.length()-1);
		
		//使用pinyin4j截取字符串
		String[] headArray = PinYin4jUtils.getHeadByString(province+city+district);
		String cityCode = PinYin4jUtils.hanziToPinyin(city);
		
		
		//设置区域简码
		clazz.getMethod("setShortcode", String.class).invoke(instance, PinYin4jUtils.stringArrayToString(headArray, ""));
		clazz.getMethod("setCitycode", String.class).invoke(instance, cityCode);
		return instance;
	}
}
