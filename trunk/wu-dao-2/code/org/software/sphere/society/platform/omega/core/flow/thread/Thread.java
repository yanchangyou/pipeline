package org.software.sphere.society.platform.omega.core.flow.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

import org.software.sphere.society.platform.omega.common.RuleReadNetDataByOmega;
import org.software.sphere.society.platform.omega.core.execute.Request;
import org.software.sphere.society.platform.omega.core.execute.Response;
import org.software.sphere.society.platform.omega.core.execute.Session;
import org.software.sphere.society.platform.omega.core.flow.Flow;

public class Thread extends Flow {

	private java.lang.String port;

	public java.lang.String getPort() {
		return port;
	}

	public void setPort(java.lang.String port) {
		this.port = port;
	}

	public void execute(Session clientSession) throws ConnectException,
			Exception {
		start();//�����̷߳���
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

	protected int getIntPort() {
		return Integer.parseInt(port);
	}

	private class ServiceThread extends java.lang.Thread {

		Socket socket;

		Thread thread;

		public ServiceThread(Socket socket, Thread thread) {
			this.socket = socket;
			this.thread = thread;
		}

		public void run() {
			log.info("[" + Thread.this.getName() + "]����ʼ");
			thread.service(thread, socket);
			log.info("[" + Thread.this.getName() + "]�������");
		}

	}

	public void service(Thread thread, Socket socket) {
		try {

			log.info("����[" + this.getName() + "]����socket");
			Request request = thread.getRequest();
			Response response = thread.getResponse();
//			Context context = new Context();

			Session clientSession = new Session(request, response, socket);
			/**
			 * ��ȡ���� ����&����, && ���еĴ������
			 */
			if (request != null) { //���տͻ�������
				log.info("����[" + this.getName() + "]���տͻ�����������");
				String requestData = RuleReadNetDataByOmega.readData(socket);
//				Tree requestTree = OmegaNetCompiler.compile(requestData);
//				requestTree.execute(this.getContext());
//				this.getContext().merge(this.request.getName(), requestTree);
				System.out.println("���������� : " + requestData);
				log.info("����[" + this.getName() + "]�ͻ��������������");
			}
			
//			Flow flow = service.getFlow();

//			flow.setRoot(service.getRoot());
//			flow.setParentContext(context);

			// TODO CHECH THIS
//			this.addChildElement(flow);
			
			log.info("����[" + this.getName() + "]��ʼ����");
			
//			flow.deal(clientSession);

			for (Iterator iter = flowList.iterator(); iter.hasNext();) {
				Flow flow = (Flow) iter.next();
				flow.execute(clientSession);
			}
			
			log.info("����[" + this.getName() + "]��ɴ���");
			
			if (response != null) { //��Ӧ�ͻ�������
				log.info("����[" + this.getName() + "]��ͻ���������");
				PrintWriter os = new PrintWriter(socket.getOutputStream());
				String responseData = response.getResponseData();
//				Tree responseTree = OmegaNetCompiler.compile(responseData);
//				responseTree.execute(this.getContext());
//				this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
//				this.getContext().getParent().merger(response.getName(), responseTree);
//				os.print(responseTree.getResult());
				os.print(responseData);
				os.flush();
				log.info("����[" + this.getName() + "]��ͻ������������");
			}
			
			socket.close(); // �ر�Socket
			log.info("����[" + this.getName() + "]�ر�socket");
		} catch (ConnectException e) {
			log.error("��ȡ[" + this.getName() + "]����ʧ��---ԭ��" + e.getMessage());
		} 
//		catch (DataNotFoundException e) {
//			log.error("����[" + this.getName() + "]����ʧ��---ԭ���� :" + e.getMessage());
////			e.printStackTrace();
//		} 
		catch (IOException e) {
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
