package 核心.抽象类;


import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.节点.乾坤包节点;


public abstract class 电脑 extends 乾坤包节点 {

	public 电脑() throws IOException, SAXException {
		super();
	}

	public void 添加系统(系统 系统) {
		this.放入(系统);
	}
	
	public 系统 获取系统(Integer 系统的ID) {
		return (系统) this.查看(系统的ID);
	}
	
	public 系统 获取系统(String 系统的名称) {
		return (系统) this.查看(系统的名称);
	}
	
	protected 启动系统 启动;

	public 启动系统 get启动() {
		return 启动;
	}

	public void set启动(启动系统 启动) {
		this.启动 = 启动;
	}

	public void 启动() {
		启动.启动();
	}
}
