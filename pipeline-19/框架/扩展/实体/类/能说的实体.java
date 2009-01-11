package 扩展.实体.类;

import java.io.PrintWriter;
import java.net.Socket;

import 基本.类.会话;
import 扩展.动作.接口.能说的;
import 基本.类.基本的实体;

public class 能说的实体 extends 基本的实体 implements 能说的 {

	public void 说(会话 会话, String 内容) {
		
		Socket 连接 = 会话.获取连接();
		try {
			PrintWriter os = new PrintWriter(连接.getOutputStream());
			os.println(内容);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String 获取说的内容() {
		return "hello";
	}
}
