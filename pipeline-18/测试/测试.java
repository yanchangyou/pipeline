import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.xml.sax.SAXException;

import 核心.类.电脑;
import 核心.节点.乾坤包节点;


public class 测试 {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, SAXException {
//		单点测试();
		太极牌电脑测试();
	}

	public static void 单点测试() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		乾坤包节点 济公 = (乾坤包节点) Class.forName("地神").newInstance();
		
		System.out.println(济公);
	}
	
	public static void 太极牌电脑测试() throws InstantiationException,
		IllegalAccessException, ClassNotFoundException, IOException, SAXException {

		URL[] us  =  { new  URL( "file:/d:/pipeline/lib/太极牌电脑.20090109.pl.jar" )};
		URLClassLoader URLClassLoader = new URLClassLoader(us);
		
		电脑 太极牌电脑 = (电脑) Class.forName("太极牌电脑", true, URLClassLoader).newInstance();
	
		太极牌电脑.构建(URLClassLoader);
		
//		System.out.println(太极牌电脑);
		
		太极牌电脑.启动();
	}
}
