package ���Ľ�����������;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

class LoginThread extends Thread implements Protocol{//��¼�߳�
	private JFrame loginf;
	private JTextField t;
	public void run(){
		//���õ�¼����
		JPanel loginp=new JPanel();
		JTextField loginname=new JTextField();
		JTextField loginIP=new JTextField();
		JTextField loginport=new JTextField();
		
		/*��¼�����ʵ�ִ��������Դ����*/
		//�������˳�����ť�������ڲ��ࣩ
		JButton b1=new JButton("�˳�");
		loginp.add(b1);//���˳���ť��ӵ������
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		final JButton b2=new JButton("��¼");
		loginp.add(b2);//������¼����ť��ӵ������
		loginf.setVisible(true);
		//��������¼����ť��JTextField�Ļس�
		class ButtonListener implements ActionListener{
			private Socket s;
			public void actionPerformed(ActionEvent e){
				if(checkName(loginname.getText())){//�����¼��
					try{//��ȡIP�Ͷ˿�
						s=new Socket(loginIP.getText(),Integer.parseInt(loginport.getText()));
						try{//���ӷ�����
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
							//���������߳�
							Thread chat=new ChatThread(loginname.getText(),s,in,out);
							loginf.setVisible(false);
							loginf.setEnabled(false);
							chat.start();
						}catch(IOException e1){//�������쳣
							t.setText("ͨ��ʧ�ܣ������ԣ�");
							try{
								s.close();
							}catch(IOException e2){
								
							}
						}catch(VersionException e3){//�汾�����쳣���ӿ��ж��壩
							t.setText(e3.getMessage());
							try{
								s.close();
							}catch(IOException e4){
								
							}
						}catch(ExistException e5){//�û������쳣���ӿ��ж��壩
							t.setText(e5.getMessage());
							try{
								s.close();
							}catch(IOException e6){
								
							}
						}
					}catch(IOException e7){//Socket���ӷ������쳣
						t.setText("���ӷ�����ʧ�ܣ������ԣ�");
					}
				}
			}
		}
		ButtonListener bl=new ButtonListener();
		b2.addActionListener(bl);//Ϊ����¼����ťע�������
		loginname.addActionListener(bl);//��¼��ע�������
		loginIP.addActionListener(bl);//��¼IPע�������
		loginport.addActionListener(bl);//��¼�˿�ע�������
	}
	
	//�жϵ�¼���Ƿ���Ч
	private boolean checkName(String name){
		if(name.length()<NAME_MIN){
			t.setText("���󣺵�¼������С��"+NAME_MIN+"�ַ�");
			return false;
		}
		if(name.length()>NAME_MAX){
			t.setText("���󣺵�¼�����ܴ���"+NAME_MAX+"�ַ�");
			return false;
		}
		if(name.indexOf(" ")>-1){
			t.setText("���󣺵�¼�����ܰ����ո�");
			return false;
		}
		for(int i=0;i<FORBID_WORDS.length;i++){
			if(name.indexOf(FORBID_WORDS[i])>-1){
				t.setText("���󣺵�¼�����ܰ���������Ϣ");
				return false;
			}
		}
		return true;
	}
}
