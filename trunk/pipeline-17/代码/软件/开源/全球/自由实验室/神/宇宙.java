package 软件.开源.全球.自由实验室.神;

import java.util.HashMap;
import java.util.Map;

public class 宇宙 extends 名称 {

	Map 乾坤包 = new HashMap();
	
	private int count = 0;
	
	public void 添加电脑(电脑 电脑) {
		乾坤包.put(电脑.获取名称(), 电脑);
		乾坤包.put("" + count , 电脑);	
		count ++;
	}
	
	public 电脑 获取电脑(int 电脑ID) {
		return (电脑) 乾坤包.get("" + 电脑ID);
	}
	
	public 电脑 获取电脑(String 电脑名称) {
		return (电脑) 乾坤包.get(电脑名称);
	}
	
}
