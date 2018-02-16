package ��һ��TCP_IPЭ���URL;

import java.io.*;
import java.net.*;
import java.util.*;
//дURL�Ĳ����࣬��ȡ��վ����Ϣ��������վ��ҳ���ص����ش�����
public class URLTest {
	public static void main(String[] args) {
		try{
			//����urlName���ַ��������args���鳤�ȴ���0����args[0]��ֵ����urlName
			String urlName="http://www.itxx8.cn/index.asp";
			if(args.length>0){
				urlName=args[0];
			}
			URL url=new URL(urlName);//����URL����
			System.out.println("��ӡurl��һЩ��Ϣ��");
			System.out.println("getProtocol:"+url.getProtocol());//��ȡЭ������
			System.out.println("getHost:"+url.getHost());//��ȡ��������
			System.out.println("getFile"+url.getFile());//��ȡ��URL���ļ���
			System.out.println("getPath"+url.getPath());//��ȡ·��
			System.out.println("getPort"+url.getPort());//��ȡ�˿ں�
			System.out.println("getDefaultPort"+url.getDefaultPort());//��ȡĬ�϶˿ں�
			
			URLConnection connection=url.openConnection();//��Զ�̶�������ӣ���������ֵ
			connection.connect();//���ӵ����������򿪴�URL���õ���Դ��ͨ������
			System.out.println("��ӡͷ�ֶ���Ϣ��");
			int n=1;
			String key;
			while((key=connection.getHeaderFieldKey(n))!=null){
				String value=connection.getHeaderField(n);//���ص�n��ͷ�ֶε�ֵ
				System.out.println(key+":"+value);//��ӡͷ�ֶεļ���ֵ
				n++;
			}
			//��ӡ������Դ��һЩ����
			System.out.println("��ӡ������Դ��һЩ����");
			System.out.println("getContentType:"+connection.getContentType());//����������Դ����������
			System.out.println("getContentLength:"+connection.getContentLength());//����������Դ�����ݳ���
			System.out.println("getContentEncoding:"+connection.getContentEncoding());//����������Դ�����ݱ���
			//����������Դ�ķ������ڣ�Ϊ����������α�׼ʱ��1970��1��1�յĺ�����
			System.out.println("getDate:"+connection.getDate());
			//����������Դ�ϴε��޸����ڣ���δ֪����0
			System.out.println("getLastModified:"+connection.getLastModified());
			//���������ļ������ش��̣���ȡ�ļ�
			BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			//��ӡ���Դ�ļ�ǰ10��
			String line;
			n=1;
			while((line=in.readLine())!=null && n<=10){
				System.out.println(line);//�����ļ������
				n++;
			}
			if(line!=null){
				System.out.println("......");
			}
			
//			//�������������ָ��Ŀ���ļ�
//			BufferedWriter bw=new BufferedWriter(new FileWriter("e://URLTest.html"));
//			PrintWriter pw=new PrintWriter(bw);//�����������һ����װ
//			String temps=null;//������ʱ�ַ�������
//			while((temps=in.readLine()) != null){//���������л�ȡ��Դ�������Ƿ��ȡ���
//				//����ȡ������д��Ŀ���ļ�
//				pw.println(temps);
//			}
//			System.out.println("���ã���վ��ҳ�Ѿ�������ϣ��Ѿ�д����URLTest.html");
//			pw.close();//�ر������
			in.close();//�ر�������
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
