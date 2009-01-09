package 太极;

import java.io.IOException;

import org.xml.sax.SAXException;

import 核心.接口.可写的;
import 核心.类.系统;

public class 太极系统 extends 系统 implements 可写的 {

	public 太极系统() throws IOException, SAXException {
		super();
	}

	public void 写出(核心.类.内容 内容) {
		System.out.println(内容);
	}
}
