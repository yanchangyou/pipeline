package 扩展.动作.接口;

import 基本.接口.能动作的;
import 通用.类.名单;

public interface 能群说的 extends 能动作的 {

	public 名单 获取听众名单();
	
	public String 获取要说的内容();
	
	public void 对所有听众说(名单 听众名单, String 内容);

}
