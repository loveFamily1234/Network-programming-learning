package ���Ľ�����������;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatThread extends Thread implements Protocol{//�����߳�
	private Map users=new HashMap();
	private String name;
	private Socket s;
	private BufferedReader in;
	private PrintWriter out;
	private JComboBox cb;
	private JFrame f;
	private JTextArea ta;
	private JTextField tf;
	private static long time;//��һ����Ϣ�ķ���ʱ��
	private static int total;//��������ͳ��
	public  ChatThread(String name,Socket s,BufferedReader in,PrintWriter out){
		/*ʡ��*/
	}
	public void run(){
		//���������Ҵ��ڽ���
		JButton jb=new JButton("˽�Ĵ���");
		/*ʡ��*/
		//������˽�Ĵ��ڡ���ť�������ڲ��ࣩ
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*ʡ��*/
			}
		});
		class MyWindowListener implements WindowListener{//�������رա���ť
			public void actionPerformed(ActionEvent e){
				/*ʡ��*/
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
		//���շ��������͵���Ϣ
		class GetMsgThread extends Thread{
			public void run(){
				/*
				 * ���Ͻ��շ�������Ϣ�����ѭ����ֹ����null����ѭ��
				 * ��ϵͳ��Ϣ���д�����Ҫ�����ĺ�˽����Ϣ�Ĳ�ͬ��ʾ
				 * ���쳣����ʱ��ϵͳ�����˳���Ϣ��ʵ�ִ��������Դ����
				 */
			}
		}
		GetMsgThread gt=new GetMsgThread();
		gt.start();
		//�����û��ڡ����Ĵ��ڡ��������Ϣ�������ڲ��ࣩ
		tf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				/*ʡ��*/
			}
		});
		f.setVisible(true);
	}
	//����ϵͳ��Ϣ
	private String specialMsg(String msg){
		if(msg.startsWith(SYSTEM_MSG)){
			msg=msg.replaceFirst(SYSTEM_MSG, "");
			//�����˽���������
			if(msg.startsWith(ADD_USER)){
				/*ʡ��*/
				msg+="����������";
			}
			//�������뿪������
			else if(msg.startsWith(DELETE_USER)){
				/*ʡ��*/
				msg+="�˳�������";
			}
			//��¼ʱ��õ������û��б���Ϣ
			else if(msg.startsWith(EXIST_USERS)){
				/*ʡ��*/
				msg+="����������";
			}
			//��ʱ��ʾ��������
			f.setTitle(SOFTWARE+"-"+name+"  ��ǰ����������"+total);
			return msg;
		}
		return msg;
	}
	//�����Ϣ�Ƿ������ͣ�����������дʻ㡢����Ϣ��ˢ��
	private boolean isAllowed(String msg,String msgto){
		//���˿���Ϣ
		//�������дʻ�
		//��ˢ��
		/*ʡ��*/
	}
	//˽�Ĵ���
	private class ChatWindow{
		JFrame fs;
		JTextArea tas;
		String name;
		public ChatWindow(String username){
			this.name=username;
			JTextField tfs=new JTextField();
			/*ʡ�ԣ���˽�Ĵ��ڡ�����ʵ�ּ�����Դ����*/
			//�����û��ڡ�˽�Ĵ��ڡ��������Ϣ�������ڲ��ࣩ
			tfs.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(users.containsKey(name)){
						/*ʡ��*/
					}else{
						tas.append("��Ϣ����ʧ�ܣ��û��Ѿ��뿪�����ҡ�\n");
					}
				}
			});
		}
	}
	private void sendMsg(String name1,String msg,boolean isRoomShow){
		/*ʡ��*/
	}
}
