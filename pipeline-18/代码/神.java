import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

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
public abstract class 神 {
	
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
		
		/**
		 * pipeline 文件
		 */
		URL pipelineFileURL = this.getClass().getResource(this.getClass().getName() + ".pl.xml");
		
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
		digester.parse(new File(pipelineFileURL.getFile()));
	}
}