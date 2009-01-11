package 基本.类;


import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

import 基本.常量;
import 基本.接口.可构建的;
import 基本.接口.基本的;
import 核心.类.实体;

public abstract class 基本的实体 extends 实体 implements 基本的, 可构建的 {

	public String 获取状态() {
		return this.get状态();
	}

	public void 设置状态(String 状态) {
		this.set状态(状态);
	}

	public String get状态() {
		return 状态;
	}

	public void set状态(String 状态) {
		this.状态 = 状态;
	}
	
	protected String 状态;
	
	protected 基本的 父节点;
	
	public String toString() {
		return super.toString() + ", 状态 :" + 状态 + "";
	}
	
	public 基本的 获取父节点() {
		return 父节点;
	}

	public void 设置父节点(基本的 父节点) {
		this.父节点 = 父节点;
	}

	/**
	 * 初始化节点
	 * @throws IOException
	 * @throws SAXException
	 */
	public 基本的实体() {
		this.set状态(常量.节点状态.已初始化);
	}
	
	public void 构建() throws IOException, SAXException {
		构建(this.getClass().getClassLoader());
	}
	
	public void 构建(ClassLoader classLoader) throws IOException, SAXException {
		/**
		 * digester 文件
		 */
		URL digesterFileURL = classLoader.getResource(this.getClass().getName() + ".dg.xml");
		
		/**
		 * pipeline 文件
		 */
		String pipelinePath = this.getClass().getName() + ".pl.xml";
		
		
		日志.info(java.net.URLDecoder.decode(digesterFileURL.toString(), "UTF-8"));
		日志.info(pipelinePath);
		
		/**
		 * 生成digester对象用于解析
		 */
		Digester digester = DigesterLoader.createDigester(digesterFileURL);
		Thread.currentThread().setContextClassLoader(classLoader);
		digester.setUseContextClassLoader(true);
		digester.setClassLoader(classLoader);
		/**
		 * 把节点自己传入, digester栈中第一个元素
		 */
		digester.push(this);
		
		/**
		 * 开始解析pipeline文件构建节点的灵魂
		 */
		System.out.println(new File(".").getAbsolutePath());
		digester.parse(classLoader.getResourceAsStream(java.net.URLDecoder.decode(pipelinePath, "UTF-8")));
		
	}
}