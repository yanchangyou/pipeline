package 基本.类;

import 异常.异常;
import 核心.接口.可动作化的;
import 核心.接口.可流程化的;
import 核心.类.实体;

public abstract class 动作 extends 基本的实体 implements 可动作化的, 可流程化的 {

	public void 执行(实体 实体, 会话 会话)  throws 异常{
		做(实体, 会话);
	}
}
