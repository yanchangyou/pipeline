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
		 * û������ʹ����������, ��Ĭ����ͻ��˶Ի�
		 */
		if (this.service == null) {
			log.info("ֱ����ͻ��˻Ự, ������Զ�̷���");
			socket = clientSession.getClientSocket();
			isClientSocket = true;
		} else { //ʹ�������ķ���
			log.info("��ʼԶ�̷������");
			isClientSocket = false;
			String[] serviceArray = this.service.split("\\s*;\\s*"); //����֧��һϵ�з���(֮��ʹ��;�ָ�), Ŀ��:�߿�����, �˷��񲻿���, �����ñ��õķ���
			for (int i = 0; i < serviceArray.length; i++) {
				if (serviceArray[i].split("\\.").length != 6) {
					throw new Exception("���ǺϷ���·��");
				}
				Service_old service = (Service_old) this.context.getAbsolutePathElement(serviceArray[i]);
				Server server = (Server) this.context.getAbsolutePathElement(serviceArray[i].substring(0, serviceArray[i].lastIndexOf('.')));

				String host = server.getHost();
				int port = service.getIntPort();

				try {
					socket = new Socket(host, port);
				} catch (ConnectException e) { //�˷��񲻿���, ���ϵ�����һ������
					log.error("����ʧ��, ��" + host + ":" + port + ", ������Ϣ:" + e.getMessage());
					log.error("��ʼ���ӱ��÷�����");
					continue;
				}
				break;
			}
		}
		
		if (socket == null) { //���û������, �׳��쳣
//			log.error("����ʧ��");
			String msg = "����ʧ�� : �ڲ���[" + this.getName() +"��]���������쳣, ��ȷ������[" + this.service + "]�Ѿ�����";
			log.error(msg);
			throw new ConnectException(msg);
		}
		
		if (request != null && request.getRequestData() != null) {
			log.info("[" + this.getName() + "]������������");
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			String requestData = request.getRequestData();
			log.info("[" + this.getName() + "]��������");
			Tree requestTree = OmegaTreeCompiler.compile(requestData);
			log.info("[" + this.getName() + "]����ִ��");
			requestTree.execute(this.getRequest().getContext());
			if (this.request.getName() == null) {
				
			} else {
				this.getContext().merge(this.request.getName(), requestTree, DefaultTree3D.class);//��Ӧ�����ݷŵ���������
			}
			os.print(requestTree.getResult() );
			os.flush();
			log.info("[" + this.getName() + "]�����������");
		}
		/**
		 * ��ȡ���� ����&����, && ���еĴ������
		 */
		if (response != null) {
			log.info("[" + this.getName() + "]��������");
			String responseData = RuleReadNetDataByOmega.readData(socket);
			log.info("[" + this.getName() + "]�����������");
			log.info("[" + this.getName() + "]��������");
			Tree responseTree = OmegaTreeCompiler.compile(responseData);
			log.info("[" + this.getName() + "]����ִ��");
			responseTree.execute(this.getContext());
			if (response.getName() == null) {
				//nothiing				
			} else {
				this.getContext().merge(response.getName(), responseTree, DefaultTree3D.class);
//				this.getContext().getParent().merger(response.getName(), responseTree);//��Ӧ�����ݷŵ���������
			}
		}
		if (isClientSocket) { //����ǿͻ��˾Ͳ��ر�
			log.info("��ͻ��˻Ự����");
		} else {
			socket.close();
			log.info("Զ�̷�����ý���");
		}
	}
}
