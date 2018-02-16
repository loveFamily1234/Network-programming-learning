package 第四节网络聊天室;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

class LoginThread extends Thread implements Protocol{//登录线程
	private JFrame loginf;
	private JTextField t;
	public void run(){
		//设置登录界面
		JPanel loginp=new JPanel();
		JTextField loginname=new JTextField();
		JTextField loginIP=new JTextField();
		JTextField loginport=new JTextField();
		
		/*登录界面的实现代码见光盘源代码*/
		//监听“退出”按钮（匿名内部类）
		JButton b1=new JButton("退出");
		loginp.add(b1);//将退出按钮添加到面板中
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		final JButton b2=new JButton("登录");
		loginp.add(b2);//将“登录”按钮添加到面板中
		loginf.setVisible(true);
		//监听“登录”按钮和JTextField的回车
		class ButtonListener implements ActionListener{
			private Socket s;
			public void actionPerformed(ActionEvent e){
				if(checkName(loginname.getText())){//检验登录名
					try{//获取IP和端口
						s=new Socket(loginIP.getText(),Integer.parseInt(loginport.getText()));
						try{//连接服务器
							InputStream is=s.getInputStream();
							InputStreamReader ir=new InputStreamReader(is,"GBK");
							BufferedReader in=new BufferedReader(ir);
							
							OutputStream os=s.getOutputStream();
							OutputStreamWriter or=new OutputStreamWriter(os,"GBK");
							PrintWriter out=new PrintWriter(or);
							
							out.println(VERSION);
							out.flush();
							out.println(loginname.getText());
							out.flush();
							String ver;
							if(!(ver=in.readLine()).equals(VERSION)){
								throw new VersionException(ver);
							}
							if(in.readLine().equals(USER_EXIST)){
								throw new ExistException();
							}
							//启动聊天线程
							Thread chat=new ChatThread(loginname.getText(),s,in,out);
							loginf.setVisible(false);
							loginf.setEnabled(false);
							chat.start();
						}catch(IOException e1){//流操作异常
							t.setText("通信失败，请重试！");
							try{
								s.close();
							}catch(IOException e2){
								
							}
						}catch(VersionException e3){//版本存在异常（接口中定义）
							t.setText(e3.getMessage());
							try{
								s.close();
							}catch(IOException e4){
								
							}
						}catch(ExistException e5){//用户存在异常（接口中定义）
							t.setText(e5.getMessage());
							try{
								s.close();
							}catch(IOException e6){
								
							}
						}
					}catch(IOException e7){//Socket连接服务器异常
						t.setText("连接服务器失败，请重试！");
					}
				}
			}
		}
		ButtonListener bl=new ButtonListener();
		b2.addActionListener(bl);//为“登录”按钮注册监听器
		loginname.addActionListener(bl);//登录名注册监听器
		loginIP.addActionListener(bl);//登录IP注册监听器
		loginport.addActionListener(bl);//登录端口注册监听器
	}
	
	//判断登录名是否有效
	private boolean checkName(String name){
		if(name.length()<NAME_MIN){
			t.setText("错误：登录名不能小于"+NAME_MIN+"字符");
			return false;
		}
		if(name.length()>NAME_MAX){
			t.setText("错误：登录名不能大于"+NAME_MAX+"字符");
			return false;
		}
		if(name.indexOf(" ")>-1){
			t.setText("错误：登录名不能包含空格");
			return false;
		}
		for(int i=0;i<FORBID_WORDS.length;i++){
			if(name.indexOf(FORBID_WORDS[i])>-1){
				t.setText("错误：登录名不能包含敏感信息");
				return false;
			}
		}
		return true;
	}
}
