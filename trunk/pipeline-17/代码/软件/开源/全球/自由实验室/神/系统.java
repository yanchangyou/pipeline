package 软件.开源.全球.自由实验室.神;

import java.util.HashMap;
import java.util.Map;

public class 系统 extends 名称 implements 可写的 {
	
	int 端口;
	
	功能包 功能包;
	
	Map 乾坤包 = new HashMap();
	
	private int count = 0;
	
	public void 添加服务(服务 服务) {
		乾坤包.put(服务.获取名称(), 服务);
		乾坤包.put("" + count , 服务);	
		count ++;
	}
	
	public 服务 获取服务(int 服务ID) {
		return (服务) 乾坤包.get("" + 服务ID);
	}
	
	public 服务 获取服务(String 服务名称) {
		return (服务) 乾坤包.get(服务名称);
	}
	
	public void 设置功能(功能包 功能包) {
		this.功能包 = 功能包;
	}
	
	public 功能包 获取功能包() {
		return this.功能包;
	}
	
	public int 获取端口() {
		return this.端口;
	}
	
	public void 设置端口(int 端口) {
		this.端口 = 端口;
	}

	public void 写(String 内容) {
		System.out.println(内容);
	}
	
	public void 运行() {
		
	}
	
	public void 启动主动服务() {
		for (int i = 0; i < count; i++) {
			服务 服务项 = (服务) 乾坤包.get("" + i);
			if (服务项.获取运行类型().equals(服务.服务运行类型.主动运行)) {
				服务项.运行(this);
			}
		}
	}
}
