package ������UDP_Socket���_UDP���ն�;

import java.net.*;
public class UDPServer {//����UDP���ն�
	public static void main(String[] args) {
		byte[] bufferIn=new  byte[26];
		byte[] bufferOut;
		try{
			//�������䣬�������ݰ��׽��ֲ��󶨵�13�˿�
			DatagramSocket ds=new DatagramSocket(13);
			System.out.println("UDP���ն���������13�˿�...");
			int num=0;
			while(num<100){
				num++;
				//׼���ռ��ŷ⣬���ճ���Ϊlength�����ݰ�
				DatagramPacket packetIn=new DatagramPacket(bufferIn,bufferIn.length);
				ds.receive(packetIn);//���ţ��������ݱ�
				InetAddress ia=packetIn.getAddress();//ͨ���ŷ�õ������˵�IP��ַ�Ͷ˿ں�
				int port=packetIn.getPort();//��ȡ�˿�
				String str=new String(bufferIn);//��ȡ�ַ���
				str=str.toUpperCase();//���ַ�����ɴ�д
				bufferOut=str.getBytes();//��������鸳ֵ
				System.out.println("�յ��ĵ�"+num+"���ţ���Դip��"+ia.toString()+"���˿ں�Ϊ"+port);
				//���ŵ��ŷ�
				DatagramPacket packetOut=new DatagramPacket(bufferOut,bufferOut.length,ia,port);
				ds.send(packetOut);//���ţ����ţ�
			}
			ds.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
