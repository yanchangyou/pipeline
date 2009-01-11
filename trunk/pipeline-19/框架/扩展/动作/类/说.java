package 扩展.动作.类;

import 基本.类.会话;
import 基本.类.动作;
import 扩展.动作.接口.能说的;
import 核心.类.实体;

public class 说 extends 动作 {
//	
//	private String 内容;
//
//	public void 设置内容(String 内容) {
//		this.内容 = 内容;
//	}

	public void 做(实体 实体, 会话 会话) {
		能说的 能说的实体 = ((能说的)实体);
		能说的实体.说(会话, 能说的实体.获取说的内容());
	}

}
