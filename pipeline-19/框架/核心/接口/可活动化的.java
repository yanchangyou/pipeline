package 核心.接口;

import 基本.类.会话;
import 异常.异常;
import 核心.类.实体;

public interface 可活动化的 extends 核心的 {

	public void 去接待(实体 实体) throws 异常;;
	
	public void 举办(会话 会话) throws 异常;;

	public void 去欢送(实体 实体) throws 异常;;
	
	public void 设置流程(可流程化的 流程) throws 异常;;
	
	public 可流程化的 获取流程() throws 异常;;
	
	public String 获取活动状态() throws 异常;
}
