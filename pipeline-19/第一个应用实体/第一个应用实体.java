

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import org.xml.sax.SAXException;

import 基本.类.会话;
import 基本.类.系统;
import 异常.异常;
import 扩展.动作.接口.能听的;
import 扩展.动作.接口.能忆的;
import 扩展.动作.接口.能登记的;
import 扩展.动作.接口.能群说的;
import 扩展.动作.接口.能记的;
import 扩展.动作.接口.能说的;
import 扩展.实体.类.收听者;
import 扩展.实体.类.能听的实体;
import 扩展.实体.类.能群说的实体;
import 扩展.实体.类.能说的实体;
import 扩展.系统.类.记忆区;
import 扩展.系统.类.记忆块;
import 通用.类.名单;
import 通用.类.通用函数;

public class 第一个应用实体 extends 系统 implements 能听的, 能说的, 能记的, 能忆的, 能登记的, 能群说的 {
	
	private 记忆区 记忆区 = new 记忆区();
	
	private 能听的实体 能听的实体 = new 能听的实体();
	
	private 能说的实体 能说的实体 = new 能说的实体();
	
	public 第一个应用实体() throws IOException, SAXException {
		super();
	}

	public void 说(会话 会话, String 内容) {

		System.out.println(会话.获取连接());
		Socket 连接 = 会话.获取连接(); 
		if (连接.isInputShutdown() || 连接.isOutputShutdown() || !连接.isConnected()) {
			try {
				连接.close();
				连接.shutdownOutput();
				连接.shutdownInput();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String ip$port = 通用函数.获取IP和端口(连接);
			内容 = "****消息 : [" + ip$port + " 退出] *****";
			能说的实体.说(会话, 内容);
			return;
		} 
		能说的实体.说(会话, 内容);
	}

	private String 当前数据区;
	
	public void 处理听到的内容(String 内容) {
		当前数据区 = 内容;
		System.out.print(new Date() + ":");
		System.out.println(当前数据区);
	}

	public String 听(会话 会话) throws 异常 {
		return 能听的实体.听(会话);
	}
	
	public void 设置记忆区(记忆区 记忆区) {
		this.记忆区 = 记忆区;
	}

	public void 记(String 名字, String 内容) {
		记忆区.添加记忆块(new 记忆块(名字, 内容));
		当前数据区 = 内容;
	}

	public String 获取说的内容() {
		return 当前数据区;
	}

	public void 忆(String 名字) {
		当前数据区 = 记忆区.获取内容(名字);
	}

	private 名单 收听者名单 = new 名单();
	
	public void 登记(收听者 收听者) {
		收听者名单.填入(收听者);
	}
	private 能群说的实体 能群说的实体 = new 能群说的实体();
	
	public void 对所有听众说(名单 听众名单, String 内容) {
		能群说的实体.对所有听众说(听众名单, 内容);
	}

	public 名单 获取听众名单() {
		return this.收听者名单;
	}

	public String 获取要说的内容() {
		return this.当前数据区;
	}
}
