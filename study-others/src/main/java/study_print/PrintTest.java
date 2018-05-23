package study_print;

import java.io.File;
import java.io.FileInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * @Description
 * @author xiaoding
 * @date 2018年4月25日 上午12:34:41
 */
public class PrintTest {
	private static String filePath = "C:\\Users\\xiaoding\\Desktop\\新建 Microsoft Excel 工作表.xlsx";

	public static void main(String[] args) {
		print(filePath);
	}

	public static void print(String path) {
		ComThread.InitSTA();
		ActiveXComponent xl = new ActiveXComponent("Excel.Application");
		try {
			// System.out.println("version=" + xl.getProperty("Version"));
			Dispatch.put(xl, "Visible", new Variant(true));
			Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
			// 打开文档
			Dispatch excel = Dispatch.call(workbooks, "Open", path).toDispatch();
			// 开始打印
			Dispatch.get(excel, "PrintOut");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 始终释放资源
			ComThread.Release();
		}
	}
}
