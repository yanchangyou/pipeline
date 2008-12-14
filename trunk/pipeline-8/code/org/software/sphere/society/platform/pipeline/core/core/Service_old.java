package org.software.sphere.society.platform.pipeline.core.core;


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
//	 * service ������������ķ���, start, stop, service
//	 * 
//	 * ����ͨ�� strat, ������������, ��ʼ�������� Ȼ��ͨ�� service, ��ÿ��������з��� ���ͨ�� stop, �رշ���
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
//			// ����һ��ServerSocket�ڶ˿ڼ����ͻ�����
//		} catch (Throwable e) {
//			log.error("��������ʧ��, �����Ƕ˿ڱ�ռ��, ��ǰʹ�õĶ˿���:" + port);
//			log.error("�ڲ�������Ϣ:" + e);
//			// ������ӡ������Ϣ
//		}
//		try {
//			do {
//				Socket socket = server.accept();
//				
//				log.info(socket.getRemoteSocketAddress().toString()
//						.substring(1)+ " ������� ");
//				
//				new ServiceThread(socket, this).start();
//				// ʹ��accept()�����ȴ��ͻ������пͻ�
//				// �����������һ��Socket���󣬲�����ִ��
//			} while (true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("��������ʧ��, �����Ƕ˿ڱ�ռ��, ��ǰʹ�õĶ˿���:" +port);
//			log.error("�ڲ�������Ϣ:" + e);
//			// ������ӡ������Ϣ
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
//			log.info("[" + Service_old.this.getName() + "]����ʼ");
//			service.service(service, socket);
//			log.info("[" + Service_old.this.getName() + "]�������");
//		}
//
//	}
//
//	public void service(Service_old service, Socket socket) {
//		try {
//
//			log.info("����[" + this.getName() + "]����socket");
//			Request request = service.getRequest();
//			Response response = service.getResponse();
////			Context context = new Context();
//
//			Session clientSession = new Session(request, response, socket);
//			/**
//			 * ��ȡ���� ����&����, && ���еĴ������
//			 */
//			if (request != null) { //���տͻ�������
//				log.info("����[" + this.getName() + "]���տͻ�����������");
//				String requestData = RuleReadNetDataByPipeline.readData(socket);
//				Tree requestTree = PipelineTreeCompiler.compile(requestData);
//				requestTree.execute(this.getContext());
//				this.getContext().merge(this.request.getName(), requestTree);
//				log.info("����[" + this.getName() + "]�ͻ��������������");
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
//			log.info("����[" + this.getName() + "]��ʼ����");
//			
//			pipeline.deal(clientSession);
//
//			log.info("����[" + this.getName() + "]��ɴ���");
//			
//			if (response != null) { //��Ӧ�ͻ�������
//				log.info("����[" + this.getName() + "]��ͻ���������");
//				PrintWriter os = new PrintWriter(socket.getOutputStream());
//				String responseData = response.getResponseData();
//				Tree responseTree = PipelineTreeCompiler.compile(responseData);
//				responseTree.execute(this.getContext());
//				this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
////				this.getContext().getParent().merger(response.getName(), responseTree);
//				os.print(responseTree.getResult());
//				os.flush();
//				log.info("����[" + this.getName() + "]��ͻ������������");
//			}
//			
//			socket.close(); // �ر�Socket
//			log.info("����[" + this.getName() + "]�ر�socket");
//		} catch (ConnectException e) {
//			log.error("��ȡ[" + this.getName() + "]����ʧ��---ԭ��" + e.getMessage());
//		} catch (DataNotFoundException e) {
//			log.error("����[" + this.getName() + "]����ʧ��---ԭ���� :" + e.getMessage());
////			e.printStackTrace();
//		} catch (IOException e) {
//			log.error("����[" + this.getName() + "]����ʧ��---io�쳣");
//			e.printStackTrace();
//		} 
//		 catch (Exception e) {
//			 log.error("����[" + this.getName() + "]����ʧ��---������Ϣ:" + e.getMessage());
//			e.printStackTrace();
//		} finally {
//			try {
//				socket.close();
//			} catch (IOException e) {
//				log.error("�ر�[" + this.getName() + "]����ʧ��---������Ϣ:" + e.getMessage());
////				e.printStackTrace();
//			} // �ر�Socket
//		}
//	}
//	
//	public void stop() throws Exception {
//		if (server == null) {
//			throw new Exception("����:����û������, ���ý���");
//		} else {
//			server.close();
//		}
//	}
}
