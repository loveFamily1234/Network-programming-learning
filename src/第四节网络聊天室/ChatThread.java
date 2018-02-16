package 第四节网络聊天室;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatThread extends Thread implements Protocol{//聊天线程
	private Map users=new HashMap();
	private String name;
	private Socket s;
	private BufferedReader in;
	private PrintWriter out;
	private JComboBox cb;
	private JFrame f;
	private JTextArea ta;
	private JTextField tf;
	private static long time;//上一条信息的发出时间
	private static int total;//在线人数统计
	public  ChatThread(String name,Socket s,BufferedReader in,PrintWriter out){
		/*省略*/
	}
	public void run(){
		//设置聊天室窗口界面
		JButton jb=new JButton("私聊窗口");
		/*省略*/
		//监听“私聊窗口”按钮（匿名内部类）
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*省略*/
			}
		});
		class MyWindowListener implements WindowListener{//监听“关闭”按钮
			public void actionPerformed(ActionEvent e){
				/*省略*/
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
		MyWindowListener wl=new MyWindowListener();
		f.addWindowListener(wl);
		//接收服务器发送的信息
		class GetMsgThread extends Thread{
			public void run(){
				/*
				 * 不断接收服务器信息，外层循环防止读到null跳出循环
				 * 对系统信息进行处理，主要处理公聊和私聊信息的不同显示
				 * 当异常产生时向系统发出退出信息，实现代码见光盘源代码
				 */
			}
		}
		GetMsgThread gt=new GetMsgThread();
		gt.start();
		//监听用户在“公聊窗口”输入的信息（匿名内部类）
		tf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*省略*/
			}
		});
		f.setVisible(true);
	}
	//处理系统信息
	private String specialMsg(String msg){
		if(msg.startsWith(SYSTEM_MSG)){
			msg=msg.replaceFirst(SYSTEM_MSG, "");
			//当有人进入聊天室
			if(msg.startsWith(ADD_USER)){
				/*省略*/
				msg+="进入聊天室";
			}
			//当有人离开聊天室
			else if(msg.startsWith(DELETE_USER)){
				/*省略*/
				msg+="退出聊天室";
			}
			//登录时获得的在线用户列表信息
			else if(msg.startsWith(EXIST_USERS)){
				/*省略*/
				msg+="正在聊天室";
			}
			//即时显示在线人数
			f.setTitle(SOFTWARE+"-"+name+"  当前在线人数："+total);
			return msg;
		}
		return msg;
	}
	//检查信息是否允许发送，包括检查敏感词汇、空信息、刷屏
	private boolean isAllowed(String msg,String msgto){
		//过滤空信息
		//过滤敏感词汇
		//防刷屏
		/*省略*/
	}
	//私聊窗口
	private class ChatWindow{
		JFrame fs;
		JTextArea tas;
		String name;
		public ChatWindow(String username){
			this.name=username;
			JTextField tfs=new JTextField();
			/*省略，“私聊窗口”界面实现见光盘源代码*/
			//监听用户在“私聊窗口”输入的信息（匿名内部类）
			tfs.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(users.containsKey(name)){
						/*省略*/
					}else{
						tas.append("信息发送失败，用户已经离开聊天室。\n");
					}
				}
			});
		}
	}
	private void sendMsg(String name1,String msg,boolean isRoomShow){
		/*省略*/
	}
}
