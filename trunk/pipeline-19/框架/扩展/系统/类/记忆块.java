package 扩展.系统.类;

public class 记忆块 {

	private String 名字;
	
	private String 内容;

	public 记忆块() {
		
	}
	
	public 记忆块(String 名字, String 内容) {
		this.名字 = 名字;
		this.内容 = 内容;
	}
	
	public String get名字() {
		return 名字;
	}

	public void set名字(String 名字) {
		this.名字 = 名字;
	}

	public String 获取内容() {
		return 内容;
	}

	public void 设置内容(String 内容) {
		this.内容 = 内容;
	}
	
	
}
