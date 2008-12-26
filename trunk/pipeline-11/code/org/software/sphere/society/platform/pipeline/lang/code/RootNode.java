package org.software.sphere.society.platform.pipeline.lang.code;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.pipeline.common.Logable;
import org.software.sphere.society.platform.pipeline.exception.lang.core.CoreException;
import org.software.sphere.society.platform.pipeline.lang.core.JustDoItable;
import org.software.sphere.society.platform.pipeline.lang.core.Sessionable;
import org.xml.sax.SAXException;

/**
 * 所有节点的根节点, 所有的从跟开始<br>
 * 
 * 作用
 * 1, 用于向下导航
 * 1.1, 查找现实节点
 * 1.2, 查找流程节点
 * 1.3, 查找服务节点
 * 2, 程序执行入口
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-12 下午11:04:53
 * @file : RootNode.java
 * @version : 0.1
 */
public class RootNode implements JustDoItable, Logable {

	private List justDoItList = new ArrayList();

	public void addJustDoIt(JustDoItable justDoIt) {
		justDoItList.add(justDoIt);
	}
	
	public void justDoIt(Sessionable session) throws CoreException {
		for (Iterator iter = justDoItList.iterator(); iter.hasNext();) {
			JustDoItable justDoIt = (JustDoItable) iter.next();
			justDoIt.justDoIt(session);
		}
	}
	
	public static void main(String[] args) {
		
	}
	
	
	private static RootNode root;
	
	private java.lang.String boot; //程序的第一个执行入口
	
	/**
	 * digester文件的路径, 用于构建整个对象网络
	 */
	final static java.lang.String VALIDATOR_RULES = "org/software/sphere/society/platform/pipeline/lang/code/digestor/pipeline-digester-rules.xml";
	
	
	/**
	 * 加载digerst文件并且解析和构建对象网络
	 * @param PATH
	 * @return root节点
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

}
