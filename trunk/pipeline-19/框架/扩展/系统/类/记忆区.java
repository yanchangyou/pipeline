package 扩展.系统.类;

import java.util.HashMap;
import java.util.Map;

public class 记忆区 {

	private Map 记忆区 = new HashMap();

	public Map get记忆区() {
		return 记忆区;
	}

	public void set记忆区(Map 记忆区) {
		this.记忆区 = 记忆区;
	}
	
	public void 添加记忆块(记忆块 记忆块) {
		记忆区.put(记忆块.get名字(), 记忆块);
	}
	
	public String 获取内容(String 名字) {
		return ((记忆块)记忆区.get(名字)).获取内容();
	}
	
	public void 设置内容(String 名字, String 内容) {
		记忆区.put(名字, new 记忆块(名字, 内容));
	}
}
