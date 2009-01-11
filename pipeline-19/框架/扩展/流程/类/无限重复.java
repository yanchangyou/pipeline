package 扩展.流程.类;

import 基本.类.会话;
import 异常.异常;
import 扩展.动作.接口.能群说的;
import 核心.类.实体;

public class 无限重复 extends 有子流程的流程 {
	
	public void 执行(实体 实体, 会话 会话) {
		while (true) {
			try {
				有子流程的流程的缺省执行(this, 实体, 会话);
			} catch (异常 e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				能群说的 群说者 = ((能群说的)实体);
				群说者.对所有听众说(群说者.获取听众名单(), e.getMessage());
				break ;
			}
			
		}
	}
	
}
