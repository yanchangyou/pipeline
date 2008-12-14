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
import org.software.sphere.society.platform.pipeline.core.real.RealNode;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;
import org.xml.sax.SAXException;

/**
 * ���нڵ�ĸ��ڵ�, ���еĴӸ���ʼ
 * 
 * @author yanchangyou@gmail.com
 * @date : 2008-12-12 ����11:04:53
 * @file : Root.java
 * @version : 0.1
 */
public class Root extends DefaultNode01 {
	
	/**
	 * digester�ļ���·��, ���ڹ���������������
	 */
	final static java.lang.String VALIDATOR_RULES = "org/software/sphere/society/platform/pipeline/core/core/digestor/pipeline-digester-rules.xml";

	/**
	 * ����digerst�ļ����ҽ����͹�����������
	 * @param PATH
	 * @return root�ڵ�
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Root load(final java.lang.String PATH) throws IOException,
			SAXException {

		URL rulesUrl = Root.class.getClassLoader().getResource(VALIDATOR_RULES);

		URL xmlUrl = Root.class.getClassLoader().getResource(PATH);
		
		
		
		
		Digester digester = DigesterLoader.createDigester(rulesUrl);

//		Digester digester = new Digester();
//		digester.push(new Root());
//		
//		java.lang.String root = "root";
//		java.lang.String global = "global";
//		java.lang.String economy = "economy";
////		java.lang.String root = "root";
////		java.lang.String root = "root";
////		java.lang.String root = "root";
////		java.lang.String root = "root";
//
//		java.lang.String[] real_pattern_array = new java.lang.String[]{"*/global", "*/economy", "*/market", "*/supplier", "*/server"};
//		java.lang.Class[] real_class_array = new java.lang.Class[]{Global.class, Economy.class, Market.class, Supplier.class, Server.class};
//		
//		for (int i = 0; i < real_pattern_array.length; i++) {
//			digester.addObjectCreate(real_pattern_array[i], real_class_array[i]);
//			digester.addSetProperties(real_pattern_array[i]);
//			digester.addSetNext(real_pattern_array[i], "addNextRealNode");
//		}
//
//		java.lang.String[] string_pattern_array = new java.lang.String[]{"*/string"};
//		java.lang.Class[] string_class_array = new java.lang.Class[]{String.class};
//		
//		for (int i = 0; i < string_pattern_array.length; i++) {
//			digester.addObjectCreate(string_pattern_array[i], string_class_array[i]);
//			digester.addSetProperties(string_pattern_array[i]);
//			digester.addCallMethod(string_pattern_array[i], "fromJavaString", 0);
//			digester.addSetNext(string_pattern_array[i], "addDataNode", DataNode.class.getName());
//		}
//		
//		java.lang.String[] service_pattern_array = new java.lang.String[]{"*/service"};
//		java.lang.Class[] service_class_array = new java.lang.Class[]{ServiceNode.class};
//		
//		for (int i = 0; i < service_pattern_array.length; i++) {
//			digester.addObjectCreate(service_pattern_array[i], service_class_array[i]);
//			digester.addSetProperties(service_pattern_array[i]);
//			digester.addSetNext(service_pattern_array[i], "addServiceNode", ServiceNode.class.getName());
//		}
//		
//		java.lang.String[] competitor_pattern_array = new java.lang.String[]{"*/competitor"};
//		java.lang.Class[] competitor_class_array = new java.lang.Class[]{Competitor.class};
//		
//		for (int i = 0; i < competitor_pattern_array.length; i++) {
//			digester.addObjectCreate(competitor_pattern_array[i], competitor_class_array[i]);
//			digester.addSetProperties(competitor_pattern_array[i]);
//			digester.addSetNext(competitor_pattern_array[i], "addCompetitor", Competitor.class.getName());
//		}
//		
		
		
		return (Root) digester.parse(new File(xmlUrl.getFile()));
	}
	
	/**
	 * 
	 * �Ӹ��ڵ㿪ʼִ��
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		log.info("��ʼ��[self]flow, ��flow��socketΪnull, ����ע��");
		FlowNode sefFlow = this.getSuitableSelfFlowNode();
		Session clientSession = new Session(sefFlow.getRequest(), sefFlow.getResponse(), null);
		log.info("ִ��[self]flow");
		sefFlow.execute(clientSession);
		log.info("ִ��[self]flow����");
	}

	/**
	 * ���ڵ�Ĭ��������global�ڵ�
	 * @return
	 */
	public Global getGlobal() {
		return (Global) this.getNext();
	}

	public void addNextRealNode(RealNode realNode) {
		this.setNext(realNode);
		realNode.setPreNode(this);
	}
	/**
	 * ��ȡִ�е����, ��java��������, java��main��ʼ, ��pipeline�ӵ�һ����Ϊself�����̽ڵ㿪ʼִ��<br>
	 * ��ȡ���ʵ����̽ڵ�, ���ҹ���:<br>
	 * 1, ��global��ʼ����, ���ϲ�������Ϊ[self]���ӽڵ�<br>
	 * 2, ����˽ڵ���Flow����, ����Ϊ�ҵ���<br>
	 * 3, �����ִ�д�flow<br>
	 * @return �����һ��Ҫִ�е�flow�ڵ�
	 * @throws PreNodeNotFountException 
	 */
	public FlowNode getSuitableSelfFlowNode() throws PreNodeNotFountException {
		DataNode node = this.getGlobal();
		if (node == null) {
			throw new PreNodeNotFountException("global�ڵ�û���ҵ�, ����root�ڵ������Ƿ���global�ڵ�");
		}
		
		while (node.getName().equals(KeyWords.SELF_KEY_WORLD.toJavaString())) {
			node = node.getNextNodeByName(KeyWords.SELF_KEY_WORLD);
			if (node instanceof FlowNode) {
				break;
			}
		}
		if (node == null || !(node instanceof FlowNode)) {
			throw new PreNodeNotFountException("��Ϊself��Flow�ڵ�û�ҵ�, �����Ƿ����ִ���");
		}
		return (FlowNode) node;
	}
}
