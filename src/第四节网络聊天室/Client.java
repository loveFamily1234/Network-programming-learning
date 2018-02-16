package 第四节网络聊天室;

//聊天室客户端
public class Client {
	public static void main(String[] args) {
		//启动登录线程
		Thread login=new LoginThread();
		login.start();
	}
}
