package 基本.类;

import java.net.Socket;

import 异常.异常;
import 核心.接口.可活动化的;

public class 会话 extends 基本的实体 implements Runnable {

	private 可活动化的 活动;
	
	private Socket 连接;

	public 会话(可活动化的 活动, Socket 连接) {
		this.活动 = 活动;
		this.连接 = 连接;
	}
	
	public void run() {
		try {
			活动.举办(this);
		} catch (异常 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
	public Socket 获取连接() {
		return 连接;
	}

	public void 设置连接(Socket 连接) {
		this.连接 = 连接;
	}

	public 可活动化的 get活动() {
		return 活动;
	}

	public void set活动(可活动化的 活动) {
		this.活动 = 活动;
	}
}
