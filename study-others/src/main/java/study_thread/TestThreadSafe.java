package study_thread;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadSafe {

	/**
	 * 测试线程安全
	 * @throws IOException 
	 * */
	public static void main(String[] args) throws IOException {
		/*NeedLock ts = new NeedLock();
		Thread t1 = new Thread(ts);
		Thread t2 = new Thread(ts);
		Thread t3 = new Thread(ts);
		t1.start();
		t2.start();
		t3.start();*/
		TestSafe();
		
	}
	
	public static void TestSafe() {
		Student stu = new Student();
		Lock lock = new ReentrantLock();
		
		new Thread(new Input(stu, lock),"输入线程").start();
		new Thread(new Output(stu, lock),"打印线程").start();
	}
	
}
class TestSafe implements Runnable{

	/**
	 * 火车票数量
	 * */
	private int count=100;
	private Object object = new Object();
	@Override
	public void run() {
		//说明：在没有使用同步锁之前，结果总是会出现负数或者是0票的情况
		//使用同步锁只收不再出现这种情况
			while(true) {
				synchronized(object) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(count>0) {
					System.out.println(Thread.currentThread().getName()+"当前票数："+count--);
				}
			}
		}
		
	}
}

class NeedLock implements Runnable{
	//火车票的总数
	private int count = 100;
	//定义一个锁
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		while(count!=0) {
			
			//先拿到锁
			lock.lock();
			try {
				
				Thread.sleep(50);
				if(count>0) {
					System.out.println(Thread.currentThread().getName()+"当前票数："+count--);
				}
			}catch (Exception e) {
			}finally {
				//将释放锁的操作放在finally里
				lock.unlock();
				notify();
			}
		}
		
	}
}

//创建两个线程对象，一个实现写入功能，一个实现输出功能
class Input implements Runnable{

	private Student stu;
	private Lock lock;
	public Input(Student stu,Lock lock) {
		this.stu = stu;
		this.lock = lock;
	}
	@Override
	public void run() {
		int i=0;
		while(true) {
			synchronized(stu) {
//			lock.lock();
				
			
				if(stu.flag) {
					try {stu.wait();} catch (InterruptedException e) {}
				}
				if(i%2==0){
					stu.name = "张三";
					stu.sex = "男";
				}else{
					stu.name = "lisi";
					stu.sex = "nv";
				}
				//将对方线程唤醒,标记改为true
				stu.flag = true;
				stu.notify();
				i++;
//			lock.unlock();
			}
		}
	}
	
}
class Output implements Runnable{

	private Student stu;
	private Lock lock;
	public Output(Student stu,Lock lock) {
		this.stu = stu;
		this.lock = lock;
	}
	@Override
	public void run() {
		while(true) {
			synchronized(stu) {
//			lock.lock();
				if(!stu.flag) {
					try {stu.wait();} catch (InterruptedException e) {}
				}
				System.out.println(stu.name+".."+stu.sex);
				//标记改成false,唤醒对方线程
				stu.flag = false;
				stu.notify();
//			lock.unlock();
			}
		}
	}
				
			
		
}
	

