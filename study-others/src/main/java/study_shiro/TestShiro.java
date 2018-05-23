package study_shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;;

/**   
 * shiro 有三大核心组件
 * 	subject
 * 		表示一个用户,以及这个用户的所有的凑总
 * 	secu
* @Description 
* @author xiaoding
* @date 2018年4月28日 下午12:04:59   
*/
public class TestShiro {

	@Test
	public void Test_1() {
		//创建一个工厂(还是创建一个工厂..)
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:study_shiro/shiro.ini");
		
		//创建一个SecutiryManager
		SecurityManager instance = factory.getInstance();
		
		//初始化(初始化什么东西?)
		SecurityUtils.setSecurityManager(instance);
		
		//获取到Subject
		Subject subject = SecurityUtils.getSubject();
		
		//将用户名和密码进行打包
		AuthenticationToken token = new UsernamePasswordToken("username","password");
		
		//shiro底层将会依赖一个commons-logging的jar包,入股哦不进行导入就会报错
		try {
			subject.login(token);
			
			//通过subject.getPrincipal方法可以获取到我们在执行完登录信息之后存入的值(比如存到session中)
			System.err.println(subject.getPrincipal());
			System.out.println("登录成功");
		}catch (UnknownAccountException e) {
			//如果出现异常就表示登录失败
			e.printStackTrace();
			System.out.println("登录失败:账号不存在");
		}catch (IncorrectCredentialsException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("登录失败:密码错误");
		}
		
		//shiro将会存储一个是否成功的标记位
		System.out.println("是否标记成功?"+subject.isAuthenticated());
		
		
		
		
	}
}
