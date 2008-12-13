package org.software.sphere.society.platform.pipeline.core.lang.execute;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.pipeline.PipelineConst;
import org.software.sphere.society.platform.pipeline.core.data.node01.DefaultNode01;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.core.real.Global;
import org.software.sphere.society.platform.pipeline.exception.data.MiddleNodeNotFountException;
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
	final static java.lang.String VALIDATOR_RULES = "org/software/sphere/society/platform/pipeline/core/lang/execute/digestor/pipeline-digester-rules.xml";

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
		log.info("初始化第一个flow, 此flow的socket为null, 后面注意");
		FlowNode sefFlow = this.getSelfFlow();
		Session clientSession = new Session(sefFlow.getRequest(), sefFlow.getResponse(), null);
		log.info("执行第一个 flow");
		sefFlow.execute(clientSession);
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
	 * 获取执行的入口, 和java语言类似, java从main开始, 而Ω语言从self.self.self.self.self开始执行
	 * @return
	 * @throws MiddleNodeNotFountException
	 */
	public FlowNode getSelfFlow() throws MiddleNodeNotFountException {
		final String SELF_UNIT_PATH = new String(PipelineConst.CORE.EXECUTE.SELF_UNIT_PATH);
		return (FlowNode) this.getGlobal().getNextNodeByPath(SELF_UNIT_PATH);
	}
}
