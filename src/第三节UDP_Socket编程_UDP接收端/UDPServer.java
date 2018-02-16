package 第三节UDP_Socket编程_UDP接收端;

import java.net.*;
public class UDPServer {//定义UDP接收端
	public static void main(String[] args) {
		byte[] bufferIn=new  byte[26];
		byte[] bufferOut;
		try{
			//建立信箱，创建数据包套接字并绑定到13端口
			DatagramSocket ds=new DatagramSocket(13);
			System.out.println("UDP接收端启动，在13端口...");
			int num=0;
			while(num<100){
				num++;
				//准备收件信封，接收长度为length的数据包
				DatagramPacket packetIn=new DatagramPacket(bufferIn,bufferIn.length);
				ds.receive(packetIn);//收信，接受数据报
				InetAddress ia=packetIn.getAddress();//通过信封得到发信人的IP地址和端口号
				int port=packetIn.getPort();//获取端口
				String str=new String(bufferIn);//获取字符串
				str=str.toUpperCase();//把字符串变成大写
				bufferOut=str.getBytes();//给输出数组赋值
				System.out.println("收到的第"+num+"封信，来源ip是"+ia.toString()+"，端口号为"+port);
				//发信的信封
				DatagramPacket packetOut=new DatagramPacket(bufferOut,bufferOut.length,ia,port);
				ds.send(packetOut);//发信（回信）
			}
			ds.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
