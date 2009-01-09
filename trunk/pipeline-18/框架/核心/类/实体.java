package 核心.类;



import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.节点.乾坤包节点;


public abstract class 实体 extends 乾坤包节点 {
	protected 内容 内容;
	public 实体() throws IOException, SAXException {
		super();
	}
	
	public void 设置内容(内容 内容) {
		this.内容 = 内容;
	}
	
	public 内容 获取内容() {
		return this.内容;
	}

}
