package �ڶ���Socket���_�ͻ���;

import java.io.*;
import java.net.Socket;

public class TcpClient {
	public static void main(String[] args) {
		try{
			Socket socket=new Socket("127.0.0.1",8888);//�����׽���
			boolean flag=true;//���岼���ͱ���
			OutputStream os=socket.getOutputStream();//�����׽��������
			PrintStream ps=new PrintStream(os);//��װ�����
			
			InputStream ins=socket.getInputStream();//�����׽���������
			InputStreamReader isr=new InputStreamReader(ins);//��װ������
			BufferedReader br=new BufferedReader(isr);//��װ������
			
			String str=null;//�����ַ���
			//��ȡ������ַ���
			BufferedReader kbr=new BufferedReader(new InputStreamReader(System.in));
			while(flag){
				//��ʾ�û�����Сд��ĸ����������ת��Ϊ��д
				System.out.println("��������Ҫת����Сд��ĸ����������Ϊ��ת���ɴ�д��ĸ");
				System.out.print("�����룺");
				String inputString=kbr.readLine();//�����ȡ���ַ���
				ps.println(inputString);//д����������
				if((str=br.readLine())!=null){//��ȡ����
					if(str.equals("exit")){//������롰exit�������˳�
						flag=false;
					}
					System.out.println("����������ת����Ϣ��"+str);
				}
			}
			socket.close();//�رտͻ���
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
