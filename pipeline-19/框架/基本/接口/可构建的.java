package 基本.接口;

import java.io.IOException;

import org.xml.sax.SAXException;


public interface 可构建的 extends 基本的 {

	public void 构建(ClassLoader classLoader) throws IOException, SAXException;
	
	public void 构建() throws IOException, SAXException;
}
