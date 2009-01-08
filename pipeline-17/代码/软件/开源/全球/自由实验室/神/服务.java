package 软件.开源.全球.自由实验室.神;

import java.util.HashMap;
import java.util.Map;

public class 服务 extends 名称 {

	String 运行类型;
	
	int 端口;
	public int 获取端口() {
		return this.端口;
	}
	
	public void 设置端口(int 端口) {
		this.端口 = 端口;
	}
	
	public String 获取运行类型() {
		return this.运行类型;
	}
	
	public void 设置运行类型(String 运行类型) {
		this.运行类型 = 运行类型;
	}
	
	public void 开启() {
		
	}
	
	public void 关闭() {
			
	}
	
	public void 运行(系统 系统) {
		for (int i = 0; i < count; i++) {
			可流程化的 流程 = (可流程化的) 乾坤包.get("" + i);
			流程.执行(系统);
		}
	}
	
	Map 乾坤包 = new HashMap();
	
	private int count = 0;
	
	public void 设置流程(可流程化的 流程) {
		乾坤包.put(流程.获取名称(), 流程);
		乾坤包.put("" + count , 流程);	
		count ++;
	}
	
	public 电脑 获取流程(int 流程ID) {
		return (电脑) 乾坤包.get("" + 流程ID);
	}
	
	public 电脑 获取流程(String 流程名称) {
		return (电脑) 乾坤包.get(流程名称);
	}
	
	public static class 服务运行类型 {
		public static String 主动运行 = "主动";
//		String 被动运行 = "主动运行";
//		String 主动运行 = "主动运行";
	}
	
}
