package 核心.类;



import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.节点.乾坤包节点;


public class 宇宙 extends 乾坤包节点 {

	public 宇宙() throws IOException, SAXException {
		super();
	}
	
	public void 添加电脑(电脑 电脑) {
		this.放入(电脑);
	}
	
	public 电脑 获取电脑(Integer 电脑的ID) {
		return (电脑) this.查看(电脑的ID);
	}
	
	public 电脑 获取电脑(String 电脑的名称) {
		return (电脑) this.查看(电脑的名称);
	}

}
