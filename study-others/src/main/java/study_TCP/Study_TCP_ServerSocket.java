package study_TCP;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Study_TCP_ServerSocket {

	/*
	 * 需要达成的目的:
	 * 	客户端上传一个图片，服务器接收到之后存在D盘的根目录下,然后给客户端返回一个接收成功的提示
	 * */
	public static void main(String[] args) throws Exception {
		/*//穿件服务器的套接字对象
		ServerSocket server = new ServerSocket(12580);
		while(true) {
			//服务器等待客户端发送消息
			Socket socket = server.accept();
			//开启一个新的线程以上传文件
			new Thread(new ThreadServerUpload(socket)).start();;
		}*/
//		receive();
		server();
	}
	//创建一个接收端
	public static void receive() throws Exception {
		/*//创建一个端口为12580的socket
		ServerSocket socket = new ServerSocket(12580);
		//接受传递过来的数据
		Socket accept = socket.accept();
		//拿到连接信息，socket
		InputStream input = accept.getInputStream();
		//打印从服务器发送过来的数据
		byte[] b = new byte[1024];
		int length;
		while((length=input.read(b))!=-1) {
			System.out.println("接收到:"+new String(b,0,length));
		}
		//向客户端回执数据
		OutputStream out = accept.getOutputStream();
		out.write("已收到".getBytes());
		accept.shutdownOutput();
		socket.close();*/
	}
	
	//创建一个服务器的接收端，每当有一个客户端连接时就创建一个线程用来接收文件(使用线程池)
	public static void server() throws Exception {
		//创建一个端口号为12580的接收端
		ServerSocket socket = new ServerSocket(12580);
		//创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(20);
		//当有客户端连接到时
		while(true) {
			Socket accept = socket.accept();
			pool.submit(new ServerWriteFile(accept));
		}
	}
	
}
class ServerWriteFile implements Runnable{

	//重写构造器
	public ServerWriteFile(Socket socket) {
		this.socket = socket;
	}
	private Socket socket;
	@Override
	public void run() {
		String fileName = "d:/美女/美女图片"+System.currentTimeMillis()+new Random().nextInt(1000000)+".jpg";
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName));
			//拿到输入流
			InputStream input = socket.getInputStream();
			byte[] b = new byte[1024];
			int length;
			while((length=input.read(b))!=-1) {
				bos.write(b,0,length);
			}
			//写入文件完毕
			bos.close();
			socket.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

/**
 * 多线程上传的服务器,这一种服务器是不需要关的,也就是说用户只需要连接到服务器就会运行上传服务
 * */
class ThreadServerUpload implements Runnable{

	//需要一个代表客户端的Socket
	private Socket socket;
	public ThreadServerUpload(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			//对于文件名需要采用特殊的方式
			//使用公司名称+当前毫秒值+6位随机数的方式
			String fileName = "d:/图片/美女图片"+System.currentTimeMillis()+new Random().nextInt(1000000);
			FileOutputStream fos = new FileOutputStream("d:/美女图片/"+fileName+".jpg");
			//获取到输入流对象
			InputStream in = socket.getInputStream();
			byte[] b = new byte[1024];
			int lenght = 0;
			while((lenght=in.read(b))!=-1) {
				fos.write(b,0,lenght);
			}
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
