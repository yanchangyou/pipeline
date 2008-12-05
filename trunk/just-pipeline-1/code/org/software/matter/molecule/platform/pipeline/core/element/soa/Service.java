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
	 * service ������������ķ���, start, stop, service
	 * 
	 * ����ͨ�� strat, ������������, ��ʼ�������� Ȼ��ͨ�� service, ��ÿ��������з��� ���ͨ�� stop, �رշ���
	 */
	private ServerSocket server;
	/**
	 * 
	 */
	public void start() {
		
		try {
			int port = this.getIntPort();
			server = new ServerSocket(port);
			// ����һ��ServerSocket�ڶ˿ڼ����ͻ�����
		} catch (Throwable e) {
			log.error("��������ʧ��, �����Ƕ˿ڱ�ռ��, ��ǰʹ�õĶ˿���:" + port);
			log.error("�ڲ�������Ϣ:" + e);
			// ������ӡ������Ϣ
		}
		try {
			do {
				Socket socket = server.accept();
				
				log.info(socket.getRemoteSocketAddress().toString()
						.substring(1)+ " ������� ");
				
				new ServiceThread(socket, this).start();
				// ʹ��accept()�����ȴ��ͻ������пͻ�
				// �����������һ��Socket���󣬲�����ִ��
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������ʧ��, �����Ƕ˿ڱ�ռ��, ��ǰʹ�õĶ˿���:" +port);
			log.error("�ڲ�������Ϣ:" + e);
			// ������ӡ������Ϣ
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
			System.out.println("���η���ʼ");
			service.service(service, socket);
			System.out.println("���η������");
		}

	}

	public void service(Service service, Socket socket) {
		try {

			Request request = service.getRequest();
			Response response = service.getResponse();
//			Context context = new Context();

			Session clientSession = new Session(request, response, socket);
			/**
			 * ��ȡ���� ����&����, && ���еĴ������
			 */
			if (request != null) { //���տͻ�������
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

			
			if (response != null) { //��Ӧ�ͻ�������
				PrintWriter os = new PrintWriter(socket.getOutputStream());
				String responseData = response.getResponseData();
				Tree responseTree = JPTreeCompiler.compile(responseData);
				responseTree.execute(this.getContext());
				this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
//				this.getContext().getParent().merger(response.getName(), responseTree);
				os.print(responseTree.getResult());
				os.flush();
			}
			
			socket.close(); // �ر�Socket
		} catch (ConnectException e) {
			log.error("��ȡ[" + this.getName() + "]����ʧ��---ԭ��" + e.getMessage());
		} catch (DataNotFoundException e) {
			log.error("����[" + this.getName() + "]����ʧ��---ԭ���� :" + e.getMessage());
//			e.printStackTrace();
		} catch (IOException e) {
			log.error("����[" + this.getName() + "]����ʧ��---io�쳣");
			e.printStackTrace();
		} 
		 catch (Exception e) {
			 log.error("����[" + this.getName() + "]����ʧ��---������Ϣ:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log.error("�ر�[" + this.getName() + "]����ʧ��---������Ϣ:" + e.getMessage());
//				e.printStackTrace();
			} // �ر�Socket
		}
	}
	
	public void stop() throws Exception {
		if (server == null) {
			throw new Exception("����:����û������, ���ý���");
		} else {
			server.close();
		}
	}
}
