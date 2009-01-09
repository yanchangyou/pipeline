package 核心.类;

import java.io.IOException;

import org.xml.sax.SAXException;

public class 字符串 extends 内容 {

	public 字符串() throws IOException, SAXException {
		super();
	}

	private String string;

	public String 获取字符串() {
		return string;
	}

	public void 设置字符串(String string) {
		this.string = string;
	}
	
	public String toString() {
		return this.string;
	}
	
}
