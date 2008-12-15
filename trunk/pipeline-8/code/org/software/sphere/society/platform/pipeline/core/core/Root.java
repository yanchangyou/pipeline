package org.software.sphere.society.platform.pipeline.core.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.pipeline.common.ServiceNode;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.DefaultNode0X;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.core.real.RealNode;
import org.software.sphere.society.platform.pipeline.core.real.Service;
import org.software.sphere.society.platform.pipeline.exception.core.core.CoreException;
import org.software.sphere.society.platform.pipeline.exception.core.core.NoAvailableServiceException;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;
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
 * @file : Root.java
 * @version : 0.1
 */
public class Root extends DefaultNode0X {
	
	private static Root root;
	
	private java.lang.String boot; //程序的第一个执行入口
	
	/**
	 * digester文件的路径, 用于构建整个对象网络
	 */
	final static java.lang.String VALIDATOR_RULES = "org/software/sphere/society/platform/pipeline/core/core/digestor/pipeline-digester-rules.xml";

	public Root() {
		super();
		root = this;
	}
	
	/**
	 * 查找现实节点
	 * @param nodePath
	 * @return
	 * @throws NextNodeNotFountException
	 * @throws CoreException
	 */
	public static RealNode getRealNode(String nodePath) throws NextNodeNotFountException, CoreException {
		DataNode node = root.getNextNodeByPath(nodePath);
		if (node instanceof RealNode) {
			//nothing to do
		} else {
			throw new CoreException("类型不匹配, 此节点不是real-node节点, 此节点名称:" + node.getName() + ", 类型:" + node.getClass().getName() + ", 请检查路径是否正确");
		}
		return (RealNode) node;
	}
	
	/**
	 * 查找流程节点
	 * @param nodePath
	 * @return
	 * @throws NextNodeNotFountException
	 * @throws CoreException
	 */
	public static FlowNode getFlowNode(String nodePath) throws NextNodeNotFountException, CoreException {
		DataNode node = root.getNextNodeByPath(nodePath);
		if (node instanceof FlowNode) {
			//nothing to do
		} else {
			throw new CoreException("类型不匹配, 此节点不是flow-node节点, 此节点名称:" + node.getName() + ", 类型:" + node.getClass().getName() + ", 请检查路径是否正确");
		}
		return (FlowNode) node;
	}
	

	/**
	 * 查找真实的服务节点
	 * @param nodePath
	 * @return
	 * @throws NextNodeNotFountException
	 * @throws CoreException
	 * @throws NoAvailableServiceException 
	 */
	public static ServiceNode getServiceNode(String nodePath, String serviceName) throws NextNodeNotFountException, CoreException, NoAvailableServiceException {
		ServiceNode definedServiceNode = null;	
		DataNode node = root.getNextNodeByPath(nodePath);
		if (node instanceof RealNode) {
			//nothing to do
		} else {
			throw new CoreException("类型不匹配, 此节点不是service-node节点, 此节点名称:" + node.getName() + ", 类型:" + node.getClass().getName() + ", 请检查路径是否正确");
		}
		
		RealNode realNode = (RealNode)node;
		definedServiceNode = realNode.getService(serviceName);
		if (definedServiceNode == null) {
			throw new NoAvailableServiceException("定义的服务节点没有找到, 此节点路径是:" + serviceName);
		}
		return definedServiceNode;
	}
	
	
	/**
	 * 加载digerst文件并且解析和构建对象网络
	 * @param PATH
	 * @return root节点
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Root load(final java.lang.String PATH) throws IOException,
			SAXException {

		URL rulesUrl = Root.class.getClassLoader().getResource(VALIDATOR_RULES);

		URL xmlUrl = Root.class.getClassLoader().getResource(PATH);

		Digester digester = DigesterLoader.createDigester(rulesUrl);
		return (Root) digester.parse(new File(xmlUrl.getFile()));
	}
	
	/**
	 * 
	 * 从根节点开始执行
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		log.info("执行第一个Service, 此时socket为null, 后面注意");
		 
		DataNode node = this.getNextNodeByPath(new String(this.boot));
		if (!(node instanceof Service)) {
			throw new NoAvailableServiceException("没有找服务[service]标签, 请检查此路径是否正确, 此路径是:" + this.boot); 
		}
		Service service = (Service)node;
		log.info("执行[self]flow");
		service.service();
		log.info("执行[self]flow结束");
	}

	public void addNextRealNode(RealNode realNode) {
		this.addNextNode(realNode);
		realNode.setPreNode(this);
	}
	/**
	 * 如果知道了boot属性就按照boot去找第一个执行的flow<br>
	 * 如果没有boot属性的即按照self规则找第一个执行的flow<br>
	 * 获取执行的入口, 和java语言类似, java从main开始, 而pipeline从第一个名为self的流程节点开始执行<br>
	 * 获取合适的流程节点, 查找规则:<br>
	 * 1, 从realNode开始查找, 不断查找名称为[self]的子节点<br>
	 * 2, 如果此节点是Flow类型, 就认为找到了<br>
	 * 3, 程序就执行此flow<br>
	 * @return 程序第一个要执行的flow节点
	 * @throws PreNodeNotFountException 
	 * @throws NextNodeNotFountException 
	 */
	public FlowNode getSuitableSelfFlowNode() throws NextNodeNotFountException {
		DataNode node = null;
		StringBuffer firstFlowPathBuf = new StringBuffer();
		if(this.boot != null && this.boot.trim().length() != 0) { //如果知道了boot属性, 就按照boot属性来查找第一个执行的flow
			firstFlowPathBuf.append(this.boot);
			node = this.getNextNodeByPath(new String(this.boot));
		} else { //如果没有知道boot属性, 按照默认的self规则查找第一个flow
			 node = this.getNextNodeByName(KeyWords.SELF_KEY_WORLD);
			 firstFlowPathBuf.append(node.getName());
			if (node == null) {
				throw new NextNodeNotFountException("realNode节点没有找到, 请检查root节点下面是否有realNode节点");
			}
			
			while (node.getName().equals(KeyWords.SELF_KEY_WORLD.toJavaString())) {
				node = node.getNextNodeByName(KeyWords.SELF_KEY_WORLD);
				 firstFlowPathBuf.append(node.getName());
				if (node instanceof FlowNode) {
					break;
				}
			}
			
		}
		if (node == null || !(node instanceof FlowNode)) {
			throw new NextNodeNotFountException("第一个Flow节点没找到, 请检查是否名字错误, 此路径是:" + firstFlowPathBuf);
		}
		return (FlowNode) node;
	}

	public java.lang.String getBoot() {
		return boot;
	}

	public void setBoot(java.lang.String boot) {
		this.boot = boot;
	}
}
