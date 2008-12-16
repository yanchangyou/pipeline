package org.software.sphere.society.platform.pipeline.core.flow;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.software.sphere.society.platform.pipeline.common.Logable;
import org.software.sphere.society.platform.pipeline.common.ServiceNode;
import org.software.sphere.society.platform.pipeline.core.core.FlowNodeContext;
import org.software.sphere.society.platform.pipeline.core.core.Session;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.core.data.node1X.DefaultNode1X;
import org.software.sphere.society.platform.pipeline.core.real.RealNode;
import org.software.sphere.society.platform.pipeline.exception.core.core.NoAvailableServiceException;
import org.software.sphere.society.platform.pipeline.exception.core.data.NextNodeNotFountException;
import org.software.sphere.society.platform.pipeline.exception.core.data.PreNodeNotFountException;

public abstract class FlowNode extends DefaultNode1X implements Logable {

	protected FlowNode() {
		this.flowNodeContext = new FlowNodeContext(this);
	}
	/**
	 * �����Ĵ洢���ݽڵ�
	 */
	protected FlowNodeContext flowNodeContext;

	/**
	 * �����б�
	 */
	protected List flowList = new ArrayList();

	/**
	 * ����������
	 */
//	protected Request request;

	/**
	 * ���ص���Ӧ
	 */
//	protected Response response;

	

	/** 
	 * ������ݽڵ�<br>
	 * ֱ�ӷŵ���������ȥ��
	 * 
	 * @param node
	 */
	public void addDataNode(DataNode node) {
		this.flowNodeContext.addNextNode(node);
	}
	/**
	 * ��ȡ���ݽڵ�<br>
	 * ֱ�Ӵ��������л�ȡ<br>
	 * ����û���ҵ��ʹ��ϲ���ȥѰ��<br>
	 * 
	 * @param name
	 * @return
	 * @throws NextNodeNotFountException 
	 * @throws PreNodeNotFountException 
	 */
	public DataNode getDataNode(String name) throws NextNodeNotFountException {
		
		/**
		 * ��������ģ������
		 */
		FlowNode flowNode = this;
		DataNode value = null;

		do
		{
			try {
				log.info("��ʼ��[" + flowNode.getName() + "]�����������в������ݽڵ�:" + name);
				value = flowNode.getFlowNodeContext().getNextNodeByPath(name);
				if (value == null) {
					throw new PreNodeNotFountException("�����ڴ����ݽڵ�");
				}
				log.info("�������ݽڵ�ɹ�,ֵΪ:" + value);
			} catch (PreNodeNotFountException e) {
				log.info("��" + flowNode.getName() + "���������Ĳ������ݽڵ�ʧ��,�ҵ��ϼ�������ȥ����, ʧ��ԭ��:" + e.getMessage());
//				e.printStackTrace();
			}
			if (flowNode.getPreNode() instanceof FlowNode) {
				flowNode = (FlowNode) flowNode.getPreNode();
			} else {
				break;
			}
		} while (value == null && flowNode != null) ;
		
		/**
		 * �ٵ� ��ʵģ����Ѱ��
		 */
		if (value == null) { //������ģ����û���ҵ�, ����������
			log.info("��ʼ����ʵģ���������в������ݽڵ�:" + name);
			RealNode realNode = (RealNode) flowNode.getPreNode();
			do {			
				try {
					log.info("��ʼ��[" + flowNode.getName() + "]�����������в������ݽڵ�:" + name);
					value = realNode.getRealNodeContext().getNextNodeByPath(name);
					if (value == null) {
						throw new PreNodeNotFountException("�����ڴ����ݽڵ�");
					}
					log.info("�������ݽڵ�ɹ�,ֵΪ:" + value);
				} catch (PreNodeNotFountException e) {
					log.info("��" + flowNode.getName() + "���������Ĳ������ݽڵ�ʧ��,�ҵ��ϼ�������ȥ����, ʧ��ԭ��:" + e.getMessage());
				}
				if (realNode.getPreNode() instanceof RealNode) {
					realNode = (RealNode) realNode.getPreNode();
				} else {
					break;
				}
			} while (value == null && realNode != null);
		}
		
		return value;
	}


	

