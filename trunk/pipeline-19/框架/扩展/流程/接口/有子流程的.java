package 扩展.流程.接口;

import 基本.接口.基本的;
import 核心.接口.可流程化的;

public interface 有子流程的 extends 基本的, 可流程化的 {

	public void 添加子流程(可流程化的 流程);
	
	public 可流程化的 删除子流程(可流程化的 流程);

	public 可流程化的 获取子流程(String 子流程名字);

}
