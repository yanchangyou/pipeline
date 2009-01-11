package 扩展.实体.类;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import 基本.类.会话;
import 基本.类.基本的实体;
import 异常.异常;
import 扩展.动作.接口.能听的;
import 通用.类.通用函数;

public class 能听的实体 extends 基本的实体 implements 能听的 {

	private String 内容;
	
	public String 获取内容() {
		return 内容;
	}

	public String 听(会话 会话) throws 异常 {
		String 内容 = null;
		try {
			String 身份 = 通用函数.获取IP和端口(会话.获取连接());
			BufferedReader sin = new BufferedReader(new InputStreamReader(会话.获取连接().getInputStream()));
			
			内容 = sin.readLine();
			if (内容 == null) {
				throw new 异常("**** 消息 [" + 身份 + "] 退出 ******");
			}
			内容 = "\r\n" + 身份  + " >>\r\n  " + 内容;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 内容;
	}

	public void 处理听到的内容(String 内容) {
		this.内容 = 内容;
	}
}
