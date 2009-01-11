package 扩展.实体.类;

import java.net.Socket;

import 核心.类.实体;

public class 收听者 extends 实体 {

	private Socket 连接;

	public 收听者(String 名字, Socket 连接) {
		this.名字 = 名字;
		this.连接 = 连接;
	}
	
	public Socket 获取连接() {
		return 连接;
	}

	public void 设置连接(Socket 连接) {
		this.连接 = 连接;
	}
	
}
