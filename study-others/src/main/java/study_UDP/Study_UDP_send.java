package study_UDP;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Study_UDP_send {
	//实现UDP的发送端
	/*
	 * 明确知识点:
	 * 1.DatagramPacket对象用来封装数据包,同时也可以进行拆包，拆包的同时可以获取到本次发送数据的对象的ip地址,以及这次发送数据的长度
	 * 2.DatagramSocket对象用来发送数据以及接受数据,当发送数据的时候直接使用空参构造就可以了，但是如果是用来接受数据就需要指明接受的端口号
	 * 3.当发送数据的时候，发送信息是填写在DatagramPacket上，DatagramSocket只是相当于一个码头，只是起到发送的作用
	 * 4.接受数据的时候，DatagramSocket需要指明接收的端口号，然后使用receive方法进行接收数据,使用receive方法需要指明用来接受数据的数据包对象
	 * 5.DatagramPacket的构造方法:通常来说是使用DatagramPacket(byte buf[], int length,InetAddress address, int port)
	 * 参数说明:
	 * 		buf:这一次需要被发送的数据
	 * 		lenght:这一次发送数据的长度
	 * 		address:需要被发送的地址
	 * 		port:需要被发送到的端口号
	 * 
	 * */
	public static void main(String[] args) throws Exception {
		/*DatagramSocket ds=null;
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("请输入要发送的数据,如果输入-1则表示结束");
			//拿到这一次输入的数据
			String str = scan.next();
			if(str.equals("-1"))
				break;
			//获取需要发送的数据
			byte[] b = str.getBytes();
			//获取到需要发送的接收端的地址,先就是发送到本机吧
			InetAddress inet = InetAddress.getByName("127.0.0.1");
			System.out.println(inet);
			//创建数据包对象
			DatagramPacket dp = new DatagramPacket(b, b.length, inet, 6000);
			//创建发送对象
			ds = new DatagramSocket();
			//发送数据
			ds.send(dp);
		}
		//关闭连接
		scan.close();
		ds.close();*/
		String str = "这是一个字符串";
		System.out.println(new String(str.getBytes("utf8")));
		
		/*InetAddress inet = InetAddress.getByName("192.168.103.47");
		System.out.println(inet.isReachable(1000));
		System.out.println(inet.getHostName());
		send();*/
	}
	
	
	//很久没有写过UDP的程序了，再写一个
	public static void send() throws SocketException, Exception {
		File file = new File("D:\\OneDrive\\image\\美图\\132783294.jpg");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		int length;
		byte[] b = new byte[1];
		int port = 9999;
		String address = "192.168.103.47";
		String str = "咿呀咿呀哟";
		//发送数据对象
		DatagramSocket ds = new DatagramSocket();
		//发送
		while((length=bis.read(b))!=-1) {
			//创建发送数据的数据包对象
			DatagramPacket dp = new DatagramPacket(b,b.length,InetAddress.getByName(address),port);
			ds.send(dp);
		}
		
	}
	
	
	
}
