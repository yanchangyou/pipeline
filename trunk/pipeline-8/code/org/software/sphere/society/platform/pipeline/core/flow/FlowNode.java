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
	 * 上下文存储数据节点
	 */
	protected FlowNodeContext flowNodeContext;

	/**
	 * 流程列表
	 */
	protected List flowList = new ArrayList();

	/**
	 * 发出的请求
	 */
//	protected Request request;

	/**
	 * 返回的响应
	 */
//	protected Response response;

	

	/** 
	 * 添加数据节点<br>
	 * 直接放到上下文中去了
	 * 
	 * @param node
	 */
	public void addDataNode(DataNode node) {
		this.flowNodeContext.addNextNode(node);
	}
	/**
	 * 获取数据节点<br>
	 * 直接从上下文中获取<br>
	 * 本层没有找到就从上层中去寻找<br>
	 * 
	 * @param name
	 * @return
	 * @throws NextNodeNotFountException 
	 * @throws PreNodeNotFountException 
	 */
	public DataNode getDataNode(String name) throws NextNodeNotFountException {
		
		/**
		 * 先在流程模型里找
		 */
		FlowNode flowNode = this;
		DataNode value = null;

		do
		{
			try {
				log.info("开始在[" + flowNode.getName() + "]流程上下文中查找数据节点:" + name);
				value = flowNode.getFlowNodeContext().getNextNodeByPath(name);
				if (value == null) {
					throw new PreNodeNotFountException("不存在此数据节点");
				}
				log.info("查找数据节点成功,值为:" + value);
			} catch (PreNodeNotFountException e) {
				log.info("在" + flowNode.getName() + "流程上下文查找数据节点失败,找到上级流程中去查找, 失败原因:" + e.getMessage());
//				e.printStackTrace();
			}
			if (flowNode.getPreNode() instanceof FlowNode) {
				flowNode = (FlowNode) flowNode.getPreNode();
			} else {
				break;
			}
		} while (value == null && flowNode != null) ;
		
		/**
		 * 再到 现实模型中寻找
		 */
		if (value == null) { //在流程模型中没有找到, 继续往上找
			log.info("开始在现实模型上下文中查找数据节点:" + name);
			RealNode realNode = (RealNode) flowNode.getPreNode();
			do {			
				try {
					log.info("开始在[" + flowNode.getName() + "]流程上下文中查找数据节点:" + name);
					value = realNode.getRealNodeContext().getNextNodeByPath(name);
					if (value == null) {
						throw new PreNodeNotFountException("不存在此数据节点");
					}
					log.info("查找数据节点成功,值为:" + value);
				} catch (PreNodeNotFountException e) {
					log.info("在" + flowNode.getName() + "流程上下文查找数据节点失败,找到上级流程中去查找, 失败原因:" + e.getMessage());
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
	 * 获取定义的服务节点<br>
	 * 直接从上下文中获取<br>
	 * 本层没有找到就从上层中去寻找<br>
	 * 
	 * @param name
	 * @return
	 * @throws NextNodeNotFountException 
	 * @throws NoAvailableServiceException 
	 * @throws PreNodeNotFountException 
	 */
	public ServiceNode getDefinedServiceNode(String name) throws NextNodeNotFountException, NoAvailableServiceException {
		
		/**
		 * 先在流程模型里找
		 */
		FlowNode flowNode = this;

		ServiceNode definedServiceNode = null;
		
		while (flowNode.getPreNode() instanceof FlowNode) {
			flowNode = (FlowNode) flowNode.getPreNode();
		}
		
		/**
		 * 再到 现实模型中寻找
		 */
		
		log.info("开始在现实模型上下文中查找数据节点:" + name);
		RealNode realNode = (RealNode) flowNode.getPreNode();
		definedServiceNode = realNode.getService(name);
		while (realNode != null && definedServiceNode == null && realNode.getPreNode() instanceof RealNode) {
			realNode = (RealNode) realNode.getPreNode();
			definedServiceNode = realNode.getService(name);
		}
		if (definedServiceNode == null) {
			throw new NoAvailableServiceException("定义的服务节点没有找到, 此节点路径是:" + name);
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
	 * 流程处理的执行接口, 有子类进行实现
	 * @param clientSession
	 * @throws ConnectException
	 * @throws Exception
	 */
	public abstract void execute(Session clientSession)
			throws ConnectException, Exception;

	/**
	 * 一个缺省的执行模式:顺序执行
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
	 * 获取节点层次数 根节点定义为0
	 * 
	 * @return 层次数
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
	 * 缺省的处理请求
	 * @throws IOException 
	 * @throws VarNotFountException 
	 * @throws NextNodeNotFountException 
	 *
	 */
//	public void dealRequest(Socket socket) throws IOException, NextNodeNotFountException, VarNotFountException {
//		if (request != null && request.toJavaString() != null) {
//			PrintWriter os = new PrintWriter(socket.getOutputStream());
//			java.lang.String requestData = request.toJavaString();
//			log.info("开始向外输出数据 : " + requestData);
//			java.lang.String result = Evale.eval(requestData, this).toString();
//			os.println(result);
//			os.flush();
//			this.getFlowNodeContext().addNextNode(this.request.getNodeName(), new String(result));
//			log.info("完毕向外输出数据");
//		}
//	}
	
	/**
	 * 缺省处理响应
	 *
	 */
//	public void dealResponse(Socket socket) throws IOException, NextNodeNotFountException, VarNotFountException {
//		/**
//		 * 读取数据 采用&续行, && 换行的处理规则
//		 */
//		if (response != null) {
//			log.info("开始向内读取数据");
//			java.lang.String responseData = RuleReadNetDataByPipeline.readData(socket.getInputStream());
//			java.lang.String result = Evale.eval(responseData, this).toString();
//			this.getFlowNodeContext().addNextNode(this.response.getNodeName(), new String(result));
//			log.info("完毕向内读取数据 : " + result);
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

