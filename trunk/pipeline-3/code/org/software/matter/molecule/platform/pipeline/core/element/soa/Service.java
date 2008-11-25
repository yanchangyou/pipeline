package org.software.matter.molecule.platform.pipeline.core.element.soa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.core.context.PipelineContext;
import org.software.matter.molecule.platform.pipeline.core.element.Root;
import org.software.matter.molecule.platform.pipeline.core.element.pipeline.Pipeline;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;

public class Service extends SOA {

	private Request request;

	private Response response;

	private Pipeline pipeline;

	private String port;

	public int getIntPort() {
		if (port == null || port.length() == 0) {
			port = this.getMeta().getProperty("port");
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
			server = new ServerSocket(this.getIntPort());
			// ����һ��ServerSocket�ڶ˿ڼ����ͻ�����
		} catch (Exception e) {
			System.out.println("can not listen to:" + e);
			// ������ӡ������Ϣ
		}
		try {
			do {
				Socket socket = server.accept();
				
				System.out.println(socket.getRemoteSocketAddress().toString()
						.substring(1)+ " ������� ");
				
				new ServiceThread(socket, this).start();
				// ʹ��accept()�����ȴ��ͻ������пͻ�
				// �����������һ��Socket���󣬲�����ִ��
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error." + e);
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
			PipelineContext pipelineContext = new PipelineContext();

			if (request != null && request.getInput() != null) {
				BufferedReader is = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String requestData = is.readLine();
				request.setRequestData(requestData);
			}

			Pipeline pipeline = service.getPipeline();

			pipeline.setRoot(service.getRoot());
			pipeline.setPipelineContext(pipelineContext);

			pipeline.deal(request, response);

			if (response != null && response.getOutput() != null) {
				PrintWriter os = new PrintWriter(socket.getOutputStream());
				// ��Socket����õ��������������PrintWriter����
				String responseData = response.getResponseData();
				os.println(responseData);
			}
			socket.close(); // �ر�Socket
		} catch (ConnectException e) {
			System.out.println("���� : �����쳣, ������Զ�̷���û�п���");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
