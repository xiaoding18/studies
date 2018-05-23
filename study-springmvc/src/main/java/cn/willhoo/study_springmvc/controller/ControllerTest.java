package cn.willhoo.study_springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.willhoo.study_springmvc.entity.QueryVo;
import cn.willhoo.study_springmvc.entity.User;
import cn.willhoo.study_springmvc.exceptions.NoUserException;
import cn.willhoo.study_springmvc.service.UserService;

/**
 * @Description
 * @author xiaoding
 * @date 2018年4月5日 下午4:19:09
 */
@Controller
//@RequestMapping("user")如果在这个Controller上配置这个标签,则表示这个类中的所有的请求都需要在前面加上user,即可以窄化请求
/*
 * RequestMapping注解可以限制访问的方式,默认是允许所有的请求,可以通过下列方法来设置这个request只能被某种方式访问(这个属性是一个数组,如果有一个元素在数组中,则表示只能以这种方式访问这个请求)
 */
//@RequestMapping(method= {RequestMethod.GET,RequestMethod.POST})
public class ControllerTest {

	@Autowired
	private UserService service;

	/**
	 * 测试一个方法,用于第一次开起springmvc
	 * 
	 * @return
	 */
	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		List<User> users = service.findAllUser();
		// 设置响应的参数
		mav.addObject("users", users);

		// 设置响应的地址
		mav.setViewName("/WEB-INF/jsp/hello.jsp");

		// 在设置了视图解析器之后,我们可以将jsp页面写的超级简单
		// 其实现方式应该是字符串的拼接
		mav.setViewName("hello");
		return mav;

	}

	/**
	 * 如果要接收页面中传递过来的参数,则使用第二种方式会好一点
	 */
	@RequestMapping("/getParameter_1")
	public String getParameter_1(Model model, HttpServletRequest req) {
		// 得到传递过来的参数
		String id = req.getParameter("id");
		// 输出得到的参数
		System.out.println(id);

		// 根据id查询用户的信息
		User users = service.findUserById(new Integer(id));

		// 将查询到的用户信息放入请求中(貌似如果是不写名字,默认是这个类的类名)
		model.addAttribute(users);
		return "getParameter_1";
	}
	
	@RequestMapping("/getParam")
	public String getParam(Model model,HttpServletRequest req) {
		//得到传递过来的参数
		String param = req.getParameter("paramName");
		
		//将参数添加到返回值里面
		model.addAttribute("param",param);
		return "showParam";
	}

	/**
	 * 接收页面请求的参数2 使用这种方式接收页面请求中的参数的话,不建议使用简单类型的基本类型,原因是我们如果使用包装类型的话能在不传参数的时候可以为null
	 * 但是由于简单类型不能为null,所以会报错
	 * 
	 * @RequestParam 如果我们需要请求的参数和方法的形参名字不同,则可以对参数使用这个注解,使用这个注解之后,在jsp页面中将可以使用这个参数名称
	 *               来传递参数 defaultValue属性:
	 *               有时,我们页面中没有传递这个值,那么在方法中将会出现错误,为了避免这种错误,可以使用这个属性,当页面中没有传递这个参数的时候
	 *               将会使用defaultValue中指定的值作为默认值
	 *               这个值是可以进行自动转换的,即defaultValue写的是字符串,但是会根据你参数的类型自动转换为integer类型或者是其他类型
	 */
	@RequestMapping("/getParameter_2")
	public String getParameter_2(Model model, @RequestParam(defaultValue = "26") Integer id) {

		User user = service.findUserById(id);

		model.addAttribute(user);

		return "getParameter_2";
	}

	/**
	 * 如果在方法中含有多个参数,并且其是一个实体类的参数,我们可以使用实体类来接收这个参数
	 */
	@RequestMapping("/getParameter_3")
	public String getParameter_3(Model model, User user) {
		// 查出和传递过来的用户同一个地址的所有用户
		System.out.println(user);
		List<User> userList = service.findUserListByAddress(user.getAddress());
		model.addAttribute("users", userList);
		return "getParameter_3";
	}

	/**
	 * 测试包装类型的参数封装 总体上来说,如果要封装包装类型的参数,则需要在页面上传入和包装类型中成员变量的值相同的前缀 比如:
	 * 包装类型中的成员变量是user,并且这个类型有一个属性username那么在传递参数的时候我们就需要让参数名等于user.username
	 * 其实这个和struts2是类似的..
	 */
	@RequestMapping("/getParameter_4")
	public String getParameter_4(Model model, QueryVo vo) {
		System.out.println(vo.getUser());
		return "getParameter_3";
	}
	
	/**
	 * 高级参数绑定,绑定List集合类型的参数
	 * 需要注意的是,springmvc不能直接在形参中填写List数据类型的参数,需要在包装类中编写,否则会报错 
	 */
	@RequestMapping("/getParameter_5")
	public String getParameter_5(Model model,QueryVo vo) {
		//打印所有得到的参数
		vo.getUserList().forEach(System.out::println);
		return "getParameter_5";
	}

	
	/**
	 * 获取所有的用户信息,并且打印到页面
	 * 
	 */
	@RequestMapping("/findAllUser")
	@ResponseBody
	public List<User> findAllUser(Model model) {
		ArrayList<String> arrayList = new ArrayList<String>();
		
		List<User> users = service.findAllUser();
		model.addAttribute("users",users);
		return users;
	}
	
	/**
	 * 测试springmvc的转发和重定向(使用string的方式)
	 */
	@RequestMapping("/servletTest")
	public String servletTest(Model model) {
		User user = new User();
		user.setUsername("用户123");
		user.setAddress("用户地址456");
		model.addAttribute("usr",user);
		//测试请求转发,注意在这里不能少了.do
		return "forward:findAllUser.do";
		
		//测试请求重定向
//		return "redirect:findAllUser.do";
	}
	
	/**
	 * 测试自定义异常
	 */
	@RequestMapping("/exceptionTest")
	public String ExceptionTest(String username) throws NoUserException{
		System.out.println(username);
		if(username == null) {	
			throw new NoUserException("没有用户!");
		}else {
			//如果有这个用户
			System.out.println(username);
		}
		return null;
	}
	
	
	/**
	 * 测试文件上传,在上传完毕之后将会跳转到一个页面显示这个图片
	 * 文件上传逻辑:
	 * 1. 获取文件的原来的名称
	 * 2. 重新命名文件
	 * 3. 上传文件
	 * 4. 保存文件的名称
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/uploadTest")
	public String uploadTest(Model model,MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(1);
		//如果传送过来的文件对象和文件名不为空
		if(file != null && file.getOriginalFilename() != null) {
			String filename = file.getOriginalFilename();
			String extName = FilenameUtils.getExtension(filename);
			filename = UUID.randomUUID().toString()+"."+extName;
			
			//将文件保存至本地地址
			file.transferTo(new File("D:\\Tomcat\\virtualFileDir\\image",filename));
			
			//将file对象保存到数据库中
			service.saveFile(filename);
			//将请求信息传递给页面
			model.addAttribute("filename",filename);
		}
		//跳转到一个页面让这个页面显示用户刚才上传的图片
		return "showImg";
	}
	
}
