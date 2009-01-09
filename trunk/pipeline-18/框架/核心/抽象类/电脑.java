package 核心.抽象类;


import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.常量;
import 核心.节点.乾坤包节点;


public abstract class 电脑 extends 乾坤包节点 {

	public 电脑() throws IOException, SAXException {
		super();
	}

	public void 安装系统(系统 系统) {
		this.放入(系统);
	}
	
	public 系统 获取系统(Integer 系统的ID) {
		return (系统) this.查看(系统的ID);
	}
	
	public 系统 获取系统(String 系统的名称) {
		return (系统) this.查看(系统的名称);
	}
	
	public void 启动() {
		for (int i = 0; i < this.当前编号.intValue(); i++) {
			系统 系统 = 获取系统(new Integer(i));
			if (系统.get启动类型().equals(常量.系统启动类型.自动启动)) {
				系统.启动主动服务();
			}
		}
	}
}
