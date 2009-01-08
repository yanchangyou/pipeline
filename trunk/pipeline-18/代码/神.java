import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

/**
 * ȫ�ܵ���<br>
 * <br>
 * ��д��Ҫ ��������<br>
 * 1, һ��java��<br>
 * 2, һ��digester�����ļ�<br>
 * 3, һ��pipeline�ļ�<br>
 * 
 * �������������Զ���������<br>
 * <br>
 * @author yanchangyou@gmail.com
 * @date : 2009-1-8 ����09:18:49
 * @file : ��.java
 * @version : 0.1
 */
public abstract class �� implements ��־ {
	
	/**
	 * ��ʼ����
	 * @throws IOException
	 * @throws SAXException
	 */
	public ��() throws IOException, SAXException {
		/**
		 * digester �ļ�
		 */
		URL digesterFileURL = this.getClass().getResource(this.getClass().getName() + ".dg.xml");
		
		��־.info(digesterFileURL);
		
		/**
		 * pipeline �ļ�
		 */
		URL pipelineFileURL = this.getClass().getResource(this.getClass().getName() + ".pl.xml");
		
		��־.info(pipelineFileURL);
		
		/**
		 * ����digester�������ڽ���
		 */
		Digester digester = DigesterLoader.createDigester(digesterFileURL);
		
		/**
		 * �����Լ�����, digesterջ�е�һ��Ԫ��
		 */
		digester.push(this);
		
		/**
		 * ��ʼ����pipeline�ļ�����������
		 */
		digester.parse(new File(pipelineFileURL.getFile()));
	}
}