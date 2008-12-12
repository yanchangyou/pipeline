package org.software.sphere.society.platform.omega.core.flow.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.software.sphere.society.platform.omega.common.RuleReadNetDataByOmega;
import org.software.sphere.society.platform.omega.core.data.Node;
import org.software.sphere.society.platform.omega.core.data.node0X.String;
import org.software.sphere.society.platform.omega.core.execute.Request;
import org.software.sphere.society.platform.omega.core.execute.Response;
import org.software.sphere.society.platform.omega.core.execute.Session;
import org.software.sphere.society.platform.omega.core.flow.FlowNode;

public class Thread extends FlowNode {

	private java.lang.String times;
	
	private java.lang.String port;

	public Map portSocketListMap = new HashMap();
	
	public java.lang.String getPort() {
		return port;
	}

	public void setPort(java.lang.String port) {
		this.port = port;
	}

	public void execute(Session clientSession) throws ConnectException,
			Exception {
		start();// �����̷߳���
	}

	/**
	 * service ������������ķ���, start, stop, service
	 * 
	 * ����ͨ�� strat, ������������, ��ʼ�������� Ȼ��ͨ�� service, ��ÿ��������з��� ���ͨ�� stop, �رշ���
	 */

	ServerSocket server = null;
	/**
	 * @throws Exception 
	 * 
	 */
	public void start() throws Exception {
		try {
			 server = new ServerSocket(Integer.parseInt(port));
			log.info("��[" + port + "]�˿ڴ���������, �ȴ�����");
			// ����һ��ServerSocket�ڶ˿ڼ����ͻ�����
		} catch (Throwable e) {
			log.error("��������ʧ��, �����Ƕ˿ڱ�ռ��, ��ǰʹ�õĶ˿���:" + port);
			log.error("�ڲ�������Ϣ:" + e);
			throw new Exception("�ڲ�������Ϣ:" + e.getMessage());
			// ������ӡ������Ϣ
		}
		List socketList = new ArrayList();
		portSocketListMap.put(""+port, socketList);
		try {
			do {
				Socket socket = server.accept();
				socketList.add(socket);
				log.info(socket.getRemoteSocketAddress().toString()
						.substring(1)
						+ " ������� ");

				new ServiceThread(socket, this).start();
				// ʹ��accept()�����ȴ��ͻ������пͻ�
				// �����������һ��Socket���󣬲�����ִ��
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������ʧ��, �����Ƕ˿ڱ�ռ��, ��ǰʹ�õĶ˿���:" + port);
			log.error("�ڲ�������Ϣ:" + e);
			throw new Exception("�ڲ�������Ϣ:" + e.getMessage());
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
			thread.service(socket);
			log.info("[" + Thread.this.getName() + "]�������");
		}
	}

	public void service(Socket socket) {
		try {
			int number_times = 0;
			if (times == null || times.trim().equals("")) {
				number_times = 1;
			} else if (times.trim().equalsIgnoreCase("X")) {
				number_times = Integer.MAX_VALUE;
			} else {
				number_times = Integer.parseInt(times);
			}
			for (int i=0; i<number_times; i++){
				log.info("����[" + this.getName() + "]����socket");
				Request request = this.getRequest();
				Response response = this.getResponse();
				
				String requestData = null;
				String responseData = null;
				
				
				/**
				 * ��ȡ���� ����&����, && ���еĴ������
				 */
				if (request != null) { //���տͻ�������
					log.info("����[" + this.getName() + "]���տͻ�����������");
					requestData = new String(RuleReadNetDataByOmega.readData(socket.getInputStream()));
//					Tree requestTree = OmegaNetCompiler.compile(requestData);
//					requestTree.execute(this.getContext());
//					this.getContext().merge(this.request.getName(), requestTree);
					System.out.println("���������� : " + requestData);
					log.info("����[" + this.getName() + "]�ͻ��������������");
				}
				
				log.info("����[" + this.getName() + "]��ʼ����");
				
				log.info("����[" + this.getName() + "]��ɴ���");
				
				if (response != null) { //��Ӧ�ͻ�������
					log.info("����[" + this.getName() + "]��ͻ���������");
					PrintWriter os = new PrintWriter(socket.getOutputStream());
//					responseData = response.getResponseData();
//					Tree responseTree = OmegaNetCompiler.compile(responseData);
//					responseTree.execute(this.getContext());
//					this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
//					this.getContext().getParent().merger(response.getName(), responseTree);
//					os.print(responseTree.getResult());
					Node node = this.getVar(requestData.trim());
					if (node != null) {
						responseData = new String(node.toString());
					} else {
						responseData = new String("����û���ҵ�");
					}
					
					os.print(responseData + "\r\n");
					os.flush();
					log.info("����[" + this.getName() + "]��ͻ������������");
				}
				
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
		this.server.close();
	}

	public java.lang.String getTimes() {
		return times;
	}

	public void setTimes(java.lang.String times) {
		this.times = times;
	}

}
