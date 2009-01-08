package 软件.开源.全球.自由实验室.神;

import java.util.HashMap;
import java.util.Map;

public class 电脑 extends 名称 {

	Map 乾坤包 = new HashMap();
	
	启动系统 启动;
	
	private int count = 0;
	
	public void 安装系统(系统 系统) {
		乾坤包.put(系统.获取名称(), 系统);
		乾坤包.put("" + count , 系统);	
		count ++;
	}
	
	public 电脑 获取系统(int 系统ID) {
		return (电脑) 乾坤包.get("" + 系统ID);
	}
	
	public 电脑 获取系统(String 系统名称) {
		return (电脑) 乾坤包.get(系统名称);
	}
	
	public void 设置启动(启动系统 启动) {
		this.启动 = 启动;
	}
	
	public void 启动() {
		启动.启动();
	}
	
}
