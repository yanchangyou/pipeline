package org.software.sphere.society.platform.pipeline.core.lang.execute;


public class Service_old {
//
//	private Request request;
//
//	private Response response;
//
////	private Pipeline pipeline;
//
//	private String port;
//
//	public Service_old() {
//	}
//	
//	public int getIntPort() {
//		if (port == null || port.length() == 0) {
//			port = (String) this.getMeta().getProperty("port");
//		}
//		return Integer.parseInt(port);
//	}
//
//	public String getPort() {
//		return port;
//	}
//	
//	public void setPort(String port) {
//		this.port = port;
//	}
//
//	public Request getRequest() {
//		return request;
//	}
//
////	public Pipeline getPipeline() {
////		return pipeline;
////	}
//
////	public void setPipeline(Pipeline pipeline) {
////		this.pipeline = pipeline;
////		this.addChildElement(pipeline);
////	}
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
////
////	public String toString() {
////		return super.toString() + "\npipeline : "
////				+ pipeline + "\nrequest : " + request + "\nresponse : "
////				+ response;
////	}
//
//	/**
//	 * service 对外的三个核心方法, start, stop, service
//	 * 
//	 * 首先通过 strat, 对外启动监听, 开始接收请求 然后通过 service, 对每个请求进行服务 最后通过 stop, 关闭服务
//	 */
//	private ServerSocket server;
//	/**
//	 * 
//	 */
//	public void start() {
//		
//		try {
//			int port = this.getIntPort();
//			server = new ServerSocket(port);
//			// 创建一个ServerSocket在端口监听客户请求
//		} catch (Throwable e) {
//			log.error("创建服务失败, 可能是端口被占用, 当前使用的端口是:" + port);
//			log.error("内部错误信息:" + e);
//			// 出错，打印出错信息
//		}
//		try {
//			do {
//				Socket socket = server.accept();
//				
//				log.info(socket.getRemoteSocketAddress().toString()
//						.substring(1)+ " 请求服务 ");
//				
//				new ServiceThread(socket, this).start();
//				// 使用accept()阻塞等待客户请求，有客户
//				// 请求到来则产生一个Socket对象，并继续执行
//			} while (true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("启动服务失败, 可能是端口被占用, 当前使用的端口是:" +port);
//			log.error("内部错误信息:" + e);
//			// 出错，打印出错信息
//		}
//	}
//
//	private class ServiceThread extends Thread {
//
//		Socket socket;
//
//		Service_old service;
//
//		public ServiceThread(Socket socket, Service_old service) {
//			this.socket = socket;
//			this.service = service;
//		}
//
//		public void run() {
//			log.info("[" + Service_old.this.getName() + "]服务开始");
//			service.service(service, socket);
//			log.info("[" + Service_old.this.getName() + "]服务结束");
//		}
//
//	}
//
//	public void service(Service_old service, Socket socket) {
//		try {
//
//			log.info("服务[" + this.getName() + "]建立socket");
//			Request request = service.getRequest();
//			Response response = service.getResponse();
////			Context context = new Context();
//
//			Session clientSession = new Session(request, response, socket);
//			/**
//			 * 读取数据 采用&续行, && 换行的处理规则
//			 */
//			if (request != null) { //接收客户端请求
//				log.info("服务[" + this.getName() + "]接收客户端输入数据");
//				String requestData = RuleReadNetDataByPipeline.readData(socket);
//				Tree requestTree = PipelineTreeCompiler.compile(requestData);
//				requestTree.execute(this.getContext());
//				this.getContext().merge(this.request.getName(), requestTree);
//				log.info("服务[" + this.getName() + "]客户端输入数据完毕");
//			}
//			
//			Pipeline pipeline = service.getPipeline();
//
////			pipeline.setRoot(service.getRoot());
////			pipeline.setParentContext(context);
//
//			// TODO CHECH THIS
//			this.addChildElement(pipeline);
//			
//			log.info("服务[" + this.getName() + "]开始处理");
//			
//			pipeline.deal(clientSession);
//
//			log.info("服务[" + this.getName() + "]完成处理");
//			
//			if (response != null) { //响应客户端请求
//				log.info("服务[" + this.getName() + "]向客户端输出结果");
//				PrintWriter os = new PrintWriter(socket.getOutputStream());
//				String responseData = response.getResponseData();
//				Tree responseTree = PipelineTreeCompiler.compile(responseData);
//				responseTree.execute(this.getContext());
//				this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
////				this.getContext().getParent().merger(response.getName(), responseTree);
//				os.print(responseTree.getResult());
//				os.flush();
//				log.info("服务[" + this.getName() + "]向客户端输出结果完毕");
//			}
//			
//			socket.close(); // 关闭Socket
//			log.info("服务[" + this.getName() + "]关闭socket");
//		} catch (ConnectException e) {
//			log.error("获取[" + this.getName() + "]服务失败---原因" + e.getMessage());
//		} catch (DataNotFoundException e) {
//			log.error("处理[" + this.getName() + "]服务失败---原因是 :" + e.getMessage());
////			e.printStackTrace();
//		} catch (IOException e) {
//			log.error("处理[" + this.getName() + "]服务失败---io异常");
//			e.printStackTrace();
//		} 
//		 catch (Exception e) {
//			 log.error("处理[" + this.getName() + "]服务失败---错误信息:" + e.getMessage());
//			e.printStackTrace();
//		} finally {
//			try {
//				socket.close();
//			} catch (IOException e) {
//				log.error("关闭[" + this.getName() + "]服务失败---错误信息:" + e.getMessage());
////				e.printStackTrace();
//			} // 关闭Socket
//		}
//	}
//	
//	public void stop() throws Exception {
//		if (server == null) {
//			throw new Exception("错误:服务没有启动, 不用结束");
//		} else {
//			server.close();
//		}
//	}
}
