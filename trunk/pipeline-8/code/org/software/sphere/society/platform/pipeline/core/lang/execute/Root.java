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
	final static java.lang.String VALIDATOR_RULES = "org/software/sphere/society/platform/pipeline/core/lang/execute/digestor/pipeline-digester-rules.xml";

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
		log.info("��ʼ����һ��flow, ��flow��socketΪnull, ����ע��");
		FlowNode sefFlow = this.getSelfFlow();
		Session clientSession = new Session(sefFlow.getRequest(), sefFlow.getResponse(), null);
		log.info("ִ�е�һ�� flow");
		sefFlow.execute(clientSession);
	}

	/**
	 * ���ڵ�Ĭ��������global�ڵ�
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
	 * ��ȡִ�е����, ��java��������, java��main��ʼ, �������Դ�self.self.self.self.self��ʼִ��
	 * @return
	 * @throws MiddleNodeNotFountException
	 */
	public FlowNode getSelfFlow() throws MiddleNodeNotFountException {
		final String SELF_UNIT_PATH = new String(PipelineConst.CORE.EXECUTE.SELF_UNIT_PATH);
		return (FlowNode) this.getGlobal().getNextNodeByPath(SELF_UNIT_PATH);
	}
}
