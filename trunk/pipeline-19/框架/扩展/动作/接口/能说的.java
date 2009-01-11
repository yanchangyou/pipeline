package 扩展.动作.接口;

import 基本.接口.能动作的;
import 基本.类.会话;

public interface 能说的 extends 能动作的 {

	public String 获取说的内容();
	
	public void 说(会话 会话, String 内容);

}
