package 扩展.动作.类;

import 基本.类.会话;
import 基本.类.动作;
import 扩展.动作.接口.能群说的;
import 核心.类.实体;

public class 群说 extends 动作 {

	public void 做(实体 实体, 会话 会话) {
		能群说的 群说者 = ((能群说的)实体);
		群说者.对所有听众说(群说者.获取听众名单(), 群说者.获取要说的内容());
	}

}
