package study_UDP;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Study_UDP_receive {

	//用来实现UDP的接收端
	/*
	 * 方法明确:
	 * 1.接收端需要指明需要接收的端口号
	 * 2.接收端可以从获取到的数据包中获取到本次发送的数据包的长度
	 * 3.接收端可以获取到发送端的IP地址
	 * */
	public static void main(String[] args) throws Exception {
		/*//创建用来接收数据的数组
		byte[] b = new byte[1024];
		//创建一个用来接收数据的对象,需要指明接收数据的端口号
		DatagramSocket ds = new DatagramSocket(6000);
		//创建一个数据包对象用来接收数据
		DatagramPacket dp = new DatagramPacket(b,b.length);
		StringBuilder sb = new StringBuilder();
		int count = 0;
		while(true) {
			if(count==10)
				break;
			//接收数据
			ds.receive(dp);
			String str = new String(b,0,dp.getLength());
			//接下来就是打印数据了
			sb.append("接收到了:"+str+"\r\n");
			count++;
		}
		ds.close();
		System.out.println(sb.toString());*/
		receive();
	}
	
	//创建接收端
	public static void receive() throws Exception {
		byte[] b = new byte[1024];
		//接收数据的对象
		DatagramPacket dp = new DatagramPacket(b, b.length);
		DatagramSocket ds = new DatagramSocket(6000);
		ds.receive(dp);
		System.out.println(new String(b,0,dp.getLength()));
	}
}
