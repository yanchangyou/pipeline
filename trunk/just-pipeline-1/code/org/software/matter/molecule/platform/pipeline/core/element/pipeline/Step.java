package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.common.RuleReadNetDataByJP;
import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.core.data.tree3D.DefaultTree3D;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Server;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;
import org.software.matter.molecule.platform.pipeline.core.lang.JPTreeCompiler;
import org.software.matter.molecule.platform.pipeline.core.session.Session;

public class Step extends Unit {

	public Step() {
		super();
		this.setUnitList(null);
	}

	private String service;

	private Request request;

	private Response response;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String toString() {
		return new ToStringBuilder(this).toString();
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public void deal(Session clientSession) throws ConnectException, Exception {
		
		Socket socket = null;
		
		boolean isClientSocket = false;
		/**
		 * 没有声明使用其它服务, 就默认与客户端对话
		 */
		if (this.service == null) {
			log.info("直接与客户端会话, 不调用远程服务");
			socket = clientSession.getClientSocket();
			isClientSocket = true;
		} else { //使用声明的服务
			log.info("开始远程服务调用");
			isClientSocket = false;
			String[] serviceArray = this.service.split("\\s*;\\s*"); //服务支持一系列服务(之间使用;分割), 目的:高可用性, 此服务不可用, 马上用备用的服务
			for (int i = 0; i < serviceArray.length; i++) {
				if (serviceArray[i].split("\\.").length != 6) {
					throw new Exception("不是合法的路径");
				}
				Service service = (Service) this.context.getAbsolutePathElement(serviceArray[i]);
				Server server = (Server) this.context.getAbsolutePathElement(serviceArray[i].substring(0, serviceArray[i].lastIndexOf('.')));

				String host = server.getHost();
				int port = service.getIntPort();

				try {
					socket = new Socket(host, port);
				} catch (ConnectException e) { //此服务不可用, 马上调用下一个服务
					log.error("连接失败, 在" + host + ":" + port + ", 内容信息:" + e.getMessage());
					log.error("开始连接备用服务器");
					continue;
				}
				break;
			}
		}
		
		if (socket == null) { //如果没有连接, 抛出异常
//			log.error("连接失败");
			String msg = "连接失败 : 在步骤[" + this.getName() +"中]发生连接异常, 请确保服务[" + this.service + "]已经启动";
			log.error(msg);
			throw new ConnectException(msg);
		}
		
		if (request != null && request.getRequestData() != null) {
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			String requestData = request.getRequestData();
			Tree requestTree = JPTreeCompiler.compile(requestData);
			requestTree.execute(this.getContext());
			if (this.request.getName() == null) {
				
			} else {
				this.getContext().merge(this.request.getName(), requestTree);//响应的数据放到上下文中
			}
			os.print(requestTree.getResult() );
			os.flush();
		}
		/**
		 * 读取数据 采用&续行, && 换行的处理规则
		 */
		if (response != null) {
			String responseData = RuleReadNetDataByJP.readData(socket);

			Tree responseTree = JPTreeCompiler.compile(responseData);
			responseTree.execute(this.getContext());
			if (response.getName() == null) {
				//nothiing				
			} else {
				this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
//				this.getContext().getParent().merger(response.getName(), responseTree);//响应的数据放到上下文中
			}
		}
		if (isClientSocket) { //如果是客户端就不关闭
			log.info("与客户端会话结束");
		} else {
			socket.close();
			log.info("远程服务调用结束");
		}
	}
}
