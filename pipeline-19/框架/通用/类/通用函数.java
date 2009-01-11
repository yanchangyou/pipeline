package 通用.类;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import 基本.类.会话;
import 扩展.实体.类.收听者;

public class 通用函数 {

	public static String 获取IP和端口(Socket socket) {
		return socket.getRemoteSocketAddress().toString()
		.substring(1);
	}

	public static void 输出内容(收听者 收听者, String 内容) throws IOException {
		输出内容(收听者.获取连接(), 内容);
	}

	public static void 输出内容(会话 会话, String 内容) throws IOException {
		输出内容(会话.获取连接(), 内容);
	}

	public static void 输出内容(Socket 连接, String 内容) throws IOException {
		if (连接.isConnected() && !连接.isClosed()) {
			PrintWriter printWriter = new PrintWriter(连接.getOutputStream());
			printWriter.println(内容);
			printWriter.flush();
		}
	}

	public static void 输出内容(OutputStream os, String 内容) {
		
	}
}
