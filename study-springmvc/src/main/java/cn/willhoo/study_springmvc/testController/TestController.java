package cn.willhoo.study_springmvc.testController;

import cn.willhoo.study_springmvc.entity.User;
import cn.willhoo.study_springmvc.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月11日 上午9:30:49   
*/
@Controller
public class TestController {

	@Autowired
	private UserService service;
	
	
	/**
	 * 传递简单参数方式
	 * 在默认请情况下形参的名称需要和页面中参数 的名称一致
	 * 形式参数类型尽量使用包装类型
	 * @RequestParam
	 * 	请求参数注解
	 * 	value:指定接收页面的参数名称
	 * 	required:指定这个参数是否必要,默认值是true,即必须要传递这个参数
	 * 	defaultValue:无论required是否为true,都可以指定这个参数的默认值
	 */
	@RequestMapping("queryById")
	public ModelAndView queryById(@RequestParam(defaultValue="26")Integer id) {
		System.out.println("得到参数"+id);
		
		//如果我们将数据存入页面中
		User user = service.findUserById(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("user",user);
		
		//返回
		return mav;
	}
	
	
	/**
	 * 使用controller接收数组类型的参数
	 */
	@RequestMapping("findArrayParam")
	public void findArrayParam(ModelAndView model,Long[] ids) {
		String array = Arrays.toString(ids);
		System.err.println(array);
	}
	
	/**
	 * 测试日期之间的转换
	 */
	@RequestMapping("dateFormat")
	public void dateFormat(User user) {
		
		System.err.println(user.getBirthday());
	}
	
	/**
	 * 测试文件的上传
	 * @throws IOException 
	 */
	@RequestMapping("fileUpload")
	public String fileUpload(MultipartFile upLoadFile) throws IOException {
		//得到文件的原始文件名(就是得到用户上传的文件的全名)
		String originalFilename = upLoadFile.getOriginalFilename();
		
		//对文件名进行处理,我们需要将这个文件名处理成不会冲突的文件名
		String extName = FilenameUtils.getExtension(originalFilename);
		
		//生成一个UUID的文件名
		String fileName = UUID.randomUUID().toString()+extName;
		
		//用户文件将会上传到这个地址
		String destFile = "D:\\Tomcat\\virtualFileDir\\image";
		
		//将这个文件写入系统文件中
		FileUtils.copyToFile(upLoadFile.getInputStream(), new File(destFile,fileName));

		System.err.println("文件写入完成,即将跳转到这个图片的路径");
		return "redirect:http://localhost:8081/image/"+fileName;
	}
}
