package �ڶ���Socket���_��������;

import java.io.*;
import java.net.*;
//д�������ˣ��Զ˿ڽ��м��������ܿͻ�������Ϊ�ͻ��˷���
public class TcpServer {
	public static void main(String[] args) {
		//��������8888�˿ڽ��м��������յ��ͻ�����������󣬴��������������߳�
		long count=0;//�������������ı���
		ServerSocket serverSocket=null;//�����������׽���
		try{
			serverSocket=new ServerSocket(8888);//�����󶨵�8888�˿ڵ�ServerSocket����
			System.out.println("��������8888�˿�ʵʩ����......");//��ӡ��ʾ��Ϣ
			//������ѭ�����տͻ��˵�����Ϊ��ͬ�Ŀͻ����ṩ����
			while(true){
				//���տͻ��˵����������������������򷵻����Ӷ�Ӧ��Socket����
				Socket socket=serverSocket.accept();
				count++;
				ServerThread serverThread=new ServerThread(socket,count);//�����������߳�
				serverThread.start();//�����������߳�
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

//����ServerThread�࣬�̳���Thread��
class ServerThread extends Thread{
	//���ݿͻ��˺ͼ������������������߳�
	Socket socket;
	long count;
	public ServerThread(Socket socket, long count) {//���췽��
		this.socket = socket;
		this.count = count;
	}
	public void run(){//��дrun����
		int timeCounter=0;
		try{
			InputStream ins=socket.getInputStream();//��ȡ�׽��ֵ�������
			InputStreamReader isr=new InputStreamReader(ins);//��װ������
			BufferedReader br=new BufferedReader(isr);//��װ������
			
			OutputStream os=socket.getOutputStream();//��ȡ�׽��ֵ������
			PrintStream pw=new PrintStream(os);//��װ�����
			
			while(true){
				timeCounter++;//�������������ı���
				String str=br.readLine();//�����ַ���Ϊ��ȡbr��ֵ
				if(str.equals("exit")){
					//����ͻ���������ǡ�exit������رտͻ���
					pw.println("exit");
					pw.flush();
					socket.close();
					break;
				}
				//��֪�ͻ����ǵڼ���ת�����м����ͻ��ˣ������ת�����ַ���
				pw.println("����"+socket.toString()+"��"+timeCounter+"�η���ת������������"+count+"���ͻ����ߣ�ת������ַ���Ϊ"
						+str.toUpperCase());
				pw.flush();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
