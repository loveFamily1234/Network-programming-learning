package ������UDP_Socket���_UDP���Ͷ�;

import java.io.*;
import java.net.*;
public class UDPClient {//����UDP���Ͷ�
	public static void main(String[] args) {
		byte[] bufferOut=new byte[26];
		byte[] bufferIn=new byte[26];
		for(int i=0;i<bufferOut.length;i++){
			bufferOut[i]=(byte)('a'+i);//��������Ԫ��
		}
		try{
			//�������䣬�������ݰ��׽��ֲ��󶨵�3333�˿�
			DatagramSocket ds=new DatagramSocket(3333);
			InetAddress ia=InetAddress.getByName("love-family");//ͨ��������ȷ��������IP��ַ
			//׼�������ŷ�
			DatagramPacket packetOut=new DatagramPacket(bufferOut,bufferOut.length,ia,13);
			ds.send(packetOut);//����
			//׼���ռ��ŷ�
			DatagramPacket packetIn=new DatagramPacket(bufferIn,bufferIn.length);
			ds.receive(packetIn);//����
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
