package org.software.sphere.society.platform.omega.core.element.pipeline;

import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import org.software.sphere.society.platform.omega.common.RuleReadNetDataByOmega;
import org.software.sphere.society.platform.omega.core.data.Tree;
import org.software.sphere.society.platform.omega.core.data.tree3D.DefaultTree3D;
import org.software.sphere.society.platform.omega.core.element.esoa.Server;
import org.software.sphere.society.platform.omega.core.element.esoa.Service_old;
import org.software.sphere.society.platform.omega.core.element.rr.Request;
import org.software.sphere.society.platform.omega.core.element.rr.Response;
import org.software.sphere.society.platform.omega.core.lang.OmegaTreeCompiler;
import org.software.sphere.society.platform.omega.core.session.Session;

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
		this.addChildElement(request);
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
		this.addChildElement(response);
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
				Service_old service = (Service_old) this.context.getAbsolutePathElement(serviceArray[i]);
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
			log.info("[" + this.getName() + "]发出请求数据");
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			String requestData = request.getRequestData();
			log.info("[" + this.getName() + "]编译数据");
			Tree requestTree = OmegaTreeCompiler.compile(requestData);
			log.info("[" + this.getName() + "]解析执行");
			requestTree.execute(this.getRequest().getContext());
			if (this.request.getName() == null) {
				
			} else {
				this.getContext().merge(this.request.getName(), requestTree, DefaultTree3D.class);//响应的数据放到上下文中
			}
			os.print(requestTree.getResult() );
			os.flush();
			log.info("[" + this.getName() + "]发送数据完毕");
		}
		/**
		 * 读取数据 采用&续行, && 换行的处理规则
		 */
		if (response != null) {
			log.info("[" + this.getName() + "]接收数据");
			String responseData = RuleReadNetDataByOmega.readData(socket);
			log.info("[" + this.getName() + "]接收数据完毕");
			log.info("[" + this.getName() + "]编译数据");
			Tree responseTree = OmegaTreeCompiler.compile(responseData);
			log.info("[" + this.getName() + "]解析执行");
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
