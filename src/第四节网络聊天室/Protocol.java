package ���Ľ�����������;

//Э��ӿ�
public interface Protocol {
	final static String SOFTWARE="QQ������";//�����
	final static String VERSION="1.4";//����汾��
	final static String DEFAULT_IP="127.0.0.1";//Ĭ��IP��ַ
	final static int DEFAULT_PORT=8888;//Ĭ�϶˿ں�
	final static int NAME_MAX=15;//�û�����󳤶�
	final static int NAME_MIN=4;//�û�����С����
	final static String[] FORBID_WORDS={"����ķ������","�и�����","�ֲ�����"};	//���дʻ�
	final static int TIME_BETWEEN_MSG=2;//��ˢ��ʱ������
	final static String SYSTEM_MSG="1k3N1k";//ϵͳ��Ϣ
	final static String ADD_USER="d9Fa0j";//���û�����������
	final static String DELETE_USER="9fIk3l";//�û��뿪������
	final static String EXIST_USERS="9A3kmc";//�ѵ�¼�û�
	final static String MSG_FROM="4lCcj8";//��Ϣ��Դ���
	final static String NAME_END="ci8Kja";//������Ϣ��β���
	final static String USER_LOGOUT="k8O0z2";//�û��˳�������
	final static String USER_EXIST="USER_EXIST";//�û���������ʹ��
	//��¼���ѱ�ʹ�õ��쳣
	final static class ExistException extends Exception{
		public ExistException(){
			super("�û����Ѵ���");
		}
	}
	//�汾�����쳣
	final static class VersionException extends Exception{
		public VersionException(String ver){
			super("�汾���ڣ����°汾Ϊv"+ver);
		}
	}
}
