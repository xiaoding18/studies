package study_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class TestThread {

	//练习使用线程
	public static void main(String[] str)  {
		/*//创建一个线程并且开启
		Thread1 thread1 = new Thread1();
		//输出线程的名字
		System.out.println(thread1.getName());
		//修改线程的名字
		thread1.setName("线程2");
		//输出修改后线程的名字
		System.out.println("修改之后的名字是"+thread1.getName());
		thread1.start();
		//让主线程报错
		throw new RuntimeException("主线程出异常了!");*/
		
	}
	
	
	/**
	 * 使用线程池创建对象
	 * */
	
	public void TestThreadPool() {
		//构建一个容量为3的线程池
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
		//调用线程池里的方法
		newFixedThreadPool.submit(new ImplRunnable());
		newFixedThreadPool.submit(new ImplRunnable());
		newFixedThreadPool.submit(new ImplRunnable());
		newFixedThreadPool.submit(new ImplRunnable());
		newFixedThreadPool.submit(new ImplRunnable());
		newFixedThreadPool.submit(new ImplRunnable());
	}
	
	/**
	 * 使用多线程计算1-200的和
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * */
	@Test
	public void TestThreadPoolCallable() throws InterruptedException, ExecutionException {
		//先拿到线程池
		ExecutorService es = Executors.newFixedThreadPool(2);
		int sum = 0;
		//然后再拿到线程
		sum += es.submit(new ImplCallable(1,100)).get();
		sum += es.submit(new ImplCallable(100,201)).get();
		Future<Integer> submit = es.submit(new ImplCallable(1,201));
		System.out.println("单线程计算出来的结果是:"+submit.get());
		System.out.println("多线程计算出来的结果是:"+sum);
		
		
	}
}
/**
 * 当需要拿到能拿到返回值的多线程时需要实现Callable接口
 * */
class ImplCallable implements Callable<Integer>{

	//运算的开始位置
	private int begin;
	//运算的结束位置
	private int end;
	public ImplCallable(int begin,int end) {
		this.begin = begin;
		this.end = end;
	}
	//进行求和
	@Override
	public Integer call() throws Exception {
		int sum=0;
        for(;begin<end;begin++) {
			sum+=begin;
		}
		return sum;
	}
	
}

/**
 * 配置一个实现Runnable接口的类
 * */
class ImplRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"  线程池对象方法调用");
		System.out.println();
	}
	
}
class Thread1 extends Thread{
	@Override
	public void run() {
		System.out.println("这个是Thread1里面输出的数据");
		//在线程里面获取这个线程的名字
		System.out.println(getName());
		//修改线程的名字
		setName("Thread1");
		//输出修改后线程的名字
		System.out.println("修改之后的名字是"+getName());
	}
}
