package 核心.类;

import 核心.接口.核心的;

public class 实体 implements 核心的 {

	public String 获取名字() {
		return this.get名字();
	}

	public void 设置名字(String 名字) {
		this.set名字(名字);
	}
	
	protected String 名字;

	public String get名字() {
		return 名字;
	}

	public void set名字(String 名字) {
		this.名字 = 名字;
	}

	public String toString() {
		return super.toString() + " 名字 : " + this.get名字();
	}
}
