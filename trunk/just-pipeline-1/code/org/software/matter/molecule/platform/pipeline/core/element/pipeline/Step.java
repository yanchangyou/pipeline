package org.software.matter.molecule.platform.pipeline.core.element.pipeline;

import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.software.matter.molecule.platform.pipeline.common.RuleReadNetDataByJP;
import org.software.matter.molecule.platform.pipeline.core.data.Tree;
import org.software.matter.molecule.platform.pipeline.core.data.tree3D.DefaultTree3D;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Request;
import org.software.matter.molecule.platform.pipeline.core.element.rr.Response;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Server;
import org.software.matter.molecule.platform.pipeline.core.element.soa.Service;
import org.software.matter.molecule.platform.pipeline.core.lang.JPTreeCompiler;
import org.software.matter.molecule.platform.pipeline.core.session.Session;

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
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String toString() {
		return new ToStringBuilder(this).toString();
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
				Service service = (Service) this.context.getAbsolutePathElement(serviceArray[i]);
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
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			String requestData = request.getRequestData();
			Tree requestTree = JPTreeCompiler.compile(requestData);
			requestTree.execute(this.getContext());
			if (this.request.getName() == null) {
				
			} else {
				this.getContext().merge(this.request.getName(), requestTree);//��Ӧ�����ݷŵ���������
			}
			os.print(requestTree.getResult() );
			os.flush();
		}
		/**
		 * ��ȡ���� ����&����, && ���еĴ������
		 */
		if (response != null) {
			String responseData = RuleReadNetDataByJP.readData(socket);

			Tree responseTree = JPTreeCompiler.compile(responseData);
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
