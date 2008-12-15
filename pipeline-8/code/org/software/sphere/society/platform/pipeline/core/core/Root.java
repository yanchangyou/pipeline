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
 * @file : Root.java
 * @version : 0.1
 */
public class Root extends DefaultNode0X {
	
	private static Root root;
	
	private java.lang.String boot; //����ĵ�һ��ִ�����
	
	/**
	 * digester�ļ���·��, ���ڹ���������������
	 */
	final static java.lang.String VALIDATOR_RULES = "org/software/sphere/society/platform/pipeline/core/core/digestor/pipeline-digester-rules.xml";

	public Root() {
		super();
		root = this;
	}
	
	/**
	 * ������ʵ�ڵ�
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
			throw new CoreException("���Ͳ�ƥ��, �˽ڵ㲻��real-node�ڵ�, �˽ڵ�����:" + node.getName() + ", ����:" + node.getClass().getName() + ", ����·���Ƿ���ȷ");
		}
		return (RealNode) node;
	}
	
	/**
	 * �������̽ڵ�
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
			throw new CoreException("���Ͳ�ƥ��, �˽ڵ㲻��flow-node�ڵ�, �˽ڵ�����:" + node.getName() + ", ����:" + node.getClass().getName() + ", ����·���Ƿ���ȷ");
		}
		return (FlowNode) node;
	}
	

	/**
	 * ������ʵ�ķ���ڵ�
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
			throw new CoreException("���Ͳ�ƥ��, �˽ڵ㲻��service-node�ڵ�, �˽ڵ�����:" + node.getName() + ", ����:" + node.getClass().getName() + ", ����·���Ƿ���ȷ");
		}
		
		RealNode realNode = (RealNode)node;
		definedServiceNode = realNode.getService(serviceName);
		if (definedServiceNode == null) {
			throw new NoAvailableServiceException("����ķ���ڵ�û���ҵ�, �˽ڵ�·����:" + serviceName);
		}
		return definedServiceNode;
	}
	
	
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
		log.info("ִ�е�һ��Service, ��ʱsocketΪnull, ����ע��");
		 
		DataNode node = this.getNextNodeByPath(new String(this.boot));
		if (!(node instanceof Service)) {
			throw new NoAvailableServiceException("û���ҷ���[service]��ǩ, �����·���Ƿ���ȷ, ��·����:" + this.boot); 
		}
		Service service = (Service)node;
		log.info("ִ��[self]flow");
		service.service();
		log.info("ִ��[self]flow����");
	}

	public void addNextRealNode(RealNode realNode) {
		this.addNextNode(realNode);
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
			 node = this.getNextNodeByName(KeyWords.SELF_KEY_WORLD);
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
