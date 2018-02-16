package 第二节Socket编程_客户端;

import java.io.*;
import java.net.Socket;

public class TcpClient {
	public static void main(String[] args) {
		try{
			Socket socket=new Socket("127.0.0.1",8888);//创建套接字
			boolean flag=true;//定义布尔型变量
			OutputStream os=socket.getOutputStream();//返回套接字输出流
			PrintStream ps=new PrintStream(os);//封装输出流
			
			InputStream ins=socket.getInputStream();//返回套接字输入流
			InputStreamReader isr=new InputStreamReader(ins);//封装输入流
			BufferedReader br=new BufferedReader(isr);//封装输入流
			
			String str=null;//定义字符串
			//获取输入的字符串
			BufferedReader kbr=new BufferedReader(new InputStreamReader(System.in));
			while(flag){
				//提示用户输入小写字母，服务器将转换为大写
				System.out.println("请输入您要转换的小写字母，服务器将为您转换成大写字母");
				System.out.print("请输入：");
				String inputString=kbr.readLine();//定义获取的字符串
				ps.println(inputString);//写到服务器端
				if((str=br.readLine())!=null){//读取数据
					if(str.equals("exit")){//如果输入“exit”，则退出
						flag=false;
					}
					System.out.println("服务器返回转换信息："+str);
				}
			}
			socket.close();//关闭客户端
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
