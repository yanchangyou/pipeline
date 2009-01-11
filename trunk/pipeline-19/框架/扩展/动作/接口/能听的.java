package 扩展.动作.接口;

import 基本.接口.能动作的;
import 基本.类.会话;
import 异常.异常;

public interface 能听的 extends 能动作的 {

	public String 听(会话 会话) throws 异常 ;
	
	public void 处理听到的内容(String 内容) throws 异常 ;
}
