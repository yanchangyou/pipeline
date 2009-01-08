package 核心.抽象类;



import java.io.IOException;

import org.xml.sax.SAXException;

import 神.神;


public abstract class 宇宙 extends 神 {

	public 宇宙() throws IOException, SAXException {
		super();
	}
	
	public void 添加电脑(电脑 电脑) {
		this.放入(电脑);
	}
	
	public 电脑 获取电脑(int 电脑的ID) {
		return (电脑) this.拿出(电脑的ID);
	}
	
	public 电脑 获取电脑(String 电脑的名称) {
		return (电脑) this.拿出(电脑的名称);
	}

}
