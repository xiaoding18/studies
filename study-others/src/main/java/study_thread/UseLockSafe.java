package study_thread;/*package study_thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class UseLockSafe {

	@Test
	public void Question1_() {
		Student stu = new Student();
		//创建锁对象
		Lock lock = new ReentrantLock();
		//创建线程开始运行
		Thread thread1 = new Thread(new Input(stu, lock));
		Thread thread2 = new Thread(new Output(stu, lock));
		thread1.start();
		thread2.start();
	}
}

 * 输入线程，将stu赋值之后进入休眠，等待输出线程
 
class Input implements Runnable{

	private Student stu;
	private Lock lock;
	public Input(Student stu,Lock lock) {
		this.lock = lock;
		this.stu = stu;
	}
	@Override
	public void run() {
		int i = 0 ;
		while(true){
		  synchronized(stu){
			  //标记是true,等待
			    if(stu.flag){
			    	try{stu.wait();}catch(Exception ex){}
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
		  }
			
		}
	}
	
}
class Output implements Runnable{

	private Student stu;
	private Lock lock;
	public Output(Student stu,Lock lock) {
		this.lock = lock;
		this.stu = stu;
	}
	@Override
	public void run() {
		while(true){
		  synchronized(stu){	
			  //判断标记,是false,等待
			if(!stu.flag){
				try{stu.wait();}catch(Exception ex){}
		    }
			System.out.println(stu.name+".."+stu.sex);
			//标记改成false,唤醒对方线程
			stu.flag = false;
			stu.notify();
		  }
		}
	}
}
*/