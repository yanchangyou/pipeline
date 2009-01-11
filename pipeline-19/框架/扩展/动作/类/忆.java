package 扩展.动作.类;

import 基本.类.会话;
import 基本.类.动作;
import 扩展.动作.接口.能忆的;
import 核心.类.实体;

public class 忆 extends 动作 {

	private String 名字;

	public void 做(实体 实体, 会话 会话) {
		((能忆的)实体).忆(名字);
	}

	public String get名字() {
		return 名字;
	}

	public void set名字(String 名字) {
		this.名字 = 名字;
	}

}
