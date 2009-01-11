package 扩展.活动.类;

import 基本.类.基本的实体;
import 通用.类.名单;

public class 活动项目 extends 基本的实体 {

	private 名单 参与者名单;

	public 名单 获取参与者名单() {
		return 参与者名单;
	}

	public void 设置名单(名单 参与者名单) {
		this.参与者名单 = 参与者名单;
	}
	
	public String toString() {
		return super.toString() + 参与者名单;
	}
}
