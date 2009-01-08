package 软件.开源.全球.自由实验室.神;

import java.util.HashMap;
import java.util.Map;

public class 功能包 extends 名称 {

	Map 乾坤包 = new HashMap();
	
	private int count = 0;
	
	void 添加功能(功能 功能) {
		乾坤包.put(功能.获取名称(), 功能);
		乾坤包.put("" + count , 功能);	
		count ++;
	}
	
	public 功能 获取功能(int 功能ID) {
		return (功能) 乾坤包.get("" + 功能ID);
	}
	
	public 功能 获取功能(String 功能名称) {
		return (功能) 乾坤包.get(功能名称);
	}
}
