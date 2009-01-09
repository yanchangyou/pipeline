package 核心.节点;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

import 共用.可统一的;
import 核心.常量;

/**
 * 全能的神<br>
 * <br>
 * 编写节点要 三个东西<br>
 * 1, 一个java类<br>
 * 2, 一个digester解析文件<br>
 * 3, 一个pipeline文件<br>
 * 
 * 节点会根据这三者自动构建出节点<br>
 * <br>
 * @author yanchangyou@gmail.com
 * @date : 2009-1-8 下午09:18:49
 * @file : 节点.java
 * @version : 0.1
 */
public abstract class 节点 implements 可统一的 {

	public int 获取被引用次数() {
		return this.get被引用次数();
	}

	public void 设置被引用次数(int 被引用次数) {
		this.set被引用次数(被引用次数);
	}

	public String 获取名字() {
		return this.get名字();
	}

	public void 设置名字(String 名字) {
		this.set名字(名字);
	}

	public String 获取状态() {
		return this.get状态();
	}

	public void 设置状态(String 状态) {
		this.set状态(状态);
	}

	public Integer 获取顺序编号() {
		return this.get顺序编号();
	}

	public void 设置顺序编号(Integer 编号) {
		this.set顺序编号(编号);
	}
	
	
	public Integer get顺序编号() {
		return 顺序编号;
	}

	public void set顺序编号(Integer 顺序编号) {
		this.顺序编号 = 顺序编号;
	}
	
	public int get被引用次数() {
		return 被引用次数;
	}

	public void set被引用次数(int 被引用次数) {
		this.被引用次数 = 被引用次数;
	}

	public String get状态() {
		return 状态;
	}

	public void set状态(String 状态) {
		this.状态 = 状态;
	}

	public String get名字() {
		return 名字;
	}

	public void set名字(String 名字) {
		this.名字 = 名字;
	}
	
	protected String 状态;
	
	protected String 名字;
	
	protected int 被引用次数 = 0;

	protected Integer 顺序编号 = new Integer(0);
	
	public String toString() {
		return "名字 : " + this.名字 + ", 状态 :" + 状态 + ",  被引用次数 : " + 被引用次数 ;
	}
	
	/**
	 * 初始化节点
	 * @throws IOException
	 * @throws SAXException
	 */
	public 节点() {
		this.set状态(常量.节点状态.已初始化);
	}
	
	public void 构建() throws IOException, SAXException {
		/**
		 * digester 文件
		 */
		URL digesterFileURL = this.getClass().getResource(this.getClass().getName() + ".dg.xml");
		
		/**
		 * pipeline 文件
		 */
		URL pipelineFileURL = this.getClass().getResource(this.getClass().getName() + ".pl.xml");
		
		
		日志.info(java.net.URLDecoder.decode(digesterFileURL.toString(), "UTF-8"));
		日志.info(java.net.URLDecoder.decode(pipelineFileURL.getFile(), "UTF-8"));
		
		/**
		 * 生成digester对象用于解析
		 */
		Digester digester = DigesterLoader.createDigester(digesterFileURL);
		
		/**
		 * 把节点自己传入, digester栈中第一个元素
		 */
		digester.push(this);
		
		/**
		 * 开始解析pipeline文件构建节点的灵魂
		 */
		digester.parse(new File(java.net.URLDecoder.decode(pipelineFileURL.getFile(), "UTF-8")));
		
	}
}