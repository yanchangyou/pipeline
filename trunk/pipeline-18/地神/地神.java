
import java.io.IOException;

import org.xml.sax.SAXException;

import 神.神;


public class 地神 extends 神 {

	private String 姓名;

	public String get姓名() {
		return 姓名;
	}

	public void set姓名(String 姓名) {
		this.姓名 = 姓名;
	}

	public String toString() {
		return this.get姓名();
	}
	
	public 地神() throws IOException, SAXException {
		super();
	}
}
