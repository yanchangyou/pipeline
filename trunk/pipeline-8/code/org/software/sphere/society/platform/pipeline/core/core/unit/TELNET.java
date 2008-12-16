package org.software.sphere.society.platform.pipeline.core.core.unit;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.software.sphere.society.platform.pipeline.common.ServiceNode;
import org.software.sphere.society.platform.pipeline.core.core.KeyWords;
import org.software.sphere.society.platform.pipeline.core.core.Request;
import org.software.sphere.society.platform.pipeline.core.core.Response;
import org.software.sphere.society.platform.pipeline.core.core.Root;
import org.software.sphere.society.platform.pipeline.core.core.Session;
import org.software.sphere.society.platform.pipeline.core.data.DataNode;
import org.software.sphere.society.platform.pipeline.core.data.node0X.String;
import org.software.sphere.society.platform.pipeline.exception.core.core.DataDealException;

public class TELNET extends Unit {

	public void execute(Session clientSession) throws ConnectException,
			Exception {

//		encodeRequestData(clientSession.getRequest(), this.getFlowNodeContext());

		Socket socket = null;

		boolean isClientSocket = false;
		/**
		 * û������ʹ����������, ��Ĭ����ͻ��˶Ի�
		 */
		if (this.service == null) {
			log.info("ֱ����ͻ��˻Ự, ������Զ�̷���");
			socket = clientSession.getClientSocket();
			isClientSocket = true;
		} else { // ʹ�������ķ���
			log.info("��ʼԶ�̷������");
			isClientSocket = false;
			ServiceNode serviceNode = null;
			if (service.indexOf('@') > -1) { //ʹ�þ���·���������
				String[] part = new String(service).split(new String("@"));
				serviceNode = Root.getServiceNode(part[1], part[0]);
			} else {						//ʹ�����·���������
				serviceNode = this.getDefinedServiceNode(new String(service));	
			}
			socket = (Socket) serviceNode.getAvailableGod();
		}

		if (socket == null) { // ���û������, �׳��쳣
			java.lang.String msg = "����ʧ�� : �ڲ���[" + this.getName() + "��]���������쳣, ��ȷ������["
					+ this.service + "]�Ѿ�����";
			log.error(msg);
			throw new ConnectException(msg);
		}

//		this.dealRequest(socket);
//
//		this.dealResponse(socket);
		
		
		if (isClientSocket) { // ����ǿͻ��˾Ͳ��ر�
			log.info("��ͻ��˻Ự����");
		} else {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
			log.info("Զ�̷�����ý���");
		}
	}

	public Object getGod() throws UnknownHostException, IOException, DataDealException {
		String host = (String) this.getFlowNodeContext().getNextNodeByName(new String("host"));
		if (host == null || !host.toJavaString().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			throw new DataDealException("�����쳣, �����ڻ���ȷhost��ip��ַ, ��ip��ַ��:" + host + ", ����telnet�ڵ�[" + this.getName() + "]�Ĳ����Ƿ���ȷ");
		}
		String portString = (String) this.getFlowNodeContext().getNextNodeByName(new String("port"));

		if (portString == null || !portString.toJavaString().matches("\\d{1,5}")) {
			throw new DataDealException("�����쳣, �����ڻ���ȷ�˿ں�, �˶˿ں���:" + portString);
		}
		
		int port = Integer.parseInt(portString.toJavaString());
		return new Socket(host.toJavaString(), port);
	}

	public void decodeResponseData(Response response, String responseData) {
		response.addNextNode(KeyWords.DATA_KEY_WORLD, responseData);
	}

	public void encodeRequestData(Request request, DataNode paramater) {
		String requestData = (String) paramater.getNextNodeByName(new String("msg"));//new String("Hello World!");
		request.addNextNode(KeyWords.DATA_KEY_WORLD, requestData);
	}
}
