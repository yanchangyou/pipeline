package 软件.开源.全球.自由实验室.神;

import java.util.HashMap;
import java.util.Map;

public class 启动系统 {
	
	Map 乾坤包 = new HashMap();
	
	private int count = 0;
	
	public void 添加系统(系统 系统) {
		乾坤包.put(系统.获取名称(), 系统);
		乾坤包.put("" + count , 系统);	
		count ++;
	}
	
	public 系统 获取系统(int 系统ID) {
		return (系统) 乾坤包.get("" + 系统ID);
	}
	
	public 系统 获取系统(String 系统名称) {
		return (系统) 乾坤包.get(系统名称);
	}

	public void 启动() {
		for (int i = 0; i < count; i++) {
			系统 系统 = (系统) 乾坤包.get("" + i);
			系统.启动主动服务();
		}
	}
}
