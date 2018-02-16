package 第二节Socket编程_服务器端;

import java.io.*;
import java.net.*;
//写服务器端，对端口进行监听，接受客户端请求并为客户端服务
public class TcpServer {
	public static void main(String[] args) {
		//服务器对8888端口进行监听，接收到客户端连接请求后，创建和启动服务线程
		long count=0;//声明用来计数的变量
		ServerSocket serverSocket=null;//声明服务器套接字
		try{
			serverSocket=new ServerSocket(8888);//创建绑定到8888端口的ServerSocket对象
			System.out.println("服务器对8888端口实施监听......");//打印提示信息
			//服务器循环接收客户端的请求，为不同的客户端提供服务
			while(true){
				//接收客户端的连接请求，若有连接请求则返回连接对应的Socket对象
				Socket socket=serverSocket.accept();
				count++;
				ServerThread serverThread=new ServerThread(socket,count);//创建服务器线程
				serverThread.start();//启动服务器线程
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

//定义ServerThread类，继承自Thread类
class ServerThread extends Thread{
	//根据客户端和计数器来创建服务器线程
	Socket socket;
	long count;
	public ServerThread(Socket socket, long count) {//构造方法
		this.socket = socket;
		this.count = count;
	}
	public void run(){//重写run方法
		int timeCounter=0;
		try{
			InputStream ins=socket.getInputStream();//获取套接字的输入流
			InputStreamReader isr=new InputStreamReader(ins);//封装输入流
			BufferedReader br=new BufferedReader(isr);//封装输入流
			
			OutputStream os=socket.getOutputStream();//获取套接字的输出流
			PrintStream pw=new PrintStream(os);//封装输出流
			
			while(true){
				timeCounter++;//声明用来计数的变量
				String str=br.readLine();//定义字符串为读取br的值
				if(str.equals("exit")){
					//如果客户端输入的是“exit”，则关闭客户端
					pw.println("exit");
					pw.flush();
					socket.close();
					break;
				}
				//告知客户端是第几次转换、有几个客户端，并输出转换的字符串
				pw.println("这是"+socket.toString()+"第"+timeCounter+"次发送转换请求，现在有"+count+"个客户在线，转换后的字符串为"
						+str.toUpperCase());
				pw.flush();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
