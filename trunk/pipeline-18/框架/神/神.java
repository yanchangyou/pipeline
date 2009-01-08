package 神;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

import 共用.日志;
import 共用.有名字的;

/**
 * 全能的神<br>
 * <br>
 * 编写神要 三个东西<br>
 * 1, 一个java类<br>
 * 2, 一个digester解析文件<br>
 * 3, 一个pipeline文件<br>
 * 
 * 神会根据这三者自动构建出神<br>
 * <br>
 * @author yanchangyou@gmail.com
 * @date : 2009-1-8 下午09:18:49
 * @file : 神.java
 * @version : 0.1
 */
public abstract class 神 implements 日志, 有名字的 {

	protected String 名字;
	
	protected Map 乾坤包 = new HashMap();
	
	protected  int count = 0;
	
	public String get名字() {
		return 名字;
	}

	public void set名字(String 名字) {
		this.名字 = 名字;
	}
	
	protected void 放入(有名字的 对象) {
		乾坤包.put(对象.get名字(), 对象);
		乾坤包.put("" + count , 对象);	
		count ++;
	}
	
	protected 有名字的 拿出(int 对象的ID) {
		return (有名字的) 乾坤包.get("" + 对象的ID);
	}
	
	protected 有名字的 拿出(String 对象的名字) {
		return (有名字的) 乾坤包.get(对象的名字);
	}
	
	/**
	 * 初始化神
	 * @throws IOException
	 * @throws SAXException
	 */
	public 神() throws IOException, SAXException {
		/**
		 * digester 文件
		 */
		URL digesterFileURL = this.getClass().getResource(this.getClass().getName() + ".dg.xml");
		日志.info(digesterFileURL);
		/**
		 * pipeline 文件
		 */
		URL pipelineFileURL = this.getClass().getResource(this.getClass().getName() + ".pl.xml");
		日志.info(pipelineFileURL.getFile());
		
		/**
		 * 生成digester对象用于解析
		 */
		Digester digester = DigesterLoader.createDigester(digesterFileURL);
		
		/**
		 * 把神自己传入, digester栈中第一个元素
		 */
		digester.push(this);
		
		/**
		 * 开始解析pipeline文件构建神的灵魂
		 */
		digester.parse(new File(java.net.URLDecoder.decode(pipelineFileURL.getFile(), "UTF-8")));
	}
}