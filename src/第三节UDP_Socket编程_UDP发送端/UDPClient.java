package 第三节UDP_Socket编程_UDP发送端;

import java.io.*;
import java.net.*;
public class UDPClient {//定义UDP发送端
	public static void main(String[] args) {
		byte[] bufferOut=new byte[26];
		byte[] bufferIn=new byte[26];
		for(int i=0;i<bufferOut.length;i++){
			bufferOut[i]=(byte)('a'+i);//定义数组元素
		}
		try{
			//建立信箱，创建数据包套接字并绑定到3333端口
			DatagramSocket ds=new DatagramSocket(3333);
			InetAddress ia=InetAddress.getByName("love-family");//通过主机名确定主机的IP地址
			//准备发信信封
			DatagramPacket packetOut=new DatagramPacket(bufferOut,bufferOut.length,ia,13);
			ds.send(packetOut);//发信
			//准备收件信封
			DatagramPacket packetIn=new DatagramPacket(bufferIn,bufferIn.length);
			ds.receive(packetIn);//收信
			String str1=new String(packetIn.getData());
			String str2=new String(bufferIn);
			System.out.println(str1);
			System.out.println(str2);
			
			ds.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
