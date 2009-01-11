package 扩展.动作.类;

import 基本.类.会话;
import 基本.类.动作;
import 异常.异常;
import 扩展.动作.接口.能听的;
import 核心.类.实体;

public class 听 extends 动作 {

	public void 做(实体 实体, 会话 会话) throws 异常 {
		能听的 能听的实体 = ((能听的)实体);
		String 内容 = 能听的实体.听(会话);
		能听的实体.处理听到的内容(内容);
	}

}
