package 核心.接口;

import 基本.类.会话;
import 异常.异常;
import 核心.类.实体;

public interface 可动作化的 extends 核心的 {

	public void 做(实体 实体, 会话 会话) throws 异常;

}
