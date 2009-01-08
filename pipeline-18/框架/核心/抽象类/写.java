package 核心.抽象类;



import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.接口.可写的;
import 核心.接口.可流程化的;
import 神.神;


public abstract class 写 extends 神 implements 可写的, 可流程化的 {

	public 写() throws IOException, SAXException {
		super();
	}
	protected 内容 内容;
	
	public void set内容(内容 内容) {
		this.内容 = 内容;
	}
	
	public 内容 get内容() {
		return this.内容;
	}
	
	public void 写出(可写的 可写的神) {
		可写的神.写出(this.get内容());
	}
}
