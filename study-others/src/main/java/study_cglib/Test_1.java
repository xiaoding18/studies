package study_cglib;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**   
* @Description 
* 练习使用cglib工具包,直接放结论:
* 	如果需要代理的类有接口,那么久可以使用Jdk自带的代理,如果需要代理的类没有实现接口,那么就可以使用cglib工具
* @author xiaoding
* @date 2018年3月23日 上午9:53:30   
*/
public class Test_1 {

	@Test
	public void Test1() {
		List<String> list = new ArrayList<>();
		
		//现在来代理这个类
		Callback back = null;
		@SuppressWarnings("unchecked")
		List<String> listProxy = (List<String>) Enhancer.create(list.getClass(), new MethodInterceptor() {
			
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				
				//得到方法名称
				String methodName = method.getName();
				if(methodName.equals("add")) {
					System.out.println(args[0]);
					
					//然后得到这个参数
					String str = (String) args[0];
					
					//对于参数进行增强
					str += "被增强了";
					
					//继续执行这个方法
					return method.invoke(list, str);
				}
				
				//如果是其他的方法则直接执行
				return method.invoke(list, args);
			}
			
			
		});
		
		listProxy.add("strs");
		System.out.println(list);
		
		
		
	}
}
