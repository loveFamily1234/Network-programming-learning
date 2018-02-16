package ���Ľ�����������;

import java.net.*;
import java.io.*;
import java.util.*;
//�����ҷ�������
public class Server implements Protocol{
	private static Map users=new HashMap();//�û�������key���̴߳����Ӧvalue
	static class UserThread extends Thread{//�û��߳��࣬ÿ���û���Ӧһ���߳�
		private Socket s;
		private String username="";
		private PrintWriter out;
		private static int online;//ͳ����������
		private static String lock="";
		public UserThread(Socket s){
			this.s=s;
		}
		public void run(){
			try{
				//������
				InputStream is=s.getInputStream();
				InputStreamReader ir=new InputStreamReader(is,"GBK");
				BufferedReader in=new BufferedReader(ir);
				OutputStream os=s.getOutputStream();
				OutputStreamWriter or=new OutputStreamWriter(os,"GBK");
				out=new PrintWriter(or);
				
				out.println(VERSION);
				out.flush();
				if(!in.readLine().equals(VERSION)){//�жϰ汾�Ƿ����
					throw new Exception("�汾����");
				}
				this.username=in.readLine();
				synchronized(lock){
					if(isExist(this.username)){//��ȡ�û��������ж� �Ƿ��Ѿ�����
						throw new ExistException();
					}
					out.println(SYSTEM_MSG);//��¼�ɹ�
					out.flush();
					//֪ͨ�����������û���¼
					sendAll(SYSTEM_MSG+ADD_USER+this.username);
					System.out.print("\rOnline:"+(++online));//ˢ����������
					listAll();//�����߳��û����������û��б�
					users.put(this.username,this);//�����û����뼯��
				}
				String msg="";
				String touser="All";
				while(!s.isClosed()&&(msg=in.readLine())!=null&&msg.length()>0){
					//�յ��û��˳���ϵͳ��Ϣ��ɾ�������еĶ�Ӧ�֪ͨ�����û�
					if(msg.startsWith(SYSTEM_MSG+USER_LOGOUT)){
						synchronized(lock){
							users.remove(this.username);
						}
						sendAll(SYSTEM_MSG+DELETE_USER+this.username);
						s.close();
						System.out.print("\rOnline"+(--online)+" ");
					}
					else{
						//�յ�������Ϣ�����������Ͷ������Ϣ���ݲ�����
						touser=msg.substring(0,msg.indexOf(NAME_END));
						msg=msg.replaceFirst(touser+NAME_END, "");
						send(msg,touser);
					}
				}
			}
			catch(ExistException e){//��¼ʱ�����û����Ѵ��������֪ͨ�û�
				out.println(SYSTEM_MSG+USER_EXIST);
				out.flush();
			}
			catch(Exception e){
				
			}
			finally{
				try{
					s.close();
				}catch(Exception e){
					
				}
			}
		}
		//������Ϣ�������û�
		private void sendAll(String msg){
			//�˴�����ʡ�ԣ�����ʵ�ִ��������Դ����
		}
		//�����̷߳��������û��б�
		private void listAll(){
			//�˴�����ʡ�ԣ�����ʵ�ִ��������Դ����
		}
		//�ж��û����Ƿ��Ѿ�����ʹ��
		private boolean isExist(String name){
			//�˴�����ʡ�ԣ�����ʵ�ִ��������Դ����
		}
		//�����̶߳�Ӧ���û�������Ϣ
		private void sendUser(String msg){
			//�˴�����ʡ�ԣ�����ʵ�ִ��������Դ����
		}
		//��ָ����������Ϣ
		private void send(String msg,String touser){
			//�˴�����ʡ�ԣ�����ʵ�ִ��������Դ����
		}
	}
	//������������������
	public static void main(String[] args) {
		//���ݲ����������ö˿ںţ���Чʱʹ��Ĭ��ֵ����������Ӧ��Ϣ
		int port=DEFAULT_PORT;
		if(args.length>0){
			int newport;
			try{
				//�ж϶˿��Ƿ����
				newport=Integer.parseInt(args[0]);
				if(newport>65535||newport<0){//��Ч�˿�
					System.out.println("The port"+newport+"is invalid");
				}
				else if(newport<=1024){//����ϵͳԤ���˿�
					System.out.println("The port 0~1024 is not allowed");
				}else{
					port=newport;
				}
			}catch(NumberFormatException e){//����ת���������Ĳ���
				System.out.println("Invalid port number!");
			}
		}
		try{
			ServerSocket ss=new ServerSocket(port);
			System.out.print("Server is running.\nPort:"+port+"\nOnline:0");
			while(true){
				Socket s=ss.accept();
				Thread t=new UserThread(s);
				t.start();
			}
		}catch(IOException e){//�˿ڰ�ʧ��
			System.out.println("Failed to bind "+port+" port.");
		}
	}
}
