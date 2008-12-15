package org.software.sphere.society.platform.pipeline.core.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node01.DefaultNode01;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;
import org.software.sphere.society.platform.pipeline.core.real.RealNode;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;
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
	
	private java.lang.String boot; //����ĵ�һ��ִ�����
	
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
	 * ���ڵ�Ĭ��������realNode�ڵ�
	 * @return
	 */
	public RealNode getRealNode() {
		return (RealNode) this.getNext();
	}

	public void addNextRealNode(RealNode realNode) {
		this.setNext(realNode);
		realNode.setPreNode(this);
	}
	/**
	 * ���֪����boot���ԾͰ���bootȥ�ҵ�һ��ִ�е�flow<br>
	 * ���û��boot���Եļ�����self�����ҵ�һ��ִ�е�flow<br>
	 * ��ȡִ�е����, ��java��������, java��main��ʼ, ��pipeline�ӵ�һ����Ϊself�����̽ڵ㿪ʼִ��<br>
	 * ��ȡ���ʵ����̽ڵ�, ���ҹ���:<br>
	 * 1, ��realNode��ʼ����, ���ϲ�������Ϊ[self]���ӽڵ�<br>
	 * 2, ����˽ڵ���Flow����, ����Ϊ�ҵ���<br>
	 * 3, �����ִ�д�flow<br>
	 * @return �����һ��Ҫִ�е�flow�ڵ�
	 * @throws PreNodeNotFountException 
	 * @throws NextNodeNotFountException 
	 */
	public FlowNode getSuitableSelfFlowNode() throws NextNodeNotFountException {
		DataNode node = null;
		StringBuffer firstFlowPathBuf = new StringBuffer();
		if(this.boot != null && this.boot.trim().length() != 0) { //���֪����boot����, �Ͱ���boot���������ҵ�һ��ִ�е�flow
			firstFlowPathBuf.append(this.boot);
			node = this.getNextNodeByPath(new String(this.boot));
		} else { //���û��֪��boot����, ����Ĭ�ϵ�self������ҵ�һ��flow
			 node = this.getRealNode();
			 firstFlowPathBuf.append(node.getName());
			if (node == null) {
				throw new NextNodeNotFountException("realNode�ڵ�û���ҵ�, ����root�ڵ������Ƿ���realNode�ڵ�");
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
			throw new NextNodeNotFountException("��һ��Flow�ڵ�û�ҵ�, �����Ƿ����ִ���, ��·����:" + firstFlowPathBuf);
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
