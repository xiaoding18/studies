package study_day03.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**   
* @Description 这个类用来打印日志
* @author xiaoding
* @date 2018年3月23日 上午10:36:01   
* 注意:
* 	如果是使用Spring增强一个实现了接口的类,那么spring将会调用sun的动态代理来实现
* 	如果这个类没有实现任何的接口,那么spring将会调用AspectJ来实现增强
*/
@Component
//给这个类添加一个Aspect注解,相当于是在配置文件中配置这个类成为一个切面类
@Aspect
public class LogAdvice {

	
	public void printLog() {
		System.out.println("已经打印日志");
	}
	
	
	/**
	 * 环绕通知是spring给我们提供的一种可以手动控制通知点执行实际的机制
	 * @throws Throwable 
	 */
	public void aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("这个是环绕通知1");
		System.out.println("这个是环绕通知2");
		
		//在这里执行目标方法
		pjp.proceed();
		//先获取这个目标的参数列表
		Object[] args = pjp.getArgs();
		System.out.println(args.length);
		
		
		System.out.println("这个是环绕通知3");
		System.out.println("这个是环绕通知4");
	}
	
	//来配置一个最终通知
	@After("MyPointCut()")//所有的方法都将会被执行
	//如果说是每一个方法都需要编写这一段代码的话,那就太麻烦了
	public void after() {
		System.out.println(this.getClass());
		System.out.println("这个是一个最终通知");
	}
	
	@Pointcut("execution(* study_day03.service.*.*(..))")
	public void MyPointCut() {}
	
	/**
	 * 配置一个前置通知
	 */
	public void beforeMethod() {
		System.out.println("-----------这个是一个前置通知-----------");
	}
	
	
	/**
	 * 配置一个异常通知,异常通知将会在程序遇到异常的时候执行
	 * 异常通知使用注解来配置
	 */
	//这个是一个切面,所以使用Aspect注解
	//使用Aspect注解需要表示一个切点,表示这个切面将会使用哪个切点
	//在这里我们可以使用定义过的切点,而不需要每一次都自定义一个切点
	@AfterThrowing("Test_PointCut()")
	public void exceptionMethod() {
		System.out.println("已经发生异常了!!!!!");
		
	}
	
	//使用一个方法来创建一个切点,方法的名称就是这个切点的名字,这个方法不需要有方法体
	@Pointcut("execution(* study_day03.service.impl.*.*(..))")
	public void Test_PointCut() {}
}
