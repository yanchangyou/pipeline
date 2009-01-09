package org.software.sphere.society.platform.pipeline.lang.code;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.pipeline.common.Logable;
import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.core.Skillable;
import org.xml.sax.SAXException;

/**
 * ���нڵ�ĸ��ڵ�, ���еĴӸ���ʼ<br>
 * 
 * ����
 * 1, �������µ���
 * 1.1, ������ʵ�ڵ�
 * 1.2, �������̽ڵ�
 * 1.3, ���ҷ���ڵ�
 * 2, ����ִ�����
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-12 ����11:04:53
 * @file : RootNode.java
 * @version : 0.1
 */
public class RootNode extends Code implements Skillable, Logable {

	private static RootNode root;
	
	private java.lang.String boot; //����ĵ�һ��ִ�����
	
	/**
	 * digester�ļ���·��, ���ڹ���������������
	 */
	final static java.lang.String VALIDATOR_RULES = "org/software/sphere/society/platform/pipeline/lang/code/digestor/pipeline-digester-rules.xml";
	
	
	/**
	 * ����digerst�ļ����ҽ����͹�����������
	 * @param PATH
	 * @return root�ڵ�
	 * @throws IOException
	 * @throws SAXException
	 */
	public static RootNode load(final java.lang.String PATH) throws IOException,
			SAXException {

		URL rulesUrl = RootNode.class.getClassLoader().getResource(VALIDATOR_RULES);

		URL xmlUrl = RootNode.class.getClassLoader().getResource(PATH);

		Digester digester = DigesterLoader.createDigester(rulesUrl);
		return (RootNode) digester.parse(new File(xmlUrl.getFile()));
	}

	public java.lang.String getBoot() {
		return boot;
	}

	public void setBoot(java.lang.String boot) {
		this.boot = boot;
	}

	public static RootNode getRoot() {
		return root;
	}

	public static void setRoot(RootNode root) {
		RootNode.root = root;
	}

	public void justDoIt(Skillable skill) throws CoreException {
		for (Iterator iter = nextNodeList.iterator(); iter.hasNext();) {
			Skillable subSkill = (Skillable) iter.next();
			subSkill.justDoIt(subSkill);
		}
	}
}