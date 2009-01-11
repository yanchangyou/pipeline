package 扩展.动作.类;

import 基本.类.会话;
import 基本.类.动作;
import 扩展.动作.接口.能登记的;
import 扩展.实体.类.收听者;
import 核心.类.实体;
import 通用.类.通用函数;

public class 登记 extends 动作 {

	public void 做(实体 实体, 会话 会话) {
		String 标志 = 通用函数.获取IP和端口(会话.获取连接());
		收听者 收听者 = new 收听者(标志, 会话.获取连接());
		((能登记的)实体).登记(收听者);
	}

}
