package study_proxyModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Study_ProxyModel {
	//使用代理模式来创建一个程序，这里使用的是动态代理
	@Test
	public  void main() {
		//通过动态代理增强List的add方法，当使用add方法的时候将会添加的字符串添加一个sb:
		final List<String> list = new ArrayList<>();
		//拿到ArrayList的Class对象
		Class cls = list.getClass();
		List listProxy = (List)Proxy.newProxyInstance(this.getClass().getClassLoader(), cls.getInterfaces(), new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//当运行的不是add方法时，不对这个方法进行增强
				if(!method.getName().equals("add"))
					return method.invoke(list, args);
				//否则就对这个方法进行增强
				return method.invoke(list, "sb"+args[0]);
			}
			
		});
		//执行这个方法
		listProxy.add("我是个好人");
		System.out.println(listProxy);
	}
}
