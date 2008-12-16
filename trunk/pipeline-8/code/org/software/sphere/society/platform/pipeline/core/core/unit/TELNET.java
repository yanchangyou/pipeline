package org.software.sphere.society.platform.pipeline.core.core.unit;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.software.sphere.society.platform.pipeline.common.ServiceNode;
import org.software.sphere.society.platform.pipeline.core.core.KeyWords;
import org.software.sphere.society.platform.pipeline.core.core.Request;
import org.software.sphere.society.platform.pipeline.core.core.Response;
import org.software.sphere.society.platform.pipeline.core.core.Root;
import org.software.sphere.society.platform.pipeline.core.core.Session;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.exception.core.core.DataDealException;

public class TELNET extends Unit {

	public void execute(Session clientSession) throws ConnectException,
			Exception {

//		encodeRequestData(clientSession.getRequest(), this.getFlowNodeContext());

		Socket socket = null;

		boolean isClientSocket = false;
		/**
		 * 没有声明使用其它服务, 就默认与客户端对话
		 */
		if (this.service == null) {
			log.info("直接与客户端会话, 不调用远程服务");
			socket = clientSession.getClientSocket();
			isClientSocket = true;
		} else { // 使用声明的服务
			log.info("开始远程服务调用");
			isClientSocket = false;
			ServiceNode serviceNode = null;
			if (service.indexOf('@') > -1) { //使用绝对路径定义服务
				String[] part = new String(service).split(new String("@"));
				serviceNode = Root.getServiceNode(part[1], part[0]);
			} else {						//使用相对路径定义服务
				serviceNode = this.getDefinedServiceNode(new String(service));	
			}
			socket = (Socket) serviceNode.getAvailableGod();
		}

		if (socket == null) { // 如果没有连接, 抛出异常
			java.lang.String msg = "连接失败 : 在步骤[" + this.getName() + "中]发生连接异常, 请确保服务["
					+ this.service + "]已经启动";
			log.error(msg);
			throw new ConnectException(msg);
		}

//		this.dealRequest(socket);
//
//		this.dealResponse(socket);
		
		
		if (isClientSocket) { // 如果是客户端就不关闭
			log.info("与客户端会话结束");
		} else {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
			log.info("远程服务调用结束");
		}
	}

	public Object getGod() throws UnknownHostException, IOException, DataDealException {
		String host = (String) this.getFlowNodeContext().getNextNodeByName(new String("host"));
		if (host == null || !host.toJavaString().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			throw new DataDealException("数据异常, 不存在或不正确host的ip地址, 此ip地址是:" + host + ", 请检查telnet节点[" + this.getName() + "]的参数是否正确");
		}
		String portString = (String) this.getFlowNodeContext().getNextNodeByName(new String("port"));

		if (portString == null || !portString.toJavaString().matches("\\d{1,5}")) {
			throw new DataDealException("数据异常, 不存在或不正确端口号, 此端口号是:" + portString);
		}
		
		int port = Integer.parseInt(portString.toJavaString());
		return new Socket(host.toJavaString(), port);
	}

	public void decodeResponseData(Response response, String responseData) {
		response.addNextNode(KeyWords.DATA_KEY_WORLD, responseData);
	}

	public void encodeRequestData(Request request, DataNode paramater) {
		String requestData = (String) paramater.getNextNodeByName(new String("msg"));//new String("Hello World!");
		request.addNextNode(KeyWords.DATA_KEY_WORLD, requestData);
	}
}
