package 扩展.实体.类;

import java.io.IOException;

import 核心.类.实体;
import 通用.类.名单;
import 通用.类.通用函数;

public class 能群说的实体 extends 实体 {

	public void 对所有听众说(名单 听众名单, String 内容) {
		for (int i = 0; i < 听众名单.获取下一个编号().intValue(); i++) {
			收听者 收听者 = (收听者) 听众名单.查看(new Integer(i));
			try {
				通用函数.输出内容(收听者, 内容);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
