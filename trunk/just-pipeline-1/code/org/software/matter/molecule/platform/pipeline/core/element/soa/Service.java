package org.software.matter.molecule.platform.pipeline.core.element.soa;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.common.RuleReadNetDataByJP;
import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.core.data.tree3D.DefaultTree3D;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;
import org.software.matter.molecule.platform.pipeline.core.lang.JPTreeCompiler;
import org.software.matter.molecule.platform.pipeline.core.session.Session;
import org.software.matter.molecule.platform.pipeline.exception.flow.DataNotFoundException;

public class Service extends SOA {

	private Request request;

	private Response response;

	private Pipeline pipeline;

	private String port;

	public Service() {
	}
	
	public int getIntPort() {
		if (port == null || port.length() == 0) {
			port = (String) this.getMeta().smartGet("port");
		}
		return Integer.parseInt(port);
	}

	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}

	public Request getRequest() {
		return request;
	}

	public Pipeline getPipeline() {
		return pipeline;
	}

	public void setPipeline(Pipeline pipeline) {
		this.pipeline = pipeline;
		this.addChildElement(pipeline);
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
		return new ToStringBuilder(this).toString() + "\npipeline : "
				+ pipeline + "\nrequest : " + request + "\nresponse : "
				+ response;
	}

	private Root root;

	public void setRoot(Root root) {
		this.root = root;
	}

	public Root getRoot() {
		return root;
	}

	/**
	 * service 对外的三个核心方法, start, stop, service
	 * 
	 * 首先通过 strat, 对外启动监听, 开始接收请求 然后通过 service, 对每个请求进行服务 最后通过 stop, 关闭服务
	 */
	private ServerSocket server;
	/**
	 * 
	 */
	public void start() {
		
		try {
			int port = this.getIntPort();
			server = new ServerSocket(port);
			// 创建一个ServerSocket在端口监听客户请求
		} catch (Throwable e) {
			log.error("创建服务失败, 可能是端口被占用, 当前使用的端口是:" + port);
			log.error("内部错误信息:" + e);
			// 出错，打印出错信息
		}
		try {
			do {
				Socket socket = server.accept();
				
				log.info(socket.getRemoteSocketAddress().toString()
						.substring(1)+ " 请求服务 ");
				
				new ServiceThread(socket, this).start();
				// 使用accept()阻塞等待客户请求，有客户
				// 请求到来则产生一个Socket对象，并继续执行
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("启动服务失败, 可能是端口被占用, 当前使用的端口是:" +port);
			log.error("内部错误信息:" + e);
			// 出错，打印出错信息
		}
	}

	private class ServiceThread extends Thread {

		Socket socket;

		Service service;

		public ServiceThread(Socket socket, Service service) {
			this.socket = socket;
			this.service = service;
		}

		public void run() {
			System.out.println("本次服务开始");
			service.service(service, socket);
			System.out.println("本次服务结束");
		}

	}

	public void service(Service service, Socket socket) {
		try {

			Request request = service.getRequest();
			Response response = service.getResponse();
//			Context context = new Context();

			Session clientSession = new Session(request, response, socket);
			/**
			 * 读取数据 采用&续行, && 换行的处理规则
			 */
			if (request != null) { //接收客户端请求
				String requestData = RuleReadNetDataByJP.readData(socket);
				Tree requestTree = JPTreeCompiler.compile(requestData);
				requestTree.execute(this.getContext());
				this.getContext().merge(this.request.getName(), requestTree);
			}
			
			Pipeline pipeline = service.getPipeline();

//			pipeline.setRoot(service.getRoot());
//			pipeline.setParentContext(context);

			// TODO CHECH THIS
			this.addChildElement(pipeline);
			
			pipeline.deal(clientSession);

			
			if (response != null) { //响应客户端请求
				PrintWriter os = new PrintWriter(socket.getOutputStream());
				String responseData = response.getResponseData();
				Tree responseTree = JPTreeCompiler.compile(responseData);
				responseTree.execute(this.getContext());
				this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
//				this.getContext().getParent().merger(response.getName(), responseTree);
				os.print(responseTree.getResult());
				os.flush();
			}
			
			socket.close(); // 关闭Socket
		} catch (ConnectException e) {
			log.error("获取[" + this.getName() + "]服务失败---原因" + e.getMessage());
		} catch (DataNotFoundException e) {
			log.error("处理[" + this.getName() + "]服务失败---原因是 :" + e.getMessage());
//			e.printStackTrace();
		} catch (IOException e) {
			log.error("处理[" + this.getName() + "]服务失败---io异常");
			e.printStackTrace();
		} 
		 catch (Exception e) {
			 log.error("处理[" + this.getName() + "]服务失败---错误信息:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log.error("关闭[" + this.getName() + "]服务失败---错误信息:" + e.getMessage());
//				e.printStackTrace();
			} // 关闭Socket
		}
	}
	
	public void stop() throws Exception {
		if (server == null) {
			throw new Exception("错误:服务没有启动, 不用结束");
		} else {
			server.close();
		}
	}
}
