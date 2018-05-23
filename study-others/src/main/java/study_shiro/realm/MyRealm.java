package study_shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**   
* @Description realm用来充当shiro和我们的应用程序之间的桥梁,如果需要编写一个realm
* 我们需要让这个类来继承AuthorizingRealm
* @author xiaoding
* @date 2018年4月28日 下午12:09:17   
*/
public class MyRealm extends AuthorizingRealm{

	/**
	 * 这个方法执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("进行授权");
		return null;
	}

	
	/**
	 * 这个方法用来执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("进行认证(登录)");
		
		//现在模拟数据库中存放的账户
		String username = "username";
		String password = "password";
		
		//我们传递过来的用户登录信息在token中
		//我们需要进行一次强转来获取具体的信息
		UsernamePasswordToken info = (UsernamePasswordToken)token;
		if(!info.getUsername().equals(username)) {
			//如果账户不存在
			/*
			 * 我们本来需要进行的操作是抛出一个异常,但是为了简化我们的操作,我们这里只需要返回一个null即可
			 */
			return null;
		}
		
		//在判断用户名存在之后,我们需要返回一个值来让shiro判断密码(也就是说不用我们来判断密码)
		/*
		 * 参数1
		 * 	用于用户登录成功之后存储用户信息
		 * 	
		 * 参数2
		 * 	数据库的密码
		 * 参数3
		 * 	realm的别名,当我们在含有realm的时候将会使用这个参数,正常来说我们不需要管这个参数
		 */
		/*
		 * 在shiro对比完密码之后,可能会出现密码正确或者是密码错误的异常
		 * 正确:没有异常,正常通过
		 * 错误:将会抛出一个异常信息,表示密码错误
		 */
		return new SimpleAuthenticationInfo("DBINFO",password,"");
	}

}
