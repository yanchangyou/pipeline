package 核心.抽象类;


import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.节点.乾坤包节点;


public abstract class 功能包 extends 乾坤包节点 {

	public 功能包() throws IOException, SAXException {
		super();
	}
	
	public void 添加功能(功能 功能) {
		this.放入(功能);
	}
	
	public 功能 获取功能(Integer 功能的ID) {
		return (功能) this.查看(功能的ID);
	}
	
	public 功能 获取功能(String 功能的名称) {
		return (功能) this.查看(功能的名称);
	}

}
