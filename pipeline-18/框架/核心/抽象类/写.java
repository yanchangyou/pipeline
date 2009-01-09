package 核心.抽象类;



import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.接口.可写的;
import 核心.接口.可流程化的;
import 核心.节点.乾坤包节点;


public class 写 extends 乾坤包节点 implements 可流程化的 {

	public 写() throws IOException, SAXException {
		super();
	}
	protected 内容 内容;
	
	public void 设置内容(内容 内容) {
		this.内容 = 内容;
	}
	
	public 内容 获取内容() {
		return this.内容;
	}
	
	public void 写出(可写的 可写的) {
		可写的.写出(this.获取内容());
	}

	public void 执行(系统 系统) {
		写出((可写的) 系统);
	}
}
