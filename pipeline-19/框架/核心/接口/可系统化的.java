package 核心.接口;

import 基本.类.活动;
import 异常.异常;


public interface 可系统化的 extends 核心的 {

	public void 启动() throws 异常;

	public void 停止() throws 异常;
	
	public void 添加活动(活动 活动);
	
	public 活动 删除活动(活动 活动);
	
	public 活动 获取活动(String 活动名字);

}
