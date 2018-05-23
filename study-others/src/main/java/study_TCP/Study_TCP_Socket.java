package study_TCP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Study_TCP_Socket {
	public static void main(String[] args) throws Exception {
		/*//获取到服务器的连接
		Socket socket = new Socket("xiaoding-pc",12580);
		//连接服务器
		OutputStream out = socket.getOutputStream();
		//获取文件的输入流
		FileInputStream fis = new FileInputStream("美女图片.jpg");
		//边读边输出
		byte[] b = new byte[1024];
		int lenght;
		while((lenght=fis.read(b))!=-1) {
			out.write(b,0,lenght);
			out.flush();
		}
		socket.shutdownOutput();
		System.out.println("上传成功");
		fis.close();
		socket.close();*/
//		clientFile();
		send();
	}
	//从服务器接受一个文件
	public static void receive() throws Exception {
		
		int port = 11111;
		while(true) {
			File file = new File("d://file"+System.currentTimeMillis()+".jpg");
			FileOutputStream fos = new FileOutputStream(file);
			String address = "192.168.103.47";
			Socket socket = new Socket(address,port);
			copyFile copyFile = new Study_TCP_Socket().new copyFile(fos, socket.getInputStream());
			new Thread(copyFile).start();
			socket.close();
			Thread.sleep(1000);
		}
	}
	
	//我也来创建一个线程,用来文件的复制
	private class copyFile implements Runnable{

		private OutputStream out;
		private InputStream input;
		public copyFile(OutputStream out,InputStream input) {
			this.out = out;
			this.input = input;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//一个超级大的缓存数组
			try {
				BufferedInputStream bis = new BufferedInputStream(input);
				BufferedOutputStream bos = new BufferedOutputStream(out);
				byte[] b = new byte[1024*64];
				int length;
				while((length=bis.read(b))!=-1) {
					bos.write(b, 0, length);
				}
				System.out.println("接收文件完成");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	//再次回顾一下客户端的编写
	public static void send() throws Exception {
		//如果是要发送文件
		String message = "前排";
		String filePath = "";
		int port = 11111;
		String address = "192.168.103.47";
		Socket socket = new Socket(address,port);
		OutputStream out = socket.getOutputStream();
		if(filePath.equals("")) {
			out.write(message.getBytes("gbk"));
		}else {
			File file = new File(filePath);
			//读取文件的信息并且发送
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			int length;
			byte[] b = new byte[1024];
			while((length=bis.read(b))!=-1) {
				out.write(b, 0, length);
			}
			System.out.println("发送文件完毕");
		}
			
			
		socket.shutdownOutput();
		socket.close();
	}
	
	//发送文件否?
	public static String problem() {
		return null;
	}
	
	//向服务端发送文件
	public static void clientFile() throws Exception {
		File file = new File("D:\\OneDrive\\image\\美图\\132783294.jpg");
		//先创建Socket对象
		Socket socket = new Socket("192.168.103.47",11111);
		//获取到输出流对象
		OutputStream out = socket.getOutputStream();
		byte[] b = new byte[1024];
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		int length;
		while((length=bis.read(b))!=-1) {
			out.write(b,0,length);
		}
		//向服务器发送数据完毕
		socket.shutdownOutput();
		System.out.println("发送文件完成");
		bis.close();
		socket.close();
	}
	
	
}
