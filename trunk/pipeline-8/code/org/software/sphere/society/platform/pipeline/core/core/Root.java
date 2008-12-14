package org.software.sphere.society.platform.pipeline.core.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node01.DefaultNode01;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.core.real.Global;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;
import org.xml.sax.SAXException;

/**
 * 所有节点的根节点, 所有的从跟开始
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-12 下午11:04:53
 * @file : Root.java
 * @version : 0.1
 */
public class Root extends DefaultNode01 {
	
	/**
	 * digester文件的路径, 用于构建整个对象网络
	 */
	final static java.lang.String VALIDATOR_RULES = "org/software/sphere/society/platform/pipeline/core/core/digestor/pipeline-digester-rules.xml";

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
		log.info("初始化[self]flow, 此flow的socket为null, 后面注意");
		FlowNode sefFlow = this.getSuitableSelfFlowNode();
		Session clientSession = new Session(sefFlow.getRequest(), sefFlow.getResponse(), null);
		log.info("执行[self]flow");
		sefFlow.execute(clientSession);
		log.info("执行[self]flow结束");
	}

	/**
	 * 根节点默认下面有global节点
	 * @return
	 */
	public Global getGlobal() {
		return (Global) this.getNext();
	}

	public void setGlobal(Global global) {
		this.setNext(global);
		global.setPreNode(this);
	}
	/**
	 * 获取执行的入口, 和java语言类似, java从main开始, 而pipeline从第一个名为self的流程节点开始执行<br>
	 * 获取合适的流程节点, 查找规则:<br>
	 * 1, 从global开始查找, 不断查找名称为[self]的子节点<br>
	 * 2, 如果此节点是Flow类型, 就认为找到了<br>
	 * 3, 程序就执行此flow<br>
	 * @return 程序第一个要执行的flow节点
	 * @throws PreNodeNotFountException 
	 */
	public FlowNode getSuitableSelfFlowNode() throws PreNodeNotFountException {
		DataNode node = this.getGlobal();
		if (node == null) {
			throw new PreNodeNotFountException("global节点没有找到, 请检查root节点下面是否有global节点");
		}
		
		while (node.getName().equals(KeyWords.SELF_KEY_WORLD.toJavaString())) {
			node = node.getNextNodeByName(KeyWords.SELF_KEY_WORLD);
			if (node instanceof FlowNode) {
				break;
			}
		}
		if (node == null || !(node instanceof FlowNode)) {
			throw new PreNodeNotFountException("名为self的Flow节点没找到, 请检查是否名字错误");
		}
		return (FlowNode) node;
	}
}
