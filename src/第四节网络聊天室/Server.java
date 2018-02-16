package 第四节网络聊天室;

import java.net.*;
import java.io.*;
import java.util.*;
//聊天室服务器端
public class Server implements Protocol{
	private static Map users=new HashMap();//用户名存入key，线程存入对应value
	static class UserThread extends Thread{//用户线程类，每个用户对应一个线程
		private Socket s;
		private String username="";
		private PrintWriter out;
		private static int online;//统计在线人数
		private static String lock="";
		public UserThread(Socket s){
			this.s=s;
		}
		public void run(){
			try{
				//创建流
				InputStream is=s.getInputStream();
				InputStreamReader ir=new InputStreamReader(is,"GBK");
				BufferedReader in=new BufferedReader(ir);
				OutputStream os=s.getOutputStream();
				OutputStreamWriter or=new OutputStreamWriter(os,"GBK");
				out=new PrintWriter(or);
				
				out.println(VERSION);
				out.flush();
				if(!in.readLine().equals(VERSION)){//判断版本是否过期
					throw new Exception("版本过期");
				}
				this.username=in.readLine();
				synchronized(lock){
					if(isExist(this.username)){//读取用户名，并判断 是否已经存在
						throw new ExistException();
					}
					out.println(SYSTEM_MSG);//登录成功
					out.flush();
					//通知所有人有新用户登录
					sendAll(SYSTEM_MSG+ADD_USER+this.username);
					System.out.print("\rOnline:"+(++online));//刷新在线人数
					listAll();//给本线程用户发送在线用户列表
					users.put(this.username,this);//将本用户加入集合
				}
				String msg="";
				String touser="All";
				while(!s.isClosed()&&(msg=in.readLine())!=null&&msg.length()>0){
					//收到用户退出的系统消息，删除集合中的对应项，通知所有用户
					if(msg.startsWith(SYSTEM_MSG+USER_LOGOUT)){
						synchronized(lock){
							users.remove(this.username);
						}
						sendAll(SYSTEM_MSG+DELETE_USER+this.username);
						s.close();
						System.out.print("\rOnline"+(--online)+" ");
					}
					else{
						//收到聊天信息，解析出发送对象和信息内容并发送
						touser=msg.substring(0,msg.indexOf(NAME_END));
						msg=msg.replaceFirst(touser+NAME_END, "");
						send(msg,touser);
					}
				}
			}
			catch(ExistException e){//登录时出现用户名已存在情况，通知用户
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
		//发送信息给所有用户
		private void sendAll(String msg){
			//此处代码省略，方法实现代码见光盘源代码
		}
		//给本线程发送在线用户列表
		private void listAll(){
			//此处代码省略，方法实现代码见光盘源代码
		}
		//判断用户名是否已经有人使用
		private boolean isExist(String name){
			//此处代码省略，方法实现代码见光盘源代码
		}
		//给本线程对应的用户发送信息
		private void sendUser(String msg){
			//此处代码省略，方法实现代码见光盘源代码
		}
		//给指定对象发送信息
		private void send(String msg,String touser){
			//此处代码省略，方法实现代码见光盘源代码
		}
	}
	//主方法，启动服务器
	public static void main(String[] args) {
		//根据参数的情况获得端口号，无效时使用默认值，并返回相应信息
		int port=DEFAULT_PORT;
		if(args.length>0){
			int newport;
			try{
				//判断端口是否合适
				newport=Integer.parseInt(args[0]);
				if(newport>65535||newport<0){//无效端口
					System.out.println("The port"+newport+"is invalid");
				}
				else if(newport<=1024){//操作系统预留端口
					System.out.println("The port 0~1024 is not allowed");
				}else{
					port=newport;
				}
			}catch(NumberFormatException e){//不能转换成整数的参数
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
		}catch(IOException e){//端口绑定失败
			System.out.println("Failed to bind "+port+" port.");
		}
	}
}
