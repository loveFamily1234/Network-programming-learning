package 第一节TCP_IP协议和URL;

import java.io.*;
import java.net.*;
import java.util.*;
//写URL的测试类，获取网站的信息，并将网站主页下载到本地磁盘上
public class URLTest {
	public static void main(String[] args) {
		try{
			//定义urlName的字符串，如果args数组长度大于0，则将args[0]的值赋给urlName
			String urlName="http://www.itxx8.cn/index.asp";
			if(args.length>0){
				urlName=args[0];
			}
			URL url=new URL(urlName);//创建URL对象
			System.out.println("打印url的一些信息：");
			System.out.println("getProtocol:"+url.getProtocol());//获取协议名称
			System.out.println("getHost:"+url.getHost());//获取主机名称
			System.out.println("getFile"+url.getFile());//获取此URL的文件名
			System.out.println("getPath"+url.getPath());//获取路径
			System.out.println("getPort"+url.getPort());//获取端口号
			System.out.println("getDefaultPort"+url.getDefaultPort());//获取默认端口号
			
			URLConnection connection=url.openConnection();//打开远程对象的连接，返回连接值
			connection.connect();//连接到服务器，打开此URL引用的资源的通信链接
			System.out.println("打印头字段信息：");
			int n=1;
			String key;
			while((key=connection.getHeaderFieldKey(n))!=null){
				String value=connection.getHeaderField(n);//返回第n个头字段的值
				System.out.println(key+":"+value);//打印头字段的键、值
				n++;
			}
			//打印引用资源的一些属性
			System.out.println("打印引用资源的一些属性");
			System.out.println("getContentType:"+connection.getContentType());//返回引用资源的内容类型
			System.out.println("getContentLength:"+connection.getContentLength());//返回引用资源的内容长度
			System.out.println("getContentEncoding:"+connection.getContentEncoding());//返回引用资源的内容编码
			//返回引用资源的发送日期，为距离格林威治标准时间1970年1月1日的毫秒数
			System.out.println("getDate:"+connection.getDate());
			//返回引用资源上次的修改日期，若未知返回0
			System.out.println("getLastModified:"+connection.getLastModified());
			//下载引用文件到本地磁盘，读取文件
			BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			//打印输出源文件前10行
			String line;
			n=1;
			while((line=in.readLine())!=null && n<=10){
				System.out.println(line);//读出文件并输出
				n++;
			}
			if(line!=null){
				System.out.println("......");
			}
			
//			//创建输出流，并指定目标文件
//			BufferedWriter bw=new BufferedWriter(new FileWriter("e://URLTest.html"));
//			PrintWriter pw=new PrintWriter(bw);//对输出流做进一步封装
//			String temps=null;//声明临时字符串引用
//			while((temps=in.readLine()) != null){//从输入流中获取资源并测试是否读取完毕
//				//将获取的数据写入目标文件
//				pw.println(temps);
//			}
//			System.out.println("您好，网站主页已经下载完毕，已经写入了URLTest.html");
//			pw.close();//关闭输出流
			in.close();//关闭输入流
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
