package org.software.sphere.society.platform.pipeline.core.flow.collaborate;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.software.sphere.society.platform.pipeline.common.RuleReadNetDataByPipeline;
import org.software.sphere.society.platform.pipeline.core.core.Request;
import org.software.sphere.society.platform.pipeline.core.core.Response;
import org.software.sphere.society.platform.pipeline.core.core.Session;
import org.software.sphere.society.platform.pipeline.core.flow.FlowNode;

public class Collaborate extends FlowNode {

	private java.lang.String port;

	public Map portServerSocketMap = new HashMap();
	
	public Map portSocketListMap = new HashMap();
	
	public java.lang.String getPort() {
		return port;
	}

	public void setPort(java.lang.String port) {
		this.port = port;
	}

	public void execute(Session clientSession) throws ConnectException,
			Exception {
		start(this.getIntPort());// �����̷߳���
	}

	/**
	 * service ������������ķ���, start, stop, service
	 * 
	 * ����ͨ�� strat, ������������, ��ʼ�������� Ȼ��ͨ�� service, ��ÿ��������з��� ���ͨ�� stop, �رշ���
	 */

	/**
	 * @throws Exception 
	 * 
	 */
	public void start(int port) throws Exception {
		ServerSocket server = null;
		try {
			 server = new ServerSocket(port);
			portServerSocketMap.put("" + port, server);
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

		Collaborate thread;

		public ServiceThread(Socket socket, Collaborate thread) {
			this.socket = socket;
			this.thread = thread;
		}

		public void run() {
			log.info("[" + Collaborate.this.getName() + "]����ʼ");
			thread.service(socket);
			log.info("[" + Collaborate.this.getName() + "]�������");
		}
	}

	public void service(Socket socket) {
		try {

			log.info("����[" + this.getName() + "]����socket");
			Request request = null;//this.getRequest();
			Response response = null;//this.getResponse();

			String command = null;
//			String responseData = null;

			PrintWriter os = new PrintWriter(socket.getOutputStream());

			StringBuffer welcomeMsg = new StringBuffer();
			welcomeMsg.append("================��ӭ��������������================")
					.append("\r\n");
			welcomeMsg.append("     ����Ժ����ͬ��һ��ִ�����²���, ע�ⲻҪ���Ŷ1, �����ἺΪ�˵�").append("\r\n");
			welcomeMsg.append("     1, [start at port]-----��ָ���Ķ˿�[port](����)�����������").append("\r\n");
			welcomeMsg.append("     2, [run at port]-----ִ��ָ���Ķ˿�[port](����)�Ĵ������").append("\r\n");
			welcomeMsg.append("     3, [stop at port]-----ָֹͣ���˿�[port](����)�Ĵ������").append("\r\n");
			welcomeMsg.append("   ĳ������[����],[ִ��],[ֹͣ]�����˿ڴ����������, ������ƽ�ȵ�, ���Ի��������").append("\r\n");
			welcomeMsg.append("   һ���㿪����ĳ�˿ڴ��ķ���, ��ô��ͱ���ȴ��㿪�Ķ˿�ִ����ɺ���ܼ�������").append("\r\n");
			welcomeMsg.append("=============================================")
					.append("\r\n");
			os.print(welcomeMsg);
			os.flush();
			while (!socket.isClosed()) {
				os.print("��������������>");
				os.flush();

				command = RuleReadNetDataByPipeline.readData(socket.getInputStream());

				if (command == null) {
					log.error(" Υ������, ����ֹͣ");
					break;
				}
				log.info("����[" + this.getName() + "]��ʼ����");

				Session clientSession = new Session(request, response, socket);
				log.info("��ʼִ������:" + command);
				String msg = "";
				try {

					executeCommand(command, clientSession);
					msg = "ִ��[" + command + "]����ɹ�";
					log.info("��ʼִ������" + command + "�ɹ�");
				} catch (Exception e) {
					msg = "ִ��[" + command + "]����ʧ��, ʧ�ܵ�ԭ���� : "
							+ e.getMessage();
					msg = msg + "\r\n\r\n\r\n" + welcomeMsg;
					log.info("��ʼִ������" + command + "ʧ��");
					e.printStackTrace();
//					System.out.println("��Ч����:" + command);
//					System.in.read();
				}

				os.print(msg + "\r\n");
				os.flush();
				log.info("����[" + this.getName() + "]��ͻ������������");
			}
		} catch (ConnectException e) {
			log.error("��ȡ[" + this.getName() + "]����ʧ��---ԭ��" + e.getMessage());
		}
		// catch (DataNotFoundException e) {
		// log.error("����[" + this.getName() + "]����ʧ��---ԭ���� :" + e.getMessage());
		// // e.printStackTrace();
		// }
		catch (IOException e) {
			log.error("����[" + this.getName() + "]����ʧ��---io�쳣");
			e.printStackTrace();
		} catch (Exception e) {
			log
					.error("����[" + this.getName() + "]����ʧ��---������Ϣ:"
							+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				log.error("�ر�[" + this.getName() + "]����ʧ��---������Ϣ:"
						+ e.getMessage());
				// e.printStackTrace();
			} // �ر�Socket
		}
	}

	public void stop(int port) throws Exception {
		ServerSocket server = (ServerSocket) portServerSocketMap.get("" + port);
		List socketList = (List) portSocketListMap.get("" + port);
		if (server == null) {
			throw new Exception("����:����û������, ���ý���");
		} else {
			server.close();
			for (Iterator iter = socketList.iterator(); iter.hasNext();) {
				Socket socket = (Socket) iter.next();
				socket.close();
			}
		}
	}

	public void run(Session clientSession, int port) throws ConnectException,
			Exception {
		for (Iterator iter = flowList.iterator(); iter.hasNext();) {
			FlowNode flow = (FlowNode) iter.next();
			flow.execute(clientSession);
		}
	}

	public void executeCommand(String command, Session clientSession)
			throws Exception {

		if (command == null || !command.matches("^\\w+\\s+at\\s+\\d{1,5}")) {
			throw new Exception("��Ч���� :" + command);
		}
		
		String[] token = command.split("\\s+at\\s+");

		String action = token[0];
		int port = Integer.parseInt(token[1]);

		if (action.equals("start")) {
			start(port);
		} else if (action.equals("stop")) {
			stop(port);
//			clientSession.getClientSocket().close();
		} else if (action.equals("run")) {
			run(clientSession, port);
		} else {
//			System.out.println("��Ч����:" + command);
//			System.in.read();
			throw new Exception("��Ч����" + command);
		}
	}

}