	/**
	 * ��ȡ����ķ���ڵ�<br>
	 * ֱ�Ӵ��������л�ȡ<br>
	 * ����û���ҵ��ʹ��ϲ���ȥѰ��<br>
	 * 
	 * @param name
	 * @return
	 * @throws NextNodeNotFountException 
	 * @throws NoAvailableServiceException 
	 * @throws PreNodeNotFountException 
	 */
	public ServiceNode getDefinedServiceNode(String name) throws NextNodeNotFountException, NoAvailableServiceException {
		
		/**
		 * ��������ģ������
		 */
		FlowNode flowNode = this;

		ServiceNode definedServiceNode = null;
		
		while (flowNode.getPreNode() instanceof FlowNode) {
			flowNode = (FlowNode) flowNode.getPreNode();
		}
		
		/**
		 * �ٵ� ��ʵģ����Ѱ��
		 */
		
		log.info("��ʼ����ʵģ���������в������ݽڵ�:" + name);
		RealNode realNode = (RealNode) flowNode.getPreNode();
		definedServiceNode = realNode.getService(name);
		while (realNode != null && definedServiceNode == null && realNode.getPreNode() instanceof RealNode) {
			realNode = (RealNode) realNode.getPreNode();
			definedServiceNode = realNode.getService(name);
		}
		if (definedServiceNode == null) {
			throw new NoAvailableServiceException("����ķ���ڵ�û���ҵ�, �˽ڵ�·����:" + name);
		}
		return definedServiceNode;
	}

//	public Request getRequest() {
//		return request;
//	}
//
//	public void setRequest(Request request) {
//		this.request = request;
//	}
//
//	public Response getResponse() {
//		return response;
//	}
//
//	public void setResponse(Response response) {
//		this.response = response;
//	}

	public void appendFlow(FlowNode flow) {
		flowList.add(flow);
		flow.setPreNode(this);
		this.addNextNode(flow);
	}

	public List getFlowList() {
		return flowList;
	}

	public void setFlowList(List flowList) {
		this.flowList = flowList;
	}

	/**
	 * ���̴����ִ�нӿ�, ���������ʵ��
	 * @param clientSession
	 * @throws ConnectException
	 * @throws Exception
	 */
	public abstract void execute(Session clientSession)
			throws ConnectException, Exception;

	/**
	 * һ��ȱʡ��ִ��ģʽ:˳��ִ��
	 * @param clientSession
	 * @throws ConnectException
	 * @throws Exception
	 */
	public void defaultExecute(Session clientSession)
			throws ConnectException, Exception {
		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
			FlowNode flow = (FlowNode) iter.next();
			flow.execute(clientSession);
		}
	}

	public FlowNodeContext getFlowNodeContext() {
		return flowNodeContext;
	}

	public void setContext(FlowNodeContext context) {
		this.flowNodeContext = context;
	}

	public FlowNode getPreFlowNode() {
		return (FlowNode) this.preNode;
	}

	public FlowNode getNextFlowNode(String nodeName) {
		return (FlowNode) this.getNextNodeByName(nodeName);
	}
	
	/**
	 * ��ȡ�ڵ����� ���ڵ㶨��Ϊ0
	 * 
	 * @return �����
	 */
	public int getFlowNodePreLevel() {
		int flowNodeLevel = 0;
		FlowNode flowNode = this;
		while (flowNode.getPreNode() != null) {
			flowNodeLevel++;
			if (FlowNode.class.isInstance(flowNode.getPreNode())) {
				flowNode = (FlowNode) flowNode.getPreNode();
			} else {
				break;
			}
		}
		return flowNodeLevel;
	}
	

	/**
	 * ȱʡ�Ĵ�������
	 * @throws IOException 
	 * @throws VarNotFountException 
	 * @throws NextNodeNotFountException 
	 *
	 */
//	public void dealRequest(Socket socket) throws IOException, NextNodeNotFountException, VarNotFountException {
//		if (request != null && request.toJavaString() != null) {
//			PrintWriter os = new PrintWriter(socket.getOutputStream());
//			java.lang.String requestData = request.toJavaString();
//			log.info("��ʼ����������� : " + requestData);
//			java.lang.String result = Evale.eval(requestData, this).toString();
//			os.println(result);
//			os.flush();
//			this.getFlowNodeContext().addNextNode(this.request.getNodeName(), new String(result));
//			log.info("��������������");
//		}
//	}
	
	/**
	 * ȱʡ������Ӧ
	 *
	 */
//	public void dealResponse(Socket socket) throws IOException, NextNodeNotFountException, VarNotFountException {
//		/**
//		 * ��ȡ���� ����&����, && ���еĴ������
//		 */
//		if (response != null) {
//			log.info("��ʼ���ڶ�ȡ����");
//			java.lang.String responseData = RuleReadNetDataByPipeline.readData(socket.getInputStream());
//			java.lang.String result = Evale.eval(responseData, this).toString();
//			this.getFlowNodeContext().addNextNode(this.response.getNodeName(), new String(result));
//			log.info("������ڶ�ȡ���� : " + result);
//		}
//	}
	
	
	public java.lang.String toString() {
		int flowNodeLevel = this.getNode1XPreLevel();
		java.lang.String leftPad = StringUtils.leftPad(" ", flowNodeLevel * 4);
		StringBuffer buf = new StringBuffer();
		buf.append("\r\n").append(leftPad).append(super.toString());
		buf.append("\r\n").append(leftPad).append("context = {").append(getFlowNodeContext().getNextNodesMap());
//		buf.append("\r\n").append(leftPad).append("request : ").append(request);
//		buf.append("\r\n").append(leftPad).append("response : ").append(response);
		buf.append("\r\n").append(leftPad).append("next = {").append(this.getNextNodesMap().toString());
		return buf.toString();
	}
}

