package 扩展.流程.类;

import 基本.类.会话;
import 异常.异常;
import 核心.类.实体;

public class 重复 extends 有子流程的流程 {

	private String 条件;
	
	private int 次数;
	
	public void 执行(实体 实体, 会话 会话) throws 异常 {
		for (int i = 0; i < 次数; i++) {
			有子流程的流程的缺省执行(this, 实体, 会话);	
		}
	}

	public int get次数() {
		return 次数;
	}

	public void set次数(int 次数) {
		this.次数 = 次数;
	}

	public String get条件() {
		return 条件;
	}

	public void set条件(String 条件) {
		this.条件 = 条件;
	}

}
